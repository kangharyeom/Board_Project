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
        team.setTeamName(requestBody.getAwayTeamName());

        Match match = new Match();
        match.setMatchId(requestBody.getMatchId());

        MatchList matchList = new MatchList();
        matchList.setUser(user);
        matchList.setApply(apply);
        matchList.setTeam(team);
        matchList.setMatch(match);
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setWinRecord(requestBody.getWinRecord());
        matchList.setDrawRecord(requestBody.getDrawRecord());
        matchList.setLoseRecord(requestBody.getLoseRecord());
        matchList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        matchList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        return matchList;
    }

    default MatchList matchListPatchDtoToMatchList (MatchListPatchDto requestBody) {
        MatchList matchList = new MatchList();
        matchList.setAwayTeamName(requestBody.getAwayTeamName());
        matchList.setWinRecord(requestBody.getWinRecord());
        matchList.setDrawRecord(requestBody.getDrawRecord());
        matchList.setLoseRecord(requestBody.getLoseRecord());
        matchList.setHonorScore(requestBody.getHonorScore());
        matchList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        matchList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

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
                .honorScore(matchList.getHonorScore())
                .ranking(matchList.getRanking())
                .winRecord(matchList.getWinRecord())
                .drawRecord(matchList.getDrawRecord())
                .loseRecord(matchList.getLoseRecord())
                .managerName(user.getName())
                .homeTeamName(match.getHomeTeamName())
                .awayTeamName(team.getTeamName())
                .levelType(String.valueOf(matchList.getLevelType()))
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
                        .managerName(matchList.getManagerName())
                        .winRecord(matchList.getWinRecord())
                        .drawRecord(matchList.getDrawRecord())
                        .loseRecord(matchList.getLoseRecord())
                        .awayTeamName(matchList.getAwayTeamName())
                        .honorScore(matchList.getHonorScore())
                        .ranking(matchList.getRanking())
                        .ageType(String.valueOf(matchList.getAgeType()))
                        .levelType(String.valueOf(matchList.getLevelType()))
                        .createdAt(matchList.getCreatedAt())
                        .modifiedAt(matchList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}