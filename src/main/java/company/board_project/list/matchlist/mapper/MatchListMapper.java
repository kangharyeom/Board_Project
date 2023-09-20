package company.board_project.list.matchlist.mapper;

import company.board_project.apply.entity.Apply;
import company.board_project.constant.*;
import company.board_project.list.matchlist.dto.*;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MatchListMapper {
    default MatchList matchListPostDtoToMatchList(MatchListPostDto requestBody){
        User user = new User();
        user.setPosition(user.getPosition());

        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setHonorScore(requestBody.getAwayTeamHonorScore());
        team.setTeamName(requestBody.getAwayTeamName());
        team.setManagerName(requestBody.getAwayTeamManagerName());
        team.setTotalWinRecord(requestBody.getAwayTeamTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getAwayTeamTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getAwayTeamTotalLoseRecord());
        team.setRanking(requestBody.getAwayTeamRanking());
        team.setLevelType(LevelType.valueOf(requestBody.getAwayTeamLevelType()));
        team.setAgeType(AgeType.valueOf(requestBody.getAwayTeamAgeType()));
        team.setUniformType(UniformType.valueOf(requestBody.getAwayTeamUniformType()));

        Match match = new Match();
        match.setMatchId(requestBody.getMatchId());
        match.setHomeTeamScore(requestBody.getHomeTeamScore());
        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        MatchList matchList = new MatchList();
        matchList.setUser(user);
        matchList.setApply(apply);
        matchList.setTeam(team);
        matchList.setMatch(match);
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setHomeTeamScore(requestBody.getHomeTeamScore());
        matchList.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        matchList.setHomeTeamName(requestBody.getHomeTeamName());
        matchList.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        matchList.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        matchList.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        matchList.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        matchList.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        matchList.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        matchList.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        matchList.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));
        matchList.setAwayTeamHonorScore(requestBody.getAwayTeamHonorScore());
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setAwayTeamManagerName(requestBody.getAwayTeamManagerName());
        matchList.setAwayTeamTotalWinRecord(requestBody.getAwayTeamTotalWinRecord());
        matchList.setAwayTeamTotalDrawRecord(requestBody.getAwayTeamTotalDrawRecord());
        matchList.setAwayTeamTotalLoseRecord(requestBody.getAwayTeamTotalLoseRecord());
        matchList.setAwayTeamRanking(requestBody.getAwayTeamRanking());
        matchList.setAwayTeamLevelType(LevelType.valueOf(requestBody.getAwayTeamLevelType()));
        matchList.setAwayTeamAgeType(AgeType.valueOf(requestBody.getAwayTeamAgeType()));
        matchList.setAwayTeamUniformType(UniformType.valueOf(requestBody.getAwayTeamUniformType()));

        return matchList;
    }

    default MatchList leagueMatchListPostDtoToMatchList(LeagueMatchListPostDto requestBody){
        User user = new User();
        user.setPosition(user.getPosition());

        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setHonorScore(requestBody.getAwayTeamHonorScore());
        team.setTeamName(requestBody.getAwayTeamName());
        team.setManagerName(requestBody.getAwayTeamManagerName());
        team.setTotalWinRecord(requestBody.getAwayTeamTotalWinRecord());
        team.setTotalDrawRecord(requestBody.getAwayTeamTotalDrawRecord());
        team.setTotalLoseRecord(requestBody.getAwayTeamTotalLoseRecord());
        team.setLeagueWinRecord(requestBody.getAwayTeamLeagueWinRecord());
        team.setLeagueDrawRecord(requestBody.getAwayTeamLeagueDrawRecord());
        team.setLeagueLoseRecord(requestBody.getAwayTeamLeagueLoseRecord());
        team.setRanking(requestBody.getAwayTeamRanking());
        team.setLevelType(LevelType.valueOf(requestBody.getAwayTeamLevelType()));
        team.setAgeType(AgeType.valueOf(requestBody.getAwayTeamAgeType()));
        team.setUniformType(UniformType.valueOf(requestBody.getAwayTeamUniformType()));

        Match match = new Match();
        match.setMatchId(requestBody.getMatchId());
        match.setHomeTeamScore(requestBody.getHomeTeamScore());
        match.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        match.setHomeTeamName(requestBody.getHomeTeamName());
        match.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        match.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        match.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        match.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        match.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        match.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        match.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        match.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        match.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        MatchList matchList = new MatchList();
        matchList.setUser(user);
        matchList.setApply(apply);
        matchList.setTeam(team);
        matchList.setMatch(match);
        matchList.setHomeTeamScore(requestBody.getHomeTeamScore());
        matchList.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        matchList.setHomeTeamName(requestBody.getHomeTeamName());
        matchList.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        matchList.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        matchList.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        matchList.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        matchList.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        matchList.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        matchList.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        matchList.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        matchList.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        matchList.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        matchList.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));
        matchList.setAwayTeamHonorScore(requestBody.getAwayTeamHonorScore());
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setAwayTeamManagerName(requestBody.getAwayTeamManagerName());
        matchList.setAwayTeamTotalWinRecord(requestBody.getAwayTeamTotalWinRecord());
        matchList.setAwayTeamTotalDrawRecord(requestBody.getAwayTeamTotalDrawRecord());
        matchList.setAwayTeamTotalLoseRecord(requestBody.getAwayTeamTotalLoseRecord());
        matchList.setAwayTeamLeagueWinRecord(requestBody.getAwayTeamLeagueWinRecord());
        matchList.setAwayTeamLeagueDrawRecord(requestBody.getAwayTeamLeagueDrawRecord());
        matchList.setAwayTeamLeagueLoseRecord(requestBody.getAwayTeamLeagueLoseRecord());
        matchList.setAwayTeamRanking(requestBody.getAwayTeamRanking());
        matchList.setAwayTeamLevelType(LevelType.valueOf(requestBody.getAwayTeamLevelType()));
        matchList.setAwayTeamAgeType(AgeType.valueOf(requestBody.getAwayTeamAgeType()));
        matchList.setAwayTeamUniformType(UniformType.valueOf(requestBody.getAwayTeamUniformType()));

        return matchList;
    }

    default MatchList matchListPatchDtoToMatchList (MatchListPatchDto requestBody) {
        MatchList matchList = new MatchList();
        matchList.setHomeTeamScore(requestBody.getHomeTeamScore());
        matchList.setHomeTeamHonorScore(requestBody.getHomeTeamHonorScore());
        matchList.setHomeTeamName(requestBody.getHomeTeamName());
        matchList.setHomeTeamManagerName(requestBody.getHomeTeamManagerName());
        matchList.setHomeTeamTotalWinRecord(requestBody.getHomeTeamTotalWinRecord());
        matchList.setHomeTeamTotalDrawRecord(requestBody.getHomeTeamTotalDrawRecord());
        matchList.setHomeTeamTotalLoseRecord(requestBody.getHomeTeamTotalLoseRecord());
        matchList.setHomeTeamLeagueWinRecord(requestBody.getHomeTeamLeagueWinRecord());
        matchList.setHomeTeamLeagueDrawRecord(requestBody.getHomeTeamLeagueDrawRecord());
        matchList.setHomeTeamLeagueLoseRecord(requestBody.getHomeTeamLeagueLoseRecord());
        matchList.setHomeTeamRanking(requestBody.getHomeTeamRanking());
        matchList.setHomeTeamLevelType(LevelType.valueOf(requestBody.getHomeTeamLevelType()));
        matchList.setHomeTeamAgeType(AgeType.valueOf(requestBody.getHomeTeamAgeType()));
        matchList.setHomeTeamUniformType(UniformType.valueOf(requestBody.getHomeTeamUniformType()));
        matchList.setAwayTeamHonorScore(requestBody.getAwayTeamHonorScore());
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setAwayTeamManagerName(requestBody.getAwayTeamManagerName());
        matchList.setAwayTeamTotalWinRecord(requestBody.getAwayTeamTotalWinRecord());
        matchList.setAwayTeamTotalDrawRecord(requestBody.getAwayTeamTotalDrawRecord());
        matchList.setAwayTeamTotalLoseRecord(requestBody.getAwayTeamTotalLoseRecord());
        matchList.setAwayTeamLeagueWinRecord(requestBody.getAwayTeamLeagueWinRecord());
        matchList.setAwayTeamLeagueDrawRecord(requestBody.getAwayTeamLeagueDrawRecord());
        matchList.setAwayTeamLeagueLoseRecord(requestBody.getAwayTeamLeagueLoseRecord());
        matchList.setAwayTeamRanking(requestBody.getAwayTeamRanking());
        matchList.setAwayTeamLevelType(LevelType.valueOf(requestBody.getAwayTeamLevelType()));
        matchList.setAwayTeamAgeType(AgeType.valueOf(requestBody.getAwayTeamAgeType()));
        matchList.setAwayTeamUniformType(UniformType.valueOf(requestBody.getAwayTeamUniformType()));

        return matchList;
    }

    default MatchListResponseDto matchListToMatchListResponse(MatchList matchList){

        User user = matchList.getUser();

        Team team = matchList.getTeam();

        Apply apply = matchList.getApply();

        Match match = matchList.getMatch();

        return MatchListResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .matchId(match.getMatchId())
                .applyId(apply.getApplyId())
                .matchListId(matchList.getMatchListId())
                .homeTeamScore(matchList.getHomeTeamScore())
                .homeTeamHonorScore(matchList.getHomeTeamHonorScore())
                .homeTeamName(matchList.getHomeTeamName())
                .homeTeamManagerName(matchList.getHomeTeamManagerName())
                .homeTeamLeagueMatchPoints(matchList.getHomeTeamLeagueMatchPoints())
                .homeTeamLeagueWinRecord(matchList.getHomeTeamLeagueWinRecord())
                .homeTeamLeagueDrawRecord(matchList.getHomeTeamLeagueDrawRecord())
                .homeTeamLeagueLoseRecord(matchList.getHomeTeamLeagueLoseRecord())
                .homeTeamTotalWinRecord(matchList.getHomeTeamTotalWinRecord())
                .homeTeamTotalDrawRecord(matchList.getHomeTeamTotalDrawRecord())
                .homeTeamTotalLoseRecord(matchList.getHomeTeamTotalLoseRecord())
                .homeTeamRanking(matchList.getHomeTeamRanking())
                .homeTeamLevelType(String.valueOf(matchList.getHomeTeamLevelType()))
                .homeTeamAgeType(String.valueOf(matchList.getHomeTeamAgeType()))
                .homeTeamUniformType(String.valueOf(matchList.getHomeTeamUniformType()))

                .awayTeamScore(matchList.getAwayTeamScore())
                .awayTeamHonorScore(matchList.getAwayTeamHonorScore())
                .awayTeamName(matchList.getAwayTeamName())
                .awayTeamManagerName(matchList.getAwayTeamManagerName())
                .awayTeamLeagueMatchPoints(matchList.getAwayTeamLeagueMatchPoints())
                .awayTeamLeagueWinRecord(matchList.getAwayTeamLeagueWinRecord())
                .awayTeamLeagueDrawRecord(matchList.getAwayTeamLeagueDrawRecord())
                .awayTeamLeagueLoseRecord(matchList.getAwayTeamLeagueLoseRecord())
                .awayTeamTotalWinRecord(matchList.getAwayTeamTotalWinRecord())
                .awayTeamTotalDrawRecord(matchList.getAwayTeamTotalDrawRecord())
                .awayTeamTotalLoseRecord(matchList.getAwayTeamTotalLoseRecord())
                .awayTeamRanking(matchList.getAwayTeamRanking())
                .awayTeamLevelType(String.valueOf(matchList.getAwayTeamLevelType()))
                .awayTeamAgeType(String.valueOf(matchList.getAwayTeamAgeType()))
                .awayTeamUniformType(String.valueOf(matchList.getAwayTeamUniformType()))
                .createdAt(matchList.getCreatedAt())
                .modifiedAt(matchList.getModifiedAt())
                .build();
    }

    default MatchListResponseListDto matchListDtoToMatchListResponse(List<MatchList> matchLists){

        return MatchListResponseListDto.builder()
                .matchListResponseDtoList(matchListsToMatchListResponse(matchLists))
                .build();
    }

    default List<MatchListResponseDto> matchListsToMatchListResponse(List<MatchList> matchLists){
        return matchLists.stream()
                .map(matchList -> MatchListResponseDto.builder()
                        .matchListId(matchList.getMatchListId())
                        .homeTeamScore(matchList.getHomeTeamScore())
                        .homeTeamHonorScore(matchList.getHomeTeamHonorScore())
                        .homeTeamName(matchList.getHomeTeamName())
                        .homeTeamManagerName(matchList.getHomeTeamManagerName())
                        .homeTeamLeagueMatchPoints(matchList.getHomeTeamLeagueMatchPoints())
                        .homeTeamLeagueWinRecord(matchList.getHomeTeamLeagueWinRecord())
                        .homeTeamLeagueDrawRecord(matchList.getHomeTeamLeagueDrawRecord())
                        .homeTeamLeagueLoseRecord(matchList.getHomeTeamLeagueLoseRecord())
                        .homeTeamTotalWinRecord(matchList.getHomeTeamTotalWinRecord())
                        .homeTeamTotalDrawRecord(matchList.getHomeTeamTotalDrawRecord())
                        .homeTeamTotalLoseRecord(matchList.getHomeTeamTotalLoseRecord())
                        .homeTeamRanking(matchList.getHomeTeamRanking())
                        .homeTeamLevelType(String.valueOf(matchList.getHomeTeamLevelType()))
                        .homeTeamAgeType(String.valueOf(matchList.getHomeTeamAgeType()))
                        .homeTeamUniformType(String.valueOf(matchList.getHomeTeamUniformType()))

                        .awayTeamScore(matchList.getAwayTeamScore())
                        .awayTeamHonorScore(matchList.getAwayTeamHonorScore())
                        .awayTeamName(matchList.getAwayTeamName())
                        .awayTeamManagerName(matchList.getAwayTeamManagerName())
                        .awayTeamLeagueMatchPoints(matchList.getAwayTeamLeagueMatchPoints())
                        .awayTeamLeagueWinRecord(matchList.getAwayTeamLeagueWinRecord())
                        .awayTeamLeagueDrawRecord(matchList.getAwayTeamLeagueDrawRecord())
                        .awayTeamLeagueLoseRecord(matchList.getAwayTeamLeagueLoseRecord())
                        .awayTeamTotalWinRecord(matchList.getAwayTeamTotalWinRecord())
                        .awayTeamTotalDrawRecord(matchList.getAwayTeamTotalDrawRecord())
                        .awayTeamTotalLoseRecord(matchList.getAwayTeamTotalLoseRecord())
                        .awayTeamRanking(matchList.getAwayTeamRanking())
                        .awayTeamLevelType(String.valueOf(matchList.getAwayTeamLevelType()))
                        .awayTeamAgeType(String.valueOf(matchList.getAwayTeamAgeType()))
                        .awayTeamUniformType(String.valueOf(matchList.getAwayTeamUniformType()))
                        .createdAt(matchList.getCreatedAt())
                        .modifiedAt(matchList.getModifiedAt())
                        .createdAt(matchList.getCreatedAt())
                        .modifiedAt(matchList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}