package company.board_project.list.matchlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchListPostDto {
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private Long matchId;
    private Long honorScore;
    private String teamName;
    private String managerName;
    private String matchRules;
    private String locationType;
    private String levelType;
    private String leagueRules;
    private String uniformType;
    private Long leagueMatchPoints = 0L;
    private Long winRecord = 0L;
    private Long drawRecord = 0L;
    private Long loseRecord = 0L;
    private Long ranking;
    private Long teamGoals = 0L;
    private Long teamAssist = 0L;
    private Long cleanSheet = 0L;

   /* private Long mostGoals = 0L;
    private Long mostAssists = 0L;
    private Long mostMoMs = 0L;*/
}
