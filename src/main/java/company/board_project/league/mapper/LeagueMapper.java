package company.board_project.league.mapper;

import company.board_project.constant.*;
import company.board_project.content.entity.Content;
import company.board_project.league.dto.LeagueListDto;
import company.board_project.league.dto.LeaguePatchDto;
import company.board_project.league.dto.LeaguePostDto;
import company.board_project.league.dto.LeagueResponseDto;
import company.board_project.league.entity.League;
import company.board_project.match.entity.Match;
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
        user.setName(requestBody.getLeagueManagerName());

        Team team = new Team();
        team.setHonorScore(requestBody.getHonorScore());
        team.setTeamId(requestBody.getTeamId());

        List<Content> contents = new ArrayList<>();

        League league = new League();
        league.setTeam(team);
        league.setUser(user);
        league.setContents(contents);
        league.setHonorScore(requestBody.getHonorScore());
        league.setMatchCount(requestBody.getMatchCount());
        league.setTeamCount(requestBody.getTeamCount());
        league.setWinPoints(requestBody.getWinPoints());
        league.setLeagueName(requestBody.getLeagueName());
        league.setLeagueManagerName(requestBody.getLeagueManagerName());
        league.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        league.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        league.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        league.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        league.setPeriod(requestBody.getPeriod());
        league.setLeagueRules(requestBody.getLeagueRules());
        league.setFrequency(Frequency.valueOf(requestBody.getFrequency()));

        return league;
    }
    default League leaguePatchDtoToLeague(LeaguePatchDto requestBody) {
        League league = new League();

        league.setLeagueId( requestBody.getLeagueId() );
        league.setMatchCount(requestBody.getMatchCount());
        league.setTeamCount(requestBody.getTeamCount());
        league.setHonorScore(requestBody.getHonorScore());
        league.setWinPoints(requestBody.getWinPoints());
        league.setLeagueName(requestBody.getLeagueName());
        league.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        league.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        league.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        league.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        league.setPeriod(requestBody.getPeriod());
        league.setLeagueRules(requestBody.getLeagueRules());
        league.setFrequency(Frequency.valueOf(requestBody.getFrequency()));

        return league;
    }

    default LeagueResponseDto leagueToLeagueResponse(League league){
        User user = league.getUser();
        List<Content> contents = league.getContents();
        Team team = league.getTeam();
        List<Match> mathchs = league.getMatchs();

        return LeagueResponseDto.builder()
                .leagueId(league.getLeagueId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .contents(contents)
                .matchs(mathchs)
                .matchCount(league.getMatchCount())
                .teamCount(league.getTeamCount())
                .honorScore(league.getHonorScore())
                .winPoints(league.getWinPoints())
                .leagueName(league.getLeagueName())
                .leagueManagerName(user.getName())
                .sportsType(String.valueOf(league.getSportsType()))
                .ageType(String.valueOf(league.getAgeType()))
                .locationType(String.valueOf(league.getLocationType()))
                .period(league.getPeriod())
                .levelType(String.valueOf(league.getLevelType()))
                .leagueRules(league.getLeagueRules())
                .frequency(String.valueOf(league.getFrequency()))
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
                        .leagueManagerName(league.getUser().getName())
                        .teamId(league.getTeam().getTeamId())
                        .honorScore(league.getHonorScore())
                        .winPoints(league.getWinPoints())
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
                        .createdAt(league.getCreatedAt())
                        .modifiedAt(league.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
