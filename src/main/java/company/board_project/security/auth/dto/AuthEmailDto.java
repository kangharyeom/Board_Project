package company.board_project.security.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
public class AuthEmailDto {
    @Email
    private String email;
}