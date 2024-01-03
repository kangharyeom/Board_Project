package company.whistle.domain.league.league.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueListDto {
    List<LeagueResponseDto> leagueResponseDto;
}
