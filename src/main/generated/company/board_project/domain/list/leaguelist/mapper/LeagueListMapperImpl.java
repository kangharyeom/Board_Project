package company.board_project.domain.list.leaguelist.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.Formation;
import company.board_project.constant.Frequency;
import company.board_project.constant.LevelType;
import company.board_project.constant.LocationType;
import company.board_project.constant.UniformType;
import company.board_project.domain.list.leaguelist.dto.LeagueListPatchDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListPostDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListResponseDto;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T14:55:02+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class LeagueListMapperImpl implements LeagueListMapper {

    @Override
    public LeagueList leagueListPostDtoToLeagueList(LeagueListPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        LeagueList leagueList = new LeagueList();

        leagueList.setLeagueHonorScore( requestBody.getLeagueHonorScore() );
        leagueList.setHonorScore( requestBody.getHonorScore() );
        leagueList.setMemberCount( requestBody.getMemberCount() );
        leagueList.setChampionCount( requestBody.getChampionCount() );
        leagueList.setTeamName( requestBody.getTeamName() );
        leagueList.setManagerName( requestBody.getManagerName() );
        leagueList.setSubManagerName( requestBody.getSubManagerName() );
        leagueList.setLeagueName( requestBody.getLeagueName() );
        if ( requestBody.getFormation() != null ) {
            leagueList.setFormation( Enum.valueOf( Formation.class, requestBody.getFormation() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            leagueList.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            leagueList.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getLevelType() != null ) {
            leagueList.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getFrequency() != null ) {
            leagueList.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getUniformType() != null ) {
            leagueList.setUniformType( Enum.valueOf( UniformType.class, requestBody.getUniformType() ) );
        }
        leagueList.setLeagueMatchCount( requestBody.getLeagueMatchCount() );
        leagueList.setLeagueMatchPoints( requestBody.getLeagueMatchPoints() );
        leagueList.setLeagueWinRecord( requestBody.getLeagueWinRecord() );
        leagueList.setLeagueDrawRecord( requestBody.getLeagueDrawRecord() );
        leagueList.setLeagueLoseRecord( requestBody.getLeagueLoseRecord() );
        leagueList.setTeamGoals( requestBody.getTeamGoals() );
        leagueList.setTeamAssist( requestBody.getTeamAssist() );
        leagueList.setCleanSheet( requestBody.getCleanSheet() );

        return leagueList;
    }

    @Override
    public LeagueList leagueListPatchDtoToLeagueList(LeagueListPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        LeagueList leagueList = new LeagueList();

        leagueList.setLeagueListId( requestBody.getLeagueListId() );
        leagueList.setLeagueHonorScore( requestBody.getLeagueHonorScore() );
        leagueList.setHonorScore( requestBody.getHonorScore() );
        leagueList.setMemberCount( requestBody.getMemberCount() );
        leagueList.setChampionCount( requestBody.getChampionCount() );
        leagueList.setSubManagerName( requestBody.getSubManagerName() );
        if ( requestBody.getFormation() != null ) {
            leagueList.setFormation( Enum.valueOf( Formation.class, requestBody.getFormation() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            leagueList.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            leagueList.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getLevelType() != null ) {
            leagueList.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getFrequency() != null ) {
            leagueList.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getUniformType() != null ) {
            leagueList.setUniformType( Enum.valueOf( UniformType.class, requestBody.getUniformType() ) );
        }
        leagueList.setLeagueMatchCount( requestBody.getLeagueMatchCount() );
        leagueList.setLeagueMatchPoints( requestBody.getLeagueMatchPoints() );
        leagueList.setLeagueWinRecord( requestBody.getLeagueWinRecord() );
        leagueList.setLeagueDrawRecord( requestBody.getLeagueDrawRecord() );
        leagueList.setLeagueLoseRecord( requestBody.getLeagueLoseRecord() );
        leagueList.setTeamGoals( requestBody.getTeamGoals() );
        leagueList.setTeamAssist( requestBody.getTeamAssist() );
        leagueList.setCleanSheet( requestBody.getCleanSheet() );

        return leagueList;
    }

    @Override
    public List<LeagueListResponseDto> leagueListsToLeagueListResponse(List<LeagueList> leagueLists) {
        if ( leagueLists == null ) {
            return null;
        }

        List<LeagueListResponseDto> list = new ArrayList<LeagueListResponseDto>( leagueLists.size() );
        for ( LeagueList leagueList : leagueLists ) {
            list.add( leagueListToLeagueListResponse( leagueList ) );
        }

        return list;
    }
}
