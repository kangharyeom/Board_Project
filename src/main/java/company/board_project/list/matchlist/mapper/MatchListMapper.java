package company.board_project.list.matchlist.mapper;

import company.board_project.apply.entity.Apply;
import company.board_project.constant.*;
import company.board_project.league.dto.LeagueToTeamListPostDto;
import company.board_project.league.entity.League;
import company.board_project.list.matchlist.dto.*;
import company.board_project.list.matchlist.entity.MatchList;
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

        MatchList matchList = new MatchList();
        matchList.setUser(user);
        matchList.setApply(apply);
        matchList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        matchList.setWinRecord(requestBody.getWinRecord());
        matchList.setDrawRecord(requestBody.getDrawRecord());
        matchList.setLoseRecord(requestBody.getLoseRecord());
        matchList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        matchList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        matchList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        /*  matchList.setMostGoals(requestBody.getMostGoals());
        matchList.setMostAssist(requestBody.getMostAssist());
        matchList.setMostMom(requestBody.getMostMom());*/

        return matchList;
    }

    default MatchList leagueMatchListPostDtoToMatchList(MatchListPostDto requestBody){
        User user = new User();
        user.setName(requestBody.getManagerName());
        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getTeamName());

        League league = new League();
        league.setLeagueId(requestBody.getLeagueId());

        MatchList matchList = new MatchList();
        matchList.setUser(user);
        matchList.setLeague(league);
        matchList.setTeam(team);
        matchList.setTeamName(requestBody.getTeamName());
        matchList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        matchList.setWinRecord(requestBody.getWinRecord());
        matchList.setDrawRecord(requestBody.getDrawRecord());
        matchList.setLoseRecord(requestBody.getLoseRecord());
        matchList.setHonorScore(requestBody.getHonorScore());
        matchList.setTeamName(requestBody.getTeamName());
//        matchList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
//        matchList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
//        matchList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
//        matchList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        matchList.setManagerName(requestBody.getManagerName());

        /*  matchList.setMostGoals(requestBody.getMostGoals());
        matchList.setMostAssist(requestBody.getMostAssist());
        matchList.setMostMom(requestBody.getMostMom());*/

        return matchList;
    }

    default MatchList matchListPatchDtoToMatchList (MatchListPatchDto requestBody) {
        MatchList matchList = new MatchList();
        matchList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        matchList.setWinRecord(requestBody.getWinRecord());
        matchList.setDrawRecord(requestBody.getDrawRecord());
        matchList.setLoseRecord(requestBody.getLoseRecord());
        matchList.setHonorScore(requestBody.getHonorScore());
        matchList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        matchList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        matchList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        matchList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        /*  matchList.setMostGoals(requestBody.getMostGoals());
        matchList.setMostAssist(requestBody.getMostAssist());
        matchList.setMostMom(requestBody.getMostMom());*/

        return matchList;
    }

//    default MatchListResponseDto matchListToMatchListResponse(MatchList matchList){
//
//        User user = matchList.getUser();
//        Team team = matchList.getTeam();
//        Apply apply = matchList.getApply();
//
//        return MatchListResponseDto.builder()
//                .userId(user.getUserId())
//                .matchListId(matchList.getMatchListId())
//                .teamId(team.getTeamId())
//                .applyId(apply.getApplyId())
//                .leagueMatchPoints(matchList.getLeagueMatchPoints())
//                .winRecord(matchList.getWinRecord())
//                .drawRecord(matchList.getDrawRecord())
//                .loseRecord(matchList.getLoseRecord())
//                .honorScore(matchList.getHonorScore())
//                .ranking(matchList.getRanking())
//               /* .mostGoals(matchList.getMostGoals())
//                .mostAssist(matchList.getMostAssist())
//                .mostMom(matchList.getMostMom())*/
//                .winRecord(matchList.getWinRecord())
//                .drawRecord(matchList.getDrawRecord())
//                .loseRecord(matchList.getLoseRecord())
//                .managerName(user.getName())
//                .teamName(matchList.getTeamName())
//                .ageType(String.valueOf(matchList.getAgeType()))
//                .locationType(String.valueOf(matchList.getLocationType()))
//                .levelType(String.valueOf(matchList.getLevelType()))
//                .createdAt(matchList.getCreatedAt())
//                .modifiedAt(matchList.getModifiedAt())
//                .build();
//    }

    default MatchListResponseDto matchListToMatchListResponse(MatchList matchList){

        User user = matchList.getUser();

        Team team = matchList.getTeam();

        League league = matchList.getLeague();

        Apply apply = matchList.getApply();

        return MatchListResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .matchListId(matchList.getMatchListId())
                .leagueMatchPoints(matchList.getLeagueMatchPoints())
                .honorScore(matchList.getHonorScore())
                .ranking(matchList.getRanking())
               /* .mostGoals(matchList.getMostGoals())
                .mostAssist(matchList.getMostAssist())
                .mostMom(matchList.getMostMom())*/
                .winRecord(matchList.getWinRecord())
                .drawRecord(matchList.getDrawRecord())
                .loseRecord(matchList.getLoseRecord())
                .managerName(user.getName())
                .teamName(matchList.getTeamName())
                .ageType(String.valueOf(matchList.getAgeType()))
                .locationType(String.valueOf(matchList.getLocationType()))
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
                        .leagueMatchPoints(matchList.getLeagueMatchPoints())
                        .winRecord(matchList.getWinRecord())
                        .drawRecord(matchList.getDrawRecord())
                        .loseRecord(matchList.getLoseRecord())
                        .teamName(matchList.getTeamName())
                        .honorScore(matchList.getHonorScore())
                        .ranking(matchList.getRanking())
           /*             .mostGoals(matchList.getMostGoals())
                        .mostAssist(matchList.getMostAssist())
                        .mostMom(matchList.getMostMom())*/
                        .ageType(String.valueOf(matchList.getAgeType()))
                        .locationType(String.valueOf(matchList.getLocationType()))
                        .levelType(String.valueOf(matchList.getLevelType()))
                        .createdAt(matchList.getCreatedAt())
                        .modifiedAt(matchList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}