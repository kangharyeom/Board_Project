package company.board_project.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class CommentResponseDto {
    private Long commentId;
    @NotBlank(message = "댓글의 내용을 입력해야 합니다.")
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
