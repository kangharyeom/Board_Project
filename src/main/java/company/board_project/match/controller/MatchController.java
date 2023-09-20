package company.board_project.match.controller;

import company.board_project.content.dto.*;
import company.board_project.content.entity.Content;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.list.leaguelist.entity.LeagueList;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.list.matchlist.service.MatchListService;
import company.board_project.match.dto.*;
import company.board_project.match.entity.Match;
import company.board_project.match.mapper.MatchMapper;
import company.board_project.match.service.MatchService;
import company.board_project.response.MultiResponseDto;
import company.board_project.team.entity.Team;
import company.board_project.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    private final TeamRepository teamRepository;
    private final MatchListService matchListService;

    @PostMapping
    public ResponseEntity postMatch(@Validated @RequestBody MatchPostDto requestBody) {

        Match match = matchService.createMatch(matchMapper.matchPostDtoToMatch(requestBody), requestBody.getUserId(),requestBody.getTeamId());
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match);

        matchListService.createMatchListByMatchController(new MatchList(), matchResponseDto.getMatchId(), matchResponseDto.getTeamId(),matchResponseDto.getUserId());

        return ResponseEntity.ok(matchResponseDto);
    }

    @PostMapping("/league")
    public ResponseEntity postLeagueMatch(@Validated @RequestBody LeagueMatchPostDto requestBody) {

        Match match = matchService.createLeagueMatch(matchMapper.leagueMatchPostDtoToMatch(requestBody), requestBody.getUserId(),requestBody.getTeamId());
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match);

        matchListService.createLeagueMatchListByMatchController(new MatchList(), matchResponseDto.getMatchId(), matchResponseDto.getTeamId(),matchResponseDto.getUserId(), matchResponseDto.getLeagueListId());

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity getMatch(@PathVariable("matchId") Long matchId) {
        Match match = matchService.findMatch(matchId);
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping
    public ResponseEntity getMatches(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<Match> pageMatches = matchService.findMatches(page - 1, size);
        List<Match> matches = pageMatches.getContent();
        log.info("전체 요청 :" + matches);
        return new ResponseEntity<>(
                new MultiResponseDto<>(matchMapper.matchesToMatchesResponse(matches, teamRepository),
                        pageMatches),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getSearch(@RequestParam(value = "keyword",required = false) String keyword) {
        List<Match> matches = matchService.findAllSearch(keyword);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matches, teamRepository);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/search/username")
    public ResponseEntity getSearchByUserName(@RequestParam(value = "name",required = false) String name) {
        List<Match> matches = matchService.findAllSearchByUserName(name);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matches, teamRepository);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/newest")
    public ResponseEntity getMatchesNewest() {
        List<Match> matches = matchService.findMatchesNewest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchesToMatchesResponse(matches, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping("/latest")
    public ResponseEntity getMatchesLatest() {
        List<Match> matches = matchService.findMatchesLatest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchesToMatchesResponse(matches, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity patchMatch(@RequestBody MatchPatchDto requestBody,
                                       @PathVariable("matchId") Long matchId) {
        requestBody.updateId(matchId);
        Match match = matchService.updateMatch(
                matchMapper.matchPatchDtoToMatch(requestBody));

        MatchResponseDto matchResponse = matchMapper.matchToMatchResponse(match);

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity deleteMatch(@PathVariable("matchId") Long matchId) {
        matchService.deleteMatch(matchId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
