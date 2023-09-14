package company.board_project.leagueteamlist.repository;

import company.board_project.leagueteamlist.entity.LeagueTeamList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueTeamListRepository extends JpaRepository<LeagueTeamList, Long> {
}
