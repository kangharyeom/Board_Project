package company.board_project.domain.list.match.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchListResponseListDto {
    private List<MatchListResponseDto> matchListResponseDtoList;
}
