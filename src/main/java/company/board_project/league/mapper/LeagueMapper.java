package company.board_project.league.mapper;

import company.board_project.constant.*;
import company.board_project.content.entity.Content;
import company.board_project.league.dto.LeagueListDto;
import company.board_project.league.dto.LeaguePatchDto;
import company.board_project.league.dto.LeaguePostDto;
import company.board_project.league.dto.LeagueResponseDto;
import company.board_project.league.entity.League;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    default League leaguePostDtoToLeague(LeaguePostDto requestBody){
        User user = new User();

        user.setUserId(requestBody.getUserId());
        user.setName(requestBody.getManagerName());

        Team team = new Team();
        team.setHonorScore(requestBody.getHonorScore());
        team.setTeamId(requestBody.getTeamId());
        team.setMemberCount(requestBody.getMemberCount());

        List<Content> contents = new ArrayList<>();
        League league = new League();
        league.setTeam(team);
        league.setUser(user);
        league.setContents(contents);
        league.setLeagueEndCount(requestBody.getLeagueEndCount());
        league.setMemberCount(requestBody.getMemberCount());
        league.setHonorScore(requestBody.getHonorScore());
        league.setMemberCount(requestBody.getMemberCount());
        league.setMatchCount(requestBody.getMatchCount());
        league.setTeamCount(requestBody.getTeamCount());
        league.setLeagueName(requestBody.getLeagueName());
        league.setManagerName(requestBody.getManagerName());
        league.setManagerTeamName(requestBody.getManagerTeamName());
        league.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        league.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        league.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        league.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        league.setPeriod(requestBody.getPeriod());
        league.setLeagueRules(requestBody.getLeagueRules());
        league.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        league.setSeasonType(SeasonType.valueOf(requestBody.getSeasonType()));

        return league;
    }

    default League leaguePatchDtoToLeague(LeaguePatchDto requestBody) {
        League league = new League();

        league.setLeagueId( requestBody.getLeagueId() );
        league.setMatchCount(requestBody.getMatchCount());
        league.setMemberCount(requestBody.getMemberCount());
        league.setTeamCount(requestBody.getTeamCount());
        league.setMemberCount(requestBody.getMemberCount());
        league.setHonorScore(requestBody.getHonorScore());
        league.setLeagueName(requestBody.getLeagueName());
        league.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        league.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        league.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        league.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        league.setPeriod(requestBody.getPeriod());
        league.setLeagueRules(requestBody.getLeagueRules());
        league.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        league.setSeasonType(SeasonType.valueOf(requestBody.getSeasonType()));

        return league;
    }

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

    default List<LeagueResponseDto> leaguesToLeagueResponse(List<League> leagues){
        return leagues.stream()
                .map(league -> LeagueResponseDto.builder()
                        .leagueId(league.getLeagueId())
                        .userId(league.getUser().getUserId())
                        .managerName(league.getManagerName())
                        .managerTeamName(league.getManagerTeamName())
                        .teamId(league.getTeam().getTeamId())
                        .teamId(league.getTeam().getTeamId())
                        .memberCount(league.getMemberCount())
                        .honorScore(league.getHonorScore())
                        .memberCount(league.getMemberCount())
                        .matchCount(league.getMatchCount())
                        .teamCount(league.getTeamCount())
                        .leagueName(league.getLeagueName())
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
                        .build())
                .collect(Collectors.toList());
    }
}
