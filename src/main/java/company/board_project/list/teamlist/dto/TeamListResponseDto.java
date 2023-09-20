package company.board_project.list.teamlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class TeamListResponseDto {
    private Long teamListId;
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

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
