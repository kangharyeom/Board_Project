package company.board_project.match.leaguematch.controller;

import company.board_project.league.service.LeagueService;
import company.board_project.leaguematch.dto.*;
import company.board_project.match.leaguematch.dto.*;
import company.board_project.match.leaguematch.mapper.LeagueMatchMapper;
import company.board_project.match.leaguematch.service.LeagueMatchService;
import company.board_project.list.leaguelist.service.LeagueListService;
import company.board_project.match.leaguematch.entity.LeagueMatch;
import company.board_project.response.MultiResponseDto;
import company.board_project.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/matches/league")
public class LeagueMatchController {
    private final LeagueMatchService leagueMatchService;
    private final LeagueMatchMapper leagueMatchMapper;
    private final TeamService teamService;
    private final LeagueListService leagueListService;
    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity postLeagueMatch(@Validated @RequestBody LeagueMatchPostDto requestBody) {

        LeagueMatch leagueMatch = leagueMatchService.createLeagueMatch(leagueMatchMapper.leagueMatchPostDtoToLeagueMatch(requestBody)
                , requestBody.getHomeTeamUserId()
                , requestBody.getAwayTeamUserId()
                , requestBody.getHomeTeamId()
                , requestBody.getAwayTeamId()
                , requestBody.getHomeTeamLeagueListId()
                , requestBody.getAwayTeamLeagueListId()
        );
        LeagueMatchResponseDto leagueMatchResponseDto = leagueMatchMapper.leagueMatchToLeagueMatchResponse(leagueMatch);

        return ResponseEntity.ok(leagueMatchResponseDto);
    }

    @GetMapping("/{leagueMatchId}")
    public ResponseEntity getLeagueMatch(@PathVariable("leagueMatchId") Long leagueMatchId) {
        LeagueMatch leagueMatch = leagueMatchService.findLeagueMatch(leagueMatchId);
        LeagueMatchResponseDto leagueMatchResponseDto = leagueMatchMapper.leagueMatchToLeagueMatchResponse(leagueMatch);

        return ResponseEntity.ok(leagueMatchResponseDto);
    }

    @GetMapping
    public ResponseEntity getLeagueMatches(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<LeagueMatch> pageLeagueMatches = leagueMatchService.findLeagueMatches(page - 1, size);
        List<LeagueMatch> leagueMatches = pageLeagueMatches.getContent();
        log.info("전체 요청 :" + leagueMatches);
        return new ResponseEntity<>(
                new MultiResponseDto<>(leagueMatchMapper.leagueMatchesToLeagueMatchesResponse(leagueMatches),
                        pageLeagueMatches),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getLeagueMatchSearch(@RequestParam(value = "keyword",required = false) String keyword) {
        List<LeagueMatch> leagueMatches = leagueMatchService.findAllSearch(keyword);
        LeagueMatchListDto leagueMatchListDto = leagueMatchMapper.leagueMatchListDtoToLeagueMatchResponse(leagueMatches);

        return ResponseEntity.ok(leagueMatchListDto);
    }

    @GetMapping("/league/search/username")
    public ResponseEntity getLeagueMatchSearchByUserName(@RequestParam(value = "name",required = false) String name) {
        List<LeagueMatch> leagueMatches = leagueMatchService.findAllSearchByUserName(name);
        LeagueMatchListDto leagueMatchListDto = leagueMatchMapper.leagueMatchListDtoToLeagueMatchResponse(leagueMatches);

        return ResponseEntity.ok(leagueMatchListDto);
    }

    @GetMapping("/league/newest")
    public ResponseEntity getLeagueMatchesNewest() {
        List<LeagueMatch> leagueMatches = leagueMatchService.findLeagueMatchesNewest();
        List<LeagueMatchResponseDto> leagueMatchResponseDto = leagueMatchMapper.leagueMatchesToLeagueMatchesResponse(leagueMatches);

        return ResponseEntity.ok(leagueMatchResponseDto);
    }

    @GetMapping("/league/latest")
    public ResponseEntity getLeagueMatchesLatest() {
        List<LeagueMatch> leagueMatches = leagueMatchService.findLeagueMatchesLatest();
        List<LeagueMatchResponseDto> leagueMatchResponseDto = leagueMatchMapper.leagueMatchesToLeagueMatchesResponse(leagueMatches);

        return ResponseEntity.ok(leagueMatchResponseDto);
    }

    @PatchMapping("/{leagueMatchId}")
    public ResponseEntity patchLeagueMatch(@RequestBody LeagueMatchPatchDto requestBody,
                                       @PathVariable("leagueMatchId") Long leagueMatchId) {
        LeagueMatch leagueMatch = leagueMatchService.updateLeagueMatch(
                leagueMatchMapper.leagueMatchPatchDtoToLeagueMatch(requestBody),leagueMatchId);

        LeagueMatchResponseDto matchResponse = leagueMatchMapper.leagueMatchToLeagueMatchResponse(leagueMatch);

        return ResponseEntity.ok(matchResponse);
    }

    @PatchMapping("/end/{leagueMatchId}")
    public ResponseEntity patchLeagueMatchEnd(@RequestBody LeagueMatchEndDto requestBody
            , @PathVariable("leagueMatchId") Long leagueMatchId
    ) {

        LeagueMatch leagueMatch = leagueMatchService.updateLeagueMatchEnd(
                leagueMatchMapper.leagueMatchEndDtoToLeagueMatch(requestBody)
                ,leagueMatchId
        );

        leagueMatch.setLeagueMatchId(leagueMatchId);
        LeagueMatchEndResponseDto matchResponse = leagueMatchMapper.leagueMatchToLeagueMatchEndResponse(leagueMatch);
        long homeTeamScore = matchResponse.getHomeTeamScore();
        long awayTeamScore = matchResponse.getAwayTeamScore();

        leagueMatchService.updateForLeagueMatchEnd(homeTeamScore, awayTeamScore, leagueMatchId);
        teamService.updateForLeagueMatchEnd(homeTeamScore,awayTeamScore, requestBody.getHomeTeamId(),requestBody.getAwayTeamId());
        leagueListService.updateForLeagueMatchEnd(homeTeamScore, awayTeamScore, requestBody.getHomeTeamLeagueListId(), requestBody.getAwayTeamLeagueListId());
//        leagueService.checkEndTheLeague(requestBody.getLeagueId());

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{leagueMatchId}")
    public ResponseEntity deleteLeagueMatch(@PathVariable("leagueMatchId") Long leagueMatchId) {
        leagueMatchService.deleteLeagueMatch(leagueMatchId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
