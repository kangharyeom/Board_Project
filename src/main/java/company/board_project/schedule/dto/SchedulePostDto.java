package company.board_project.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SchedulePostDto {
    private Long userId;
    private Long teamId;
    private Long matchId;
    private Long leagueId;
}
