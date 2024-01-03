package company.whistle.domain.apply.leagueApply.mapper;

import company.whistle.domain.apply.leagueApply.dto.*;
import company.whistle.domain.apply.leagueApply.entity.LeagueApply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueApplyMapper {
    LeagueApply leagueApplyPostDtoToLeagueApply(LeagueApplyPostDto requestBody);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "team.teamId", target = "teamId")
    @Mapping(source = "league.leagueId", target = "leagueId")
    LeagueApplyResponseDto leagueApplyToLeagueApplyResponse(LeagueApply leagueApply);

    default LeagueApplyListDto leagueApplyListDtoToLeagueApplyResponse(List<LeagueApply> leagueApplies){
        return LeagueApplyListDto.builder()
                .leagueApplyResponseDtoList(leagueAppliesToLeagueApplyResponse(leagueApplies))
                .build();
    }

//     List<LeagueApplyResponseDto> leagueAppliesToLeagueApplyResponse(List<LeagueApply> applies){
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

