package company.board_project.team.dto;

import company.board_project.schedule.entity.Schedule;
import company.board_project.apply.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
    private Long teamId;
    private Long userId;
    private Long championCount;
    private Long memberCount;
    private Long leagueMatchCount;
    private Long leagueWinRecord;
    private Long leagueDrawRecord;
    private Long leagueLoseRecord;
    private Long totalMatchCount;
    private Long totalWinRecord;
    private Long totalDrawRecord;
    private Long totalLoseRecord;
    private Long honorScore;
    private Long ranking;
    private Long mostGoals;
    private Long mostAssist;
    private Long mostMom;

    private String formation;
    private String teamName;
    private String introduction;
    private String ageType;
    private String locationType;
    private String sportsType;
    private String levelType;
    private String managerName;
    private String subManagerName;
    private String frequency;
    private String uniformType;
    private String leagueName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Schedule> scheduleList;
    private List<Apply> applies;
}
