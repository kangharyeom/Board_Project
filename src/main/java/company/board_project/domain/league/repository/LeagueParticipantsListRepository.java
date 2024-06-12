package company.board_project.domain.league.repository;

import company.board_project.domain.league.entity.LeagueParticipantsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueParticipantsListRepository extends JpaRepository<LeagueParticipantsList, Long> {

}
