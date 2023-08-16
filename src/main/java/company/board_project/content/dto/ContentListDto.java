package company.board_project.content.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ContentListDto {
    List<ContentResponseDto> contentResponseDto;
}