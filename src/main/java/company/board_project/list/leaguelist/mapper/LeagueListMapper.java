package company.board_project.list.leaguelist.mapper;

import company.board_project.apply.entity.Apply;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.list.leaguelist.dto.*;
import company.board_project.list.leaguelist.entity.LeagueList;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueListMapper {
    default LeagueList leagueListPostDtoToLeagueList(LeagueListPostDto requestBody){
        User user = new User();
        user.setPosition(user.getPosition());

        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        LeagueList leagueList = new LeagueList();
        leagueList.setUser(user);
        leagueList.setApply(apply);
        leagueList.setPosition(Position.valueOf(requestBody.getPosition()));
        leagueList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        leagueList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        leagueList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        leagueList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        leagueList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        leagueList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        leagueList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));

        /*  leagueList.setMostGoals(requestBody.getMostGoals());
        leagueList.setMostAssist(requestBody.getMostAssist());
        leagueList.setMostMom(requestBody.getMostMom());*/

        return leagueList;
    }

    default LeagueList leagueLeagueListPostDtoToLeagueList(LeagueListPostDto requestBody){
        User user = new User();
        user.setName(requestBody.getManagerName());
        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getTeamName());

        League league = new League();
        league.setLeagueId(requestBody.getLeagueId());

        LeagueList leagueList = new LeagueList();
        leagueList.setUser(user);
        leagueList.setLeague(league);
        leagueList.setLeague(league);
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setMemberCount(requestBody.getMemberCount());
        leagueList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        leagueList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        leagueList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        leagueList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        leagueList.setHonorScore(requestBody.getHonorScore());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setLeagueName(requestBody.getLeagueName());
//        leagueList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
//        leagueList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
//        leagueList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
//        leagueList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        leagueList.setManagerName(requestBody.getManagerName());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setSubManagerName(requestBody.getSubManagerName());

        /*  leagueList.setMostGoals(requestBody.getMostGoals());
        leagueList.setMostAssist(requestBody.getMostAssist());
        leagueList.setMostMom(requestBody.getMostMom());*/

        return leagueList;
    }

    default LeagueList leagueListPatchDtoToLeagueList (LeagueListPatchDto requestBody) {
        LeagueList leagueList = new LeagueList();
        leagueList.setPosition(Position.valueOf(requestBody.getPosition()));
        leagueList.setMemberCount(requestBody.getMemberCount());
        leagueList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        leagueList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        leagueList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        leagueList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        leagueList.setHonorScore(requestBody.getHonorScore());
        leagueList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        leagueList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        leagueList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        leagueList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        leagueList.setSubManagerName(requestBody.getSubManagerName());

        /*  leagueList.setMostGoals(requestBody.getMostGoals());
        leagueList.setMostAssist(requestBody.getMostAssist());
        leagueList.setMostMom(requestBody.getMostMom());*/

        return leagueList;
    }

    default LeagueListResponseDto leagueListToLeagueListResponse(LeagueList leagueList){

        User user = leagueList.getUser();
        Team team = leagueList.getTeam();
        League league = leagueList.getLeague();
        Apply apply = leagueList.getApply();

        return LeagueListResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueListId(leagueList.getLeagueListId())
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .memberCount(leagueList.getMemberCount())
                .leagueMatchPoints(leagueList.getLeagueMatchPoints())
                .leagueWinRecord(leagueList.getLeagueWinRecord())
                .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                .honorScore(leagueList.getHonorScore())
                .ranking(leagueList.getRanking())
               /* .mostGoals(leagueList.getMostGoals())
                .mostAssist(leagueList.getMostAssist())
                .mostMom(leagueList.getMostMom())*/
                .leagueWinRecord(leagueList.getLeagueWinRecord())
                .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                .managerName(user.getName())
                .teamName(leagueList.getTeamName())
                .ageType(String.valueOf(leagueList.getAgeType()))
                .locationType(String.valueOf(leagueList.getLocationType()))
                .levelType(String.valueOf(leagueList.getLevelType()))
                .frequency(String.valueOf(leagueList.getFrequency()))
                .createdAt(leagueList.getCreatedAt())
                .modifiedAt(leagueList.getModifiedAt())
                .build();
    }

    default LeagueListResponseDto leagueListToLeagueLeagueListResponse(LeagueList leagueList){

        User user = leagueList.getUser();

        Team team = leagueList.getTeam();

        League league = leagueList.getLeague();

        Apply apply = leagueList.getApply();

        return LeagueListResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .leagueListId(leagueList.getLeagueListId())
                .championCount(leagueList.getChampionCount())
                .memberCount(leagueList.getMemberCount())
                .leagueMatchPoints(leagueList.getLeagueMatchPoints())
                .leagueWinRecord(leagueList.getLeagueWinRecord())
                .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                .honorScore(leagueList.getHonorScore())
                .ranking(leagueList.getRanking())
               /* .mostGoals(leagueList.getMostGoals())
                .mostAssist(leagueList.getMostAssist())
                .mostMom(leagueList.getMostMom())*/
                .leagueWinRecord(leagueList.getLeagueWinRecord())
                .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                .leagueName(leagueList.getLeagueName())
                .managerName(user.getName())
                .teamName(leagueList.getTeamName())
                .ageType(String.valueOf(leagueList.getAgeType()))
                .locationType(String.valueOf(leagueList.getLocationType()))
                .levelType(String.valueOf(leagueList.getLevelType()))
                .frequency(String.valueOf(leagueList.getFrequency()))
                .createdAt(leagueList.getCreatedAt())
                .modifiedAt(leagueList.getModifiedAt())
                .build();
    }

    default LeagueListResponseListDto leagueListsToLeaugeListResponse(List<LeagueList> leagueLists){

        return LeagueListResponseListDto.builder()
                .leagueListResponseDtoList(leagueListsToLeagueListResponse(leagueLists))
                .build();
    }

    default List<LeagueListResponseDto> leagueListsToLeagueListResponse(List<LeagueList> leagueLists){
        return leagueLists.stream()
                .map(leagueList -> LeagueListResponseDto.builder()
                        .leagueListId(leagueList.getLeagueListId())
                        .managerName(leagueList.getManagerName())
                        .memberCount(leagueList.getMemberCount())
                        .leagueMatchPoints(leagueList.getLeagueMatchPoints())
                        .leagueWinRecord(leagueList.getLeagueWinRecord())
                        .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                        .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                        .teamName(leagueList.getTeamName())
                        .honorScore(leagueList.getHonorScore())
                        .ranking(leagueList.getRanking())
           /*             .mostGoals(leagueList.getMostGoals())
                        .mostAssist(leagueList.getMostAssist())
                        .mostMom(leagueList.getMostMom())*/
                        .ageType(String.valueOf(leagueList.getAgeType()))
                        .locationType(String.valueOf(leagueList.getLocationType()))
                        .levelType(String.valueOf(leagueList.getLevelType()))
                        .frequency(String.valueOf(leagueList.getFrequency()))
                        .createdAt(leagueList.getCreatedAt())
                        .modifiedAt(leagueList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}