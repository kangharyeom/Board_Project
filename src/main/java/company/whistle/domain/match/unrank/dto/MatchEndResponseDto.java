package company.whistle.domain.match.unrank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class MatchEndResponseDto {
    private Long matchId;
    private Long scheduleId;
    private Long homeTeamUserId;
    private Long awayTeamUserId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long homeTeamScore;
    private Long awayTeamScore;
    private String matchStatus;
    private String matchTime;
    private String matchDate;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "MatchEndResponseDto{" +
                "matchId=" + matchId +
                ", scheduleId=" + scheduleId +
                ", homeTeamUserId=" + homeTeamUserId +
                ", awayTeamUserId=" + awayTeamUserId +
                ", homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", matchStatus='" + matchStatus + '\'' +
                ", matchTime='" + matchTime + '\'' +
                ", matchDate='" + matchDate + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
