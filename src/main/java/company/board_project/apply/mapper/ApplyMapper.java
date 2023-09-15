package company.board_project.apply.mapper;

import company.board_project.constant.*;
import company.board_project.match.entity.Match;
import company.board_project.apply.dto.ApplyListDto;
import company.board_project.apply.dto.ApplyPostDto;
import company.board_project.apply.dto.ApplyResponseDto;
import company.board_project.apply.entity.Apply;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ApplyMapper {
    default Apply applyPostDtoToApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setUser(user);
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));

        return apply;
    }

    default Apply applyPostDtoToTeamApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setUser(user);
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));

        return apply;
    }

    default Apply applyPostDtoToMatchApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());

        Apply apply = new Apply();
        apply.setTeam(team);
        apply.setUser(user);
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));

        return apply;
    }

    default Apply applyPostDtoToLeagueApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());

        Apply apply = new Apply();
        apply.setTeam(team);
        apply.setUser(user);
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));

        return apply;
    }

    default ApplyResponseDto applyToApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .teamName(apply.getTeam().getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default ApplyResponseDto applyToTeamApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeam().getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default ApplyResponseDto applyToLeagueApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeam().getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default ApplyResponseDto applyToMatchApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();
        Match match = apply.getMatch();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeam().getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default ApplyListDto applyListDtoToApplyResponse(List<Apply> applies){

        return ApplyListDto.builder()
                .applyResponseDtoList(appliesToApplyResponse(applies))
                .build();
    }

    default List<ApplyResponseDto> appliesToApplyResponse(List<Apply> applies){
        return applies.stream()
                .map(apply -> ApplyResponseDto.builder()
                        .applyId(apply.getApplyId())
                        .createdAt(apply.getCreatedAt())
                        .modifiedAt(apply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

