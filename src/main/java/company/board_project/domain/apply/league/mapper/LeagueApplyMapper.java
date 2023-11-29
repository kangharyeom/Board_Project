package company.board_project.domain.apply.league.mapper;

import company.board_project.domain.apply.league.dto.*;
import company.board_project.domain.apply.league.entity.LeagueApply;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueApplyMapper {

    LeagueApply leagueApplyPostDtoToLeagueApply(LeagueApplyPostDto requestBody);

    default LeagueApplyResponseDto leagueApplyToLeagueApplyResponse(LeagueApply leagueApply){

        User user = leagueApply.getUser();
        Team team = leagueApply.getTeam();
        League league = leagueApply.getLeague();

        return LeagueApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .leagueApplyId(leagueApply.getLeagueApplyId())
                .teamName(leagueApply.getTeamName())
                .managerName(leagueApply.getManagerName())
                .levelType(String.valueOf(leagueApply.getLevelType()))
                .ageType(String.valueOf(leagueApply.getAgeType()))
                .applyType(String.valueOf(leagueApply.getApplyType()))
                .createdAt(leagueApply.getCreatedAt())
                .modifiedAt(leagueApply.getModifiedAt())
                .build();
    }

    default LeagueApplyListDto leagueApplyListDtoToLeagueApplyResponse(List<LeagueApply> leagueApplies){

        return LeagueApplyListDto.builder()
                .leagueApplyResponseDtoList(leagueAppliesToLeagueApplyResponse(leagueApplies))
                .build();
    }

    default List<LeagueApplyResponseDto> leagueAppliesToLeagueApplyResponse(List<LeagueApply> applies){
        return applies.stream()
                .map(leagueApply -> LeagueApplyResponseDto.builder()
                        .leagueApplyId(leagueApply.getLeagueApplyId())
                        .teamName(leagueApply.getTeamName())
                        .managerName(leagueApply.getManagerName())
                        .applyType(String.valueOf(leagueApply.getApplyType()))
                        .levelType(String.valueOf(leagueApply.getLevelType()))
                        .ageType(String.valueOf(leagueApply.getAgeType()))
                        .createdAt(leagueApply.getCreatedAt())
                        .modifiedAt(leagueApply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

