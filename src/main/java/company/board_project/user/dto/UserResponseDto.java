package company.board_project.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private Long userTeamApplyId;
    private Long userMatchApplyId;
    private Long userLeagueApplyId;
    private String loginId;
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
