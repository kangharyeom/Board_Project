package company.board_project.domain.team.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.Formation;
import company.board_project.constant.Frequency;
import company.board_project.constant.LevelType;
import company.board_project.constant.LocationType;
import company.board_project.constant.SportsType;
import company.board_project.constant.TeamMemberRole;
import company.board_project.constant.UniformType;
import company.board_project.domain.team.dto.TeamMemberListPostDto;
import company.board_project.domain.team.dto.TeamPatchDto;
import company.board_project.domain.team.dto.TeamPostDto;
import company.board_project.domain.team.dto.TeamResponseDto;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.entity.TeamMemberList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T18:57:04+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public Team teamPostDtoToTeam(TeamPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Team team = new Team();

        team.setChampionCount( requestBody.getChampionCount() );
        team.setMemberCount( requestBody.getMemberCount() );
        team.setLeagueMatchCount( requestBody.getLeagueMatchCount() );
        team.setLeagueMatchPoints( requestBody.getLeagueMatchPoints() );
        team.setLeagueWinRecord( requestBody.getLeagueWinRecord() );
        team.setLeagueDrawRecord( requestBody.getLeagueDrawRecord() );
        team.setLeagueLoseRecord( requestBody.getLeagueLoseRecord() );
        team.setTotalMatchCount( requestBody.getTotalMatchCount() );
        team.setTotalWinRecord( requestBody.getTotalWinRecord() );
        team.setTotalDrawRecord( requestBody.getTotalDrawRecord() );
        team.setTotalLoseRecord( requestBody.getTotalLoseRecord() );
        team.setHonorScore( requestBody.getHonorScore() );
        team.setTeamName( requestBody.getTeamName() );
        team.setIntroduction( requestBody.getIntroduction() );
        team.setManagerName( requestBody.getManagerName() );
        team.setLeagueName( requestBody.getLeagueName() );
        team.setSubManagerName( requestBody.getSubManagerName() );
        if ( requestBody.getAgeType() != null ) {
            team.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getSportsType() != null ) {
            team.setSportsType( Enum.valueOf( SportsType.class, requestBody.getSportsType() ) );
        }
        if ( requestBody.getLevelType() != null ) {
            team.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            team.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getFrequency() != null ) {
            team.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getUniformType() != null ) {
            team.setUniformType( Enum.valueOf( UniformType.class, requestBody.getUniformType() ) );
        }
        if ( requestBody.getFormation() != null ) {
            team.setFormation( Enum.valueOf( Formation.class, requestBody.getFormation() ) );
        }

        return team;
    }

    @Override
    public Team teamPatchDtoToTeam(TeamPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Team team = new Team();

        team.setTeamId( requestBody.getTeamId() );
        team.setChampionCount( requestBody.getChampionCount() );
        team.setMemberCount( requestBody.getMemberCount() );
        team.setLeagueMatchCount( requestBody.getLeagueMatchCount() );
        team.setLeagueMatchPoints( requestBody.getLeagueMatchPoints() );
        team.setLeagueWinRecord( requestBody.getLeagueWinRecord() );
        team.setLeagueDrawRecord( requestBody.getLeagueDrawRecord() );
        team.setLeagueLoseRecord( requestBody.getLeagueLoseRecord() );
        team.setTotalMatchCount( requestBody.getTotalMatchCount() );
        team.setTotalWinRecord( requestBody.getTotalWinRecord() );
        team.setTotalDrawRecord( requestBody.getTotalDrawRecord() );
        team.setTotalLoseRecord( requestBody.getTotalLoseRecord() );
        team.setHonorScore( requestBody.getHonorScore() );
        team.setIntroduction( requestBody.getIntroduction() );
        team.setManagerName( requestBody.getManagerName() );
        team.setLeagueName( requestBody.getLeagueName() );
        team.setSubManagerName( requestBody.getSubManagerName() );
        if ( requestBody.getAgeType() != null ) {
            team.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getSportsType() != null ) {
            team.setSportsType( Enum.valueOf( SportsType.class, requestBody.getSportsType() ) );
        }
        if ( requestBody.getLevelType() != null ) {
            team.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getLocationType() != null ) {
            team.setLocationType( Enum.valueOf( LocationType.class, requestBody.getLocationType() ) );
        }
        if ( requestBody.getFrequency() != null ) {
            team.setFrequency( Enum.valueOf( Frequency.class, requestBody.getFrequency() ) );
        }
        if ( requestBody.getUniformType() != null ) {
            team.setUniformType( Enum.valueOf( UniformType.class, requestBody.getUniformType() ) );
        }
        if ( requestBody.getFormation() != null ) {
            team.setFormation( Enum.valueOf( Formation.class, requestBody.getFormation() ) );
        }

        return team;
    }

    @Override
    public TeamMemberList teamMemberListPostDtoToTeam(TeamMemberListPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        TeamMemberList teamMemberList = new TeamMemberList();

        teamMemberList.setName( requestBody.getName() );
        if ( requestBody.getTeamMemberRole() != null ) {
            teamMemberList.setTeamMemberRole( Enum.valueOf( TeamMemberRole.class, requestBody.getTeamMemberRole() ) );
        }

        return teamMemberList;
    }

    @Override
    public List<TeamResponseDto> teamsToTeamResponse(List<Team> teams) {
        if ( teams == null ) {
            return null;
        }

        List<TeamResponseDto> list = new ArrayList<TeamResponseDto>( teams.size() );
        for ( Team team : teams ) {
            list.add( teamToTeamResponseDto( team ) );
        }

        return list;
    }
}
