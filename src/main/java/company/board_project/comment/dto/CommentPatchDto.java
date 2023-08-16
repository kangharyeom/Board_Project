package company.board_project.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentPatchDto {
    private Long commentId;
    private Long contentId;
    @NotBlank(message = "댓글의 내용을 입력해야 합니다.")
    private String comment;
    private LocalDateTime modifiedAt;
}
