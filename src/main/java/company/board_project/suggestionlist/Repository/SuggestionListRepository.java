package company.board_project.suggestionlist.Repository;

import company.board_project.suggestionlist.Entity.SuggestionList;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuggestionListRepository extends JpaRepository<SuggestionList,Long> {
    @Query(value = "select * from suggestionLists where team_id = :teamId", nativeQuery = true)
    List<SuggestionList> findAllByTeamId(@Param("teamId") long teamId);
}
