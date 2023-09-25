package company.board_project.list.matchlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchAwayTeamDto {
    private Long matchListId;
    private Long awayTeamUserId;
    private Long awayTeamId;
    private Long applyId;
    private Long matchId;
    private String awayTeamName;
    private String awayTeamManagerName;
    private Long awayTeamHonorScore;
    private Long awayTeamTotalWinRecord;
    private Long awayTeamTotalDrawRecord;
    private Long awayTeamTotalLoseRecord;
    private String awayTeamLevelType;
    private String awayTeamAgeType;
    private String awayTeamUniformType;
}
