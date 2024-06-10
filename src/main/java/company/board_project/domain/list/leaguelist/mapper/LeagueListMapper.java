package company.board_project.domain.list.leaguelist.mapper;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.list.leaguelist.dto.LeagueListPatchDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListPostDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListResponseDto;
import company.board_project.domain.list.leaguelist.dto.LeagueListResponseListDto;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueListMapper {

    LeagueList leagueListPostDtoToLeagueList(LeagueListPostDto requestBody);

    LeagueList leagueListPatchDtoToLeagueList(LeagueListPatchDto requestBody);


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

//                .mostGoals(leagueList.getMostGoals())
//                .mostAssist(leagueList.getMostAssist())
//                .mostMom(leagueList.getMostMom())

                .createdAt(leagueList.getCreatedAt())
                .modifiedAt(leagueList.getModifiedAt())
                .build();
    }

    default LeagueListResponseListDto leagueListsToLeaugeListResponse(List<LeagueList> leagueLists){

        return LeagueListResponseListDto.builder()
                .leagueListResponseDtoList(leagueListsToLeagueListResponse(leagueLists))
                .build();
    }

    List<LeagueListResponseDto> leagueListsToLeagueListResponse(List<LeagueList> leagueLists);
}