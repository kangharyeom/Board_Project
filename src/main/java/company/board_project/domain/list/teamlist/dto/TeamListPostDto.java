package company.board_project.domain.list.teamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamListPostDto {
    private Long userId;
    private Long teamId;
    private Long applyId;
    private String name;
    private String position;
    private String teamMemberType;
    private String ageType;
    private String locationType;
    private String levelType;
    private String frequency;

   /* private Long mostGoals = 0L;
    private Long mostAssists = 0L;
    private Long mostMoMs = 0L;*/
}
