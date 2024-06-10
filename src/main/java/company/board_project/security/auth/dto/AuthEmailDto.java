package company.board_project.security.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class AuthEmailDto {
    @Email
    private String email;
}