package company.board_project.list.matchlist.service;

import company.board_project.apply.entity.Apply;
import company.board_project.apply.service.ApplyService;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.list.matchlist.repository.MatchListRepository;
import company.board_project.match.entity.Match;
import company.board_project.match.service.MatchService;
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
    private final UserService userService;
    private final ApplyService applyService;
    private final MatchService matchService;

    public MatchList createMatchList(
            MatchList matchList, Long userId,Long applyId, Long teamId, Long matchId) {
        User user = userService.findUser(userId);
        Apply apply = applyService.findApply(applyId);
        Team team = teamService.findTeam(teamId);
        Match match = matchService.findMatch(matchId);

        matchList.setUser(user);
        matchList.setApply(apply);
        matchList.setTeam(team);
        matchList.setMatch(match);

        matchList.setHomeTeamHonorScore(match.getHomeTeamHonorScore());
        matchList.setHomeTeamScore(matchList.getHomeTeamScore());
        matchList.setHomeTeamName(match.getHomeTeamName());
        matchList.setHomeTeamManagerName(match.getHomeTeamManagerName());
        matchList.setHomeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord());
        matchList.setHomeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord());
        matchList.setHomeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord());
        matchList.setHomeTeamLevelType(match.getHomeTeamLevelType());
        matchList.setHomeTeamAgeType(match.getHomeTeamAgeType());
        matchList.setHomeTeamUniformType(match.getHomeTeamUniformType());

        matchList.setAwayTeamHonorScore(matchList.getAwayTeamScore());
        matchList.setAwayTeamHonorScore(team.getHonorScore());
        matchList.setAwayTeamName(team.getTeamName());
        matchList.setAwayTeamManagerName(team.getManagerName());
        matchList.setAwayTeamTotalWinRecord(team.getTotalWinRecord());
        matchList.setAwayTeamTotalDrawRecord(team.getTotalDrawRecord());
        matchList.setAwayTeamTotalLoseRecord(team.getTotalLoseRecord());
        matchList.setAwayTeamLevelType(team.getLevelType());
        matchList.setAwayTeamAgeType(team.getAgeType());
        matchList.setAwayTeamUniformType(team.getUniformType());

        matchList.setAwayTeamScore(matchList.getAwayTeamScore());

        matchList.setHomeTeamName(match.getHomeTeamName());

        return matchListRepository.save(matchList);
    }

    public MatchList createMatchListByMatchController(
            MatchList matchList, Long userId, Long teamId, Long matchId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Match match = matchService.findMatch(matchId);

        matchList.setUser(user);
        matchList.setTeam(team);
        matchList.setMatch(match);

        matchList.setHomeTeamHonorScore(match.getHomeTeamHonorScore());
        matchList.setHomeTeamName(match.getHomeTeamName());
        matchList.setHomeTeamManagerName(match.getHomeTeamManagerName());
        matchList.setHomeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord());
        matchList.setHomeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord());
        matchList.setHomeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord());
        matchList.setHomeTeamLevelType(match.getHomeTeamLevelType());
        matchList.setHomeTeamAgeType(match.getHomeTeamAgeType());
        matchList.setHomeTeamUniformType(match.getHomeTeamUniformType());

        matchList.setAwayTeamHonorScore(team.getHonorScore());
        matchList.setAwayTeamName(team.getTeamName());
        matchList.setAwayTeamManagerName(team.getManagerName());
        matchList.setAwayTeamTotalWinRecord(team.getTotalWinRecord());
        matchList.setAwayTeamTotalDrawRecord(team.getTotalDrawRecord());
        matchList.setAwayTeamTotalLoseRecord(team.getTotalLoseRecord());
        matchList.setAwayTeamLevelType(team.getLevelType());
        matchList.setAwayTeamAgeType(team.getAgeType());
        matchList.setAwayTeamUniformType(team.getUniformType());

        matchList.setAwayTeamScore(matchList.getAwayTeamScore());

        matchList.setHomeTeamName(match.getHomeTeamName());

        return matchListRepository.save(matchList);
    }

    public MatchList updateMatchList(
            MatchList matchList,
            Long teamListId) {

        MatchList findMatchList = findVerifiedMatchList(teamListId);

        Optional.ofNullable(matchList.getHomeTeamHonorScore())
                .ifPresent(findMatchList::setHomeTeamHonorScore);

        Optional.ofNullable(matchList.getHomeTeamName())
                .ifPresent(findMatchList::setHomeTeamName);

        Optional.ofNullable(matchList.getHomeTeamManagerName())
                .ifPresent(findMatchList::setHomeTeamManagerName);

        Optional.ofNullable(matchList.getHomeTeamTotalWinRecord())
                .ifPresent(findMatchList::setHomeTeamTotalWinRecord);

        Optional.ofNullable(matchList.getHomeTeamTotalDrawRecord())
                .ifPresent(findMatchList::setHomeTeamTotalDrawRecord);

        Optional.ofNullable(matchList.getHomeTeamTotalLoseRecord())
                .ifPresent(findMatchList::setHomeTeamTotalLoseRecord);

        Optional.ofNullable(matchList.getHomeTeamLevelType())
                .ifPresent(findMatchList::setHomeTeamLevelType);

        Optional.ofNullable(matchList.getHomeTeamAgeType())
                .ifPresent(findMatchList::setHomeTeamAgeType);

        Optional.ofNullable(matchList.getHomeTeamUniformType())
                .ifPresent(findMatchList::setHomeTeamUniformType);

        Optional.ofNullable(matchList.getAwayTeamHonorScore())
                .ifPresent(findMatchList::setAwayTeamHonorScore);

        Optional.ofNullable(matchList.getAwayTeamName())
                .ifPresent(findMatchList::setAwayTeamName);

        Optional.ofNullable(matchList.getAwayTeamManagerName())
                .ifPresent(findMatchList::setAwayTeamManagerName);

        Optional.ofNullable(matchList.getAwayTeamTotalWinRecord())
                .ifPresent(findMatchList::setAwayTeamTotalWinRecord);

        Optional.ofNullable(matchList.getAwayTeamTotalDrawRecord())
                .ifPresent(findMatchList::setAwayTeamTotalDrawRecord);

        Optional.ofNullable(matchList.getAwayTeamTotalLoseRecord())
                .ifPresent(findMatchList::setAwayTeamTotalLoseRecord);

        Optional.ofNullable(matchList.getAwayTeamLevelType())
                .ifPresent(findMatchList::setAwayTeamLevelType);

        Optional.ofNullable(matchList.getAwayTeamAgeType())
                .ifPresent(findMatchList::setAwayTeamAgeType);

        Optional.ofNullable(matchList.getAwayTeamUniformType())
                .ifPresent(findMatchList::setAwayTeamUniformType);

        /*Optional.ofNullable(teamList.getMostGoals())
                .ifPresent(findTeamList::setMostGoals);

        Optional.ofNullable(teamList.getMostAssist())
                .ifPresent(findTeamList::setMostAssist);

        Optional.ofNullable(teamList.getMostMom())
                .ifPresent(findTeamList::setMostMom);*/

        return matchListRepository.save(findMatchList);
    }

    public MatchList findMatchList(long matchListId) {
        return findVerifiedMatchList(matchListId);
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
