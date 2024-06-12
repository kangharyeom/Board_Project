package company.board_project.domain.comment.mapper;

import company.board_project.domain.comment.dto.CommentPatchDto;
import company.board_project.domain.comment.dto.CommentPostDto;
import company.board_project.domain.comment.dto.CommentResponseDto;
import company.board_project.domain.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T09:14:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostDtoToComment(CommentPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setComment( requestBody.getComment() );
        comment.setName( requestBody.getName() );

        return comment;
    }

    @Override
    public Comment commentPatchDtoToComment(CommentPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( requestBody.getCommentId() );
        comment.setComment( requestBody.getComment() );
        comment.setName( requestBody.getName() );

        return comment;
    }

    @Override
    public List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comment) {
        if ( comment == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comment.size() );
        for ( Comment comment1 : comment ) {
            list.add( commentToCommentResponseDto( comment1 ) );
        }

        return list;
    }

    @Override
    public List<CommentResponseDto> contentCommentsToCommentResponseDtos(List<Comment> comment) {
        if ( comment == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comment.size() );
        for ( Comment comment1 : comment ) {
            list.add( commentToCommentResponseDto( comment1 ) );
        }

        return list;
    }
}
