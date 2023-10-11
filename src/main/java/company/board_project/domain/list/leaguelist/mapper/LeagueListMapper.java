package company.board_project.domain.list.leaguelist.mapper;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.list.leaguelist.dto.LeagueListPatchDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListPostDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListResponseDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListResponseListDto;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.global.constant.*;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueListMapper {

    default LeagueList leagueListPostDtoToLeagueList(LeagueListPostDto requestBody){
        User user = new User();
        user.setName(requestBody.getManagerName());
        user.setUserId(requestBody.getUserId());

        Apply apply = new Apply();
        apply.setApplyId(requestBody.getApplyId());

        Team team = new Team();
        team.setTeamId(requestBody.getTeamId());
        team.setTeamName(requestBody.getTeamName());
        team.setSubManagerName(requestBody.getSubManagerName());
        team.setHonorScore(requestBody.getHonorScore());
        team.setMemberCount(requestBody.getMemberCount());
        team.setChampionCount(requestBody.getChampionCount());

//        enum 타입 주석처리
        team.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        team.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        team.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        team.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        team.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        team.setFormation(Formation.valueOf(requestBody.getFormation()));


        League league = new League();
        league.setLeagueId(requestBody.getLeagueId());
        league.setLeagueName(requestBody.getLeagueName());

        LeagueList leagueList = new LeagueList();
        leagueList.setUser(user);
        leagueList.setLeague(league);
        leagueList.setTeam(team);
        leagueList.setApply(apply);
        leagueList.setLeagueMatchCount(requestBody.getLeagueMatchCount());
        leagueList.setSubManagerName(requestBody.getSubManagerName());
        leagueList.setCleanSheet(requestBody.getCleanSheet());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setMemberCount(requestBody.getMemberCount());
        leagueList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        leagueList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        leagueList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        leagueList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        leagueList.setHonorScore(requestBody.getHonorScore());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setManagerName(requestBody.getManagerName());
        leagueList.setLeagueName(requestBody.getLeagueName());
        leagueList.setTeamGoals(requestBody.getTeamGoals());
        leagueList.setTeamAssist(requestBody.getTeamAssist());
        leagueList.setSubManagerName(requestBody.getSubManagerName());

//        enum 타입 모음

        leagueList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        leagueList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        leagueList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        leagueList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        leagueList.setFrequency(Frequency.valueOf(requestBody.getFrequency()));
        leagueList.setFormation(Formation.valueOf(requestBody.getFormation()));

        /*  leagueList.setMostGoals(requestBody.getMostGoals());
        leagueList.setMostAssist(requestBody.getMostAssist());
        leagueList.setMostMom(requestBody.getMostMom());*/

        return leagueList;
    }

    default LeagueList leagueListPatchDtoToLeagueList (LeagueListPatchDto requestBody) {
        LeagueList leagueList = new LeagueList();
        leagueList.setSubManagerName(requestBody.getSubManagerName());
        leagueList.setChampionCount(requestBody.getChampionCount());
        leagueList.setMemberCount(requestBody.getMemberCount());
        leagueList.setLeagueMatchCount(requestBody.getLeagueMatchCount());
        leagueList.setLeagueMatchPoints(requestBody.getLeagueMatchPoints());
        leagueList.setLeagueWinRecord(requestBody.getLeagueWinRecord());
        leagueList.setLeagueDrawRecord(requestBody.getLeagueDrawRecord());
        leagueList.setLeagueLoseRecord(requestBody.getLeagueLoseRecord());
        leagueList.setHonorScore(requestBody.getHonorScore());
        leagueList.setTeamGoals(requestBody.getTeamGoals());
        leagueList.setTeamAssist(requestBody.getTeamAssist());
        leagueList.setAgeType(AgeType.valueOf(requestBody.getAgeType()));
        leagueList.setLevelType(LevelType.valueOf(requestBody.getLevelType()));
        leagueList.setLocationType(LocationType.valueOf(requestBody.getLocationType()));
        leagueList.setUniformType(UniformType.valueOf(requestBody.getUniformType()));
        leagueList.setFormation(Formation.valueOf(requestBody.getFormation()));
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
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .leagueListId(leagueList.getLeagueListId())

                .managerName(user.getName())

                .subManagerName(team.getSubManagerName())
                .championCount(team.getChampionCount())
                .memberCount(team.getMemberCount())
                .honorScore(team.getHonorScore())
                .teamName(team.getTeamName())
                .ageType(String.valueOf(team.getAgeType()))
                .locationType(String.valueOf(team.getLocationType()))
                .levelType(String.valueOf(team.getLevelType()))
                .uniformType(String.valueOf(team.getUniformType()))
                .formation(String.valueOf(team.getFormation()))
                .frequency(String.valueOf(team.getFrequency()))

                .leagueName(league.getLeagueName())

                .leagueMatchCount(leagueList.getLeagueMatchCount())
                .leagueHonorScore(leagueList.getLeagueHonorScore())
                .leagueMatchPoints(leagueList.getLeagueMatchPoints())
                .leagueWinRecord(leagueList.getLeagueWinRecord())
                .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                .teamAssist(leagueList.getTeamAssist())
                .teamGoals(leagueList.getTeamGoals())
                .cleanSheet(leagueList.getCleanSheet())

               /* .mostGoals(leagueList.getMostGoals())
                .mostAssist(leagueList.getMostAssist())
                .mostMom(leagueList.getMostMom())*/

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
                        .subManagerName(leagueList.getSubManagerName())
                        .leagueListId(leagueList.getLeagueListId())
                        .managerName(leagueList.getManagerName())
                        .memberCount(leagueList.getMemberCount())
                        .championCount(leagueList.getChampionCount())
                        .leagueMatchCount(leagueList.getLeagueMatchCount())
                        .leagueMatchPoints(leagueList.getLeagueMatchPoints())
                        .leagueWinRecord(leagueList.getLeagueWinRecord())
                        .leagueDrawRecord(leagueList.getLeagueDrawRecord())
                        .leagueLoseRecord(leagueList.getLeagueLoseRecord())
                        .teamName(leagueList.getTeamName())
                        .honorScore(leagueList.getHonorScore())
                        .teamAssist(leagueList.getTeamAssist())
                        .teamGoals(leagueList.getTeamGoals())
                        .cleanSheet(leagueList.getCleanSheet())
           /*             .mostGoals(leagueList.getMostGoals())
                        .mostAssist(leagueList.getMostAssist())
                        .mostMom(leagueList.getMostMom())*/
                        .ageType(String.valueOf(leagueList.getAgeType()))
                        .locationType(String.valueOf(leagueList.getLocationType()))
                        .levelType(String.valueOf(leagueList.getLevelType()))
                        .uniformType(String.valueOf(leagueList.getUniformType()))
                        .frequency(String.valueOf(leagueList.getFrequency()))
                        .formation(String.valueOf(leagueList.getFormation()))
                        .createdAt(leagueList.getCreatedAt())
                        .modifiedAt(leagueList.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}