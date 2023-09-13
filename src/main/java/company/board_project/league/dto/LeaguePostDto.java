package company.board_project.league.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeaguePostDto {
    private Long userId;
    private Long teamId;
    private Long matchCount;
    private Long teamCount;
    private String participantTeamName;

    @NotBlank(message = "리그의 이름을 입력 해야 합니다.")
    private String leagueName;
    @NotBlank(message = "운동의 유형을 입력 해야 합니다.")
    private String sportType;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "리그 경기 기간을 입력 해야 합니다.")
    private String period;
    @NotBlank(message = "리그의 난이도를 입력 해야 합니다.")
    private String levelType;
    private String leagueRules;
    @NotBlank(message = "리그의 경기 빈도를 입력 해야 합니다.")
    private String frequency;
    private String title;
    private String content;

    // 유저 정보
    private String name;
}
