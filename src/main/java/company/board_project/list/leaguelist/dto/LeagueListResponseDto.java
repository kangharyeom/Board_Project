package company.board_project.list.leaguelist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class LeagueListResponseDto {
    private Long leagueListId;
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private Long honorScore;
    private Long championCount;
    private Long memberCount;
    private String position;
    private String teamName;
    private String managerName;
    private String subManagerName;
    private String leagueName;
    private String ageType;
    private String locationType;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private String uniformType;
    private Long leagueMatchPoints;
    private Long leagueWinRecord;
    private Long leagueDrawRecord;
    private Long leagueLoseRecord;
    private Long ranking;
    private Long teamGoals;
    private Long teamAssist;
    private Long cleanSheet;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
