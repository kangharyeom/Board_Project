package company.board_project.matchSuggestionList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MatchSuggestionListResponseDto {
    private Long userId;
    private Long teamId;
    private Long teamName;
    private String levelType;
}
