package company.board_project.domain.list.matchlist.service;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.service.ApplyService;
import company.board_project.domain.list.matchlist.repository.MatchListRepository;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.global.constant.MatchResultStatus;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.list.matchlist.entity.MatchList;
import company.board_project.domain.match.normalmatch.entity.Match;
import company.board_project.domain.match.normalmatch.service.MatchService;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchListService {
    private final MatchListRepository matchListRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final ApplyService applyService;
    private final MatchService matchService;

    public MatchList createMatchList(
            MatchList matchList, Long awayTeamUserId,Long applyId, Long awayTeamId, Long matchId) {
        User user = userService.findUser(awayTeamUserId);
        Apply apply = applyService.findApply(applyId);
        Team team = teamService.findTeam(awayTeamId);
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

        user.setMatchId(matchId);

        matchList.setUser(user);
        matchList.setTeam(team);
        matchList.setMatch(match);

        matchList.setHomeTeamId(team.getTeamId());
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

        userRepository.save(user);

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

        Optional.ofNullable(matchList.getAwayTeamUserId())
                .ifPresent(findMatchList::setAwayTeamUserId);

        Optional.ofNullable(matchList.getAwayTeamId())
                .ifPresent(findMatchList::setAwayTeamId);

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

    public MatchList updateMatchListWithAwayTeam(
            MatchList matchList
            ,Long matchListId
    ) {

        MatchList findMatchList = findVerifiedMatchList(matchListId);

        Optional.ofNullable(matchList.getAwayTeamHonorScore())
                .ifPresent(findMatchList::setAwayTeamHonorScore);

        Optional.ofNullable(matchList.getAwayTeamUserId())
                .ifPresent(findMatchList::setAwayTeamUserId);

        Optional.ofNullable(matchList.getAwayTeamId())
                .ifPresent(findMatchList::setAwayTeamId);

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

    public MatchList updateMatchEnd(MatchList matchList
            , Long matchListId
    ) {

        MatchList findMatchList = findVerifiedMatchList(matchListId);

        Optional.ofNullable(matchList.getHomeTeamScore())
                .ifPresent(findMatchList::setHomeTeamScore);

        Optional.ofNullable(matchList.getAwayTeamScore())
                .ifPresent(findMatchList::setAwayTeamScore);

        Optional.ofNullable(matchList.getMatchStatus())
                .ifPresent(findMatchList::setMatchStatus);

        return matchListRepository.save(findMatchList);
    }

    public MatchList updateForMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            , Long matchListId
    ) {
//        리그 매치 정보 수정
        MatchList findMatchList = findVerifiedMatchList(matchListId);

//        homeTeam 이긴 경우
        if(homeTeamScore>awayTeamScore){
            findMatchList.setHomeTeamHonorScore(findMatchList.getHomeTeamHonorScore()+300L);
            findMatchList.setHomeTeamTotalWinRecord(findMatchList.getHomeTeamTotalWinRecord()+1L);
            findMatchList.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("WIN"));

            findMatchList.setAwayTeamHonorScore(findMatchList.getAwayTeamHonorScore()+10L);
            findMatchList.setAwayTeamTotalLoseRecord(findMatchList.getAwayTeamTotalLoseRecord()+1L);
            findMatchList.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));



//        homeTeam 패배한 경우
        } else if(homeTeamScore<awayTeamScore){
            findMatchList.setHomeTeamHonorScore(findMatchList.getHomeTeamHonorScore()+10L);
            findMatchList.setHomeTeamTotalLoseRecord(findMatchList.getHomeTeamTotalLoseRecord()+1L);
            findMatchList.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));

            findMatchList.setAwayTeamHonorScore(findMatchList.getAwayTeamHonorScore()+300L);
            findMatchList.setAwayTeamTotalWinRecord(findMatchList.getAwayTeamTotalWinRecord()+1L);
            findMatchList.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("WIM"));



//        무승부인 경우
        } else {
            findMatchList.setHomeTeamHonorScore(findMatchList.getHomeTeamHonorScore()+100L);
            findMatchList.setHomeTeamTotalDrawRecord(findMatchList.getHomeTeamTotalDrawRecord()+1L);
            findMatchList.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

            findMatchList.setAwayTeamHonorScore(findMatchList.getAwayTeamHonorScore()+100L);
            findMatchList.setAwayTeamTotalDrawRecord(findMatchList.getAwayTeamTotalDrawRecord()+1L);
            findMatchList.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

        }
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
