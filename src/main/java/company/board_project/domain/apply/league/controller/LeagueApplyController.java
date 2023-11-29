package company.board_project.domain.apply.league.controller;

import company.board_project.domain.apply.league.dto.*;
import company.board_project.domain.apply.league.entity.LeagueApply;
import company.board_project.domain.apply.league.mapper.LeagueApplyMapper;
import company.board_project.domain.apply.league.service.LeagueApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/applies/leagues")
public class LeagueApplyController {
    private final LeagueApplyService leagueApplyService;
    private final LeagueApplyMapper leagueApplyMapper;

    /*
     * 리그 가입 신청
     */
    @PostMapping
    public ResponseEntity postLeagueApply(@Validated @RequestBody LeagueApplyPostDto requestBody) {

        LeagueApply leagueApply = leagueApplyService.createLeagueApply(leagueApplyMapper.leagueApplyPostDtoToLeagueApply(requestBody),requestBody.getUserId(), requestBody.getLeagueId(), requestBody.getTeamId());
        LeagueApplyResponseDto leagueApplyResponseDto = leagueApplyMapper.leagueApplyToLeagueApplyResponse(leagueApply);

        return ResponseEntity.ok(leagueApplyResponseDto);
    }

    /*
     * apply 전체 조회
     */
    @GetMapping("/{leagueApplyId}")
    public ResponseEntity getApply(@PathVariable("leagueApplyId") Long leagueApplyId) {
        LeagueApply leagueApply = leagueApplyService.findLeagueApply(leagueApplyId);
        LeagueApplyResponseDto applyResponseDto = leagueApplyMapper.leagueApplyToLeagueApplyResponse(leagueApply);

        return ResponseEntity.ok(applyResponseDto);
    }

    /*
     * leagueId 단위 리그 가입 신청 조회
     */
    @GetMapping("/{leagueApplyId}")
    public ResponseEntity getAppliesByLeagueId(@PathVariable("leagueApplyId") Long leagueApplyId){

        List<LeagueApply> applies = leagueApplyService.findAllByLeagueId(leagueApplyId);
        log.info("TOTAL LEAGUE_APPLIES INFO:" + applies);
        return new ResponseEntity<>(leagueApplyMapper.leagueApplyListDtoToLeagueApplyResponse(applies),
                HttpStatus.OK);
    }

    /*
     * apply 제거
     */
    @DeleteMapping("/{leagueApplyId}")
    public ResponseEntity deleteApply(@PathVariable("leagueApplyId") Long leagueApplyId) {
        leagueApplyService.deleteApply(leagueApplyId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

