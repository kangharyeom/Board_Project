package company.whistle.domain.league.participants.repository;

import company.whistle.domain.league.participants.entity.Participants;
import company.whistle.domain.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
    @Query(value = "SELECT * from Participants WHERE league_id = :leagueId order by league_match_points desc limit 1", nativeQuery = true)
    Participants findWinnerByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select * from Participants where team_id = :teamId", nativeQuery = true)
    Optional<Participants> findParticipantsByTeamId(@Param("teamId") long teamId);

    @Query(value = "select * from Participants where user_id = :userId", nativeQuery = true)
    List<Participants> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from Participants order by created_at desc", nativeQuery = true)
    List<Participants> findLeagueParticipantsNewest();

    @Query(value = "select * from Participants order by created_at asc", nativeQuery = true)
    List<Participants> findLeagueParticipantsLatest();

    @Query(value = "select * from Participants order by honor_score desc", nativeQuery = true)
    List<Participants> findHonorScore();

    @Query(value = "select team_id from Participants where team_id = :teamId and league_participants_status = 'ACTIVITY'", nativeQuery = true)
    Long checkDuplTeamIdFromParticipants(@Param("teamId") long teamId);

    @Query(value = "SELECT * from Participants WHERE league_id = :leagueId and league_participants_status = 'ACTIVITY' order by league_match_points desc ", nativeQuery = true)
    List<Participants> findAllParticipantsByLeagueId(@Param("leagueId") long leagueId);

}
