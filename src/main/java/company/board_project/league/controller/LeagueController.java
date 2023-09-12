package company.board_project.league.controller;

import company.board_project.league.mapper.LeagueMapper;
import company.board_project.league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/leagues")
public class LeagueController {
    private final LeagueService leagueService;
    private final LeagueMapper leagueMapper;
}
