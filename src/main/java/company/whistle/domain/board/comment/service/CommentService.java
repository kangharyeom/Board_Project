package company.whistle.domain.board.comment.service;

import company.whistle.domain.board.comment.repository.CommentRepository;
import company.whistle.domain.board.comment.entity.Comment;
import company.whistle.domain.board.content.entity.Content;
import company.whistle.domain.board.content.service.ContentService;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ContentService contentService;

    public Comment createComment(Comment comment, Long contentId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (contentId == null || loginUserId == null) {
                log.info("loginUserId: {}", loginUserId);
                log.info("contentId: {}", contentId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }
            Content content = contentService.findByContentId(contentId);

            comment.setUser(loginUser);
            comment.setContent(content);
            commentRepository.save(comment);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.COMMENT_NOT_CREATED);
        }
        return comment;
    }

    public Comment updateComment(Comment comment, Long commentId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (commentId == null || loginUserId == null) {
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }

            Comment findComment = findByCommentId(commentId);
            /*
             * 유저(LOGIN 한 유저) 권한 검증
             * 해당 유저가 게시글을 작성한 유저가 아니라면 UNAUTHORIZED 를 던짐
             * */
            User writer = userService.findByUserId(findComment.getUser().getUserId()); // 작성자 찾기
            if (!Objects.equals(writer.getUserId(), loginUserId)) { // 작성자와 로그인한 사람이 다를 경우
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            Optional.ofNullable(comment.getComment())
                    .ifPresent(findComment::setComment);
            commentRepository.save(findComment);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.COMMENT_NOT_PATCHED);
        }
        return comment;
    }

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size,
                Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId) {
        try {
            Comment findComment = findByCommentId(commentId);

            /*
             * 유저(LOGIN 한 유저) 권한 검증
             * 해당 유저가 게시글을 작성한 유저가 아니라면 UNAUTHORIZED 를 던짐
             * */
            User writer = userService.findByUserId(findComment.getUser().getUserId()); // 작성자 찾기
            if (!Objects.equals(userService.getLoginUser().getUserId(), writer.getUserId())) { // 작성자와 로그인한 사람이 다를 경우
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }
            commentRepository.delete(findComment);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.CONTENT_NOT_DELETED);
        }
    }

    public Comment findByCommentId(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElseThrow(() ->
                new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
    }

    public List<Comment> findByContentId(long contentId) {
        List<Comment> findContentComments = commentRepository.findAllByContentId(contentId);
        if(findContentComments==null){
            throw new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND);
        }
        return findContentComments;
    }
}