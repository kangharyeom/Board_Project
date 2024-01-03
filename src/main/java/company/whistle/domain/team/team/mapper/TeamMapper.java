package company.whistle.domain.team.team.mapper;

import company.whistle.domain.team.team.dto.*;
import company.whistle.domain.team.team.entity.Team;
import company.whistle.domain.team.team.dto.SquadResponseDto;
import company.whistle.domain.team.squad.entity.Squad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team teamPostDtoToTeam(TeamPostDto requestBody);
    Squad squadPostDtoToSquad(TeamPostDto requestBody);

    Team teamPatchDtoToTeam(TeamPatchDto requestBody);

    @Mapping(source = "user.userId", target = "userId")
    TeamResponseDto teamToTeamResponseDto(Team responseDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "team.teamId", target = "teamId")
    SquadResponseDto squadToSquadResponse(Squad squad);

    default TeamListDto teamListDtoToTeamResponse(List<Team> teams){

        return TeamListDto.builder()
                .teamResponseDtoList(teamsToTeamResponse(teams))
                .build();
    }

    List<TeamResponseDto> teamsToTeamResponse(List<Team> teams);
}
