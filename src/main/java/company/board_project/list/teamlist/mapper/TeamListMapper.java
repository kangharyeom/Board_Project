package company.board_project.list.teamlist.mapper;

import company.board_project.apply.entity.Apply;
import company.board_project.constant.*;
import company.board_project.list.teamlist.dto.*;
import company.board_project.list.teamlist.entity.TeamList;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamListMapper {
    default TeamList teamListPostDtoToTeamList(TeamListPostDto requestBody){
        User user = new User();
        user.setPosition(user.getPosition());

        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        TeamList teamList = new TeamList();
        teamList.setUser(user);
        teamList.setApply(apply);
        teamList.setPosition(Position.valueOf(requestBody.getPosition()));
        teamList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        teamList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        teamList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        teamList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        teamList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        teamList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        teamList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        /*  teamList.setMostGoals(requestBody.getMostGoals());
        teamList.setMostAssist(requestBody.getMostAssist());
        teamList.setMostMom(requestBody.getMostMom());*/

        return teamList;
    }

    default TeamList teamListPatchDtoToTeamList (TeamListPatchDto requestBody) {
        TeamList teamList = new TeamList();
        teamList.setPosition(Position.valueOf(requestBody.getPosition()));
        teamList.setMemberCount(requestBody.getMemberCount());
        teamList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        teamList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        teamList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        teamList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        teamList.setHonorScore(requestBody.getHonorScore());
        teamList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        teamList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        teamList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        teamList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        teamList.setSubManagerName(requestBody.getSubManagerName());

        /*  teamList.setMostGoals(requestBody.getMostGoals());
        teamList.setMostAssist(requestBody.getMostAssist());
        teamList.setMostMom(requestBody.getMostMom());*/

        return teamList;
    }

    default TeamListResponseDto teamListToTeamListResponse(TeamList teamList){

        User user = teamList.getUser();
        Team team = teamList.getTeam();
        Apply apply = teamList.getApply();

        return TeamListResponseDto.builder()
                .userId(user.getUserId())
                .teamListId(teamList.getTeamListId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .memberCount(teamList.getMemberCount())
                .leagueMatchPoints(teamList.getLeagueMatchPoints())
                .leagueWinRecord(teamList.getLeagueWinRecord())
                .leagueDrawRecord(teamList.getLeagueDrawRecord())
                .leagueLoseRecord(teamList.getLeagueLoseRecord())
                .honorScore(teamList.getHonorScore())
                .ranking(teamList.getRanking())
               /* .mostGoals(teamList.getMostGoals())
                .mostAssist(teamList.getMostAssist())
                .mostMom(teamList.getMostMom())*/
                .leagueWinRecord(teamList.getLeagueWinRecord())
                .leagueDrawRecord(teamList.getLeagueDrawRecord())
                .leagueLoseRecord(teamList.getLeagueLoseRecord())
                .managerName(user.getName())
                .teamName(teamList.getTeamName())
                .ageType(String.valueOf(teamList.getAgeType()))
                .locationType(String.valueOf(teamList.getLocationType()))
                .levelType(String.valueOf(teamList.getLevelType()))
                .frequency(String.valueOf(teamList.getFrequency()))
                .createdAt(teamList.getCreatedAt())
                .modifiedAt(teamList.getModifiedAt())
                .build();
    }

    default TeamListResponseListDto teamListDtoToTeamListResponse(List<TeamList> teamLists){

        return TeamListResponseListDto.builder()
                .teamListResponseDtoList(teamListsToTeamListResponse(teamLists))
                .build();
    }

    default List<TeamListResponseDto> teamListsToTeamListResponse(List<TeamList> teamLists){
        return teamLists.stream()
                .map(teamList -> TeamListResponseDto.builder()
                        .teamListId(teamList.getTeamListId())
                        .managerName(teamList.getManagerName())
                        .memberCount(teamList.getMemberCount())
                        .leagueMatchPoints(teamList.getLeagueMatchPoints())
                        .leagueWinRecord(teamList.getLeagueWinRecord())
                        .leagueDrawRecord(teamList.getLeagueDrawRecord())
                        .leagueLoseRecord(teamList.getLeagueLoseRecord())
                        .teamName(teamList.getTeamName())
                        .honorScore(teamList.getHonorScore())
                        .ranking(teamList.getRanking())
           /*             .mostGoals(teamList.getMostGoals())
                        .mostAssist(teamList.getMostAssist())
                        .mostMom(teamList.getMostMom())*/
                        .ageType(String.valueOf(teamList.getAgeType()))
                        .locationType(String.valueOf(teamList.getLocationType()))
                        .levelType(String.valueOf(teamList.getLevelType()))
                        .frequency(String.valueOf(teamList.getFrequency()))
                        .createdAt(teamList.getCreatedAt())
                        .modifiedAt(teamList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}