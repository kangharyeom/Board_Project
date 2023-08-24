package company.board_project.content.service;

import company.board_project.awsS3.AwsS3Service;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.entity.Content;
import company.board_project.content.entity.ContentFile;
import company.board_project.content.mapper.ContentMapper;
import company.board_project.content.repository.ContentFileRepository;
import company.board_project.content.repository.ContentRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final CommentRepository commentRepository;
    private final AwsS3Service awsS3Service;
    private final ContentFileRepository contentFileRepository;

    // 게시글 생성 //
    public Content createContent(Content content) {
//        System.out.println("로그인한 user: "+userService.getLoginUser());
//        content.setUser(userService.getLoginUser());
        contentRepository.save(content);

        return content;
    }

    // 게시글 생성 (파일 추가)
    public Content createContentFile(Content content, List<String> filePaths) {
        blankCheck(filePaths);
//        System.out.println("로그인한 user: "+userService.getLoginUser());
//        content.setUser(userService.getLoginUser());

        contentRepository.save(content);

        List<String> fileNameList = new ArrayList<>();
        for (String contentFileUrl : filePaths) {
            ContentFile file = new ContentFile(content.getContentId(),contentFileUrl);
            file.setContentId(content.getContentId());
            contentFileRepository.save(file);
            fileNameList.add(file.getContentFileUrl());
        }

        return content;
    }

    // 게시글 수정 //
    public Content updateContent(Content content) {

        Content findContent = findVerifiedContent(content.getContentId());

//        User writer = userService.findUser(findContent.getUser().getUserId()); // 작성자 찾기
       /* if(userService.getLoginUser().getUserId() != writer.getUserId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED); //예외 발생(권한 없음)*/

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

    // 회원 이름(닉네임) 단위 검색
    public List<Content> findAllSearchByUserName(String name){
        return contentRepository.findAllSearchByUserName(name);
    }

    // 최신 순서 필터
    public List<Content> findContentsNewest() {
        return contentRepository.findContentsNewest();
    }

    // 오래된 순서 필터
    public List<Content> findContentsLatest() {
        return contentRepository.findContentsLatest();
    }

    // 게시글 삭제 //
    public void deleteContent(Long contentId) {
        Content findContent = findVerifiedContent(contentId);

//        User writer = userService.findUser(findContent.getUser().getUserId()); // 작성자 찾기
        /*if(userService.getLoginUser().getUserId() != writer.getUserId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED); //예외 발생(권한 없음)*/
        contentRepository.delete(findContent);
    }

    // 유저 검증 로직 //
    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

    // 게시글 검증 로직 //
    public Content findVerifiedContent(Long contentId) {
        Optional<Content> optionalContent = contentRepository.findByContentId(contentId);

        Content findContent =
                optionalContent.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findContent;
    }

    private void blankCheck(List<String> filePaths) {
        if(filePaths == null || filePaths.isEmpty()){ //.isEmpty()도 되는지 확인해보기
            throw new BusinessLogicException(Exceptions.CONTENT_FILE_CHECK_ERROR);
        }
    }
}