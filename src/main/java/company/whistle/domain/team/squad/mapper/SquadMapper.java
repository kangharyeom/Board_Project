package company.whistle.domain.team.squad.mapper;

import company.whistle.domain.apply.team.entity.TeamApply;
import company.whistle.domain.team.squad.dto.SquadResponseDto;
import company.whistle.domain.team.squad.dto.SquadPatchDto;
import company.whistle.domain.team.squad.dto.SquadPostDto;
import company.whistle.domain.team.squad.dto.SquadResponseListDto;
import company.whistle.domain.team.squad.entity.Squad;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SquadMapper {
    Squad squadPostDtoToSquad(SquadPostDto requestBody);

    Squad squadPatchDtoToSquad(SquadPatchDto requestBody);

    default SquadResponseDto squadToSquadResponse(Squad squad){

        User user = squad.getUser();
        Team team = squad.getTeam();
        TeamApply teamApply = squad.getTeamApply();

        return SquadResponseDto.builder()
                .userId(user.getUserId())
                .squadId(squad.getSquadId())
                .teamId(team.getTeamId())
                .teamApplyId(teamApply.getTeamApplyId())
//                .mostGoals(squad.getMostGoals())
//                .mostAssist(squad.getMostAssist())
//                .mostMom(squad.getMostMom())
                .name(squad.getName())
                .position(String.valueOf(squad.getPosition()))
                .ageType(String.valueOf(squad.getAgeType()))
                .teamMemberType(String.valueOf(squad.getTeamMemberRole()))
                .locationType(String.valueOf(squad.getLocationType()))
                .levelType(String.valueOf(squad.getLevelType()))
                .frequency(String.valueOf(squad.getFrequency()))
                .createdAt(squad.getCreatedAt())
                .modifiedAt(squad.getModifiedAt())
                .build();
    }

    default SquadResponseListDto squadDtoToSquadResponse(List<Squad> squads){

        return SquadResponseListDto.builder()
                .squadResponseDtoList(squadsToSquadResponse(squads))
                .build();
    }

    List<SquadResponseDto> squadsToSquadResponse(List<Squad> squads);
}