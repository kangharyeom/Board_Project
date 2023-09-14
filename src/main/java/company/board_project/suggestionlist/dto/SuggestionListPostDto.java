package company.board_project.suggestionlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SuggestionListPostDto {
    private Long userId;
    private Long teamId;
    private Long teamName;
    private String levelType;
}
