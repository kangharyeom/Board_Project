package company.board_project.domain.league.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.Frequency;
import company.board_project.constant.LevelType;
import company.board_project.constant.LocationType;
import company.board_project.constant.SeasonType;
import company.board_project.constant.SportsType;
import company.board_project.domain.league.dto.LeaguePatchDto;
import company.board_project.domain.league.dto.LeaguePostDto;
import company.board_project.domain.league.dto.LeagueResponseDto;
import company.board_project.domain.league.entity.League;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:42:35+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class LeagueMapperImpl implements LeagueMapper {

    @Override
    public League leaguePostDtoToLeague(LeaguePostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        League league = new League();

        league.setMatchCount( requestBody.getMatchCount() );
        league.setLeagueEndCount( requestBody.getLeagueEndCount() );
        league.setTeamCount( requestBody.getTeamCount() );
        league.setMemberCount( requestBody.getMemberCount() );
        league.setLeagueName( requestBody.getLeagueName() );
        league.setManagerName( requestBody.getManagerName() );
        league.setManagerTeamName( requestBody.getManagerTeamName() );
        if ( requestBody.getSportsType() != null ) {
            league.setSportsType( Enum.valueOf( SportsType.class, requestBody.getSportsType() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            league.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            league.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        league.setPeriod( requestBody.getPeriod() );
        if ( requestBody.getLevelType() != null ) {
            league.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        league.setLeagueRules( requestBody.getLeagueRules() );
        if ( requestBody.getFrequency() != null ) {
            league.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getSeasonType() != null ) {
            league.setSeasonType( Enum.valueOf( SeasonType.class, requestBody.getSeasonType() ) );
        }

        return league;
    }

    @Override
    public League leaguePatchDtoToLeague(LeaguePatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        League league = new League();

        league.setLeagueId( requestBody.getLeagueId() );
        league.setMatchCount( requestBody.getMatchCount() );
        league.setTeamCount( requestBody.getTeamCount() );
        league.setMemberCount( requestBody.getMemberCount() );
        league.setLeagueName( requestBody.getLeagueName() );
        if ( requestBody.getSportsType() != null ) {
            league.setSportsType( Enum.valueOf( SportsType.class, requestBody.getSportsType() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            league.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            league.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        league.setPeriod( requestBody.getPeriod() );
        if ( requestBody.getLevelType() != null ) {
            league.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        league.setLeagueRules( requestBody.getLeagueRules() );
        if ( requestBody.getFrequency() != null ) {
            league.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getSeasonType() != null ) {
            league.setSeasonType( Enum.valueOf( SeasonType.class, requestBody.getSeasonType() ) );
        }

        return league;
    }

    @Override
    public List<LeagueResponseDto> leaguesToLeagueResponse(List<League> leagues) {
        if ( leagues == null ) {
            return null;
        }

        List<LeagueResponseDto> list = new ArrayList<LeagueResponseDto>( leagues.size() );
        for ( League league : leagues ) {
            list.add( leagueToLeagueResponse( league ) );
        }

        return list;
    }
}
