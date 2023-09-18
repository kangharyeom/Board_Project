package company.board_project.list.leaguelist.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueListResponseListDto {
    private List<LeagueListResponseDto> leagueListResponseDtoList;
}
