package company.board_project.suggestionlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class SuggestionListResponseDto {
    private Long userId;
    private Long teamId;
    private Long teamName;
    private String levelType;
}
