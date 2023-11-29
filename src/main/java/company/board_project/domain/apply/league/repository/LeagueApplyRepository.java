package company.board_project.domain.apply.league.repository;

import company.board_project.domain.apply.league.entity.LeagueApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface LeagueApplyRepository extends JpaRepository<LeagueApply,Long> {
    // applyId 단위 전체 조회
    @Query(value = "select * from applies where apply_id = :applyId", nativeQuery = true)
    List<LeagueApply> findAllByApplyId(@Param("applyId") long applyId);

    // teamId 단위 조회
    @Query(value = "select * from applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByTeamId(@Param("teamId") long teamId);

    // matchId 단위 조회
    @Query(value = "select * from applies where match_id = :matchId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByMatchId(@Param("matchId") long matchId);

    // leagueId 단위 조회
    @Query(value = "select * from applies where league_id = :leagueId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByLeagueId(@Param("leagueId") long leagueId);

    /*
     * applyId 단위 조회
     * findVerified를 위해 Optional<LeagueApply>로 가져옴
     */
    Optional<LeagueApply> findByApplyId(@Param("applyId") long applyId);
}
