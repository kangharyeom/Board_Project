package company.board_project.teamlist.repository;

import company.board_project.teamlist.entity.TeamList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamListRepository extends JpaRepository<TeamList, Long> {
}
