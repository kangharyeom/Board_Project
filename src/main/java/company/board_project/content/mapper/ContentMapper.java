package company.board_project.content.mapper;

import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.comment.entity.Comment;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.dto.*;
import company.board_project.content.entity.Content;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface ContentMapper {

    Content contentPostDtoToContent(ContentPostDto requestBody);
    Content contentPatchDtoToContent(ContentPatchDto requestBody);

    default ContentResponseDto contentToContentResponse(Content content){
        User user = content.getUser();

        return ContentResponseDto.builder()
                .contentId(content.getContentId())
                .userId(user.getUserId())
                .name(user.getName())
                .title(content.getTitle())
                .content(content.getContent())
                .createdAt(content.getCreatedAt())
                .modifiedAt(content.getModifiedAt())
                .build();
    }

    // 컨텐츠 to 컨텐트 단건 조회 //
    default ContentAllResponseDto contentToContentAllResponse(Content content, CommentRepository commentRepository){
        User user = content.getUser();
        List<Comment> comments = commentRepository.findAllByContentId(content.getContentId());
        Collections.reverse(comments);

        return ContentAllResponseDto.builder()
                .contentId(content.getContentId())
                .userId(user.getUserId())
                .name(user.getName())
                .title(content.getTitle())
                .content(content.getContent())
                .comments(commentsToCommentResponseDtos(comments))
                .createdAt(content.getCreatedAt())
                .modifiedAt(content.getModifiedAt())
                .build();
    }

    // 컨텐츠 List 선언
    default ContentListDto contentListDtoToContentResponse(List<Content> contents){

        return ContentListDto.builder()
                .contentResponseDto(contentsToContentsResponse(contents))
                .build();
    }

    // 컨텐츠(다중) to 컨텐츠 리스폰스 (전체) //
    default List<ContentResponseDto> contentsToContentsResponse(List<Content> contents){
        return contents.stream()
                .map(content -> ContentResponseDto.builder()
                        .contentId(content.getContentId())
                        .userId(content.getUser().getUserId())
                        .name(content.getUser().getName())
                        .title(content.getTitle())
                        .content(content.getContent())
                        .createdAt(content.getCreatedAt())
                        .modifiedAt(content.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 커멘츠 to 커멘트 리스폰스 (전체) //
    default List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments){
        return comments.stream()
                .map(comment -> CommentResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .contentId(comment.getContent().getContentId())
                        .userId(comment.getUser().getUserId())
                        .name(comment.getUser().getName())
                        .comment(comment.getComment())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .title(comment.getContent().getTitle())
                        .name(comment.getUser().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
