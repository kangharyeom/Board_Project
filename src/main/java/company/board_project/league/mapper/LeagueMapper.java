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
        user.setName(requestBody.getName());

        List<Content> contents = new ArrayList<>();

        League league = new League();
        league.setUser(user);
        league.setContents(contents);
        league.setMatchCount(requestBody.getMatchCount());
        league.setTeamCount(requestBody.getTeamCount());
        league.setLeagueName(requestBody.getLeagueName());
        league.setParticipantTeamName(requestBody.getParticipantTeamName());
        league.setSportType(SportsType.valueOf(requestBody.getSportType()));
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
        league.setLeagueName(requestBody.getLeagueName());
        league.setParticipantTeamName(requestBody.getParticipantTeamName());
        league.setSportType(SportsType.valueOf(requestBody.getSportType()));
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
                .leagueName(league.getLeagueName())
                .leagueManagerName(user.getName())
                .sportType(String.valueOf(league.getSportType()))
                .ageType(String.valueOf(league.getAgeType()))
                .locationType(String.valueOf(league.getLocationType()))
                .period(league.getPeriod())
                .levelType(String.valueOf(league.getLevelType()))
                .leagueRules(league.getLeagueRules())
                .frequency(String.valueOf(league.getFrequency()))
                .participantTeamName(league.getParticipantTeamName())
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
                        .matchCount(league.getMatchCount())
                        .teamCount(league.getTeamCount())
                        .leagueName(league.getLeagueName())
                        .sportType(String.valueOf(league.getSportType()))
                        .ageType(String.valueOf(league.getAgeType()))
                        .locationType(String.valueOf(league.getLocationType()))
                        .period(league.getPeriod())
                        .levelType(String.valueOf(league.getLevelType()))
                        .leagueRules(league.getLeagueRules())
                        .frequency(String.valueOf(league.getFrequency()))
                        .participantTeamName(league.getParticipantTeamName())
                        .createdAt(league.getCreatedAt())
                        .modifiedAt(league.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
