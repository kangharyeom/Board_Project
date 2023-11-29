package company.board_project.domain.list.league.dto;

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
