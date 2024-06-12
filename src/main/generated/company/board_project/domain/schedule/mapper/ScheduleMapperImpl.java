package company.board_project.domain.schedule.mapper;

import company.board_project.domain.schedule.dto.SchedulePostDto;
import company.board_project.domain.schedule.entity.Schedule;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T09:14:04+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule schedulePostDtoToSchedule(SchedulePostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        return schedule;
    }
}
