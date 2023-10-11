package company.board_project.domain.match.normalmatch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class MatchResponseDto {
    private Long matchId;
    private Long userId;
    private Long teamId;
    private Long homeTeamHonorScore;
    private String homeTeamName;
    private String homeTeamManagerName;
    private Long homeTeamTotalWinRecord;
    private Long homeTeamTotalDrawRecord;
    private Long homeTeamTotalLoseRecord;
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
}
