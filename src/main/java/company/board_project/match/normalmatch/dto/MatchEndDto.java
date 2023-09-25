package company.board_project.match.normalmatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchEndDto {
    private Long matchId;
    private Long matchListId;
    private Long homeTeamUserId;
    private Long awayTeamUserId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long homeTeamScore;
    private Long awayTeamScore;
    private String matchStatus;

}
