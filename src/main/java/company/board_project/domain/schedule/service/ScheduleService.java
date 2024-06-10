package company.board_project.domain.schedule.service;

import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public Schedule createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);

        return schedule;
    }

    public List<Schedule> findSchedules(Long scheduleId) {
        return scheduleRepository.findByScheduleId(scheduleId);
    }

    public void deleteSchedule(Long scheduleId) {
        Schedule findSchedule = findVerifiedSchedule(scheduleId);

        scheduleRepository.delete(findSchedule);
    }

    public Schedule findVerifiedSchedule(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        Schedule findSchedule =
                optionalSchedule.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findSchedule;
    }
}
