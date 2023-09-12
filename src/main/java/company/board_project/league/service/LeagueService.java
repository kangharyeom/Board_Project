package company.board_project.league.service;

import company.board_project.league.repository.LeagueRepository;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final UserService userService;
}
