package company.board_project.match.mapper;

import company.board_project.constant.*;
import company.board_project.match.dto.MatchListDto;
import company.board_project.match.dto.MatchPatchDto;
import company.board_project.match.dto.MatchPostDto;
import company.board_project.match.dto.MatchResponseDto;
import company.board_project.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.team.repository.TeamRepository;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    default Match matchPostDtoToMatch(MatchPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getHomeTeamName());

        Match match = new Match();
        match.setTeam(team);
        match.setUser(user);
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportType(SportsType.valueOf(requestBody.getSportType()));
        match.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }
    default Match matchPatchDtoToMatch(MatchPatchDto requestBody) {
        Match match = new Match();

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportType(SportsType.valueOf(requestBody.getSportType()));
        match.setMatchStatus(MatchStatus.valueOf(requestBody.getMatchStatus()));
        match.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf(requestBody.getAwayTeamMatchResultStatus()));
        match.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf(requestBody.getHomeTeamMatchResultStatus()));
        match.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        match.setMatchRules(requestBody.getMatchRules());
        match.setAwayTeamName(requestBody.getAwayTeamName());

        return match;
    }

    default MatchResponseDto matchToMatchResponse(Match match, TeamRepository teamRepository){
        User user = match.getUser();
        List<Team> teams = teamRepository.findByMatchId(match.getMatchId());

        return MatchResponseDto.builder()
                .userId(user.getUserId())
                .homeTeamName(teams.toString())
                .awayTeamName(teams.toString())
                .name(user.getName())
                .sportType(String.valueOf(match.getSportType()))
                .ageType(String.valueOf(match.getAgeType()))
                .locationType(String.valueOf(match.getLocationType()))
                .levelType(String.valueOf(match.getLevelType()))
                .matchStatus(String.valueOf(match.getMatchStatus()))
                .awayTeamMatchResultStatus(String.valueOf(match.getAwayTeamMatchResultStatus()))
                .homeTeamMatchResultStatus(String.valueOf(match.getHomeTeamMatchResultStatus()))
                .createdAt(match.getCreatedAt())
                .modifiedAt(match.getModifiedAt())
                .build();
    }

    default MatchListDto matchListDtoToMatchResponse(List<Match> matchs, TeamRepository teamRepository){

        return MatchListDto.builder()
                .matchResponseDto(matchsToMatchsResponse(matchs, teamRepository))
                .build();
    }

    default List<MatchResponseDto> matchsToMatchsResponse(List<Match> matchs, TeamRepository teamRepository){
        return matchs.stream()
                .map(match -> MatchResponseDto.builder()
                        .userId(match.getUser().getUserId())
                        .name(match.getUser().getName())
                        .teamList(teamRepository.findByMatchId(match.getMatchId()))
                        .teamList(teamRepository.findByMatchId(match.getMatchId()))
                        .sportType(String.valueOf(match.getSportType()))
                        .ageType(String.valueOf(match.getAgeType()))
                        .locationType(String.valueOf(match.getLocationType()))
                        .levelType(String.valueOf(match.getLevelType()))
                        .matchStatus(String.valueOf(match.getMatchStatus()))
                        .awayTeamMatchResultStatus(String.valueOf(match.getAwayTeamMatchResultStatus()))
                        .homeTeamMatchResultStatus(String.valueOf(match.getHomeTeamMatchResultStatus()))
                        .createdAt(match.getCreatedAt())
                        .modifiedAt(match.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
