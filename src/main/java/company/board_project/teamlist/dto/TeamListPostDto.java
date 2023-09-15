package company.board_project.teamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamListPostDto {
    private Long leagueId;
    private Long teamId;
}
