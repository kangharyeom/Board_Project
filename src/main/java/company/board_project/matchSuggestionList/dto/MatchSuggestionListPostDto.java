package company.board_project.matchSuggestionList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchSuggestionListPostDto {
    private Long userId;
    private Long teamId;
    private Long teamName;
    private String levelType;
}
