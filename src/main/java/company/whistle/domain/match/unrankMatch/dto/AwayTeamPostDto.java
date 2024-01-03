package company.whistle.domain.match.unrankMatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AwayTeamPostDto {
    private Long awayTeamUserId;
    private Long matchId;
    private Long awayTeamId;
    private Long matchApplyId;

    public void updateId(Long id){
        this.matchId = id;
    }
}
