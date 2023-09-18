package company.board_project.list.leaguelist.repository;

import company.board_project.list.leaguelist.entity.LeagueList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueListRepository extends JpaRepository<LeagueList, Long> {
    @Query(value = "select * from league_Lists where league_id = :leagueId", nativeQuery = true)
    List<LeagueList> findAllLeaguesByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from league_Lists where user_id = :userId", nativeQuery = true)
    List<LeagueList> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from league_Lists order by created_at desc", nativeQuery = true)
    List<LeagueList> findLeagueListsNewest();

    @Query(value = "select * from league_Lists order by created_at asc", nativeQuery = true)
    List<LeagueList> findLeagueListsLatest();

    @Query(value = "select * from league_Lists order by honor_score desc", nativeQuery = true)
    List<LeagueList> findHonorScore();

}
