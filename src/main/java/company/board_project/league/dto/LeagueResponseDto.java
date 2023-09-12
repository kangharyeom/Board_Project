package company.board_project.league.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LeagueResponseDto {
    private Long leagueId;
    private Long userId;
    private Long teamId;
    private String leagueName;
    private String sportType;
    private String ageType;
    private String location;
    private String period;
    private String levelType;
    private String leagueRules;
    private String frequency;

    private String name;
}
