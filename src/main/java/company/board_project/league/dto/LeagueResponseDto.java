package company.board_project.league.dto;

import company.board_project.content.entity.Content;
import company.board_project.match.entity.Match;
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
    private Long matchCount;
    private Long teamCount;
    private String leagueName;
    private String leagueManagerName;
    private String sportType;
    private String ageType;
    private String locationType;
    private String period;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private String participantTeamName;

    private List<Content> contents;
    private List<Match> matchs;

    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
