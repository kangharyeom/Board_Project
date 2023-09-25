package company.board_project.list.matchlist.controller;

import company.board_project.list.matchlist.dto.MatchAwayTeamDto;
import company.board_project.list.matchlist.dto.MatchListPatchDto;
import company.board_project.list.matchlist.dto.MatchListPostDto;
import company.board_project.list.matchlist.dto.MatchListResponseDto;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.list.matchlist.mapper.MatchListMapper;
import company.board_project.list.matchlist.service.MatchListService;
import company.board_project.match.normalmatch.dto.MatchEndDto;
import company.board_project.match.normalmatch.dto.MatchEndResponseDto;
import company.board_project.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/lists/match")
public class MatchListController {
    private final MatchListService matchListService;
    private final MatchListMapper matchListMapper;
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity postMatchList(@RequestBody MatchListPostDto requestBody){

        MatchList matchList = matchListService.createMatchList(matchListMapper.matchListPostDtoToMatchList(requestBody),
                requestBody.getAwayTeamUserId(),
                requestBody.getApplyId(),
                requestBody.getAwayTeamId(),
                requestBody.getMatchId()
        );
        MatchListResponseDto matchListResponse = matchListMapper.matchListToMatchListResponse(matchList);

        return ResponseEntity.ok(matchListResponse);
    }

    @GetMapping("/{matchListId}")
    public ResponseEntity getMatchList(@PathVariable("matchListId") Long matchListId) {
        MatchList matchList = matchListService.findMatchList(matchListId);
        MatchListResponseDto matchListResponseDto = matchListMapper.matchListToMatchListResponse(matchList);

        return ResponseEntity.ok(matchListResponseDto);
    }

    @GetMapping
    public ResponseEntity getMatchLists(){

        List<MatchList> matchLists = matchListService.findMatchLists();
        log.info("전체 요청 :" + matchLists);
        return new ResponseEntity<>(matchListMapper.matchListsToMatchListResponse(matchLists),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity getMatchListsNewest() {
        List<MatchList> matchLists = matchListService.findMatchListsNewest();
        List<MatchListResponseDto> matchListResponseDtos = matchListMapper.matchListsToMatchListResponse(matchLists);

        return ResponseEntity.ok(matchListResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getMatchListsLatest() {
        List<MatchList> matchLists = matchListService.findMatchListsLatest();
        List<MatchListResponseDto> matchListResponseDtos = matchListMapper.matchListsToMatchListResponse(matchLists);

        return ResponseEntity.ok(matchListResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity getMatchListsHonorScore() {
        List<MatchList> matchLists = matchListService.findHonorScore();
        List<MatchListResponseDto> matchListResponseDtos = matchListMapper.matchListsToMatchListResponse(matchLists);

        return ResponseEntity.ok(matchListResponseDtos);
    }

    @PatchMapping("/{matchListId}")
    public ResponseEntity patchMatchList(@RequestBody MatchListPatchDto requestBody,
                                      @PathVariable("matchListId") Long matchListId) {
        MatchList matchList = matchListService.updateMatchList(
                matchListMapper.matchListPatchDtoToMatchList(requestBody), matchListId);

        MatchListResponseDto matchListResponse = matchListMapper.matchListToMatchListResponse(matchList);

        return ResponseEntity.ok(matchListResponse);
    }

    @PatchMapping("/away/{matchListId}")
    public ResponseEntity patchMatchAwayTeam(@RequestBody MatchAwayTeamDto requestBody,
                                         @PathVariable("matchListId") Long matchListId) {
        MatchList matchList = matchListService.updateMatchListWithAwayTeam(
                matchListMapper.applyToMatchList(requestBody), matchListId);
        MatchListResponseDto matchListResponse = matchListMapper.applyToMatchListResponse(matchList, requestBody.getApplyId());

        return ResponseEntity.ok(matchListResponse);
    }

    @PatchMapping("/end/{matchListId}")
    public ResponseEntity patchMatchEnd(@RequestBody MatchEndDto requestBody
            , @PathVariable("matchListId") Long matchListId
    ) {

        MatchList matchList = matchListService.updateMatchEnd(
                matchListMapper.matchEndDtoToMatch(requestBody)
                ,matchListId
        );

        matchList.setMatchListId(matchListId);
        MatchEndResponseDto matchResponse = matchListMapper.matchToMatchEndResponse(matchList);
        long homeTeamScore = matchResponse.getHomeTeamScore();
        long awayTeamScore = matchResponse.getAwayTeamScore();

        matchListService.updateForMatchEnd(homeTeamScore, awayTeamScore, matchListId);
        teamService.updateForMatchEnd(homeTeamScore,awayTeamScore, requestBody.getHomeTeamId(),requestBody.getAwayTeamId());

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{matchListId}")
    public ResponseEntity deleteMatchList(@PathVariable("matchListId") Long matchListId) {
        matchListService.deleteMatchList(matchListId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
