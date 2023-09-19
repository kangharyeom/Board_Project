package company.board_project.match.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchPatchDto {
    private Long matchId;
    private Long userId;
    private Long teamId;
    private Long homeTeamScore;
    private Long homeTeamHonorScore;
    private String homeTeamName;
    private String homeTeamManagerName;
    private Long homeTeamLeagueMatchPoints;
    private Long homeTeamLeagueWinRecord;
    private Long homeTeamLeagueDrawRecord;
    private Long homeTeamLeagueLoseRecord;
    private Long homeTeamTotalWinRecord;
    private Long homeTeamTotalDrawRecord;
    private Long homeTeamTotalLoseRecord;
    private Long homeTeamRanking;
    private String homeTeamLevelType;
    private String homeTeamAgeType;
    private String homeTeamUniformType;
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


    public void updateId(Long id){
        this.matchId = id;
    }
}
