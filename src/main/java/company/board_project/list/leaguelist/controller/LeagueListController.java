package company.board_project.list.leaguelist.controller;

import company.board_project.league.repository.LeagueRepository;
import company.board_project.list.leaguelist.dto.LeagueListPostDto;
import company.board_project.list.leaguelist.dto.LeagueListResponseDto;
import company.board_project.list.leaguelist.dto.LeagueListPatchDto;
import company.board_project.list.leaguelist.entity.LeagueList;
import company.board_project.list.leaguelist.mapper.LeagueListMapper;
import company.board_project.list.leaguelist.service.LeagueListService;
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
@RequestMapping("/api/lists/league")
public class LeagueListController {
    private final LeagueListService leagueListService;
    private final LeagueListMapper leagueListMapper;

    @PostMapping
    public ResponseEntity postLeagueList(@RequestBody LeagueListPostDto requestBody) {

        LeagueList leagueList = leagueListService.createLeagueList(leagueListMapper.leagueListPostDtoToLeagueList(requestBody),
                requestBody.getUserId(),
                requestBody.getTeamId(),
                requestBody.getLeagueId(),
                requestBody.getApplyId()
        );
        System.out.println("requestBody.getApplyId() 1"+ requestBody.getApplyId());

        LeagueListResponseDto leagueListResponse = leagueListMapper.leagueListToLeagueListResponse(leagueList);

        return ResponseEntity.ok(leagueListResponse);
    }

    @GetMapping("/{leagueListId}")
    public ResponseEntity getLeagueList(@PathVariable("leagueListId") Long leagueListId) {
        LeagueList leagueList = leagueListService.findLeagueList(leagueListId);
        LeagueListResponseDto leagueListResponse = leagueListMapper.leagueListToLeagueListResponse(leagueList);

        return ResponseEntity.ok(leagueListResponse);
    }

    @GetMapping
    public ResponseEntity getLeagueLists() {

        List<LeagueList> leagueLists = leagueListService.findLeagueLists();
        log.info("전체 요청 :" + leagueLists);
        return new ResponseEntity<>(leagueListMapper.leagueListsToLeagueListResponse(leagueLists),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity getLeagueListsNewest() {
        List<LeagueList> leagueLists = leagueListService.findLeagueListsNewest();
        List<LeagueListResponseDto> leagueListResponseDtos = leagueListMapper.leagueListsToLeagueListResponse(leagueLists);

        return ResponseEntity.ok(leagueListResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getLeagueListsLatest() {
        List<LeagueList> leagueLists = leagueListService.findLeagueListsLatest();
        List<LeagueListResponseDto> leagueListResponseDtos = leagueListMapper.leagueListsToLeagueListResponse(leagueLists);

        return ResponseEntity.ok(leagueListResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity getLeagueListsHonorScore() {
        List<LeagueList> leagueLists = leagueListService.findHonorScore();
        List<LeagueListResponseDto> leagueListResponseDtos = leagueListMapper.leagueListsToLeagueListResponse(leagueLists);

        return ResponseEntity.ok(leagueListResponseDtos);
    }

    @PatchMapping("/{leagueListId}")
    public ResponseEntity patchLeagueList(@RequestBody LeagueListPatchDto requestBody,
                                          @PathVariable("leagueListId") Long leagueListId) {
        LeagueList leagueList = leagueListService.updateLeagueList(
                leagueListMapper.leagueListPatchDtoToLeagueList(requestBody), leagueListId);

        LeagueListResponseDto leagueListResponse = leagueListMapper.leagueListToLeagueListResponse(leagueList);

        return ResponseEntity.ok(leagueListResponse);
    }

    @DeleteMapping("/{leagueListId}")
    public ResponseEntity deleteLeagueList(@PathVariable("leagueListId") Long leagueListId) {
        leagueListService.deleteLeagueList(leagueListId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
