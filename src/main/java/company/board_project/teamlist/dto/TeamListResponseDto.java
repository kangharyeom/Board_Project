package company.board_project.teamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TeamListResponseDto {
    private Long teamListId;
    private Long leagueId;
    private Long teamId;
}
