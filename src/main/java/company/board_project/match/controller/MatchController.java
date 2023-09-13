package company.board_project.match.controller;

import company.board_project.content.dto.*;
import company.board_project.content.entity.Content;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.match.dto.MatchListDto;
import company.board_project.match.dto.MatchPatchDto;
import company.board_project.match.dto.MatchPostDto;
import company.board_project.match.dto.MatchResponseDto;
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
@RequestMapping("/api/matchs")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    private final TeamRepository teamRepository;

    @PostMapping
    public ResponseEntity postMatch(@Validated @RequestBody MatchPostDto requestBody) {

        Match match = matchService.createMatch(matchMapper.matchPostDtoToMatch(requestBody));
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity getMatch(@PathVariable("matchId") Long matchId) {
        Match match = matchService.findMatch(matchId);
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping
    public ResponseEntity getMatchs(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<Match> pageMatchs = matchService.findMatchs(page - 1, size);
        List<Match> matchs = pageMatchs.getContent();
        log.info("전체 요청 :" + matchs);
        return new ResponseEntity<>(
                new MultiResponseDto<>(matchMapper.matchsToMatchsResponse(matchs, teamRepository),
                        pageMatchs),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getSearch(@RequestParam(value = "keyword",required = false) String keyword) {
        List<Match> matchs = matchService.findAllSearch(keyword);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matchs, teamRepository);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/search/username")
    public ResponseEntity getSearchByUserName(@RequestParam(value = "name",required = false) String name) {
        List<Match> matchs = matchService.findAllSearchByUserName(name);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matchs, teamRepository);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/newest")
    public ResponseEntity getMatchsNewest() {
        List<Match> matchs = matchService.findMatchsNewest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchsToMatchsResponse(matchs, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping("/latest")
    public ResponseEntity getMatchsLatest() {
        List<Match> matchs = matchService.findMatchsLatest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchsToMatchsResponse(matchs, teamRepository);

        return ResponseEntity.ok(matchResponseDto);
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity patchMatch(@RequestBody MatchPatchDto requestBody,
                                       @PathVariable("matchId") Long matchId) {
        requestBody.updateId(matchId);
        Match match = matchService.updateMatch(
                matchMapper.matchPatchDtoToMatch(requestBody));

        MatchResponseDto matchResponse = matchMapper.matchToMatchResponse(match, teamRepository);

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity deleteMatch(@PathVariable("matchId") Long matchId) {
        matchService.deleteMatch(matchId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
