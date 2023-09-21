package company.board_project.league.dto;

import company.board_project.content.entity.Content;
import company.board_project.list.matchlist.entity.MatchList;
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
    private Long memberCount;
    private Long matchCount;
    private Long teamCount;
    private Long honorScore;
    private Long winPoints;
    private String leagueName;
    private String managerName;
    private String sportsType;
    private String ageType;
    private String locationType;
    private String period;
    private String levelType;
    private String leagueRules;
    private String frequency;

    private List<Content> contents;
    private List<MatchList> matchLists;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
