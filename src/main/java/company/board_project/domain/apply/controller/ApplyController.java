package company.board_project.domain.apply.controller;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.mapper.ApplyMapper;
import company.board_project.domain.apply.service.ApplyService;
import company.board_project.domain.apply.dto.*;
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
@RequestMapping("/api/applies")
public class ApplyController {
    private final ApplyService applyService;
    private final ApplyMapper applyMapper;

    @PostMapping
    public ResponseEntity postApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createApply(applyMapper.applyPostDtoToApply(requestBody),requestBody.getUserId(), requestBody.getTeamId());
        ApplyResponseDto applyResponseDto = applyMapper.applyToApplyResponse(apply);

        return ResponseEntity.ok(applyResponseDto);
    }

    /*
    * 팀 가입 신청
    */
    @PostMapping("/teams")
    public ResponseEntity postTeamApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createTeamApply(applyMapper.applyPostDtoToTeamApply(requestBody),requestBody.getUserId(), requestBody.getTeamId());
        TeamApplyResponseDto teamApplyResponseDto = applyMapper.applyToTeamApplyResponse(apply);

        return ResponseEntity.ok(teamApplyResponseDto);
    }

    /*
    * 경기 참여 신청
    */
    @PostMapping("/matches")
    public ResponseEntity postMatchApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createMatchApply(applyMapper.applyPostDtoToMatchApply(requestBody),requestBody.getUserId(), requestBody.getMatchId(),requestBody.getTeamId());
        MatchApplyResponseDto matchApplyResponseDto = applyMapper.applyToMatchApplyResponse(apply);

        return ResponseEntity.ok(matchApplyResponseDto);
    }

    /*
     * 리그 가입 신청
     */
    @PostMapping("/leagues")
    public ResponseEntity postLeagueApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createLeagueApply(applyMapper.applyPostDtoToLeagueApply(requestBody),requestBody.getUserId(), requestBody.getLeagueId(), requestBody.getTeamId());
        LeagueApplyResponseDto leagueApplyResponseDto = applyMapper.applyToLeagueApplyResponse(apply);

        return ResponseEntity.ok(leagueApplyResponseDto);
    }

    /*
     * apply 전체 조회
     */
    @GetMapping("/{applyId}")
    public ResponseEntity getApply(@PathVariable("applyId") Long applyId) {
        Apply apply = applyService.findApply(applyId);
        ApplyResponseDto applyResponseDto = applyMapper.applyToApplyResponse(apply);

        return ResponseEntity.ok(applyResponseDto);
    }

    /*
     * teamId 단위 팀 가입 신청 조회
     */
    @GetMapping("/teams/{teamId}")
    public ResponseEntity getAppliesByTeamId(@PathVariable("teamId") Long teamId){

        List<Apply> applies = applyService.findAllByTeamId(teamId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    /*
     * leagueId 단위 리그 가입 신청 조회
     */
    @GetMapping("/leagues/{leagueId}")
    public ResponseEntity getAppliesByLeagueId(@PathVariable("leagueId") Long leagueId){

        List<Apply> applies = applyService.findAllByLeagueId(leagueId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    /*
     * matchId 단위 경기 신청 조회
     */
    @GetMapping("/matches/{matchId}")
    public ResponseEntity getAppliesByMatchId(@PathVariable("matchId") Long matchId){

        List<Apply> applies = applyService.findAllByMatchId(matchId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    /*
     * apply 제거
     */
    @DeleteMapping("/{applyId}")
    public ResponseEntity deleteApply(@PathVariable("applyId") Long applyId) {
        applyService.deleteApply(applyId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

