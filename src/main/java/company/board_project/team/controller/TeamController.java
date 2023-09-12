package company.board_project.team.controller;

import company.board_project.team.mapper.TeamMapper;
import company.board_project.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
}
