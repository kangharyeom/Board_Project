package company.board_project.domain.list.league.repository;

import company.board_project.domain.list.league.entity.LeagueList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueListRepository extends JpaRepository<LeagueList, Long> {
    @Query(value = "select * from league_Lists where league_id = :leagueId", nativeQuery = true)
    List<LeagueList> findAllLeaguesByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "SELECT * from league_Lists WHERE league_id = :leagueId order by league_match_points desc limit 1", nativeQuery = true)
    LeagueList findWinnerByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "SELECT * from league_Lists WHERE league_id = :leagueId order by league_match_points desc", nativeQuery = true)
    LeagueList findLeagueWinPointsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select league_lose_record from league_Lists where league_id = :leagueId", nativeQuery = true)
    LeagueList findLeagueLoseRecordByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from league_Lists where user_id = :userId", nativeQuery = true)
    List<LeagueList> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from league_Lists order by created_at desc", nativeQuery = true)
    List<LeagueList> findLeagueListsNewest();

    @Query(value = "select * from league_Lists order by created_at asc", nativeQuery = true)
    List<LeagueList> findLeagueListsLatest();

    @Query(value = "select * from league_Lists order by honor_score desc", nativeQuery = true)
    List<LeagueList> findHonorScore();

}
