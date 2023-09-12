package company.board_project.match.service;

import company.board_project.match.repository.MatchRepository;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
}
