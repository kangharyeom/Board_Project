package company.board_project.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
    private Long teamId;
    private Long userId;
    private Long leagueId;
    private Long matchId;
    private Long winCount;
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
    private String managerName;
    private String subManagerName;
    private String uniform;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
