package company.whistle.domain.apply.matchApply.repository;

import company.whistle.domain.apply.matchApply.entity.MatchApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchApplyRepository extends JpaRepository<MatchApply,Long> {
    // applyId 단위 전체 조회
    @Query(value = "select * from match_applies where match_apply_id = :matchApplyId", nativeQuery = true)
    List<MatchApply> findAllByMatchApplyId(@Param("matchApplyId") long matchApplyId);

    // teamId 단위 조회
    @Query(value = "select * from match_applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<MatchApply> findAllByTeamId(@Param("teamId") long teamId);

    @Query(value = "select team_id from match_applies where team_id = :teamId", nativeQuery = true)
    Long existByTeamId(@Param("teamId") long teamId);
}
