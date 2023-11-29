package company.board_project.domain.apply.team.mapper;

import company.board_project.domain.apply.team.dto.*;
import company.board_project.domain.apply.team.entity.TeamApply;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamApplyMapper {
    TeamApply applyPostDtoToTeamApply(TeamApplyPostDto requestBody);

    default TeamApplyResponseDto teamApplyToTeamApplyResponse(TeamApply teamApply){

        User user = teamApply.getUser();
        Team team = teamApply.getTeam();

        return TeamApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .teamApplyId(teamApply.getTeamApplyId())
                .teamName(teamApply.getTeamName())
                .createdAt(teamApply.getCreatedAt())
                .modifiedAt(teamApply.getModifiedAt())
                .build();
    }

    default TeamApplyResponseDto applyToTeamApplyResponse(TeamApply teamApply){

        User user = teamApply.getUser();
        Team team = teamApply.getTeam();

        return TeamApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .teamApplyId(teamApply.getTeamApplyId())
                .managerName(teamApply.getManagerName())
                .teamName(teamApply.getTeamName())
                .ageType(String.valueOf(teamApply.getAgeType()))
                .levelType(String.valueOf(teamApply.getLevelType()))
                .applyType(String.valueOf(teamApply.getApplyType()))
                .createdAt(teamApply.getCreatedAt())
                .modifiedAt(teamApply.getModifiedAt())
                .build();
    }

    default TeamApplyListDto teamApplyListDtoToTeamApplyResponse(List<TeamApply> teamApplies){

        return TeamApplyListDto.builder()
                .teamApplyResponseDtoList(teamAppliesToTeamApplyResponse(teamApplies))
                .build();
    }

    default List<TeamApplyResponseDto> teamAppliesToTeamApplyResponse(List<TeamApply> teamApplies){
        return teamApplies.stream()
                .map(teamApply -> TeamApplyResponseDto.builder()
                        .teamApplyId(teamApply.getTeamApplyId())
                        .teamName(teamApply.getTeamName())
                        .managerName(teamApply.getManagerName())
                        .applyType(String.valueOf(teamApply.getApplyType()))
                        .levelType(String.valueOf(teamApply.getLevelType()))
                        .ageType(String.valueOf(teamApply.getAgeType()))
                        .createdAt(teamApply.getCreatedAt())
                        .modifiedAt(teamApply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

