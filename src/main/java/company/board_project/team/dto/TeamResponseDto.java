package company.board_project.team.dto;

import company.board_project.constant.SportsType;
import company.board_project.schedule.entity.Schedule;
import company.board_project.suggestionlist.Entity.SuggestionList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
    private Long teamId;
    private Long userId;
    private Long leagueId;
    private Long matchId;
    private Long suggestionListId;
    private Long championCount;
    private Long memberCount;
    private Long leagueWinRecord;
    private Long leagueDrawRecord;
    private Long leagueLoseRecord;
    private Long totalWinRecord;
    private Long totalDrawRecord;
    private Long totalLoseRecord;
    private Long honorScore;
    private Long ranking;
    private Long mostGoals;
    private Long mostAssist;
    private Long mostMom;

    private String teamName;
    private String introduction;
    private String ageType;
    private String locationType;
    private String sportType;
    private String levelType;
    private String managerName;
    private String subManagerName;
    private String frequency;
    private String uniform;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Schedule> scheduleList;
    private List<SuggestionList> suggestionLists;
}
