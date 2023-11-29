package company.board_project.domain.list.teammemberlist.mapper;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.list.teammemberlist.dto.TeamMemberListPatchDto;
import company.board_project.domain.list.teammemberlist.dto.TeamMemberListPostDto;
import company.board_project.domain.list.teammemberlist.dto.TeamMemberListResponseDto;
import company.board_project.domain.list.teammemberlist.dto.TeamMemberListResponseListDto;
import company.board_project.domain.list.teammemberlist.entity.TeamMemberList;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMemberListMapper {
    TeamMemberList teamMemberListPostDtoToTeamMemberList(TeamMemberListPostDto requestBody);

    TeamMemberList teamMemberListPatchDtoToTeamMemberList(TeamMemberListPatchDto requestBody);

    default TeamMemberListResponseDto teamMemberListToTeamMemberListResponse(TeamMemberList teamMemberList){

        User user = teamMemberList.getUser();
        Team team = teamMemberList.getTeam();
        Apply apply = teamMemberList.getApply();

        return TeamMemberListResponseDto.builder()
                .userId(user.getUserId())
                .teamMemberListId(teamMemberList.getTeamMemberListId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
//                .mostGoals(teamMemberList.getMostGoals())
//                .mostAssist(teamMemberList.getMostAssist())
//                .mostMom(teamMemberList.getMostMom())
                .name(teamMemberList.getName())
                .position(String.valueOf(teamMemberList.getPosition()))
                .ageType(String.valueOf(teamMemberList.getAgeType()))
                .teamMemberType(String.valueOf(teamMemberList.getTeamMemberRole()))
                .locationType(String.valueOf(teamMemberList.getLocationType()))
                .levelType(String.valueOf(teamMemberList.getLevelType()))
                .frequency(String.valueOf(teamMemberList.getFrequency()))
                .createdAt(teamMemberList.getCreatedAt())
                .modifiedAt(teamMemberList.getModifiedAt())
                .build();
    }

    default TeamMemberListResponseListDto teamMemberListDtoToTeamMemberListResponse(List<TeamMemberList> teamMemberLists){

        return TeamMemberListResponseListDto.builder()
                .teamListResponseDtoMemberList(teamMemberListsToTeamMemberListResponse(teamMemberLists))
                .build();
    }

    List<TeamMemberListResponseDto> teamMemberListsToTeamMemberListResponse(List<TeamMemberList> teamMemberLists);
}