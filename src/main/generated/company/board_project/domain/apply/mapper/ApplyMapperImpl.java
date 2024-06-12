package company.board_project.domain.apply.mapper;

import company.board_project.constant.AgeType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchType;
import company.board_project.domain.apply.dto.LeagueApplyPostDto;
import company.board_project.domain.apply.dto.MatchApplyPostDto;
import company.board_project.domain.apply.dto.TeamApplyPostDto;
import company.board_project.domain.apply.dto.TeamApplyResponseDto;
import company.board_project.domain.apply.entity.Apply;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T09:14:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class ApplyMapperImpl implements ApplyMapper {

    @Override
    public Apply teamApplyPostDtoToTeamApply(TeamApplyPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Apply apply = new Apply();

        apply.setHostTeamId( requestBody.getHostTeamId() );
        apply.setApplierName( requestBody.getApplierName() );
        apply.setApplyMessage( requestBody.getApplyMessage() );
        apply.setAge( requestBody.getAge() );
        apply.setLevelType( requestBody.getLevelType() );
        apply.setAcceptType( requestBody.getAcceptType() );

        return apply;
    }

    @Override
    public Apply matchApplyPostDtoToMatchApply(MatchApplyPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Apply apply = new Apply();

        apply.setHostMatchId( requestBody.getHostMatchId() );
        apply.setApplierName( requestBody.getApplierName() );
        apply.setTeamName( requestBody.getTeamName() );
        if ( requestBody.getLevelType() != null ) {
            apply.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            apply.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }
        if ( requestBody.getMatchType() != null ) {
            apply.setMatchType( Enum.valueOf( MatchType.class, requestBody.getMatchType() ) );
        }

        return apply;
    }

    @Override
    public Apply leagueApplyPostDtoToLeagueApply(LeagueApplyPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Apply apply = new Apply();

        apply.setHostLeagueId( requestBody.getHostLeagueId() );
        apply.setApplierName( requestBody.getApplierName() );
        apply.setTeamName( requestBody.getTeamName() );
        if ( requestBody.getLevelType() != null ) {
            apply.setLevelType( Enum.valueOf( LevelType.class, requestBody.getLevelType() ) );
        }
        if ( requestBody.getAgeType() != null ) {
            apply.setAgeType( Enum.valueOf( AgeType.class, requestBody.getAgeType() ) );
        }

        return apply;
    }

    @Override
    public TeamApplyResponseDto applyToTeamApplyResponse(Apply apply) {
        if ( apply == null ) {
            return null;
        }

        TeamApplyResponseDto.TeamApplyResponseDtoBuilder teamApplyResponseDto = TeamApplyResponseDto.builder();

        teamApplyResponseDto.applyId( apply.getApplyId() );
        teamApplyResponseDto.age( apply.getAge() );
        teamApplyResponseDto.applierName( apply.getApplierName() );
        teamApplyResponseDto.applyMessage( apply.getApplyMessage() );
        teamApplyResponseDto.levelType( apply.getLevelType() );
        teamApplyResponseDto.acceptType( apply.getAcceptType() );
        teamApplyResponseDto.matchType( apply.getMatchType() );
        teamApplyResponseDto.createdAt( apply.getCreatedAt() );
        teamApplyResponseDto.modifiedAt( apply.getModifiedAt() );

        return teamApplyResponseDto.build();
    }
}
