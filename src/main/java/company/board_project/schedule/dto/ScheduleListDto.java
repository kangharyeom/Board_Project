package company.board_project.schedule.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleListDto {
    private List<ScheduleResponseDto> scheduleResponstList;
}
