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
    private Long homeScore;
    private Long awayScore;
    private String matchType;
    private String sportType;
    private String ageType;
    private String locationType;
    private String matchTime;
    private String levelType;
    private String matchRules;
    private String homeTeamName;
    private String awayTeamName;
    private String name;
    private String matchStatus;
    private String homeTeamMatchResultStatus;
    private String awayTeamMatchResultStatus;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Team> teamList;
}
