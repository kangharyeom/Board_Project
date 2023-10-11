package company.board_project.domain.list.teamlist.mapper;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.list.teamlist.dto.TeamListPatchDto;
import company.board_project.domain.list.teamlist.dto.TeamListPostDto;
import company.board_project.domain.list.teamlist.dto.TeamListResponseDto;
import company.board_project.domain.list.teamlist.dto.TeamListResponseListDto;
import company.board_project.global.constant.*;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamListMapper {
    default TeamList teamListPostDtoToTeamList(TeamListPostDto requestBody){
        User user = new User();
        user.setPosition(user.getPosition());
        user.setName(user.getName());

        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        TeamList teamList = new TeamList();
        teamList.setUser(user);
        teamList.setApply(apply);
        teamList.setName(requestBody.getName());
        teamList.setPosition(Position.valueOf(requestBody.getPosition()));
        teamList.setTeamMemberType(TeamMemberType.valueOf(requestBody.getTeamMemberType()));
        teamList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        teamList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        teamList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        teamList.setFrequency(Frequency.valueOf(requestBody.getFrequency()));

        /*  teamList.setMostGoals(requestBody.getMostGoals());
        teamList.setMostAssist(requestBody.getMostAssist());
        teamList.setMostMom(requestBody.getMostMom());*/

        return teamList;
    }

    default TeamList teamListPatchDtoToTeamList (TeamListPatchDto requestBody) {
        TeamList teamList = new TeamList();
        teamList.setName(requestBody.getName());
        teamList.setPosition(Position.valueOf(requestBody.getPosition()));
        teamList.setTeamMemberType(TeamMemberType.valueOf(requestBody.getTeamMemberType()));
        teamList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        teamList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        teamList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        teamList.setFrequency(Frequency.valueOf(requestBody.getFrequency()));

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
               /* .mostGoals(teamList.getMostGoals())
                .mostAssist(teamList.getMostAssist())
                .mostMom(teamList.getMostMom())*/
                .name(teamList.getName())
                .position(String.valueOf(teamList.getPosition()))
                .ageType(String.valueOf(teamList.getAgeType()))
                .teamMemberType(String.valueOf(teamList.getTeamMemberType()))
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
           /*             .mostGoals(teamList.getMostGoals())
                        .mostAssist(teamList.getMostAssist())
                        .mostMom(teamList.getMostMom())*/
                        .name(teamList.getName())
                        .position(String.valueOf(teamList.getPosition()))
                        .teamMemberType(String.valueOf(teamList.getTeamMemberType()))
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