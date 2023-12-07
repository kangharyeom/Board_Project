package company.whistle.domain.team.squad.repository;

import company.whistle.domain.team.squad.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SquadRepository extends JpaRepository<Squad, Long> {
    @Query(value = "select * from squads where league_id = :leagueId", nativeQuery = true)
    List<Squad> findAllTeamsByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from squads order by created_at desc", nativeQuery = true)
    List<Squad> findSquadNewest();

    @Query(value = "select * from squads order by created_at asc", nativeQuery = true)
    List<Squad> findSquadLatest();

    @Query(value = "select * from squads order by honor_score desc", nativeQuery = true)
    List<Squad> findHonorScore();

    @Query(value = "select team_id from squads where user_id = :userId", nativeQuery = true)
    Long findTeamIdOfSquadByUserId(@Param("userId") long userId);

    @Query(value = "select team_id from squads where user_id = :userId and team_member_status = 'ACTIVITY'", nativeQuery = true)
    Long checkDuplUserIdFromSquad(@Param("userId") long userId);

}
