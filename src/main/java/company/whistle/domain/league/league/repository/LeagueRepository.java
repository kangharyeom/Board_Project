package company.whistle.domain.league.league.repository;

import company.whistle.domain.league.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findByLeagueId(long leagueId);

    // userId단위 리그 조회
    @Query(value = "select * from leagues where user_id = :userId", nativeQuery = true)
    League findByUserId(@Param("userId") long userId);

    // 최신 등록된 리그 순서 조회
    @Query(value = "select * from leagues order by created_at desc", nativeQuery = true)
    List<League> findLeaguesNewest();

    // 오래된 순서 리그 조회
    @Query(value = "select * from leagues order by created_at asc", nativeQuery = true)
    List<League> findLeaguesLatest();

    // 명예 점수 고득점 순서 조회
    @Query(value = "select * from leagues order by honor_score desc", nativeQuery = true)
    List<League> findHonorScore();

    // 시즌 단위 조회 (시즌 진행중)
    @Query(value = "SELECT * FROM leagues WHERE season_type = 'ON_SEASON' ORDER BY league_id DESC", nativeQuery = true)
    List<League> findLeagueOnSeason();

    // 시즌 단위 조회 (시즌 종료)
    @Query(value = "SELECT * FROM leagues WHERE season_type = 'OFF_SEASON' ORDER BY league_id DESC", nativeQuery = true)
    List<League> findLeagueOffSeason();

    // 시즌 단위 조회 (팀 모집)
    @Query(value = "SELECT * FROM leagues WHERE season_type = 'TEAM_RECRUIT' ORDER BY league_id DESC", nativeQuery = true)
    List<League> findLeagueRecruit();

    @Query(value = "select manager_name from leagues where user_id = :userId", nativeQuery = true)
    String findManagerNameByUserId(@Param("userId") long userId);

    @Query(value = "select team_id from leagues where team_id = :teamId", nativeQuery = true)
    Long existByTeamId(@Param("teamId") long teamId);

    @Query(value = "select league_name from leagues where league_name = :leagueName", nativeQuery = true)
    String existByLeagueName(@Param("leagueName") String leagueName);

    @Query(value = "select manager_team_name from leagues where manager_team_name = :managerTeamName and league_season_status = 'ON_SEASON' or league_season_status = 'TEAM_RECRUIT'", nativeQuery = true)
    String existByManagerTeamName(@Param("managerTeamName") String managerTeamName);

}
