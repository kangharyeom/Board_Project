package company.board_project.domain.list.team.repository;

import company.board_project.domain.list.team.entity.TeamMemberList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamMemberListRepository extends JpaRepository<TeamMemberList, Long> {
    @Query(value = "select * from team_member_Lists where league_id = :leagueId", nativeQuery = true)
    List<TeamMemberList> findAllTeamsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from team_member_Lists where user_id = :userId", nativeQuery = true)
    List<TeamMemberList> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from team_member_Lists order by created_at desc", nativeQuery = true)
    List<TeamMemberList> findTeamListsNewest();

    @Query(value = "select * from team_member_Lists order by created_at asc", nativeQuery = true)
    List<TeamMemberList> findTeamListsLatest();

    @Query(value = "select * from team_member_Lists order by honor_score desc", nativeQuery = true)
    List<TeamMemberList> findHonorScore();

}
