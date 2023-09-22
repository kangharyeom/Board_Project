package company.board_project.apply.controller;

import company.board_project.apply.dto.*;
import company.board_project.apply.entity.Apply;
import company.board_project.apply.mapper.ApplyMapper;
import company.board_project.apply.service.ApplyService;
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

    @PostMapping("/teams")
    public ResponseEntity postTeamApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createTeamApply(applyMapper.applyPostDtoToTeamApply(requestBody),requestBody.getUserId(), requestBody.getTeamId());
        TeamApplyResponseDto teamApplyResponseDto = applyMapper.applyToTeamApplyResponse(apply);

        return ResponseEntity.ok(teamApplyResponseDto);
    }

    @PostMapping("/matches")
    public ResponseEntity postMatchApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createMatchApply(applyMapper.applyPostDtoToMatchApply(requestBody),requestBody.getUserId(), requestBody.getMatchId(),requestBody.getTeamId());
        MatchApplyResponseDto matchApplyResponseDto = applyMapper.applyToMatchApplyResponse(apply);

        return ResponseEntity.ok(matchApplyResponseDto);
    }

    @PostMapping("/leagues")
    public ResponseEntity postLeagueApply(@Validated @RequestBody ApplyPostDto requestBody) {

        Apply apply = applyService.createLeagueApply(applyMapper.applyPostDtoToLeagueApply(requestBody),requestBody.getUserId(), requestBody.getLeagueId(), requestBody.getTeamId());
        LeagueApplyResponseDto leagueApplyResponseDto = applyMapper.applyToLeagueApplyResponse(apply);

        return ResponseEntity.ok(leagueApplyResponseDto);
    }


    @GetMapping("/{applyId}")
    public ResponseEntity getApply(@PathVariable("applyId") Long applyId) {
        Apply apply = applyService.findApply(applyId);
        ApplyResponseDto applyResponseDto = applyMapper.applyToApplyResponse(apply);

        return ResponseEntity.ok(applyResponseDto);
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity getAppliesByTeamId(@PathVariable("teamId") Long teamId){

        List<Apply> applies = applyService.findAllByTeamId(teamId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    @GetMapping("/leagues/{leagueId}")
    public ResponseEntity getAppliesByLeagueId(@PathVariable("leagueId") Long leagueId){

        List<Apply> applies = applyService.findAllByLeagueId(leagueId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}")
    public ResponseEntity getAppliesByMatchId(@PathVariable("matchId") Long matchId){

        List<Apply> applies = applyService.findAllByMatchId(matchId);
        log.info("전체 요청 :" + applies);
        return new ResponseEntity<>(applyMapper.applyListDtoToApplyResponse(applies),
                HttpStatus.OK);
    }

    @DeleteMapping("/{applyId}")
    public ResponseEntity deleteApply(@PathVariable("applyId") Long applyId) {
        applyService.deleteApply(applyId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

