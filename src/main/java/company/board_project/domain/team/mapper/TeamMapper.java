package company.board_project.domain.team.mapper;

import company.board_project.domain.team.dto.TeamPatchDto;
import company.board_project.domain.team.dto.TeamPostDto;
import company.board_project.domain.team.dto.TeamResponseDto;
import company.board_project.global.constant.*;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.team.dto.TeamListDto;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    default Team teamPostDtoToTeam(TeamPostDto requestBody){
        User user = new User();
        user.setName(requestBody.getManagerName());

        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setUser(user);
        team.setChampionCount(requestBody.getChampionCount());
        team.setMemberCount(requestBody.getMemberCount());
        team.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        team.setLeagueMatchCount(requestBody.getLeagueMatchCount());
        team.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        team.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        team.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        team.setTotalMatchCount(requestBody.getTotalMatchCount());
        team.setTotalWinRecord(requestBody.getTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getTotalLoseRecord());
        team.setHonorScore(requestBody.getHonorScore());
        team.setMostGoals(requestBody.getMostGoals());
        team.setMostAssist(requestBody.getMostAssist());
        team.setMostMom(requestBody.getMostMom());
        team.setTeamName(requestBody.getTeamName());
        team.setIntroduction(requestBody.getIntroduction());
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        team.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        team.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        team.setFormation(Formation.valueOf(requestBody.getFormation()));
        team.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        team.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        team.setManagerName(requestBody.getManagerName());
        team.setLeagueName(requestBody.getLeagueName());
        team.setSubManagerName(requestBody.getSubManagerName());

        return team;
    }
    default Team teamPatchDtoToTeam (TeamPatchDto requestBody) {
        Team team = new Team();

        team.setChampionCount(requestBody.getChampionCount());
        team.setMemberCount(requestBody.getMemberCount());
        team.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        team.setLeagueMatchCount(requestBody.getLeagueMatchCount());
        team.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        team.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        team.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        team.setTotalMatchCount(requestBody.getTotalMatchCount());
        team.setTotalWinRecord(requestBody.getTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getTotalLoseRecord());
        team.setHonorScore(requestBody.getHonorScore());
        team.setMostGoals(requestBody.getMostGoals());
        team.setMostAssist(requestBody.getMostAssist());
        team.setMostMom(requestBody.getMostMom());
        team.setIntroduction(requestBody.getIntroduction());
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        team.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        team.setFormation(Formation.valueOf(requestBody.getFormation()));
        team.setManagerName(requestBody.getManagerName());
        team.setLeagueName(requestBody.getLeagueName());
        team.setSubManagerName(requestBody.getSubManagerName());
        team.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        return team;
    }

    default TeamResponseDto teamToTeamResponseDto(Team team){
        List<Schedule> schedules = new ArrayList<>();

        User user = team.getUser();
        List<Apply> applies = new ArrayList<>();

        return TeamResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .honorScore(team.getHonorScore())
                .championCount(team.getChampionCount())
                .memberCount(team.getMemberCount())
                .leagueMatchPoints(team.getLeagueMatchPoints())
                .leagueMatchCount(team.getLeagueMatchCount())
                .leagueWinRecord(team.getLeagueWinRecord())
                .leagueDrawRecord(team.getLeagueDrawRecord())
                .leagueLoseRecord(team.getLeagueLoseRecord())
                .totalMatchCount(team.getTotalMatchCount())
                .totalWinRecord(team.getTotalWinRecord())
                .totalDrawRecord(team.getTotalDrawRecord())
                .totalLoseRecord(team.getTotalLoseRecord())
                .honorScore(team.getHonorScore())
                .mostGoals(team.getMostGoals())
                .mostAssist(team.getMostAssist())
                .mostMom(team.getMostMom())
                .teamName(team.getTeamName())
                .applies(applies)
                .scheduleList(schedules)
                .leagueWinRecord(team.getLeagueWinRecord())
                .leagueDrawRecord(team.getLeagueDrawRecord())
                .leagueLoseRecord(team.getLeagueLoseRecord())
                .managerName(user.getName())
                .leagueName(team.getLeagueName())
                .sportsType(String.valueOf(team.getSportsType()))
                .ageType(String.valueOf(team.getAgeType()))
                .locationType(String.valueOf(team.getLocationType()))
                .levelType(String.valueOf(team.getLevelType()))
                .formation(String.valueOf(team.getFormation()))
                .uniformType(String.valueOf(team.getUniformType()))
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
                        .championCount(team.getChampionCount())
                        .memberCount(team.getMemberCount())
                        .leagueMatchPoints(team.getLeagueMatchPoints())
                        .leagueMatchCount(team.getLeagueMatchCount())
                        .leagueWinRecord(team.getLeagueWinRecord())
                        .leagueDrawRecord(team.getLeagueDrawRecord())
                        .leagueLoseRecord(team.getLeagueLoseRecord())
                        .totalMatchCount(team.getTotalMatchCount())
                        .totalWinRecord(team.getTotalWinRecord())
                        .totalDrawRecord(team.getTotalDrawRecord())
                        .totalLoseRecord(team.getTotalLoseRecord())
                        .leagueName(team.getLeagueName())
                        .honorScore(team.getHonorScore())
                        .mostGoals(team.getMostGoals())
                        .mostAssist(team.getMostAssist())
                        .mostMom(team.getMostMom())
                        .teamName(team.getTeamName())
                        .sportsType(String.valueOf(team.getSportsType()))
                        .ageType(String.valueOf(team.getAgeType()))
                        .locationType(String.valueOf(team.getLocationType()))
                        .levelType(String.valueOf(team.getLevelType()))
                        .formation(String.valueOf(team.getFormation()))
                        .introduction(team.getIntroduction())
                        .frequency(String.valueOf(team.getFrequency()))
                        .uniformType(String.valueOf(team.getUniformType()))
                        .createdAt(team.getCreatedAt())
                        .modifiedAt(team.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }

}
