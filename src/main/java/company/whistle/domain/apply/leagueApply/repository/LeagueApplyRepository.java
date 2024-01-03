package company.whistle.domain.apply.leagueApply.repository;

import company.whistle.domain.apply.leagueApply.entity.LeagueApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface LeagueApplyRepository extends JpaRepository<LeagueApply,Long> {
    // applyId 단위 전체 조회
    @Query(value = "select * from league_applies where league_apply_id = :leagueApplyId", nativeQuery = true)
    List<LeagueApply> findAllByLeagueApplyId(@Param("leagueApplyId") long leagueApplyId);

    @Query(value = "select * from league_applies where team_id = :teamId", nativeQuery = true)
    LeagueApply findByTeamId(@Param("teamId") long teamId);

    // teamId 단위 조회
    @Query(value = "select * from league_applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByTeamId(@Param("teamId") long teamId);

    // matchId 단위 조회
    @Query(value = "select * from league_applies where match_id = :matchId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByMatchId(@Param("matchId") long matchId);

    // leagueId 단위 조회
    @Query(value = "select * from league_applies where league_id = :leagueId order by created_at desc", nativeQuery = true)
    List<LeagueApply> findAllByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from league_applies where team_Name = :teamName", nativeQuery = true)
    Optional<LeagueApply> findByTeamName(@Param("teamName") String teamName);

    /*
     * applyId 단위 조회
     * findVerified를 위해 Optional<LeagueApply>로 가져옴
     */
    Optional<LeagueApply> findByLeagueApplyId(@Param("leagueApplyId") long leagueApplyId);

    @Query(value = "select team_id from league_applies where team_id = :teamId", nativeQuery = true)
    Long existByTeamId(@Param("teamId") long teamId);

}
