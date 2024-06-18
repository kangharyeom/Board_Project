package company.board_project.domain.match.leagueMatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueMatchPostDto {
    private Long homeTeamName;
    private Long awayTeamName;
    private Integer round;
}
