package company.board_project.domain.schedule.mapper;

import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.match.entity.Match;
import company.board_project.domain.match.match.repository.MatchRepository;
import company.board_project.domain.schedule.dto.ScheduleListDto;
import company.board_project.domain.schedule.dto.SchedulePostDto;
import company.board_project.domain.schedule.dto.ScheduleResponseDto;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule schedulePostDtoToSchedule(SchedulePostDto requestBody);

    default ScheduleResponseDto scheduleToScheduleResponse(Schedule schedule, MatchRepository matchRepository){
        User user = schedule.getUser();
        Team team = schedule.getTeam();
        League league = schedule.getLeague();
        List<Match> matchs = matchRepository.findByScheduleId(schedule.getScheduleId());

        return ScheduleResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .matchList(matchs)
                .build();
    }

    default ScheduleListDto scheduleListDtoToScheduleResponse(List<Schedule> schedules, MatchRepository matchRepository){

        return ScheduleListDto.builder()
                .scheduleResponstList(schedulesToSchedulesResponse(schedules, matchRepository))
                .build();
    }

    default List<ScheduleResponseDto> schedulesToSchedulesResponse(List<Schedule> schedules, MatchRepository matchRepository){
        return schedules.stream()
                .map(schedule -> ScheduleResponseDto.builder()
                        .userId(schedule.getUser().getUserId())
                        .matchList(matchRepository.findByScheduleId(schedule.getScheduleId()))
                        .build())
                .collect(Collectors.toList());
    }
}


