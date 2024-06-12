package company.board_project.domain.list.matchlist.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchStatus;
import company.board_project.constant.UniformType;
import company.board_project.domain.list.matchlist.dto.MatchAwayTeamDto;
import company.board_project.domain.list.matchlist.dto.MatchListPatchDto;
import company.board_project.domain.list.matchlist.dto.MatchListPostDto;
import company.board_project.domain.list.matchlist.dto.MatchListResponseDto;
import company.board_project.domain.list.matchlist.entity.MatchList;
import company.board_project.domain.match.match.dto.MatchEndDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T09:14:04+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class MatchListMapperImpl implements MatchListMapper {

    @Override
    public MatchList matchListPostDtoToMatchList(MatchListPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        MatchList matchList = new MatchList();

        matchList.setAwayTeamUserId( requestBody.getAwayTeamUserId() );
        matchList.setHomeTeamId( requestBody.getHomeTeamId() );
        matchList.setAwayTeamId( requestBody.getAwayTeamId() );
        matchList.setHomeTeamScore( requestBody.getHomeTeamScore() );
        matchList.setAwayTeamScore( requestBody.getAwayTeamScore() );
        matchList.setHomeTeamHonorScore( requestBody.getHomeTeamHonorScore() );
        matchList.setAwayTeamHonorScore( requestBody.getAwayTeamHonorScore() );
        matchList.setHomeTeamName( requestBody.getHomeTeamName() );
        matchList.setAwayTeamName( requestBody.getAwayTeamName() );
        matchList.setHomeTeamManagerName( requestBody.getHomeTeamManagerName() );
        matchList.setAwayTeamManagerName( requestBody.getAwayTeamManagerName() );
        matchList.setHomeTeamTotalWinRecord( requestBody.getHomeTeamTotalWinRecord() );
        matchList.setAwayTeamTotalWinRecord( requestBody.getAwayTeamTotalWinRecord() );
        matchList.setHomeTeamTotalDrawRecord( requestBody.getHomeTeamTotalDrawRecord() );
        matchList.setAwayTeamTotalDrawRecord( requestBody.getAwayTeamTotalDrawRecord() );
        matchList.setHomeTeamTotalLoseRecord( requestBody.getHomeTeamTotalLoseRecord() );
        matchList.setAwayTeamTotalLoseRecord( requestBody.getAwayTeamTotalLoseRecord() );
        matchList.setMatchTime( requestBody.getMatchTime() );
        matchList.setMatchDate( requestBody.getMatchDate() );
        if ( requestBody.getHomeTeamLevelType() != null ) {
            matchList.setHomeTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getHomeTeamLevelType() ) );
        }
        if ( requestBody.getAwayTeamLevelType() != null ) {
            matchList.setAwayTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getAwayTeamLevelType() ) );
        }
        if ( requestBody.getHomeTeamAgeType() != null ) {
            matchList.setHomeTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getHomeTeamAgeType() ) );
        }
        if ( requestBody.getAwayTeamAgeType() != null ) {
            matchList.setAwayTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getAwayTeamAgeType() ) );
        }
        if ( requestBody.getHomeTeamUniformType() != null ) {
            matchList.setHomeTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getHomeTeamUniformType() ) );
        }
        if ( requestBody.getAwayTeamUniformType() != null ) {
            matchList.setAwayTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getAwayTeamUniformType() ) );
        }

        return matchList;
    }

    @Override
    public MatchList matchListPatchDtoToMatchList(MatchListPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        MatchList matchList = new MatchList();

        matchList.setMatchListId( requestBody.getMatchListId() );
        matchList.setHomeTeamId( requestBody.getHomeTeamId() );
        matchList.setAwayTeamId( requestBody.getAwayTeamId() );
        matchList.setHomeTeamScore( requestBody.getHomeTeamScore() );
        matchList.setAwayTeamScore( requestBody.getAwayTeamScore() );
        matchList.setHomeTeamHonorScore( requestBody.getHomeTeamHonorScore() );
        matchList.setAwayTeamHonorScore( requestBody.getAwayTeamHonorScore() );
        matchList.setHomeTeamName( requestBody.getHomeTeamName() );
        matchList.setAwayTeamName( requestBody.getAwayTeamName() );
        matchList.setHomeTeamManagerName( requestBody.getHomeTeamManagerName() );
        matchList.setAwayTeamManagerName( requestBody.getAwayTeamManagerName() );
        matchList.setHomeTeamTotalWinRecord( requestBody.getHomeTeamTotalWinRecord() );
        matchList.setAwayTeamTotalWinRecord( requestBody.getAwayTeamTotalWinRecord() );
        matchList.setHomeTeamTotalDrawRecord( requestBody.getHomeTeamTotalDrawRecord() );
        matchList.setAwayTeamTotalDrawRecord( requestBody.getAwayTeamTotalDrawRecord() );
        matchList.setHomeTeamTotalLoseRecord( requestBody.getHomeTeamTotalLoseRecord() );
        matchList.setAwayTeamTotalLoseRecord( requestBody.getAwayTeamTotalLoseRecord() );
        matchList.setMatchTime( requestBody.getMatchTime() );
        matchList.setMatchDate( requestBody.getMatchDate() );
        if ( requestBody.getHomeTeamLevelType() != null ) {
            matchList.setHomeTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getHomeTeamLevelType() ) );
        }
        if ( requestBody.getAwayTeamLevelType() != null ) {
            matchList.setAwayTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getAwayTeamLevelType() ) );
        }
        if ( requestBody.getHomeTeamAgeType() != null ) {
            matchList.setHomeTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getHomeTeamAgeType() ) );
        }
        if ( requestBody.getAwayTeamAgeType() != null ) {
            matchList.setAwayTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getAwayTeamAgeType() ) );
        }
        if ( requestBody.getHomeTeamUniformType() != null ) {
            matchList.setHomeTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getHomeTeamUniformType() ) );
        }
        if ( requestBody.getAwayTeamUniformType() != null ) {
            matchList.setAwayTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getAwayTeamUniformType() ) );
        }

        return matchList;
    }

    @Override
    public MatchList applyToMatchList(MatchAwayTeamDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        MatchList matchList = new MatchList();

        matchList.setMatchListId( requestBody.getMatchListId() );
        matchList.setAwayTeamUserId( requestBody.getAwayTeamUserId() );
        matchList.setAwayTeamId( requestBody.getAwayTeamId() );
        matchList.setAwayTeamHonorScore( requestBody.getAwayTeamHonorScore() );
        matchList.setAwayTeamName( requestBody.getAwayTeamName() );
        matchList.setAwayTeamManagerName( requestBody.getAwayTeamManagerName() );
        matchList.setAwayTeamTotalWinRecord( requestBody.getAwayTeamTotalWinRecord() );
        matchList.setAwayTeamTotalDrawRecord( requestBody.getAwayTeamTotalDrawRecord() );
        matchList.setAwayTeamTotalLoseRecord( requestBody.getAwayTeamTotalLoseRecord() );
        if ( requestBody.getAwayTeamLevelType() != null ) {
            matchList.setAwayTeamLevelType( Enum.valueOf( LevelType.class, requestBody.getAwayTeamLevelType() ) );
        }
        if ( requestBody.getAwayTeamAgeType() != null ) {
            matchList.setAwayTeamAgeType( Enum.valueOf( AgeType.class, requestBody.getAwayTeamAgeType() ) );
        }
        if ( requestBody.getAwayTeamUniformType() != null ) {
            matchList.setAwayTeamUniformType( Enum.valueOf( UniformType.class, requestBody.getAwayTeamUniformType() ) );
        }

        return matchList;
    }

    @Override
    public MatchList matchEndDtoToMatch(MatchEndDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        MatchList matchList = new MatchList();

        matchList.setMatchListId( requestBody.getMatchListId() );
        matchList.setAwayTeamUserId( requestBody.getAwayTeamUserId() );
        matchList.setHomeTeamId( requestBody.getHomeTeamId() );
        matchList.setAwayTeamId( requestBody.getAwayTeamId() );
        matchList.setHomeTeamScore( requestBody.getHomeTeamScore() );
        matchList.setAwayTeamScore( requestBody.getAwayTeamScore() );
        if ( requestBody.getMatchStatus() != null ) {
            matchList.setMatchStatus( Enum.valueOf( MatchStatus.class, requestBody.getMatchStatus() ) );
        }

        return matchList;
    }

    @Override
    public List<MatchListResponseDto> matchListsToMatchListResponse(List<MatchList> matchLists) {
        if ( matchLists == null ) {
            return null;
        }

        List<MatchListResponseDto> list = new ArrayList<MatchListResponseDto>( matchLists.size() );
        for ( MatchList matchList : matchLists ) {
            list.add( matchListToMatchListResponse( matchList ) );
        }

        return list;
    }
}
