package company.board_project.domain.match.normalmatch.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchListDto {
    List<MatchResponseDto> matchResponseDto;
}
