package company.board_project.domain.apply.match.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchApplyListDto {
    private List<MatchApplyResponseDto> matchApplyResponseDtoList;
}
