package company.whistle.domain.apply.match.controller;

import company.whistle.domain.apply.match.dto.*;
import company.whistle.domain.apply.match.entity.MatchApply;
import company.whistle.domain.apply.match.mapper.MatchApplyMapper;
import company.whistle.domain.apply.match.service.MatchApplyService;
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
@RequestMapping("/api/applies/matches")
public class MatchApplyController {
    private final MatchApplyService matchApplyService;
    private final MatchApplyMapper matchApplyMapper;

    /*
    * 경기 참여 신청
    */
    @PostMapping
    public ResponseEntity<MatchApplyResponseDto> postMatchApply(@Validated @RequestBody MatchApplyPostDto requestBody) {

        MatchApply matchApply = matchApplyService.createMatchApply(matchApplyMapper.matchApplyPostDtoToMatchApply(requestBody),requestBody.getUserId(), requestBody.getMatchId(),requestBody.getTeamId());
        MatchApplyResponseDto matchApplyResponseDto = matchApplyMapper.matchApplyToMatchApplyResponse(matchApply);
        log.info("MATCH_APPLY POST COMPLETE: {}",matchApplyResponseDto.toString());

        return ResponseEntity.ok(matchApplyResponseDto);
    }

    /*
     * matchId 단위 경기 신청 조회
     */
    @GetMapping("/{matchApplyId}")
    public ResponseEntity<MatchApplyListDto> getMatchAppliesByMatchApplyId(@PathVariable("matchApplyId") Long matchApplyId){

        List<MatchApply> matchApplies = matchApplyService.findAllByMatchApplyId(matchApplyId);
        log.info("TOTAL MATCH_APPLIES INFO:" + matchApplies);
        return new ResponseEntity<>(matchApplyMapper.matchApplyListDtoToMatchApplyResponse(matchApplies),
                HttpStatus.OK);
    }

    /*
     * apply 제거
     */
    @DeleteMapping("/{matchApplyId}")
    public ResponseEntity<HttpStatus> deleteMatchApply(@PathVariable("matchApplyId") Long matchApplyId) {
        matchApplyService.deleteMatchApply(matchApplyId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

