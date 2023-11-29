package company.board_project.domain.list.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class TeamMemberListResponseDto {
    private Long teamMemberListId;
    private Long userId;
    private Long teamId;
    private Long teamApplyId;
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
