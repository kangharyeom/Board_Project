package company.whistle.domain.match.schedule.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseListDto {
    private List<ScheduleResponseDto> schedule;
}
