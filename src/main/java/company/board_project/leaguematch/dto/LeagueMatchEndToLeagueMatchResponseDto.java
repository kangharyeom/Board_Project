package company.board_project.leaguematch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class LeagueMatchEndToLeagueMatchResponseDto {
    private Long leagueMatchId;
    private Long homeTeamUserId;
    private Long awayTeamUserId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long homeTeamLeagueListId;
    private Long awayTeamLeagueListId;
    private Long homeTeamScore;
    private Long homeTeamHonorScore;
    private Long awayTeamScore;
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

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
