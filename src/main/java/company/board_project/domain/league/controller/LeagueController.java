package company.board_project.domain.league.controller;

import company.board_project.domain.league.dto.LeaguePatchDto;
import company.board_project.domain.league.dto.LeaguePostDto;
import company.board_project.domain.league.dto.LeagueResponseDto;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.league.mapper.LeagueMapper;
import company.board_project.domain.league.service.LeagueService;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.list.leaguelist.service.LeagueListService;
import company.board_project.global.response.MultiResponseDto;
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
@RequestMapping("/api/leagues")
public class LeagueController {
    private final LeagueService leagueService;
    private final LeagueMapper leagueMapper;
    private final LeagueListService leagueListService;

    @PostMapping
    public ResponseEntity postLeague(@RequestBody LeaguePostDto requestBody){

        League league = leagueService.createLeague(leagueMapper.leaguePostDtoToLeague(requestBody), requestBody.getUserId(), requestBody.getTeamId());
        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);

        leagueListService.createLeagueListByLeagueController(new LeagueList(),requestBody.getUserId(), requestBody.getTeamId(), leagueResponseDto.getLeagueId());

        return ResponseEntity.ok(leagueResponseDto);
    }

   /* @PostMapping
    public ResponseEntity postLeague(@RequestBody LeaguePostDto requestBody){

        League league = leagueService.createLeague(leagueMapper.leaguePostDtoToLeague(requestBody), requestBody.getUserId(), requestBody.getTeamId());
        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);



        return new ResponseEntity<>(leagueResponseDto,
                HttpStatus.OK);
    }*/

//    @PostMapping("/apply/{applyId}")
//    public ResponseEntity postApplyToLeague(@RequestBody LeaguePostDto requestBody, @PathVariable("applyId") Long applyId){
//
//        League league = leagueService.createLeague(leagueMapper.leagueApplyToLeague(requestBody), requestBody.getUserId(), requestBody.getTeamId());
//        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);
//
//        return ResponseEntity.ok(leagueResponseDto);
//    }

    @GetMapping("/{leagueId}")
    public ResponseEntity getLeague(@PathVariable("leagueId") Long leagueId) {
        League league = leagueService.findLeague(leagueId);
        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);

        return ResponseEntity.ok(leagueResponseDto);
    }

    @GetMapping
    public ResponseEntity getLeagues(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<League> pageLeagues = leagueService.findLeagues(page - 1, size);
        List<League> leagues = pageLeagues.getContent();
        log.info("전체 요청 :" + leagues);
        return new ResponseEntity<>(
                new MultiResponseDto<>(leagueMapper.leaguesToLeagueResponse(leagues),
                        pageLeagues),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity getLeaguesNewest() {
        List<League> leagues = leagueService.findLeaguesNewest();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getLeaguesLatest() {
        List<League> leagues = leagueService.findLeaguesLatest();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity getLeaguesHonorScore() {
        List<League> leagues = leagueService.findHonorScore();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    @PatchMapping("/{leagueId}")
    public ResponseEntity patchLeague(@RequestBody LeaguePatchDto requestBody,
                                       @PathVariable("leagueId") Long leagueId) {
        League league = leagueService.updateLeague(
                leagueMapper.leaguePatchDtoToLeague(requestBody));

        LeagueResponseDto leagueResponse = leagueMapper.leagueToLeagueResponse(league);

        return ResponseEntity.ok(leagueResponse);
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity deleteLeague(@PathVariable("leagueId") Long leagueId) {
        leagueService.deleteLeague(leagueId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
