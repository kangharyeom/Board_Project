package company.board_project.list.leaguelist.service;

import company.board_project.apply.entity.Apply;
import company.board_project.apply.service.ApplyService;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.league.entity.League;
import company.board_project.league.service.LeagueService;
import company.board_project.list.leaguelist.entity.LeagueList;
import company.board_project.list.leaguelist.repository.LeagueListRepository;
import company.board_project.team.entity.Team;
import company.board_project.team.service.TeamService;
import company.board_project.user.entity.User;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LeagueListService {
    private final LeagueListRepository leagueListRepository;
    private final TeamService teamService;
    private final LeagueService leagueService;
    private final UserService userService;
    private final ApplyService applyService;
    public LeagueList createTeamList(
            LeagueList leagueList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

        leagueList.setUser(user);
        leagueList.setTeam(team);
        leagueList.setApply(apply);
        leagueList.setManagerName(user.getName());

        return leagueListRepository.save(leagueList);
    }

    public LeagueList createLeagueTeamListByLeagueController(
            LeagueList leagueList, Long userId, Long teamId, Long leagueId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);


        League league = leagueService.findLeague(leagueId);

        leagueList.setUser(user);
        leagueList.setTeam(team);
        leagueList.setLeague(league);

        leagueList.setLeagueName(league.getLeagueName());

        leagueList.setTeamName(team.getTeamName());
        leagueList.setSubManagerName(team.getSubManagerName());
        leagueList.setChampionCount(team.getChampionCount());
        leagueList.setMemberCount(team.getMemberCount());
        leagueList.setHonorScore(team.getHonorScore());
        leagueList.setAgeType(team.getAgeType());
        leagueList.setLocationType(team.getLocationType());
        leagueList.setLevelType(team.getLevelType());
        leagueList.setFrequency(team.getFrequency());
        leagueList.setUniformType(team.getUniformType());

        leagueList.setManagerName(user.getName());

        return leagueListRepository.save(leagueList);
    }

    public LeagueList createLeagueLeagueList(
            LeagueList leagueList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

//        League league = new League();

        leagueList.setUser(user);
        leagueList.setTeam(team);
//        leagueList.setLeague(league);
        leagueList.setApply(apply);

//        leagueList.setLeagueName(league.getLeagueName());

        leagueList.setTeamName(team.getTeamName());
        leagueList.setSubManagerName(team.getSubManagerName());
        leagueList.setChampionCount(team.getChampionCount());
        leagueList.setMemberCount(team.getMemberCount());
        leagueList.setHonorScore(team.getHonorScore());
        leagueList.setAgeType(team.getAgeType());
        leagueList.setLocationType(team.getLocationType());
        leagueList.setLevelType(team.getLevelType());
        leagueList.setFrequency(team.getFrequency());
        leagueList.setUniformType(team.getUniformType());

        leagueList.setManagerName(user.getName());

        return leagueListRepository.save(leagueList);
    }

    public LeagueList updateLeagueList(
            LeagueList leagueList,
            Long leagueListId) {

        LeagueList findLeagueList = findVerifiedLeagueList(leagueListId);

        Optional.ofNullable(leagueList.getPosition())
                .ifPresent(findLeagueList::setPosition);

        Optional.ofNullable(leagueList.getChampionCount())
                .ifPresent(findLeagueList::setChampionCount);

        Optional.ofNullable(leagueList.getMemberCount())
                .ifPresent(findLeagueList::setMemberCount);

        Optional.ofNullable(leagueList.getLeagueWinRecord())
                .ifPresent(findLeagueList::setLeagueWinRecord);

        Optional.ofNullable(leagueList.getLeagueDrawRecord())
                .ifPresent(findLeagueList::setLeagueDrawRecord);

        Optional.ofNullable(leagueList.getLeagueLoseRecord())
                .ifPresent(findLeagueList::setLeagueLoseRecord);

        Optional.ofNullable(leagueList.getHonorScore())
                .ifPresent(findLeagueList::setHonorScore);

        Optional.ofNullable(leagueList.getRanking())
                .ifPresent(findLeagueList::setRanking);

        Optional.ofNullable(leagueList.getAgeType())
                .ifPresent(findLeagueList::setAgeType);

        Optional.ofNullable(leagueList.getLocationType())
                .ifPresent(findLeagueList::setLocationType);

        Optional.ofNullable(leagueList.getManagerName())
                .ifPresent(findLeagueList::setManagerName);

        Optional.ofNullable(leagueList.getSubManagerName())
                .ifPresent(findLeagueList::setSubManagerName);

        Optional.ofNullable(leagueList.getUniformType())
                .ifPresent(findLeagueList::setUniformType);



        /*Optional.ofNullable(leagueList.getMostGoals())
                .ifPresent(findLeagueList::setMostGoals);

        Optional.ofNullable(leagueList.getMostAssist())
                .ifPresent(findLeagueList::setMostAssist);

        Optional.ofNullable(leagueList.getMostMom())
                .ifPresent(findLeagueList::setMostMom);*/

        return leagueListRepository.save(findLeagueList);
    }

    public LeagueList findLeagueList(long leagueListId) {
        return findVerifiedLeagueList(leagueListId);
    }

    public List<LeagueList> findLeagueListsNewest() {
        return leagueListRepository.findLeagueListsNewest();
    }

    public List<LeagueList> findLeagueListsLatest() {
        return leagueListRepository.findLeagueListsLatest();
    }

    public List<LeagueList> findHonorScore() {
        return leagueListRepository.findHonorScore();
    }


    public List<LeagueList> findAllLeaguesByLeagueId(long leagueId) {
        return leagueListRepository.findAllLeaguesByLeagueId(leagueId);
    }

    public List<LeagueList> findLeagueLists() {
        return leagueListRepository.findAll();
    }

    public LeagueList findLeagueList(int leagueListId) {
        return findVerifiedLeagueList(leagueListId);
    }

    public void deleteLeagueList(long leagueListId) {
        LeagueList findLeagueList = findVerifiedLeagueList(leagueListId);

        leagueListRepository.delete(findLeagueList);
    }

    public LeagueList findVerifiedLeagueList(long leagueListId) {
        Optional<LeagueList> optionalLeague = leagueListRepository.findById(leagueListId);
        LeagueList findLeagueList =
                optionalLeague.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findLeagueList;
    }
}
