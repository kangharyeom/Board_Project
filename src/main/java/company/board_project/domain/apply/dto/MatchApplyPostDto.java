package company.board_project.domain.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchApplyPostDto {
    private long userId;
    private long teamId;
    private long matchId;
    private long hostMatchId;
    private String applierName;
    private String teamName;
    private String levelType;
    private String ageType;
    private String matchType;
}
