package company.board_project.domain.apply.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class MatchApplyResponseDto {
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long matchId;
    private Long matchApplyId;
    private String managerName;
    private String teamName;
    private String levelType;
    private String ageType;
    private String applyType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
