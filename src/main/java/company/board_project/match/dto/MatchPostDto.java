package company.board_project.match.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchPostDto {
    private Long userId;
    private Long teamId;
    @NotBlank(message = "운동의 유형을 입력 해야 합니다.")
    private String matchType;
    @NotBlank(message = "운동의 유형을 입력 해야 합니다.")
    private String sportType;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "경기 시간을 입력 해야 합니다.")
    private String matchTime;
    @NotBlank(message = "경기의 난이도를 입력 해야 합니다.")
    private String levelType;
    private String matchStatus = "BEFORE";
    private String matchRules;
    private String homeTeamName;
    private Long homeScore = 0L;
    private Long awayScore = 0L;
}
