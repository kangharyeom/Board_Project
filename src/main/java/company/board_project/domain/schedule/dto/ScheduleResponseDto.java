package company.board_project.domain.schedule.dto;

import company.board_project.domain.match.normal.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ScheduleResponseDto {
    private Long scheduleId;
    private Long userId;
    private Long teamId;
    private Long matchId;
    private Long leagueId;
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

    private List<Match> matchList;
}
