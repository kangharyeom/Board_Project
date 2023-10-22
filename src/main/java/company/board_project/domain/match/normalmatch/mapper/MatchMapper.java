package company.board_project.domain.match.normalmatch.mapper;

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
    Match matchPostDtoToMatch(MatchPostDto requestBody);

    Match matchPatchDtoToMatch(MatchPatchDto requestBody);

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
                .matchDate(match.getMatchDate())
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

    List<MatchResponseDto> matchesToMatchesResponse(List<Match> matches);
}
