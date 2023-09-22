package company.board_project.list.teamlist.repository;

import company.board_project.list.teamlist.entity.TeamList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamListRepository extends JpaRepository<TeamList, Long> {
    @Query(value = "select * from team_Lists where league_id = :leagueId", nativeQuery = true)
    List<TeamList> findAllTeamsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from team_Lists where user_id = :userId", nativeQuery = true)
    List<TeamList> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from team_Lists order by created_at desc", nativeQuery = true)
    List<TeamList> findTeamListsNewest();

    @Query(value = "select * from team_Lists order by created_at asc", nativeQuery = true)
    List<TeamList> findTeamListsLatest();

    @Query(value = "select * from team_Lists order by honor_score desc", nativeQuery = true)
    List<TeamList> findHonorScore();

}
