package company.board_project.domain.team.repository;

import company.board_project.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "select * from teams where user_id = :userId", nativeQuery = true)
    List<Team> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from teams where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchId(@Param("matchId") long matchId);

    @Query(value = "select * from teams where league_id = :leagueId", nativeQuery = true)
    List<Team> findAllTeamsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select homeTeamName from teams where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchHomeId(@Param("matchId") long matchId);

    @Query(value = "select AwayTeamName from teams where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchAwayId(@Param("matchId") long matchId);

}
