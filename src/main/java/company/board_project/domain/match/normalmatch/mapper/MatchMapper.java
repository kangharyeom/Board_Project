package company.board_project.domain.match.normalmatch.mapper;

import company.board_project.global.constant.*;
import company.board_project.domain.match.normalmatch.dto.MatchListDto;
import company.board_project.domain.match.normalmatch.dto.MatchPatchDto;
import company.board_project.domain.match.normalmatch.dto.MatchPostDto;
import company.board_project.domain.match.normalmatch.dto.MatchResponseDto;
import company.board_project.domain.match.normalmatch.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
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

        Match match = new Match();
        match.setTeam(team);
        match.setUser(user);

        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }

    default Match matchPatchDtoToMatch(MatchPatchDto requestBody) {
        Match match = new Match();

        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportsType(SportsType.valueOf(requestBody.getSportsType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }

    default MatchResponseDto matchToMatchResponse(Match match){
        User user = match.getUser();
        Team team = match.getTeam();

        return MatchResponseDto.builder()
                .matchId(match.getMatchId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .homeTeamHonorScore(team.getHonorScore())
                .homeTeamName(team.getTeamName())
                .homeTeamManagerName(team.getManagerName())
                .homeTeamTotalWinRecord(team.getTotalWinRecord())
                .homeTeamTotalDrawRecord(team.getTotalDrawRecord())
                .homeTeamTotalLoseRecord(team.getTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(team.getLevelType()))
                .homeTeamAgeType(String.valueOf(team.getAgeType()))
                .homeTeamUniformType(String.valueOf(team.getUniformType()))
                .sportsType(String.valueOf(match.getSportsType()))
                .locationType(String.valueOf(match.getLocationType()))
                .matchTime(match.getMatchTime())
                .matchStatus(String.valueOf(match.getMatchStatus()))
                .matchType(String.valueOf(match.getMatchType()))
                .createdAt(match.getCreatedAt())
                .modifiedAt(match.getModifiedAt())
                .build();
    }

    default MatchListDto matchListDtoToMatchResponse(List<Match> matches){

        return MatchListDto.builder()
                .matchResponseDto(matchesToMatchesResponse(matches))
                .build();
    }

    default List<MatchResponseDto> matchesToMatchesResponse(List<Match> matches){
        return matches.stream()
                .map(match -> MatchResponseDto.builder()
                        .matchId(match.getMatchId())
                        .userId(match.getUser().getUserId())
                        .teamId(match.getTeam().getTeamId())
                        .homeTeamHonorScore(match.getHomeTeamHonorScore())
                        .homeTeamName(match.getHomeTeamName())
                        .homeTeamManagerName(match.getHomeTeamManagerName())
                        .homeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord())
                        .homeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord())
                        .homeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord())
                        .homeTeamLevelType(String.valueOf(match.getHomeTeamLevelType()))
                        .homeTeamAgeType(String.valueOf(match.getHomeTeamAgeType()))
                        .homeTeamUniformType(String.valueOf(match.getHomeTeamUniformType()))
                        .sportsType(String.valueOf(match.getSportsType()))
                        .locationType(String.valueOf(match.getLocationType()))
                        .matchStatus(String.valueOf(match.getMatchStatus()))
                        .matchTime(match.getMatchTime())
                        .locationType(String.valueOf(match.getLocationType()))
                        .matchStatus(String.valueOf(match.getMatchStatus()))
                        .matchType(String.valueOf(match.getMatchType()))
                        .createdAt(match.getCreatedAt())
                        .modifiedAt(match.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
