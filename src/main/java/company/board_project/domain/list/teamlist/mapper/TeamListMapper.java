package company.board_project.domain.list.teamlist.mapper;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.list.teamlist.dto.TeamListPatchDto;
import company.board_project.domain.list.teamlist.dto.TeamListPostDto;
import company.board_project.domain.list.teamlist.dto.TeamListResponseDto;
import company.board_project.domain.list.teamlist.dto.TeamListResponseListDto;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamListMapper {
    TeamList teamListPostDtoToTeamList(TeamListPostDto requestBody);

    TeamList teamListPatchDtoToTeamList(TeamListPatchDto requestBody);

    default TeamListResponseDto teamListToTeamListResponse(TeamList teamList){

        User user = teamList.getUser();
        Team team = teamList.getTeam();
        Apply apply = teamList.getApply();

        return TeamListResponseDto.builder()
                .userId(user.getUserId())
                .teamListId(teamList.getTeamListId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
//                .mostGoals(teamList.getMostGoals())
//                .mostAssist(teamList.getMostAssist())
//                .mostMom(teamList.getMostMom())
                .name(teamList.getName())
                .position(String.valueOf(teamList.getPosition()))
                .ageType(String.valueOf(teamList.getAgeType()))
                .teamMemberType(String.valueOf(teamList.getTeamMemberRole()))
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

    List<TeamListResponseDto> teamListsToTeamListResponse(List<TeamList> teamLists);
}