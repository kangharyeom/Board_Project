package company.board_project.content.dto;

import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.content.entity.ContentFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ContentAllResponseDto {
    private Long contentId;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 게시글 작성 유저 정보 //
    private String name;
    private List<CommentResponseDto> comments;
}