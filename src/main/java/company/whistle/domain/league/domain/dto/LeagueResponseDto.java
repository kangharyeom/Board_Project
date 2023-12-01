package company.whistle.domain.league.domain.dto;

import company.whistle.domain.board.content.entity.Content;
import company.whistle.domain.match.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class LeagueResponseDto {
    private Long leagueId;
    private Long userId;
    private Long teamId;
    private Long leagueEndCount;
    private Long memberCount;
    private Long matchCount;
    private Long teamCount;
    private Long honorScore;
    private String leagueName;
    private String managerName;
    private String managerTeamName;
    private String sportsType;
    private String ageType;
    private String locationType;
    private String period;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private String seasonType;

    private List<Schedule> schedules;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "LeagueResponseDto{" +
                "leagueId=" + leagueId +
                ", userId=" + userId +
                ", teamId=" + teamId +
                ", leagueEndCount=" + leagueEndCount +
                ", memberCount=" + memberCount +
                ", matchCount=" + matchCount +
                ", teamCount=" + teamCount +
                ", honorScore=" + honorScore +
                ", leagueName='" + leagueName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerTeamName='" + managerTeamName + '\'' +
                ", sportsType='" + sportsType + '\'' +
                ", ageType='" + ageType + '\'' +
                ", locationType='" + locationType + '\'' +
                ", period='" + period + '\'' +
                ", levelType='" + levelType + '\'' +
                ", leagueRules='" + leagueRules + '\'' +
                ", frequency='" + frequency + '\'' +
                ", seasonType='" + seasonType + '\'' +
                ", schedules=" + schedules +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
