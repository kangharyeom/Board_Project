package company.board_project.domain.list.match.mapper;

import company.board_project.domain.apply.match.entity.MatchApply;
import company.board_project.domain.list.match.dto.*;
import company.board_project.domain.list.match.entity.MatchList;
import company.board_project.domain.match.normal.dto.MatchEndDto;
import company.board_project.domain.match.normal.dto.MatchEndResponseDto;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchListMapper {
    MatchList matchListPostDtoToMatchList(MatchListPostDto requestBody);

    MatchList matchListPatchDtoToMatchList(MatchListPatchDto requestBody);

    MatchList applyToMatchList(MatchAwayTeamDto requestBody);

    MatchList matchEndDtoToMatch(MatchEndDto requestBody);

    default MatchEndResponseDto matchToMatchEndResponse(MatchList matchList){
        Match match = matchList.getMatch();
        User user = matchList.getUser();
        Team team = matchList.getTeam();

        return MatchEndResponseDto.builder()
                .matchListId(matchList.getMatchListId())
                .matchId(match.getMatchId())
                .homeTeamUserId(user.getUserId())
                .awayTeamUserId(matchList.getAwayTeamUserId())
                .homeTeamId(team.getTeamId())
                .awayTeamId(matchList.getAwayTeamId())
                .homeTeamScore(matchList.getHomeTeamScore())
                .awayTeamScore(matchList.getAwayTeamScore())
                .matchStatus(String.valueOf(matchList.getMatchStatus()))
                .matchTime(matchList.getMatchTime())
                .matchDate(matchList.getMatchDate())
                .createdAt(matchList.getCreatedAt())
                .modifiedAt(matchList.getModifiedAt())
                .build();
    }

    default MatchListResponseDto matchListToMatchListResponse(MatchList matchList){

        User user = matchList.getUser();

        Team team = matchList.getTeam();

        MatchApply matchApply = matchList.getMatchApply();

        Match match = matchList.getMatch();

        return MatchListResponseDto.builder()
                .homeTeamUserId(user.getUserId())
                .homeTeamId(team.getTeamId())
                .matchId(match.getMatchId())
                .matchApplyId(matchApply.getMatchApplyId())
                .matchListId(matchList.getMatchListId())
                .homeTeamScore(matchList.getHomeTeamScore())
                .homeTeamHonorScore(matchList.getHomeTeamHonorScore())
                .homeTeamName(matchList.getHomeTeamName())
                .homeTeamManagerName(matchList.getHomeTeamManagerName())
                .homeTeamTotalWinRecord(matchList.getHomeTeamTotalWinRecord())
                .homeTeamTotalDrawRecord(matchList.getHomeTeamTotalDrawRecord())
                .homeTeamTotalLoseRecord(matchList.getHomeTeamTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(matchList.getHomeTeamLevelType()))
                .homeTeamAgeType(String.valueOf(matchList.getHomeTeamAgeType()))
                .homeTeamUniformType(String.valueOf(matchList.getHomeTeamUniformType()))

                .awayTeamScore(matchList.getAwayTeamScore())
                .awayTeamHonorScore(matchList.getAwayTeamHonorScore())
                .awayTeamName(matchList.getAwayTeamName())
                .awayTeamManagerName(matchList.getAwayTeamManagerName())
                .awayTeamTotalWinRecord(matchList.getAwayTeamTotalWinRecord())
                .awayTeamTotalDrawRecord(matchList.getAwayTeamTotalDrawRecord())
                .awayTeamTotalLoseRecord(matchList.getAwayTeamTotalLoseRecord())
                .awayTeamLevelType(String.valueOf(matchList.getAwayTeamLevelType()))
                .awayTeamAgeType(String.valueOf(matchList.getAwayTeamAgeType()))
                .awayTeamUniformType(String.valueOf(matchList.getAwayTeamUniformType()))
                .matchTime(matchList.getMatchTime())
                .matchDate(matchList.getMatchDate())
                .createdAt(matchList.getCreatedAt())
                .modifiedAt(matchList.getModifiedAt())
                .build();
    }

    default MatchListResponseDto applyToMatchListResponse(MatchList matchList, Long matchApplyId){
        Match match = matchList.getMatch();
        User user = matchList.getUser();
        Team team = matchList.getTeam();
        MatchApply matchApply = matchList.getMatchApply();

        return MatchListResponseDto.builder()
                .homeTeamUserId(user.getUserId())
                .awayTeamUserId(matchList.getAwayTeamUserId())
                .awayTeamId(matchList.getAwayTeamId())
                .matchId(match.getMatchId())
                .homeTeamId(team.getTeamId())
                .matchApplyId(matchApply.getMatchApplyId())
                .matchListId(matchList.getMatchListId())
                .homeTeamScore(matchList.getHomeTeamScore())
                .homeTeamHonorScore(matchList.getHomeTeamHonorScore())
                .homeTeamName(matchList.getHomeTeamName())
                .homeTeamManagerName(matchList.getHomeTeamManagerName())
                .homeTeamTotalWinRecord(matchList.getHomeTeamTotalWinRecord())
                .homeTeamTotalDrawRecord(matchList.getHomeTeamTotalDrawRecord())
                .homeTeamTotalLoseRecord(matchList.getHomeTeamTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(matchList.getHomeTeamLevelType()))
                .homeTeamAgeType(String.valueOf(matchList.getHomeTeamAgeType()))
                .homeTeamUniformType(String.valueOf(matchList.getHomeTeamUniformType()))

                .awayTeamScore(matchList.getAwayTeamScore())
                .awayTeamHonorScore(matchList.getAwayTeamHonorScore())
                .awayTeamName(matchList.getAwayTeamName())
                .awayTeamManagerName(matchList.getAwayTeamManagerName())
                .awayTeamTotalWinRecord(matchList.getAwayTeamTotalWinRecord())
                .awayTeamTotalDrawRecord(matchList.getAwayTeamTotalDrawRecord())
                .awayTeamTotalLoseRecord(matchList.getAwayTeamTotalLoseRecord())
                .awayTeamLevelType(String.valueOf(matchList.getAwayTeamLevelType()))
                .awayTeamAgeType(String.valueOf(matchList.getAwayTeamAgeType()))
                .awayTeamUniformType(String.valueOf(matchList.getAwayTeamUniformType()))
                .matchTime(matchList.getMatchTime())
                .matchDate(matchList.getMatchDate())
                .createdAt(matchList.getCreatedAt())
                .modifiedAt(matchList.getModifiedAt())
                .build();
    }

    default MatchListResponseListDto matchListDtoToMatchListResponse(List<MatchList> matchLists){

        return MatchListResponseListDto.builder()
                .matchListResponseDtoList(matchListsToMatchListResponse(matchLists))
                .build();
    }

    List<MatchListResponseDto> matchListsToMatchListResponse(List<MatchList> matchLists);
}