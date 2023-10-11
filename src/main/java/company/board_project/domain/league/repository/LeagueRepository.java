package company.board_project.domain.league.repository;

import company.board_project.domain.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {
    @Query(value = "select * from leagues where league_id = :leagueId", nativeQuery = true)
    List<League> findAllByLeagueId(@Param("leagueId") long leagueId);

    Optional<League> findByLeagueId(long leagueId);

    @Query(value = "select * from leagues where user_id = :userId", nativeQuery = true)
    List<League> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from leagues order by created_at desc", nativeQuery = true)
    List<League> findLeaguesNewest();

    @Query(value = "select * from leagues order by created_at asc", nativeQuery = true)
    List<League> findLeaguesLatest();

    @Query(value = "select * from leagues order by honor_score desc", nativeQuery = true)
    List<League> findHonorScore();
}
