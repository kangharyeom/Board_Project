package company.board_project.member.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Builder
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String name;
    private String password;
    private String phone;
}
