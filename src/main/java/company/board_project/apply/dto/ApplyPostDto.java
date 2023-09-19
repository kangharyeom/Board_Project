package company.board_project.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplyPostDto {
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long matchId;
    private Long teamListId;
    private String managerName;
    private String teamName;
    private String levelType;
    private String ageType;
    private Long userTeamApplyId;
    private Long userMatchApplyId;
    private Long userLeagueApplyId;
}
