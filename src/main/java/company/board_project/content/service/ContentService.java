package company.board_project.content.service;

import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.entity.Content;
import company.board_project.content.mapper.ContentMapper;
import company.board_project.content.repository.ContentRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.member.entity.Member;
import company.board_project.member.repository.MemberRepository;
import company.board_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final CommentRepository commentRepository;

    // 게시글 생성 //
    public Content createContent(Content content) {
        content.setMember(memberService.getLoginMember());

        contentRepository.save(content);

        return content;
    }

    // 게시글 수정 //
    public Content updateContent(Content content) {
        Content findContent = findVerifiedContent(content.getContentId());

        Member writer = memberService.findMember(findContent.getMember().getMemberId()); // 작성자 찾기
        if(memberService.getLoginMember().getMemberId() != writer.getMemberId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED); //예외 발생(권한 없음)

        Optional.ofNullable(content.getTitle())
                .ifPresent(findContent::setTitle);

        Optional.ofNullable(content.getContent())
                .ifPresent(findContent::setContent);

        return contentRepository.save(findContent);
    }

    // 게시글 단건 조회 //
    public Content findContent(Long contentId) {
        return findVerifiedContent(contentId);
    }

    // 게시글 전체 조회 //
    public Page<Content> findContents(int page, int size) {
        return contentRepository.findAll(PageRequest.of(page, size,
                Sort.by("contentId").descending()));
    }

    // 검색 기능 //
    public List<Content> findAllSearch(String keyword){
        return contentRepository.findAllSearch(keyword);
    }

    // 게시글 삭제 //
    public void deleteContent(Long contentId) {
        Content findContent = findVerifiedContent(contentId);

        Member writer = memberService.findMember(findContent.getMember().getMemberId()); // 작성자 찾기
        if(memberService.getLoginMember().getMemberId() != writer.getMemberId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED); //예외 발생(권한 없음)
        contentRepository.delete(findContent);
    }

    // 유저 검증 로직 //
    public Member findVerifiedMember(Long memberId) {
        Optional<Member> optionalUser = memberRepository.findById(memberId);
        Member findMember =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.MEMBER_NOT_FOUND));
        return findMember;
    }

    // 게시글 검증 로직 //
    public Content findVerifiedContent(Long contentId) {
        Optional<Content> optionalContent = contentRepository.findByContentId(contentId);

        Content findContent =
                optionalContent.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findContent;
    }
}