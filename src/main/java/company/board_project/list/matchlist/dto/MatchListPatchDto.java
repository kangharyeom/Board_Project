package company.board_project.list.matchlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchListPatchDto {
    private Long matchListId;
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private Long matchId;
    private Long honorScore;
    private String ageType;
    private String locationType;
    private String levelType;
    private String leagueRules;
    private String uniformType;
    private Long leagueMatchPoints;
    private Long winRecord;
    private Long drawRecord;
    private Long loseRecord;
    private Long ranking;
    private Long teamGoals;
    private Long teamAssist;
    private Long cleanSheet;
}
