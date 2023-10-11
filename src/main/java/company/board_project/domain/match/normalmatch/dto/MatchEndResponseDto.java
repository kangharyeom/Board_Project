package company.board_project.domain.match.normalmatch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class MatchEndResponseDto {
    private Long matchId;
    private Long matchListId;
    private Long homeTeamUserId;
    private Long awayTeamUserId;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long homeTeamScore;
    private Long awayTeamScore;
    private String matchStatus;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
