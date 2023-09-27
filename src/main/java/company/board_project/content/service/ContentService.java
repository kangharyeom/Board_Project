package company.board_project.content.service;

import company.board_project.content.entity.Content;
import company.board_project.content.entity.ContentFile;
import company.board_project.content.repository.ContentFileRepository;
import company.board_project.content.repository.ContentRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
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
    private final ContentRepository contentRepository;
    private final ContentFileRepository contentFileRepository;

    public Content createContent(Content content) {
        contentRepository.save(content);

        return content;
    }

    public Content createContentFile(Content content, List<String> filePaths) {
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

    public Content updateContent(Content content) {

        Content findContent = findVerifiedContent(content.getContentId());

        Optional.ofNullable(content.getTitle())
                .ifPresent(findContent::setTitle);

        Optional.ofNullable(content.getContent())
                .ifPresent(findContent::setContent);

        return contentRepository.save(findContent);
    }

    public Content findContent(Long contentId) {
        return findVerifiedContent(contentId);
    }

    public Page<Content> findContents(int page, int size) {
        return contentRepository.findAll(PageRequest.of(page, size,
                Sort.by("contentId").descending()));
    }

    public List<Content> findAllSearch(String keyword){
        return contentRepository.findAllSearch(keyword);
    }

    public List<Content> findAllSearchByUserName(String name){
        return contentRepository.findAllSearchByUserName(name);
    }

    public List<Content> findContentsNewest() {
        return contentRepository.findContentsNewest();
    }

    public List<Content> findContentsLatest() {
        return contentRepository.findContentsLatest();
    }

    public List<Content> findContentsByCategory(String category) {
        return contentRepository.findAllByCategoryType(category);
    }

    public void deleteContent(Long contentId) {
        Content findContent = findVerifiedContent(contentId);

        contentRepository.delete(findContent);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

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