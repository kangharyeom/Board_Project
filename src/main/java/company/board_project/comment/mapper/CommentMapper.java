package company.board_project.comment.mapper;

import company.board_project.comment.dto.CommentPatchDto;
import company.board_project.comment.dto.CommentPostDto;
import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.comment.entity.Comment;
import company.board_project.content.entity.Content;
import company.board_project.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostDtoToComment(CommentPostDto requestBody)
    {
        // content id, comment id 받아오기 + body 받아오기
        Content content = new Content();
        content.setContentId(requestBody.getContentId());

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setComment(requestBody.getComment());

        return comment;
    }

    default Comment commentPatchDtoToComment(CommentPatchDto requestBody)
    {
        // content id, comment id 받아오기 + body 받아오기
        Content content = new Content();
        content.setContentId(requestBody.getContentId());

        Comment comment = new Comment();
        content.setContentId(requestBody.getContentId());
        comment.setContent(content);
        comment.setComment(requestBody.getComment());

        return comment;
    }
    default CommentResponseDto commentToCommentResponseDto(Comment comment)
    {
        Member member = comment.getMember();
        Content content = comment.getContent();

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .memberId(member.getMemberId())
                .contentId(content.getContentId())
                .title(content.getTitle())
                .comment(comment.getComment())
                .name(member.getName())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comment);
    List<CommentResponseDto> contentCommentsToCommentResponseDtos(List<Comment> comment);
}