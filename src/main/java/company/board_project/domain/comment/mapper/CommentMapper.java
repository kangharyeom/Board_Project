package company.board_project.domain.comment.mapper;

import company.board_project.domain.comment.dto.CommentPatchDto;
import company.board_project.domain.comment.dto.CommentPostDto;
import company.board_project.domain.comment.dto.CommentResponseDto;
import company.board_project.domain.comment.entity.Comment;
import company.board_project.domain.content.entity.Content;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostDtoToComment(CommentPostDto requestBody) {
        // content id, comment id 받아오기 + body 받아오기
        User user = new User();

        user.setUserId(requestBody.getUserId());
        user.setName(requestBody.getName());

        Content content = new Content();
        content.setContentId(requestBody.getContentId());

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setName(requestBody.getName());
        comment.setComment(requestBody.getComment());

        return comment;
    }

    default Comment commentPatchDtoToComment(CommentPatchDto requestBody) {
        //  content id, comment id 받아오기 + body 받아오기

        Content content = new Content();
        content.setContentId(requestBody.getContentId());

        Comment comment = new Comment();
        content.setContentId(requestBody.getContentId());
        comment.setContent(content);
        comment.setComment(requestBody.getComment());

        return comment;
    }
    default CommentResponseDto commentToCommentResponseDto(Comment comment) {
        User user = comment.getUser();
        Content content = comment.getContent();

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .userId(user.getUserId())
                .name(user.getName())
                .contentId(content.getContentId())
                .title(content.getTitle())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comment);
    List<CommentResponseDto> contentCommentsToCommentResponseDtos(List<Comment> comment);
}