package company.board_project.match.controller;

import company.board_project.match.mapper.MatchMapper;
import company.board_project.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/matchs")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;
}
