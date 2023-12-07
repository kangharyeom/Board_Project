package company.whistle.domain.team.domain.repository;

import company.whistle.domain.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(@Param("teamName") String teamName);

    @Query(value = "SELECT * from teams WHERE league_id = :leagueId order by team_id desc", nativeQuery = true)
    List<Team> findAllTeamByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select manager_name from teams where user_id = :userId", nativeQuery = true)
    String findTeamManagerNameByUserId(@Param("userId") long userId);

    @Query(value = "select * from teams where league_id = :leagueId", nativeQuery = true)
    List<Team> findAllTeamsByLeagueId(@Param("leagueId") long leagueId);

    // 명예 점수 상위 조회
    @Query(value = "SELECT * FROM teams ORDER BY honor_score DESC", nativeQuery = true)
    List<Team> findByHighestHonorScore();

    // 명예 점수 하위 조회
    @Query(value = "SELECT * FROM teams ORDER BY honor_score ASC", nativeQuery = true)
    List<Team> findByLowestHonorScore();

    @Query(value = "select * from teams where team_id = :teamId", nativeQuery = true)
    Optional<Team> findByTeamId(@Param("teamId") Long teamId);
    @Query(value = "select * from teams where user_id = :userId", nativeQuery = true)
    Optional<Team> findByUserId(@Param("userId") Long userId);

    @Query(value = "select user_id from teams where user_id = :userId", nativeQuery = true)
    Long checkDuplUserId(@Param("userId") long userId);

    @Query(value = "select team_name from teams where team_name = :teamName", nativeQuery = true)
    String existByTeamName(@Param("teamName") String teamName);

}
