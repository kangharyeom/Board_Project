package company.board_project.league.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueToTeamListPostDto {
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private Long honorScore;
    private String teamName;
    private String managerName;
    private String subManagerName;
    private String leagueName;
    private String ageType;
    private String locationType;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private Long memberCount;
    private Long leagueMatchPoints = 0L;
    private Long leagueWinRecord = 0L;
    private Long leagueDrawRecord = 0L;
    private Long leagueLoseRecord = 0L;
    private Long totalMatchPoints = 0L;
    private Long totalWinRecord = 0L;
    private Long totalDrawRecord = 0L;
    private Long totalLoseRecord = 0L;
    private Long ranking;
    private Long teamGoals = 0L;
    private Long teamAssist = 0L;
    private Long cleanSheet = 0L;
}
