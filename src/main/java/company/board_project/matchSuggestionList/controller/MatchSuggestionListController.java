package company.board_project.matchSuggestionList.controller;

import company.board_project.matchSuggestionList.Entity.MatchSuggestionList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchSuggestionListController extends JpaRepository<MatchSuggestionList,Long> {
}
