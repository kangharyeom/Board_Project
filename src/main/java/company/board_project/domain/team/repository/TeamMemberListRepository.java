package company.board_project.domain.team.repository;

import company.board_project.domain.team.entity.TeamMemberList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberListRepository extends JpaRepository<TeamMemberList, Long> {
}
