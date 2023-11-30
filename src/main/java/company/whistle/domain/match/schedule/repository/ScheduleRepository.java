package company.whistle.domain.match.schedule.repository;

import company.whistle.domain.match.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "select * from schedules where league_id = :leagueId", nativeQuery = true)
    List<Schedule> findAllSchedulesByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from schedules where user_id = :userId", nativeQuery = true)
    List<Schedule> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from schedules order by created_at desc", nativeQuery = true)
    List<Schedule> findSchedulesNewest();

    @Query(value = "select * from schedules order by created_at asc", nativeQuery = true)
    List<Schedule> findSchedulesLatest();

    @Query(value = "select * from schedules order by honor_score desc", nativeQuery = true)
    List<Schedule> findHonorScore();

}
