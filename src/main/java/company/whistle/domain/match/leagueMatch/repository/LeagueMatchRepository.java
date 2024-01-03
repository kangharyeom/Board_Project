package company.whistle.domain.match.leagueMatch.repository;

import company.whistle.domain.match.leagueMatch.entity.LeagueMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueMatchRepository extends JpaRepository<LeagueMatch, Long> {
    @Query(value = "select * from league_Matches where user_id = :userId", nativeQuery = true)
    List<LeagueMatch> findByUserId(@Param("userId") long userId);

    @Query(value = "select * from league_Matches where title like %:keyword% or content like %:keyword% ", nativeQuery = true)
    List<LeagueMatch> findAllSearch(@Param(value = "keyword")String keyword);

    @Query(value = "select * from league_Matches where name like :name", nativeQuery = true)
    List<LeagueMatch> findAllSearchByUserName(@Param(value = "name")String name);

    @Query(value = "select * from league_Matches order by created_at desc", nativeQuery = true)
    List<LeagueMatch> findLeagueMatchesNewest();

    @Query(value = "select * from league_Matches order by created_at asc", nativeQuery = true)
    List<LeagueMatch> findLeagueMatchesLatest();
}
