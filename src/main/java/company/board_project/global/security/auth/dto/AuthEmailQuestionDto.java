package company.board_project.global.security.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthEmailQuestionDto {
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}