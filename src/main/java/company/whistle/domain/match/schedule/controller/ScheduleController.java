package company.whistle.domain.match.schedule.controller;

import company.whistle.domain.match.schedule.dto.ScheduleAwayTeamDto;
import company.whistle.domain.match.schedule.dto.SchedulePatchDto;
import company.whistle.domain.match.schedule.dto.ScheduleResponseDto;
import company.whistle.domain.match.schedule.entity.Schedule;
import company.whistle.domain.match.schedule.mapper.ScheduleMapper;
import company.whistle.domain.match.schedule.service.ScheduleService;
import company.whistle.domain.match.schedule.dto.SchedulePostDto;
import company.whistle.domain.match.unrank.dto.MatchEndDto;
import company.whistle.domain.match.unrank.dto.MatchEndResponseDto;
import company.whistle.domain.team.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/lists/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> postSchedule(@RequestBody SchedulePostDto requestBody){

        Schedule schedule = scheduleService.createSchedule(scheduleMapper.schedulePostDtoToSchedule(requestBody),
                requestBody.getAwayTeamUserId(),
                requestBody.getApplyId(),
                requestBody.getAwayTeamId(),
                requestBody.getMatchId()
        );
        ScheduleResponseDto scheduleResponse = scheduleMapper.scheduleToScheduleResponse(schedule);
        log.info("SCHEDULE POST COMPLETE: {}", scheduleResponse.toString());

        return ResponseEntity.ok(scheduleResponse);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable("scheduleId") Long scheduleId) {
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        ScheduleResponseDto scheduleResponseDto = scheduleMapper.scheduleToScheduleResponse(schedule);

        return ResponseEntity.ok(scheduleResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(){

        List<Schedule> schedules = scheduleService.findSchedules();
        log.info("전체 요청 :" + schedules);
        return new ResponseEntity<>(scheduleMapper.schedulesToScheduleResponse(schedules),
                HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulesNewest() {
        List<Schedule> schedules = scheduleService.findSchedulesNewest();
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleMapper.schedulesToScheduleResponse(schedules);

        return ResponseEntity.ok(scheduleResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulesLatest() {
        List<Schedule> schedules = scheduleService.findSchedulesLatest();
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleMapper.schedulesToScheduleResponse(schedules);

        return ResponseEntity.ok(scheduleResponseDtos);
    }

    @GetMapping("/score")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulesHonorScore() {
        List<Schedule> schedules = scheduleService.findHonorScore();
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleMapper.schedulesToScheduleResponse(schedules);

        return ResponseEntity.ok(scheduleResponseDtos);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> patchSchedule(@RequestBody SchedulePatchDto requestBody,
                                      @PathVariable("scheduleId") Long scheduleId) {
        Schedule schedule = scheduleService.updateSchedule(
                scheduleMapper.schedulePatchDtoToSchedule(requestBody), scheduleId);

        ScheduleResponseDto scheduleResponse = scheduleMapper.scheduleToScheduleResponse(schedule);

        return ResponseEntity.ok(scheduleResponse);
    }

    @PatchMapping("/away/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> patchMatchAwayTeam(@RequestBody ScheduleAwayTeamDto requestBody,
                                         @PathVariable("scheduleId") Long scheduleId) {
        Schedule schedule = scheduleService.updateScheduleWithAwayTeam(
                scheduleMapper.applyToSchedule(requestBody), scheduleId);
        ScheduleResponseDto scheduleResponse = scheduleMapper.applyToScheduleResponse(schedule, requestBody.getApplyId());
        log.info("SCHEDULE PATCH COMPLETE: {}", scheduleResponse.toString());

        return ResponseEntity.ok(scheduleResponse);
    }

    @PatchMapping("/end/{scheduleId}")
    public ResponseEntity<MatchEndResponseDto> patchMatchEnd(@RequestBody MatchEndDto requestBody
            , @PathVariable("scheduleId") Long scheduleId
    ) {

        Schedule schedule = scheduleService.updateMatchEnd(
                scheduleMapper.matchEndDtoToMatch(requestBody)
                ,scheduleId
        );

        schedule.setScheduleId(scheduleId);
        MatchEndResponseDto matchResponse = scheduleMapper.matchToMatchEndResponse(schedule);
        long homeTeamScore = matchResponse.getHomeTeamScore();
        long awayTeamScore = matchResponse.getAwayTeamScore();
        log.info("MATCH_END PATCH COMPLETE: {}", matchResponse.toString());

        scheduleService.updateForMatchEnd(homeTeamScore, awayTeamScore, scheduleId);
        teamService.updateForMatchEnd(homeTeamScore,awayTeamScore, requestBody.getHomeTeamId(),requestBody.getAwayTeamId());

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
