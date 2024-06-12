package company.board_project.domain.match.leaguematch.mapper;

import company.board_project.domain.match.leaguematch.dto.LeagueMatchResponseDto;
import company.board_project.domain.match.leaguematch.entity.LeagueMatch;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T09:14:04+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class LeagueMatchMapperImpl implements LeagueMatchMapper {

    @Override
    public LeagueMatchResponseDto leagueMatchPostToLeagueMatchResponse(LeagueMatch leagueMatch, Long homeTeamUserId, Long awayTeamUserId, Long homeTeamId, Long awayTeamId, Long homeTeamLeagueListId, Long awayTeamLeagueListId) {
        if ( leagueMatch == null && homeTeamUserId == null && awayTeamUserId == null && homeTeamId == null && awayTeamId == null && homeTeamLeagueListId == null && awayTeamLeagueListId == null ) {
            return null;
        }

        LeagueMatchResponseDto.LeagueMatchResponseDtoBuilder leagueMatchResponseDto = LeagueMatchResponseDto.builder();

        if ( leagueMatch != null ) {
            leagueMatchResponseDto.leagueMatchId( leagueMatch.getLeagueMatchId() );
            leagueMatchResponseDto.homeTeamUserId( leagueMatch.getHomeTeamUserId() );
            leagueMatchResponseDto.awayTeamUserId( leagueMatch.getAwayTeamUserId() );
            leagueMatchResponseDto.homeTeamId( leagueMatch.getHomeTeamId() );
            leagueMatchResponseDto.awayTeamId( leagueMatch.getAwayTeamId() );
            leagueMatchResponseDto.homeTeamLeagueListId( leagueMatch.getHomeTeamLeagueListId() );
            leagueMatchResponseDto.awayTeamLeagueListId( leagueMatch.getAwayTeamLeagueListId() );
            leagueMatchResponseDto.homeTeamScore( leagueMatch.getHomeTeamScore() );
            leagueMatchResponseDto.homeTeamHonorScore( leagueMatch.getHomeTeamHonorScore() );
            leagueMatchResponseDto.awayTeamScore( leagueMatch.getAwayTeamScore() );
            leagueMatchResponseDto.awayTeamHonorScore( leagueMatch.getAwayTeamHonorScore() );
            leagueMatchResponseDto.homeTeamName( leagueMatch.getHomeTeamName() );
            leagueMatchResponseDto.awayTeamName( leagueMatch.getAwayTeamName() );
            leagueMatchResponseDto.homeTeamManagerName( leagueMatch.getHomeTeamManagerName() );
            leagueMatchResponseDto.awayTeamManagerName( leagueMatch.getAwayTeamManagerName() );
            leagueMatchResponseDto.homeTeamLeagueMatchPoints( leagueMatch.getHomeTeamLeagueMatchPoints() );
            leagueMatchResponseDto.awayTeamLeagueMatchPoints( leagueMatch.getAwayTeamLeagueMatchPoints() );
            leagueMatchResponseDto.homeTeamLeagueWinRecord( leagueMatch.getHomeTeamLeagueWinRecord() );
            leagueMatchResponseDto.awayTeamLeagueWinRecord( leagueMatch.getAwayTeamLeagueWinRecord() );
            leagueMatchResponseDto.homeTeamLeagueDrawRecord( leagueMatch.getHomeTeamLeagueDrawRecord() );
            leagueMatchResponseDto.awayTeamLeagueDrawRecord( leagueMatch.getAwayTeamLeagueDrawRecord() );
            leagueMatchResponseDto.homeTeamLeagueLoseRecord( leagueMatch.getHomeTeamLeagueLoseRecord() );
            leagueMatchResponseDto.awayTeamLeagueLoseRecord( leagueMatch.getAwayTeamLeagueLoseRecord() );
            leagueMatchResponseDto.homeTeamTotalWinRecord( leagueMatch.getHomeTeamTotalWinRecord() );
            leagueMatchResponseDto.awayTeamTotalWinRecord( leagueMatch.getAwayTeamTotalWinRecord() );
            leagueMatchResponseDto.homeTeamTotalDrawRecord( leagueMatch.getHomeTeamTotalDrawRecord() );
            leagueMatchResponseDto.awayTeamTotalDrawRecord( leagueMatch.getAwayTeamTotalDrawRecord() );
            leagueMatchResponseDto.homeTeamTotalLoseRecord( leagueMatch.getHomeTeamTotalLoseRecord() );
            leagueMatchResponseDto.awayTeamTotalLoseRecord( leagueMatch.getAwayTeamTotalLoseRecord() );
            if ( leagueMatch.getHomeTeamLevelType() != null ) {
                leagueMatchResponseDto.homeTeamLevelType( leagueMatch.getHomeTeamLevelType().name() );
            }
            if ( leagueMatch.getAwayTeamLevelType() != null ) {
                leagueMatchResponseDto.awayTeamLevelType( leagueMatch.getAwayTeamLevelType().name() );
            }
            if ( leagueMatch.getHomeTeamAgeType() != null ) {
                leagueMatchResponseDto.homeTeamAgeType( leagueMatch.getHomeTeamAgeType().name() );
            }
            if ( leagueMatch.getAwayTeamAgeType() != null ) {
                leagueMatchResponseDto.awayTeamAgeType( leagueMatch.getAwayTeamAgeType().name() );
            }
            if ( leagueMatch.getHomeTeamUniformType() != null ) {
                leagueMatchResponseDto.homeTeamUniformType( leagueMatch.getHomeTeamUniformType().name() );
            }
            if ( leagueMatch.getAwayTeamUniformType() != null ) {
                leagueMatchResponseDto.awayTeamUniformType( leagueMatch.getAwayTeamUniformType().name() );
            }
            if ( leagueMatch.getMatchType() != null ) {
                leagueMatchResponseDto.matchType( leagueMatch.getMatchType().name() );
            }
            if ( leagueMatch.getSportType() != null ) {
                leagueMatchResponseDto.sportType( leagueMatch.getSportType().name() );
            }
            if ( leagueMatch.getLocationType() != null ) {
                leagueMatchResponseDto.locationType( leagueMatch.getLocationType().name() );
            }
            leagueMatchResponseDto.matchTime( leagueMatch.getMatchTime() );
            if ( leagueMatch.getMatchStatus() != null ) {
                leagueMatchResponseDto.matchStatus( leagueMatch.getMatchStatus().name() );
            }
            leagueMatchResponseDto.matchRules( leagueMatch.getMatchRules() );
            if ( leagueMatch.getHomeTeamMatchResultStatus() != null ) {
                leagueMatchResponseDto.homeTeamMatchResultStatus( leagueMatch.getHomeTeamMatchResultStatus().name() );
            }
            if ( leagueMatch.getAwayTeamMatchResultStatus() != null ) {
                leagueMatchResponseDto.awayTeamMatchResultStatus( leagueMatch.getAwayTeamMatchResultStatus().name() );
            }
            leagueMatchResponseDto.createdAt( leagueMatch.getCreatedAt() );
            leagueMatchResponseDto.modifiedAt( leagueMatch.getModifiedAt() );
        }

        return leagueMatchResponseDto.build();
    }
}
