package company.whistle.domain.match.schedule.mapper;

import company.whistle.domain.apply.match.entity.MatchApply;
import company.whistle.domain.match.schedule.dto.*;
import company.whistle.domain.match.schedule.entity.Schedule;
import company.whistle.domain.match.unrank.dto.MatchEndDto;
import company.whistle.domain.match.unrank.dto.MatchEndResponseDto;
import company.whistle.domain.match.unrank.entity.Match;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule schedulePostDtoToSchedule(SchedulePostDto requestBody);

    Schedule schedulePatchDtoToSchedule(SchedulePatchDto requestBody);

    Schedule applyToSchedule(ScheduleAwayTeamDto requestBody);

    Schedule matchEndDtoToMatch(MatchEndDto requestBody);

    default MatchEndResponseDto matchToMatchEndResponse(Schedule schedule){
        Match match = schedule.getMatch();
        User user = schedule.getUser();
        Team team = schedule.getTeam();

        return MatchEndResponseDto.builder()
                .scheduleId(schedule.getScheduleId())
                .matchId(match.getMatchId())
                .homeTeamUserId(user.getUserId())
                .awayTeamUserId(schedule.getAwayTeamUserId())
                .homeTeamId(team.getTeamId())
                .awayTeamId(schedule.getAwayTeamId())
                .homeTeamScore(schedule.getHomeTeamScore())
                .awayTeamScore(schedule.getAwayTeamScore())
                .matchStatus(String.valueOf(schedule.getMatchStatus()))
                .matchTime(schedule.getMatchTime())
                .matchDate(schedule.getMatchDate())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }

    default ScheduleResponseDto scheduleToScheduleResponse(Schedule schedule){
        User user = schedule.getUser();
        Team team = schedule.getTeam();
        Match match = schedule.getMatch();

        return ScheduleResponseDto.builder()
                .homeTeamUserId(user.getUserId())
                .homeTeamId(team.getTeamId())
                .matchId(match.getMatchId())
                .scheduleId(schedule.getScheduleId())
                .homeTeamScore(schedule.getHomeTeamScore())
                .homeTeamHonorScore(schedule.getHomeTeamHonorScore())
                .homeTeamName(schedule.getHomeTeamName())
                .homeTeamManagerName(schedule.getHomeTeamManagerName())
                .homeTeamTotalWinRecord(schedule.getHomeTeamTotalWinRecord())
                .homeTeamTotalDrawRecord(schedule.getHomeTeamTotalDrawRecord())
                .homeTeamTotalLoseRecord(schedule.getHomeTeamTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(schedule.getHomeTeamLevelType()))
                .homeTeamAgeType(String.valueOf(schedule.getHomeTeamAgeType()))
                .homeTeamUniformType(String.valueOf(schedule.getHomeTeamUniformType()))

                .awayTeamScore(schedule.getAwayTeamScore())
                .awayTeamHonorScore(schedule.getAwayTeamHonorScore())
                .awayTeamName(schedule.getAwayTeamName())
                .awayTeamManagerName(schedule.getAwayTeamManagerName())
                .awayTeamTotalWinRecord(schedule.getAwayTeamTotalWinRecord())
                .awayTeamTotalDrawRecord(schedule.getAwayTeamTotalDrawRecord())
                .awayTeamTotalLoseRecord(schedule.getAwayTeamTotalLoseRecord())
                .awayTeamLevelType(String.valueOf(schedule.getAwayTeamLevelType()))
                .awayTeamAgeType(String.valueOf(schedule.getAwayTeamAgeType()))
                .awayTeamUniformType(String.valueOf(schedule.getAwayTeamUniformType()))
                .matchTime(schedule.getMatchTime())
                .matchDate(schedule.getMatchDate())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }

    default ScheduleResponseDto applyToScheduleResponse(Schedule schedule, Long matchApplyId){
        Match match = schedule.getMatch();
        User user = schedule.getUser();
        Team team = schedule.getTeam();
        MatchApply matchApply = schedule.getMatchApply();

        return ScheduleResponseDto.builder()
                .homeTeamUserId(user.getUserId())
                .awayTeamUserId(schedule.getAwayTeamUserId())
                .awayTeamId(schedule.getAwayTeamId())
                .matchId(match.getMatchId())
                .homeTeamId(team.getTeamId())
                .matchApplyId(matchApply.getMatchApplyId())
                .scheduleId(schedule.getScheduleId())
                .homeTeamScore(schedule.getHomeTeamScore())
                .homeTeamHonorScore(schedule.getHomeTeamHonorScore())
                .homeTeamName(schedule.getHomeTeamName())
                .homeTeamManagerName(schedule.getHomeTeamManagerName())
                .homeTeamTotalWinRecord(schedule.getHomeTeamTotalWinRecord())
                .homeTeamTotalDrawRecord(schedule.getHomeTeamTotalDrawRecord())
                .homeTeamTotalLoseRecord(schedule.getHomeTeamTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(schedule.getHomeTeamLevelType()))
                .homeTeamAgeType(String.valueOf(schedule.getHomeTeamAgeType()))
                .homeTeamUniformType(String.valueOf(schedule.getHomeTeamUniformType()))

                .awayTeamScore(schedule.getAwayTeamScore())
                .awayTeamHonorScore(schedule.getAwayTeamHonorScore())
                .awayTeamName(schedule.getAwayTeamName())
                .awayTeamManagerName(schedule.getAwayTeamManagerName())
                .awayTeamTotalWinRecord(schedule.getAwayTeamTotalWinRecord())
                .awayTeamTotalDrawRecord(schedule.getAwayTeamTotalDrawRecord())
                .awayTeamTotalLoseRecord(schedule.getAwayTeamTotalLoseRecord())
                .awayTeamLevelType(String.valueOf(schedule.getAwayTeamLevelType()))
                .awayTeamAgeType(String.valueOf(schedule.getAwayTeamAgeType()))
                .awayTeamUniformType(String.valueOf(schedule.getAwayTeamUniformType()))
                .matchTime(schedule.getMatchTime())
                .matchDate(schedule.getMatchDate())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }

    default ScheduleResponseListDto scheduleDtoToScheduleResponse(List<Schedule> schedules){

        return ScheduleResponseListDto.builder()
                .schedule(schedulesToScheduleResponse(schedules))
                .build();
    }

    List<ScheduleResponseDto> schedulesToScheduleResponse(List<Schedule> schedules);
}