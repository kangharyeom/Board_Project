package company.board_project.domain.list.teammemberlist.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberListResponseListDto {
    private List<TeamMemberListResponseDto> teamListResponseDtoMemberList;
}
