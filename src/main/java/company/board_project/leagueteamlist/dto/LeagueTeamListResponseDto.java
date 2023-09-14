package company.board_project.leagueteamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LeagueTeamListResponseDto {
    private Long leagueTeamListId;
    private Long leagueId;
    private Long teamId;
}
