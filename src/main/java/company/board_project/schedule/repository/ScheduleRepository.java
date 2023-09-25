package company.board_project.schedule.repository;

import company.board_project.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "select * from schedules where schedule_id = :scheduleId", nativeQuery = true)
    List<Schedule> findByScheduleId(@Param("scheduleId") long scheduleId);
}
