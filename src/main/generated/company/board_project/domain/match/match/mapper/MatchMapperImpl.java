package company.board_project.domain.match.match.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.LevelType;
import company.board_project.constant.LocationType;
import company.board_project.constant.MatchStatus;
import company.board_project.constant.MatchType;
import company.board_project.constant.SportsType;
import company.board_project.constant.UniformType;
import company.board_project.domain.match.match.dto.MatchPatchDto;
import company.board_project.domain.match.match.dto.MatchPostDto;
import company.board_project.domain.match.match.dto.MatchResponseDto;
import company.board_project.domain.match.match.entity.Match;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:38:13+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Override
    public Match matchPostDtoToMatch(MatchPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Match match = new Match();

        if ( requestBody.getLocationType() != null ) {
            match.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getMatchType() != null ) {
            match.setMatchType( Enum.valueOf( MatchType.class, requestBody.getMatchType() ) );
        }
        match.setMatchTime( requestBody.getMatchTime() );
        match.setMatchRules( requestBody.getMatchRules() );

        return match;
    }

    @Override
    public Match matchPatchDtoToMatch(MatchPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Match match = new Match();

        match.setMatchId( requestBody.getMatchId() );
        match.setHomeTeamName( requestBody.getHomeTeamName() );
        match.setHomeTeamManagerName( requestBody.getHomeTeamManagerName() );
        match.setHomeTeamHonorScore( requestBody.getHomeTeamHonorScore() );
        match.setHomeTeamTotalWinRecord( requestBody.getHomeTeamTotalWinRecord() );
        match.setHomeTeamTotalDrawRecord( requestBody.getHomeTeamTotalDrawRecord() );
        match.setHomeTeamTotalLoseRecord( requestBody.getHomeTeamTotalLoseRecord() );
        match.setHomeTeamLeagueWinRecord( requestBody.getHomeTeamLeagueWinRecord() );
        match.setHomeTeamLeagueDrawRecord( requestBody.getHomeTeamLeagueDrawRecord() );
        match.setHomeTeamLeagueLoseRecord( requestBody.getHomeTeamLeagueLoseRecord() );
        if ( requestBody.getHomeTeamLevelType() != null ) {
            match.setHomeTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getHomeTeamLevelType() ) );
        }
        if ( requestBody.getHomeTeamAgeType() != null ) {
            match.setHomeTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getHomeTeamAgeType() ) );
        }
        if ( requestBody.getHomeTeamUniformType() != null ) {
            match.setHomeTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getHomeTeamUniformType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            match.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getMatchType() != null ) {
            match.setMatchType( Enum.valueOf( MatchType.class, requestBody.getMatchType() ) );
        }
        if ( requestBody.getSportsType() != null ) {
            match.setSportsType( Enum.valueOf( SportsType.class, requestBody.getSportsType() ) );
        }
        match.setMatchTime( requestBody.getMatchTime() );
        match.setMatchRules( requestBody.getMatchRules() );
        if ( requestBody.getMatchStatus() != null ) {
            match.setMatchStatus( Enum.valueOf( MatchStatus.class, requestBody.getMatchStatus() ) );
        }

        return match;
    }

    @Override
    public List<MatchResponseDto> matchesToMatchesResponse(List<Match> matches) {
        if ( matches == null ) {
            return null;
        }

        List<MatchResponseDto> list = new ArrayList<MatchResponseDto>( matches.size() );
        for ( Match match : matches ) {
            list.add( matchToMatchResponse( match ) );
        }

        return list;
    }
}
