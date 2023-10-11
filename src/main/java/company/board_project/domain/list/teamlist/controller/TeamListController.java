package company.board_project.domain.list.teamlist.controller;

import company.board_project.domain.league.repository.LeagueRepository;
import company.board_project.domain.list.teamlist.dto.TeamListPatchDto;
import company.board_project.domain.list.teamlist.dto.TeamListPostDto;
import company.board_project.domain.list.teamlist.dto.TeamListResponseDto;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.list.teamlist.mapper.TeamListMapper;
import company.board_project.domain.list.teamlist.service.TeamListService;
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
@RequestMapping("/api/lists/team")
public class TeamListController {
    private final TeamListService teamListService;
    private final TeamListMapper teamListMapper;
    private final LeagueRepository leagueRepository;

    @PostMapping
    public ResponseEntity postTeamList(@RequestBody TeamListPostDto requestBody){

        TeamList teamList = teamListService.createTeamList(teamListMapper.teamListPostDtoToTeamList(requestBody), requestBody.getUserId(), requestBody.getTeamId(), requestBody.getApplyId());
        TeamListResponseDto teamListResponse = teamListMapper.teamListToTeamListResponse(teamList);

        return ResponseEntity.ok(teamListResponse);
    }

    @GetMapping("/{teamListId}")
    public ResponseEntity getTeamList(@PathVariable("teamListId") Long teamListId) {
        TeamList teamList = teamListService.findTeamList(teamListId);
        TeamListResponseDto teamListResponseDto = teamListMapper.teamListToTeamListResponse(teamList);

        return ResponseEntity.ok(teamListResponseDto);
    }

    @GetMapping
    public ResponseEntity getTeamLists(){

        List<TeamList> teamLists = teamListService.findTeamLists();
        log.info("전체 요청 :" + teamLists);
        return new ResponseEntity<>(teamListMapper.teamListsToTeamListResponse(teamLists),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity getTeamListsNewest() {
        List<TeamList> teamLists = teamListService.findTeamListsNewest();
        List<TeamListResponseDto> teamListResponseDtos = teamListMapper.teamListsToTeamListResponse(teamLists);

        return ResponseEntity.ok(teamListResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getTeamListsLatest() {
        List<TeamList> teamLists = teamListService.findTeamListsLatest();
        List<TeamListResponseDto> teamListResponseDtos = teamListMapper.teamListsToTeamListResponse(teamLists);

        return ResponseEntity.ok(teamListResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity getTeamListsHonorScore() {
        List<TeamList> teamLists = teamListService.findHonorScore();
        List<TeamListResponseDto> teamListResponseDtos = teamListMapper.teamListsToTeamListResponse(teamLists);

        return ResponseEntity.ok(teamListResponseDtos);
    }

    @PatchMapping("/{teamListId}")
    public ResponseEntity patchTeamList(@RequestBody TeamListPatchDto requestBody,
                                      @PathVariable("teamListId") Long teamListId) {
        TeamList teamList = teamListService.updateTeamList(
                teamListMapper.teamListPatchDtoToTeamList(requestBody), teamListId);

        TeamListResponseDto teamListResponse = teamListMapper.teamListToTeamListResponse(teamList);

        return ResponseEntity.ok(teamListResponse);
    }

    @DeleteMapping("/{teamListId}")
    public ResponseEntity deleteTeamList(@PathVariable("teamListId") Long teamListId) {
        teamListService.deleteTeamList(teamListId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
