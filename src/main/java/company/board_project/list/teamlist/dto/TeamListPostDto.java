package company.board_project.list.teamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamListPostDto {
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private String position;
    private String locationType;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private String uniformType;
    private Long leagueMatchPoints = 0L;
    private Long leagueWinRecord = 0L;
    private Long leagueDrawRecord = 0L;
    private Long leagueLoseRecord = 0L;
    private Long ranking;
    private Long teamGoals = 0L;
    private Long teamAssist = 0L;
    private Long cleanSheet = 0L;

   /* private Long mostGoals = 0L;
    private Long mostAssists = 0L;
    private Long mostMoMs = 0L;*/
}
