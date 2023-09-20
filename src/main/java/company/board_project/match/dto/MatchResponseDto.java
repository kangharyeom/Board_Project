package company.board_project.match.dto;

import company.board_project.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MatchResponseDto {
    private Long matchId;
    private Long userId;
    private Long teamId;
    private Long leagueListId;
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
    private String matchType;
    private String sportType;
    private String locationType;
    private String matchTime;
    private String matchStatus;
    private String matchRules;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Team> teamList;
}
