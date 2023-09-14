package company.board_project.team.mapper;

import company.board_project.constant.*;
import company.board_project.league.dto.LeagueListDto;
import company.board_project.league.dto.LeagueResponseDto;
import company.board_project.league.entity.League;
import company.board_project.league.repository.LeagueRepository;
import company.board_project.match.entity.Match;
import company.board_project.schedule.entity.Schedule;
import company.board_project.suggestionlist.Entity.SuggestionList;
import company.board_project.suggestionlist.Repository.SuggestionListRepository;
import company.board_project.team.dto.TeamListDto;
import company.board_project.team.dto.TeamPatchDto;
import company.board_project.team.dto.TeamPostDto;
import company.board_project.team.dto.TeamResponseDto;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    default Team teamPostDtoToTeam(TeamPostDto requestBody, LeagueRepository leagueRepository){
        User user = new User();
        Match mathch = new Match();
        League league = new League();

        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setUser(user);
        team.setChampionCount(requestBody.getChampionCount());
        team.setMemberCount(requestBody.getMemberCount());
        team.setLeagueWinRecord(league.getLeagueWinRecord());
        team.setLeagueDrawRecord(league.getLeagueDrawRecord());
        team.setLeagueLoseRecord(league.getLeagueLoseRecord());
        team.setTotalWinRecord(requestBody.getTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getTotalLoseRecord());
        team.setHonorScore(requestBody.getHonorScore());
        team.setRanking(requestBody.getRanking());
        team.setMostGoals(requestBody.getMostGoals());
        team.setMostAssist(requestBody.getMostAssist());
        team.setMostMom(requestBody.getMostMom());
        team.setTeamName(requestBody.getTeamName());
        team.setIntroduction(requestBody.getIntroduction());
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        team.setManagerName(user.getName());
        team.setSubManagerName(requestBody.getSubManagerName());
        team.setUniform(UniformType.valueOf(requestBody.getUniform()));

        return team;
    }
    default Team teamPatchDtoToTeam (TeamPatchDto requestBody) {
        Team team = new Team();

        team.setChampionCount(requestBody.getChampionCount());
        team.setMemberCount(requestBody.getMemberCount());
        team.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        team.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        team.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        team.setTotalWinRecord(requestBody.getTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getTotalLoseRecord());
        team.setHonorScore(requestBody.getHonorScore());
        team.setRanking(requestBody.getRanking());
        team.setMostGoals(requestBody.getMostGoals());
        team.setMostAssist(requestBody.getMostAssist());
        team.setMostMom(requestBody.getMostMom());
        team.setIntroduction(requestBody.getIntroduction());
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        team.setManagerName(requestBody.getManagerName());
        team.setSubManagerName(requestBody.getSubManagerName());
        team.setUniform(UniformType.valueOf(requestBody.getUniform()));

        return team;
    }

    default TeamResponseDto teamToTeamResponseDto(Team team, SuggestionListRepository suggestionListRepository){
        List<Schedule> schedules = new ArrayList<>();

        User user = team.getUser();
        Match mathch = team.getMatch();
        League league = team.getLeague();
        List<SuggestionList> suggestionLists = new ArrayList<>();

        return TeamResponseDto.builder()
                .leagueId(league.getLeagueId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .matchId(mathch.getMatchId())
                .suggestionLists(suggestionLists)
                .scheduleList(schedules)
                .managerName(user.getName())
                .sportType(String.valueOf(team.getSportType()))
                .ageType(String.valueOf(team.getAgeType()))
                .locationType(String.valueOf(team.getLocationType()))
                .levelType(String.valueOf(team.getLevelType()))
                .introduction(team.getIntroduction())
                .frequency(String.valueOf(team.getFrequency()))
                .createdAt(team.getCreatedAt())
                .modifiedAt(team.getModifiedAt())
                .build();
    }

    default TeamListDto teamListDtoToTeamResponse(List<Team> teams){

        return TeamListDto.builder()
                .teamResponseDtoList(teamsToTeamResponse(teams))
                .build();
    }

    default List<TeamResponseDto> teamsToTeamResponse(List<Team> teams){
        return teams.stream()
                .map(team -> TeamResponseDto.builder()
                        .teamId(team.getTeamId())
                        .managerName(team.getManagerName())
                        .sportType(String.valueOf(team.getSportType()))
                        .ageType(String.valueOf(team.getAgeType()))
                        .locationType(String.valueOf(team.getLocationType()))
                        .levelType(String.valueOf(team.getLevelType()))
                        .introduction(team.getIntroduction())
                        .frequency(String.valueOf(team.getFrequency()))
                        .createdAt(team.getCreatedAt())
                        .modifiedAt(team.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
