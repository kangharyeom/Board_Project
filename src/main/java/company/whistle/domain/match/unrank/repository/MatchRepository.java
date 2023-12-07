package company.whistle.domain.match.unrank.repository;

import company.whistle.domain.match.unrank.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = "select * from matches where match_apply_id = :matchApplyId", nativeQuery = true)
    Optional<Match> findByMatchApplyId(@Param("matchApplyId") long matchApplyId);

    @Query(value = "select * from matches where title like %:keyword% or content like %:keyword% ", nativeQuery = true)
    List<Match> findAllSearch(@Param(value = "keyword")String keyword);

    @Query(value = "select * from matches where name like :name", nativeQuery = true)
    List<Match> findAllSearchByUserName(@Param(value = "name")String name);

    @Query(value = "select * from matches order by created_at desc", nativeQuery = true)
    List<Match> findMatchesNewest();

    @Query(value = "select * from matches order by created_at asc", nativeQuery = true)
    List<Match> findMatchesLatest();

}
