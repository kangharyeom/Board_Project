package company.board_project.suggestion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SuggestionPostDto {
    private Long userId;
    private Long teamId;
    private Long leagueTeamListId;
    private Long teamName;
    private String levelType;
}
