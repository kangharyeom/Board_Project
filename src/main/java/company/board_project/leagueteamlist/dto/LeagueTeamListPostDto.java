package company.board_project.leagueteamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueTeamListPostDto {
    private Long leagueId;
    private Long teamId;
}
