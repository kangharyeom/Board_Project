package company.board_project.list.matchlist.service;

import company.board_project.apply.entity.Apply;
import company.board_project.apply.service.ApplyService;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.league.entity.League;
import company.board_project.league.service.LeagueService;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.list.matchlist.repository.MatchListRepository;
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
public class MatchListService {
    private final MatchListRepository matchListRepository;
    private final TeamService teamService;
    private final LeagueService leagueService;
    private final UserService userService;
    private final ApplyService applyService;
    public MatchList createMatchList(
            MatchList matchList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

        matchList.setUser(user);
        matchList.setTeam(team);
        matchList.setApply(apply);
        matchList.setManagerName(user.getName());

        return matchListRepository.save(matchList);
    }

    public MatchList createLeagueTeamListByLeagueController(
            MatchList matchList, Long userId, Long teamId, Long leagueId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);


        League league = leagueService.findLeague(leagueId);

        matchList.setUser(user);
        matchList.setTeam(team);
        matchList.setLeague(league);

        matchList.setTeamName(team.getTeamName());
        matchList.setHonorScore(team.getHonorScore());
        matchList.setAgeType(team.getAgeType());
        matchList.setLocationType(team.getLocationType());
        matchList.setLevelType(team.getLevelType());
        matchList.setUniformType(team.getUniformType());

        matchList.setManagerName(user.getName());

        return matchListRepository.save(matchList);
    }

    public MatchList createLeagueTeamList(
            MatchList matchList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

//        League league = new League();

        matchList.setUser(user);
        matchList.setTeam(team);
//        teamList.setLeague(league);
        matchList.setApply(apply);

//        teamList.setLeagueName(league.getLeagueName());

        matchList.setTeamName(team.getTeamName());
        matchList.setHonorScore(team.getHonorScore());
        matchList.setAgeType(team.getAgeType());
        matchList.setLocationType(team.getLocationType());
        matchList.setLevelType(team.getLevelType());
        matchList.setUniformType(team.getUniformType());

        matchList.setManagerName(user.getName());

        return matchListRepository.save(matchList);
    }

    public MatchList updateMatchList(
            MatchList matchList,
            Long teamListId) {

        MatchList findMatchList = findVerifiedMatchList(teamListId);

        Optional.ofNullable(matchList.getWinRecord())
                .ifPresent(findMatchList::setWinRecord);

        Optional.ofNullable(matchList.getDrawRecord())
                .ifPresent(findMatchList::setDrawRecord);

        Optional.ofNullable(matchList.getLoseRecord())
                .ifPresent(findMatchList::setLoseRecord);

        Optional.ofNullable(matchList.getHonorScore())
                .ifPresent(findMatchList::setHonorScore);

        Optional.ofNullable(matchList.getRanking())
                .ifPresent(findMatchList::setRanking);

        Optional.ofNullable(matchList.getAgeType())
                .ifPresent(findMatchList::setAgeType);

        Optional.ofNullable(matchList.getLocationType())
                .ifPresent(findMatchList::setLocationType);

        Optional.ofNullable(matchList.getManagerName())
                .ifPresent(findMatchList::setManagerName);

        Optional.ofNullable(matchList.getUniformType())
                .ifPresent(findMatchList::setUniformType);



        /*Optional.ofNullable(teamList.getMostGoals())
                .ifPresent(findTeamList::setMostGoals);

        Optional.ofNullable(teamList.getMostAssist())
                .ifPresent(findTeamList::setMostAssist);

        Optional.ofNullable(teamList.getMostMom())
                .ifPresent(findTeamList::setMostMom);*/

        return matchListRepository.save(findMatchList);
    }

    public MatchList findTeamList(long teamListId) {
        return findVerifiedMatchList(teamListId);
    }

    public List<MatchList> findMatchListsNewest() {
        return matchListRepository.findMatchListsNewest();
    }

    public List<MatchList> findMatchListsLatest() {
        return matchListRepository.findMatchListsLatest();
    }

    public List<MatchList> findHonorScore() {
        return matchListRepository.findHonorScore();
    }


    public List<MatchList> findAllMatchListsByLeagueId(long leagueId) {
        return matchListRepository.findAllMatchListsByLeagueId(leagueId);
    }

    public List<MatchList> findMatchLists() {
        return matchListRepository.findAll();
    }

    public MatchList findMatchList(long teamListId) {
        return findVerifiedMatchList(teamListId);
    }

    public void deleteMatchList(long teamListId) {
        MatchList findMatchList = findVerifiedMatchList(teamListId);

        matchListRepository.delete(findMatchList);
    }

    public MatchList findVerifiedMatchList(long teamListId) {
        Optional<MatchList> optionalTeam = matchListRepository.findById(teamListId);
        MatchList findMatchList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findMatchList;
    }
}
