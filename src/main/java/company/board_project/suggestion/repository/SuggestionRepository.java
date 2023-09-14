package company.board_project.suggestion.repository;

import company.board_project.suggestion.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {
    @Query(value = "select * from suggestionLists where team_id = :teamId", nativeQuery = true)
    List<Suggestion> findAllByTeamId(@Param("teamId") long teamId);
}
