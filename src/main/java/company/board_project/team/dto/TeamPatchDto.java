package company.board_project.team.dto;

import company.board_project.constant.SportsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamPatchDto {
    private Long teamId;
    private Long userId;
    private Long championCount;
    private Long memberCount;
    private Long leagueWinRecord;
    private Long leagueDrawRecord;
    private Long leagueLoseRecord;
    private Long totalWinRecord;
    private Long totalDrawRecord;
    private Long totalLoseRecord;
    private Long honorScore;
    private Long ranking;
    private Long mostGoals;
    private Long mostAssist;
    private Long mostMom;

    private String introduction;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "운동 유형을 입력 해야 합니다.")
    private String sportType;
    @NotBlank(message = "팀 실력을 입력 해야 합니다.")
    private String levelType;
    private String managerName;
    private String subManagerName;
    private String uniform;
}
