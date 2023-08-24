package company.board_project.comment.mapper;

import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-22T10:02:04+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

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
