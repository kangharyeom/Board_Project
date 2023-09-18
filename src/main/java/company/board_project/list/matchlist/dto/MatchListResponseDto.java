package company.board_project.list.matchlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class MatchListResponseDto {
    private Long matchListId;
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long applyId;
    private Long honorScore;
    private String teamName;
    private String managerName;
    private String ageType;
    private String locationType;
    private String levelType;
    private String matchRules;
    private String uniformType;
    private Long leagueMatchPoints;
    private Long winRecord;
    private Long drawRecord;
    private Long loseRecord;
    private Long ranking;
    private Long teamGoals;
    private Long teamAssist;
    private Long cleanSheet;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
