package company.board_project.domain.apply.match.mapper;

import company.board_project.domain.apply.match.dto.*;
import company.board_project.domain.apply.match.entity.MatchApply;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MatchApplyMapper {

    MatchApply matchApplyPostDtoToMatchApply(MatchApplyPostDto requestBody);

    default MatchApplyResponseDto matchApplyToMatchApplyResponse(MatchApply matchApply){

        User user = matchApply.getUser();
        Team team = matchApply.getTeam();
        Match match = matchApply.getMatch();

        return MatchApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .matchId(match.getMatchId())
                .matchApplyId(matchApply.getMatchApplyId())
                .teamName(matchApply.getTeamName())
                .managerName(matchApply.getManagerName())
                .levelType(String.valueOf(matchApply.getLevelType()))
                .ageType(String.valueOf(matchApply.getAgeType()))
                .applyType(String.valueOf(matchApply.getApplyType()))
                .createdAt(matchApply.getCreatedAt())
                .modifiedAt(matchApply.getModifiedAt())
                .build();
    }

    default MatchApplyListDto matchApplyListDtoToMatchApplyResponse(List<MatchApply> matchApplies){

        return MatchApplyListDto.builder()
                .matchApplyResponseDtoList(matchAppliesToMatchApplyResponse(matchApplies))
                .build();
    }

    default List<MatchApplyResponseDto> matchAppliesToMatchApplyResponse(List<MatchApply> matchApplies){
        return matchApplies.stream()
                .map(matchApply -> MatchApplyResponseDto.builder()
                        .matchApplyId(matchApply.getMatchApplyId())
                        .teamName(matchApply.getTeamName())
                        .managerName(matchApply.getManagerName())
                        .applyType(String.valueOf(matchApply.getApplyType()))
                        .levelType(String.valueOf(matchApply.getLevelType()))
                        .ageType(String.valueOf(matchApply.getAgeType()))
                        .createdAt(matchApply.getCreatedAt())
                        .modifiedAt(matchApply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

