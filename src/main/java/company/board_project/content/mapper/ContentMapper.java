package company.board_project.content.mapper;

import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.comment.entity.Comment;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.dto.*;
import company.board_project.content.entity.Content;
import company.board_project.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    Content contentPostDtoToContent(ContentPostDto requestBody);

    Content contentPatchDtoToContent(ContentPatchDto requestBody);

    default ContentResponseDto contentToContentResponse(Content content){
        Member member = content.getMember();

        return ContentResponseDto.builder()
                .contentId(content.getContentId())
                .memberId(member.getMemberId())
                .title(content.getTitle())
                .content(content.getContent())
                .createdAt(content.getCreatedAt())
                .modifiedAt(content.getModifiedAt())
                .build();
    }

    // 컨텐츠 to 컨텐트 단건 조회 //
    default ContentAllResponseDto contentToContentAllResponse(Content content, CommentRepository commentRepository){
        Member member = content.getMember();
        List<Comment> comments = commentRepository.findAllByContentId(content.getContentId());
        Collections.reverse(comments);

        return ContentAllResponseDto.builder()
                .contentId(content.getContentId())
                .memberId(member.getMemberId())
                .name(member.getName())
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
                        .memberId(content.getMember().getMemberId())
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
                        .memberId(comment.getMember().getMemberId())
                        .comment(comment.getComment())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .title(comment.getContent().getTitle())
                        .name(comment.getMember().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
