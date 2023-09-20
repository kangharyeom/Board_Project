package company.board_project.apply.repository;

import company.board_project.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    @Query(value = "select * from applies where apply_id = :applyId", nativeQuery = true)
    List<Apply> findAllByApplyId(@Param("applyId") long applyId);

    @Query(value = "select * from applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByTeamId(@Param("teamId") long teamId);

    @Query(value = "select * from applies where match_id = :matchId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByMatchId(@Param("matchId") long matchId);

    @Query(value = "select * from applies where league_id = :leagueId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByLeagueId(@Param("leagueId") long leagueId);

    Optional<Apply> findByApplyId(@Param("applyId") long applyId);
}
