package company.board_project.leaguematch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueMatchEndToLeagueMatchPatchDto {
    private Long leagueMatchId;
    private Long homeTeamHonorScore;
    private Long awayTeamHonorScore;
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamManagerName;
    private String awayTeamManagerName;
    private Long homeTeamLeagueMatchPoints;
    private Long awayTeamLeagueMatchPoints;
    private Long homeTeamLeagueWinRecord;
    private Long awayTeamLeagueWinRecord;
    private Long homeTeamLeagueDrawRecord;
    private Long awayTeamLeagueDrawRecord;
    private Long homeTeamLeagueLoseRecord;
    private Long awayTeamLeagueLoseRecord;
    private Long homeTeamTotalWinRecord;
    private Long awayTeamTotalWinRecord;
    private Long homeTeamTotalDrawRecord;
    private Long awayTeamTotalDrawRecord;
    private Long homeTeamTotalLoseRecord;
    private Long awayTeamTotalLoseRecord;
    private String matchStatus;
    private String homeTeamMatchResultStatus;
    private String awayTeamMatchResultStatus;

    public void updateId(Long id){
        this.leagueMatchId = id;
    }
}
