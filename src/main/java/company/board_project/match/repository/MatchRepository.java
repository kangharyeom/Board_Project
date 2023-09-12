package company.board_project.match.repository;

import company.board_project.league.entity.League;
import company.board_project.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query(value = "select * from matchs where match_id = :matchId", nativeQuery = true)
    List<League> findAllByMatchId(@Param("matchId") long matchId);

    @Query(value = "select * from matchs where team_id = :teamId", nativeQuery = true)
    List<League> findAllByTeamId(@Param("teamId") long teamId);

    @Query(value = "select * from matchs where league_id = :leagueId", nativeQuery = true)
    List<League> findAllByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from matchs where user_id = :userId", nativeQuery = true)
    List<League> findByUserId(@Param("userId") long userId);
}
