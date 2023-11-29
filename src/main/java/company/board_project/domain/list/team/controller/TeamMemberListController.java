package company.board_project.domain.list.team.controller;

import company.board_project.domain.list.team.dto.TeamMemberListPatchDto;
import company.board_project.domain.list.team.dto.TeamMemberListPostDto;
import company.board_project.domain.list.team.dto.TeamMemberListResponseDto;
import company.board_project.domain.list.team.entity.TeamMemberList;
import company.board_project.domain.list.team.mapper.TeamMemberListMapper;
import company.board_project.domain.list.team.service.TeamMemberListService;
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
@RequestMapping("/api/lists/TeamMembers")
public class TeamMemberListController {
    private final TeamMemberListService teamMemberListService;
    private final TeamMemberListMapper teamMemberListMapper;

    @PostMapping
    public ResponseEntity postTeamMemberList(@RequestBody TeamMemberListPostDto requestBody){

        TeamMemberList teamMemberList = teamMemberListService.createTeamMemberList(teamMemberListMapper.teamMemberListPostDtoToTeamMemberList(requestBody), requestBody.getUserId(), requestBody.getTeamId(), requestBody.getApplyId());
        TeamMemberListResponseDto teamMemberListResponse = teamMemberListMapper.teamMemberListToTeamMemberListResponse(teamMemberList);

        return ResponseEntity.ok(teamMemberListResponse);
    }

    @GetMapping("/{teamMemberListId}")
    public ResponseEntity getTeamMemberList(@PathVariable("teamMemberListId") Long teamMemberListId) {
        TeamMemberList teamMemberList = teamMemberListService.findTeamMemberList(teamMemberListId);
        TeamMemberListResponseDto teamMemberListResponseDto = teamMemberListMapper.teamMemberListToTeamMemberListResponse(teamMemberList);

        return ResponseEntity.ok(teamMemberListResponseDto);
    }

    @GetMapping
    public ResponseEntity getTeamMemberLists(){

        List<TeamMemberList> teamMemberLists = teamMemberListService.findTeamMemberLists();
        log.info("전체 요청 :" + teamMemberLists);
        return new ResponseEntity<>(teamMemberListMapper.teamMemberListsToTeamMemberListResponse(teamMemberLists),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity getTeamMemberListsNewest() {
        List<TeamMemberList> teamMemberLists = teamMemberListService.findTeamMemberListsNewest();
        List<TeamMemberListResponseDto> teamMemberListResponseDtos = teamMemberListMapper.teamMemberListsToTeamMemberListResponse(teamMemberLists);

        return ResponseEntity.ok(teamMemberListResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getTeamMemberListsLatest() {
        List<TeamMemberList> teamMemberLists = teamMemberListService.findTeamMemberListsLatest();
        List<TeamMemberListResponseDto> teamMemberListResponseDtos = teamMemberListMapper.teamMemberListsToTeamMemberListResponse(teamMemberLists);

        return ResponseEntity.ok(teamMemberListResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity getTeamMemberListsHonorScore() {
        List<TeamMemberList> teamMemberLists = teamMemberListService.findHonorScore();
        List<TeamMemberListResponseDto> teamMemberListResponseDtos = teamMemberListMapper.teamMemberListsToTeamMemberListResponse(teamMemberLists);

        return ResponseEntity.ok(teamMemberListResponseDtos);
    }

    @PatchMapping("/{teamMemberListId}")
    public ResponseEntity patchTeamMemberList(@RequestBody TeamMemberListPatchDto requestBody,
                                      @PathVariable("teamMemberListId") Long teamMemberListId) {
        TeamMemberList teamMemberList = teamMemberListService.updateTeamMemberList(
                teamMemberListMapper.teamMemberListPatchDtoToTeamMemberList(requestBody), teamMemberListId);

        TeamMemberListResponseDto teamMemberListResponse = teamMemberListMapper.teamMemberListToTeamMemberListResponse(teamMemberList);

        return ResponseEntity.ok(teamMemberListResponse);
    }

    @DeleteMapping("/{teamMemberListId}")
    public ResponseEntity deleteTeamMemberList(@PathVariable("teamMemberListId") Long teamMemberListId) {
        teamMemberListService.deleteTeamMemberList(teamMemberListId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
