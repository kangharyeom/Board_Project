package company.board_project.list.teamlist.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamListResponseListDto {
    private List<TeamListResponseDto> teamListResponseDtoList;
}