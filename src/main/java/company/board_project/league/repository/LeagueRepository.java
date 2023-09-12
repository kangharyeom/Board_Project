package company.board_project.league.repository;

import company.board_project.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {
    @Query(value = "select * from leagues where league_id = :leagueId", nativeQuery = true)
    List<League> findAllByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from leagues where user_id = :userId", nativeQuery = true)
    List<League> findByUserId(@Param("userId") long userId);
}
