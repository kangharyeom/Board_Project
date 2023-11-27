package company.board_project.domain.user.repository;

import company.board_project.domain.user.entity.User;
import company.board_project.global.constant.LeagueRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByLeagueRole(LeagueRole leagueRole);
    List<User> findAll();
    Optional<User> findByLoginId(String name);
}
