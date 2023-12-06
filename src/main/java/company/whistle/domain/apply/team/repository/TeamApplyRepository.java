package company.whistle.domain.apply.team.repository;

import company.whistle.domain.apply.team.entity.TeamApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface TeamApplyRepository extends JpaRepository<TeamApply,Long> {
    // teamApplyId 단위 전체 조회
    @Query(value = "select * from team_applies where team_apply_id = :teamApplyId", nativeQuery = true)
    List<TeamApply> findAllByTeamApplyId(@Param("teamApplyId") long teamApplyId);

    // teamId 단위 조회
    @Query(value = "select * from team_applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<TeamApply> findAllByTeamId(@Param("teamId") long teamId);

    @Query(value = "select * from team_applies where user_id = :userId", nativeQuery = true)
    TeamApply findTeamApplyByUserId(@Param("userId") long userId);

    @Query(value = "select * from team_applies where team_id = :teamId and user_id = :userId and apply_status = 'APPLIED' ", nativeQuery = true)
    TeamApply checkTeamApplyByTeamIdAndUserId(@Param("teamId") long teamId, @Param("userId") long userId);

    @Query(value = "select apply_status from team_applies where user_id = :userId", nativeQuery = true)
    String checkDuplUserIdFromTeamApply(@Param("userId") long userId);

}
