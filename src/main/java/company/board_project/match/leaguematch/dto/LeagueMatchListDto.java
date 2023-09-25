package company.board_project.match.leaguematch.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueMatchListDto {
    List<LeagueMatchResponseDto> leagueMatchResponseDto;
}
