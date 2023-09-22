package company.board_project.apply.mapper;

import company.board_project.apply.dto.*;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.match.entity.Match;
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
        apply.setAgeType(AgeType.valueOf(requestBody.getAgeType()));

        return apply;
    }

    default Apply applyPostDtoToTeamApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());
        user.setName(requestBody.getManagerName());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getTeamName());
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setLevelType(LevelType.valueOf(requestBody.getLevelType()));

        Apply apply = new Apply();
        apply.setUser(user);
        apply.setManagerName(requestBody.getManagerName());
        apply.setTeamName(requestBody.getTeamName());
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        apply.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        apply.setApplyType(ApplyType.valueOf(requestBody.getApplyType()));

        return apply;
    }

    default Apply applyPostDtoToMatchApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setManagerName(requestBody.getManagerName());
        team.setTeamName(requestBody.getTeamName());
        team.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));

        Match match = new Match();
        match.setMatchId(requestBody.getMatchId());

        Apply apply = new Apply();
        apply.setTeam(team);
        apply.setUser(user);
        apply.setMatch(match);
        apply.setTeamName(requestBody.getTeamName());
        apply.setManagerName(requestBody.getManagerName());
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        apply.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        apply.setApplyType(ApplyType.valueOf(requestBody.getApplyType()));

        return apply;
    }

    default Apply applyPostDtoToLeagueApply(ApplyPostDto requestBody){
        User user = new User();
        user.setUserId(requestBody.getUserId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getTeamName());
        team.setManagerName(requestBody.getManagerName());
        team.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));

        League league = new League();
        league.setLeagueId(requestBody.getLeagueId());

        Apply apply = new Apply();
        apply.setLeague(league);
        apply.setTeam(team);
        apply.setUser(user);
        apply.setManagerName(requestBody.getManagerName());
        apply.setTeamName(requestBody.getTeamName());
        apply.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        apply.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        apply.setApplyType(ApplyType.valueOf(requestBody.getApplyType()));

        return apply;
    }

    default ApplyResponseDto applyToApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default TeamApplyResponseDto applyToTeamApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return TeamApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .userTeamApplyId(apply.getUserTeamApplyId())
                .managerName(apply.getManagerName())
                .teamName(apply.getTeamName())
                .ageType(String.valueOf(apply.getAgeType()))
                .levelType(String.valueOf(apply.getLevelType()))
                .applyType(String.valueOf(apply.getApplyType()))
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default LeagueApplyResponseDto applyToLeagueApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();
        League league = apply.getLeague();

        return LeagueApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .userLeagueApplyId(apply.getUserLeagueApplyId())
                .teamName(apply.getTeamName())
                .managerName(apply.getManagerName())
                .levelType(String.valueOf(apply.getLevelType()))
                .ageType(String.valueOf(apply.getAgeType()))
                .applyType(String.valueOf(apply.getApplyType()))
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default MatchApplyResponseDto applyToMatchApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();
        Match match = apply.getMatch();

        return MatchApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .matchId(match.getMatchId())
                .applyId(apply.getApplyId())
                .userMatchApplyId(apply.getUserMatchApplyId())
                .managerName(apply.getManagerName())
                .levelType(String.valueOf(apply.getLevelType()))
                .ageType(String.valueOf(apply.getAgeType()))
                .applyType(String.valueOf(apply.getApplyType()))
                .teamName(apply.getTeamName())
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
                        .teamName(apply.getTeamName())
                        .managerName(apply.getManagerName())
                        .applyType(String.valueOf(apply.getApplyType()))
                        .levelType(String.valueOf(apply.getLevelType()))
                        .ageType(String.valueOf(apply.getAgeType()))
                        .createdAt(apply.getCreatedAt())
                        .modifiedAt(apply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

