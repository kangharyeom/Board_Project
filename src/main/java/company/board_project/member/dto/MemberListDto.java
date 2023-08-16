package company.board_project.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MemberListDto {
    List<MemberResponseDto> memberResponseDto;
}
