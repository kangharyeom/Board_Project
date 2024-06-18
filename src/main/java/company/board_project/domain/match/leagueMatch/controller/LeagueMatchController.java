package company.board_project.domain.match.leagueMatch.controller;

import company.board_project.constant.MatchType;
import company.board_project.domain.match.leagueMatch.dto.LeagueMatchPostDto;
import company.board_project.domain.match.leagueMatch.dto.LeagueMatchResponseDto;
import company.board_project.domain.match.leagueMatch.entity.LeagueMatch;
import company.board_project.domain.match.leagueMatch.mapper.LeagueMatchMapper;
import company.board_project.domain.match.leagueMatch.service.LeagueMatchService;
import company.board_project.domain.match.match.dto.MatchPostDto;
import company.board_project.domain.match.match.dto.MatchResponseDto;
import company.board_project.domain.match.match.entity.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/leagues/{leagueId}/matches")
public class LeagueMatchController {
    private final LeagueMatchService leagueMatchService;
    private final LeagueMatchMapper leagueMatchMapper;

    @PostMapping
    public ResponseEntity postMatch(@Validated @RequestBody LeagueMatchPostDto requestBody, @PathVariable long leagueId) {
        LeagueMatch match = leagueMatchService.createLeagueMatch(leagueMatchMapper.leagueMatchPostDtoToLeagueMatch(requestBody), leagueId);
        LeagueMatchResponseDto leagueMatchResponseDto = leagueMatchMapper.leagueMatchToLeagueMatchResponseDto(match);
        return ResponseEntity.ok(leagueMatchResponseDto);
    }
}
