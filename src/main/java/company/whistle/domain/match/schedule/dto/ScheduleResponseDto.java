package company.whistle.domain.match.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class ScheduleResponseDto {
    private Long scheduleId;
    private Long homeTeamUserId;
    private Long awayTeamUserId;
    private Long matchId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long matchApplyId;
    private Long homeTeamScore;
    private Long awayTeamScore;
    private Long homeTeamHonorScore;
    private Long awayTeamHonorScore;
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamManagerName;
    private String awayTeamManagerName;
    private Long homeTeamTotalWinRecord;
    private Long awayTeamTotalWinRecord;
    private Long homeTeamTotalDrawRecord;
    private Long awayTeamTotalDrawRecord;
    private Long homeTeamTotalLoseRecord;
    private Long awayTeamTotalLoseRecord;
    private String homeTeamLevelType;
    private String awayTeamLevelType;
    private String homeTeamAgeType;
    private String awayTeamAgeType;
    private String homeTeamUniformType;
    private String awayTeamUniformType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String matchTime;
    private String matchDate;
}
