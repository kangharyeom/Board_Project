package company.board_project.domain.team.mapper;

import company.board_project.domain.apply.team.entity.TeamApply;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.team.dto.TeamPatchDto;
import company.board_project.domain.team.dto.TeamPostDto;
import company.board_project.domain.team.dto.TeamResponseDto;
import company.board_project.domain.team.dto.TeamListDto;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team teamPostDtoToTeam(TeamPostDto requestBody);

    Team teamPatchDtoToTeam(TeamPatchDto requestBody);

    default TeamResponseDto teamToTeamResponseDto(Team team){
        List<Schedule> schedules = new ArrayList<>();

        User user = team.getUser();
        List<TeamApply> teamApplies = new ArrayList<>();

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
                .teamApplies(teamApplies)
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

    List<TeamResponseDto> teamsToTeamResponse(List<Team> teams);
}
