package company.board_project.domain.list.matchlist.repository;

import company.board_project.domain.list.matchlist.entity.MatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    @Query(value = "select * from match_Lists where league_id = :leagueId", nativeQuery = true)
    List<MatchList> findAllMatchListsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from match_Lists where user_id = :userId", nativeQuery = true)
    List<MatchList> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from match_Lists order by created_at desc", nativeQuery = true)
    List<MatchList> findMatchListsNewest();

    @Query(value = "select * from match_Lists order by created_at asc", nativeQuery = true)
    List<MatchList> findMatchListsLatest();

    @Query(value = "select * from match_Lists order by honor_score desc", nativeQuery = true)
    List<MatchList> findHonorScore();

}
