package company.whistle.domain.board.content.service;

import company.whistle.domain.board.content.entity.Content;
import company.whistle.domain.board.content.entity.ContentFile;
import company.whistle.domain.board.content.repository.ContentFileRepository;
import company.whistle.domain.board.content.repository.ContentRepository;
import company.whistle.domain.user.service.UserService;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class ContentService {
    private final UserService userService;
    private final ContentRepository contentRepository;
    private final ContentFileRepository contentFileRepository;

    /*
     * 게시글 생성
     */
    public Content createContent(Content content, Long userId) {
        User user = userService.findUser(userId);

        content.setUser(user);
        contentRepository.save(content);

        return content;
    }

    /*
     * 게시글 파일 업로드
     */
    public Content createContentFile(Content content, Long userId,List<String> filePaths) {
        User user = userService.findUser(userId);

        content.setUser(user);

        blankCheck(filePaths);
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

    /*
     * 게시글 수정
     */
    public Content updateContent(Content content) {

        Content findContent = findVerifiedContent(content.getContentId());

        User writer = userService.findUser(findContent.getUser().getUserId()); // 작성자 찾기
        if (userService.getLoginUser().getUserId() != writer.getUserId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED);

        Optional.ofNullable(content.getTitle())
                .ifPresent(findContent::setTitle);

        Optional.ofNullable(content.getContent())
                .ifPresent(findContent::setContent);

        return contentRepository.save(findContent);
    }

    /*
     * 게시글 단건 조회
     */
    public Content findContent(Long contentId) {
        return findVerifiedContent(contentId);
    }

    /*
     * 게시글 전체 조회
     * pageNation 구현
     */
    public Page<Content> findContents(int page, int size) {
        return contentRepository.findAll(PageRequest.of(page, size,
                Sort.by("contentId").descending()));
    }

    /*
     * 게시글 검색 기능
     */
    public List<Content> findAllSearch(String keyword){
        return contentRepository.findAllSearch(keyword);
    }

    /*
     * 작성자 단위 검색 기능
     */
    public List<Content> findAllSearchByUserName(String name){
        return contentRepository.findAllSearchByUserName(name);
    }

    /*
     * 최신 순서 게시글 조회
     */
    public List<Content> findContentsNewest() {
        return contentRepository.findContentsNewest();
    }

    /*
     * 오래된 순서 게시글 조회
     */
    public List<Content> findContentsLatest() {
        return contentRepository.findContentsLatest();
    }

    /*
     * 카테고리 단위 게시글 조회
     */
    public List<Content> findContentsByCategory(String category) {
        return contentRepository.findAllByCategoryType(category);
    }

    /*
     * 게시글 삭제
     */
    public void deleteContent(Long contentId) {
        try {
            Content findContent = findVerifiedContent(contentId);
            contentRepository.delete(findContent);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    /*
     * 게시글 검증 로직
     */
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