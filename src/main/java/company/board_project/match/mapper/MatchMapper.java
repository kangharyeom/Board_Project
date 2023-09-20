package company.board_project.match.mapper;

import company.board_project.constant.*;
import company.board_project.match.dto.*;
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

        Match match = new Match();
        match.setTeam(team);
        match.setUser(user);

        match.setHomeTeamScore(requestBody.getHomeTeamScore());
        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamLeagueMatchPoints(requestBody.getHomeTeamLeagueMatchPoints());
        match.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        match.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        match.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportType(SportsType.valueOf(requestBody.getSportType()));
        match.setMatchTime(requestBody.getMatchTime());
       match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }

    default Match leagueMatchPostDtoToMatch(LeagueMatchPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());

//        LeagueList leagueList = new LeagueList();
//        leagueList.setLeagueListId(requestBody.getLeagueListId());

        Match match = new Match();
        match.setTeam(team);
        match.setUser(user);
//        match.setLeagueList(leagueList);

        match.setHomeTeamScore(requestBody.getHomeTeamScore());
        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamLeagueMatchPoints(requestBody.getHomeTeamLeagueMatchPoints());
        match.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        match.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        match.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportType(SportsType.valueOf(requestBody.getSportType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }

    default Match matchPatchDtoToMatch(MatchPatchDto requestBody) {
        Match match = new Match();

        match.setHomeTeamScore(requestBody.getHomeTeamScore());
        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamLeagueMatchPoints(requestBody.getHomeTeamLeagueMatchPoints());
        match.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        match.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        match.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        match.setMatchType(MatchType.valueOf(requestBody.getMatchType()));
        match.setSportType(SportsType.valueOf(requestBody.getSportType()));
        match.setMatchTime(requestBody.getMatchTime());
        match.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        match.setMatchRules(requestBody.getMatchRules());

        return match;
    }

    default MatchResponseDto matchToMatchResponse(Match match){
        User user = match.getUser();
        Team team = match.getTeam();
//        LeagueList leagueList = match.getLeagueList();

        return MatchResponseDto.builder()
                .matchId(match.getMatchId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
//                .leagueListId(leagueList.getLeagueListId())
                .homeTeamScore(match.getHomeTeamScore())
                .homeTeamHonorScore(team.getHonorScore())
                .homeTeamName(team.getTeamName())
                .homeTeamManagerName(team.getManagerName())
//                .homeTeamLeagueWinRecord(leagueList.getLeagueWinRecord())
//                .homeTeamLeagueDrawRecord(leagueList.getLeagueDrawRecord())
//                .homeTeamLeagueLoseRecord(leagueList.getLeagueLoseRecord())
                .homeTeamTotalWinRecord(team.getTotalWinRecord())
                .homeTeamTotalDrawRecord(team.getTotalDrawRecord())
                .homeTeamTotalLoseRecord(team.getTotalLoseRecord())
                .homeTeamRanking(team.getRanking())
                .homeTeamLevelType(String.valueOf(team.getLevelType()))
                .homeTeamAgeType(String.valueOf(team.getAgeType()))
                .homeTeamUniformType(String.valueOf(team.getUniformType()))
                .sportType(String.valueOf(match.getSportType()))
                .locationType(String.valueOf(match.getLocationType()))
                .matchStatus(String.valueOf(match.getMatchStatus()))
                .createdAt(match.getCreatedAt())
                .modifiedAt(match.getModifiedAt())
                .build();
    }

    default MatchListDto matchListDtoToMatchResponse(List<Match> matches, TeamRepository teamRepository){

        return MatchListDto.builder()
                .matchResponseDto(matchesToMatchesResponse(matches, teamRepository))
                .build();
    }

    default List<MatchResponseDto> matchesToMatchesResponse(List<Match> matches, TeamRepository teamRepository){
        return matches.stream()
                .map(match -> MatchResponseDto.builder()
                        .matchId(match.getMatchId())
                        .userId(match.getUser().getUserId())
                        .teamId(match.getTeam().getTeamId())
//                        .leagueListId(match.getLeagueList().getLeagueListId())
                        .homeTeamScore(match.getHomeTeamScore())
                        .homeTeamHonorScore(match.getHomeTeamHonorScore())
                        .homeTeamName(match.getHomeTeamName())
                        .homeTeamManagerName(match.getHomeTeamManagerName())
                        .homeTeamLeagueWinRecord(match.getHomeTeamLeagueWinRecord())
                        .homeTeamLeagueDrawRecord(match.getHomeTeamLeagueDrawRecord())
                        .homeTeamLeagueLoseRecord(match.getHomeTeamLeagueLoseRecord())
                        .homeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord())
                        .homeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord())
                        .homeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord())
                        .homeTeamRanking(match.getHomeTeamRanking())
                        .homeTeamLevelType(String.valueOf(match.getHomeTeamLevelType()))
                        .homeTeamAgeType(String.valueOf(match.getHomeTeamAgeType()))
                        .homeTeamUniformType(String.valueOf(match.getHomeTeamUniformType()))
                        .sportType(String.valueOf(match.getSportType()))
                        .locationType(String.valueOf(match.getLocationType()))
                        .matchStatus(String.valueOf(match.getMatchStatus()))
                        .sportType(String.valueOf(match.getSportType()))
                        .locationType(String.valueOf(match.getLocationType()))
                        .matchStatus(String.valueOf(match.getMatchStatus()))
                        .createdAt(match.getCreatedAt())
                        .modifiedAt(match.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
