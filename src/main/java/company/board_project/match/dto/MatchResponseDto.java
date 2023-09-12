package company.board_project.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@AllArgsConstructor
@Getter
@Builder
public class MatchResponseDto {
    private Long matchId;
    private Long userId;
    private Long teamId;
    private String matchType;
    private String sportType;
    private String ageType;
    private String location;
    private String matchTime;
    private String levelType;
    private String leagueRules;
}
