package company.board_project.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthEmailConfirmDto {
    @Email
    private String email;

    @NotBlank
    private String authCode;
}