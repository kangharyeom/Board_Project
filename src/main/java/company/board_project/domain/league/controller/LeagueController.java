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
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@RequestMapping("/api/leagues")
public class LeagueController {
    private final LeagueService leagueService;
    private final LeagueMapper leagueMapper;
    private final LeagueListService leagueListService;

    /*
     * 리그 생성
     */
    @PostMapping
    public ResponseEntity postLeague(@RequestBody LeaguePostDto requestBody){

        League league = leagueService.createLeague(leagueMapper.leaguePostDtoToLeague(requestBody), requestBody.getUserId(), requestBody.getTeamId());
        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);

        // 리그 리스트 생성
        leagueListService.createLeagueListByLeagueController(new LeagueList(),requestBody.getUserId(), requestBody.getTeamId(), leagueResponseDto.getLeagueId());

        return ResponseEntity.ok(leagueResponseDto);
    }

    /*
     * 리그 단건 조회
     */
    @GetMapping("/{leagueId}")
    public ResponseEntity getLeague(@PathVariable("leagueId") Long leagueId) {
        League league = leagueService.findLeague(leagueId);
        LeagueResponseDto leagueResponseDto = leagueMapper.leagueToLeagueResponse(league);

        return ResponseEntity.ok(leagueResponseDto);
    }

    /*
     * 리그 전체 조회
     */
    @GetMapping
    public ResponseEntity getLeagues(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<League> pageLeagues = leagueService.findLeagues(page - 1, size);
        List<League> leagues = pageLeagues.getContent();
        log.info("전체 요청 : {}", leagues);
        return new ResponseEntity<>(
                new MultiResponseDto<>(leagueMapper.leaguesToLeagueResponse(leagues),
                        pageLeagues),
                HttpStatus.OK);
    }

    /*
     * 최근 등록된 리그 순서 조회
     */
    @GetMapping("/newest")
    public ResponseEntity getLeaguesNewest() {
        List<League> leagues = leagueService.findLeaguesNewest();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    /*
     * 오래된 순서 리그 조회
     */
    @GetMapping("/latest")
    public ResponseEntity getLeaguesLatest() {
        List<League> leagues = leagueService.findLeaguesLatest();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    /*
     * 명예점수 고득점 순서 리그 조회
     */
    @GetMapping("/score")
    public ResponseEntity getLeaguesHonorScore() {
        List<League> leagues = leagueService.findHonorScore();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    /*
     * 시즌 단위 조회 (시즌 진행중)
     */
    @GetMapping("/score")
    public ResponseEntity getLeagueOnSeason() {
        List<League> leagues = leagueService.findLeagueOnSeason();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    /*
     * 시즌 단위 조회 (시즌 종료)
     */
    @GetMapping("/score")
    public ResponseEntity getLeagueOffSeason() {
        List<League> leagues = leagueService.findLeagueOffSeason();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }

    /*
     * 시즌 단위 조회 (팀 모집)
     */
    @GetMapping("/score")
    public ResponseEntity getLeagueRecruit() {
        List<League> leagues = leagueService.findLeagueRecruit();
        List<LeagueResponseDto> leagueResponseDtos = leagueMapper.leaguesToLeagueResponse(leagues);

        return ResponseEntity.ok(leagueResponseDtos);
    }


    /*
     * 리그 수정
     */
    @PatchMapping("/{leagueId}")
    public ResponseEntity patchLeague(@RequestBody LeaguePatchDto requestBody,
                                       @PathVariable("leagueId") Long leagueId) {
        requestBody.updateId(leagueId);
        League league = leagueService.updateLeague(
                leagueMapper.leaguePatchDtoToLeague(requestBody));

        LeagueResponseDto leagueResponse = leagueMapper.leagueToLeagueResponse(league);

        return ResponseEntity.ok(leagueResponse);
    }

    /*
     * 리그 삭제
     */
    @DeleteMapping("/{leagueId}")
    public ResponseEntity deleteLeague(@PathVariable("leagueId") Long leagueId) {
        leagueService.deleteLeague(leagueId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
