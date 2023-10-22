package company.board_project.domain.content.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentListDto {
    List<ContentResponseDto> contentResponseDto;
}