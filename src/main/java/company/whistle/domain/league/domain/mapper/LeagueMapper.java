package company.whistle.domain.league.domain.mapper;

import company.whistle.domain.board.content.entity.Content;
import company.whistle.domain.league.domain.entity.League;
import company.whistle.domain.league.domain.dto.LeagueListDto;
import company.whistle.domain.league.domain.dto.LeaguePatchDto;
import company.whistle.domain.league.domain.dto.LeaguePostDto;
import company.whistle.domain.league.domain.dto.LeagueResponseDto;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    League leaguePostDtoToLeague(LeaguePostDto requestBody);

    League leaguePatchDtoToLeague(LeaguePatchDto requestBody);

    default LeagueResponseDto leagueToLeagueResponse(League league){
        User user = league.getUser();
        List<Content> contents = league.getContents();
        Team team = league.getTeam();

        return LeagueResponseDto.builder()
                .leagueId(league.getLeagueId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .contents(contents)
                .leagueEndCount(league.getLeagueEndCount())
                .memberCount(league.getMemberCount())
                .matchCount(league.getMatchCount())
                .teamCount(league.getTeamCount())
                .honorScore(league.getHonorScore())
                .leagueName(league.getLeagueName())
                .managerName(league.getManagerName())
                .managerTeamName(league.getManagerTeamName())
                .sportsType(String.valueOf(league.getSportsType()))
                .ageType(String.valueOf(league.getAgeType()))
                .locationType(String.valueOf(league.getLocationType()))
                .period(league.getPeriod())
                .levelType(String.valueOf(league.getLevelType()))
                .leagueRules(league.getLeagueRules())
                .frequency(String.valueOf(league.getFrequency()))
                .seasonType(String.valueOf(league.getSeasonType()))
                .createdAt(league.getCreatedAt())
                .modifiedAt(league.getModifiedAt())
                .build();
    }

    default LeagueListDto leagueListDtoToLeagueResponse(List<League> leagues){

        return LeagueListDto.builder()
                .leagueResponseDto(leaguesToLeagueResponse(leagues))
                .build();
    }

    List<LeagueResponseDto> leaguesToLeagueResponse(List<League> leagues);
}
