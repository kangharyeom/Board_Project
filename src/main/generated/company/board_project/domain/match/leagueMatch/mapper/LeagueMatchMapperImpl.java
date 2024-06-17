package company.board_project.domain.match.leagueMatch.mapper;

import company.board_project.domain.match.leagueMatch.dto.LeagueMatchPostDto;
import company.board_project.domain.match.leagueMatch.dto.LeagueMatchResponseDto;
import company.board_project.domain.match.leagueMatch.entity.LeagueMatch;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:42:34+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class LeagueMatchMapperImpl implements LeagueMatchMapper {

    @Override
    public LeagueMatch leagueMatchPostDtoToLeagueMatch(LeagueMatchPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        LeagueMatch leagueMatch = new LeagueMatch();

        if ( requestBody.getHomeTeamName() != null ) {
            leagueMatch.setHomeTeamName( String.valueOf( requestBody.getHomeTeamName() ) );
        }
        if ( requestBody.getAwayTeamName() != null ) {
            leagueMatch.setAwayTeamName( String.valueOf( requestBody.getAwayTeamName() ) );
        }
        leagueMatch.setRound( requestBody.getRound() );

        return leagueMatch;
    }

    @Override
    public LeagueMatchResponseDto leagueMatchToLeagueMatchResponseDto(LeagueMatch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        LeagueMatchResponseDto leagueMatchResponseDto = new LeagueMatchResponseDto();

        return leagueMatchResponseDto;
    }
}
