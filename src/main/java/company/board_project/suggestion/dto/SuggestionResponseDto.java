package company.board_project.suggestion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class SuggestionResponseDto {
    private Long suggestionId;
    private Long userId;
    private Long teamId;
    private Long leagueTeamListId;
    private Long teamName;
    private String levelType;
}
