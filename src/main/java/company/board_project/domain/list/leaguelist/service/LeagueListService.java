package company.board_project.domain.list.leaguelist.service;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.service.ApplyService;
import company.board_project.domain.list.leaguelist.repository.LeagueListRepository;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.league.repository.LeagueRepository;
import company.board_project.domain.league.service.LeagueService;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.service.UserService;
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
    private final LeagueRepository leagueRepository;
    public LeagueList createLeagueList(
            LeagueList leagueList, Long userId, Long teamId, Long applyId, Long leagueId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);
        League league = leagueService.findLeague(leagueId);

        leagueList.setUser(user);
        leagueList.setTeam(team);
        leagueList.setApply(apply);
        leagueList.setLeague(league);

        leagueList.setManagerName(user.getName());

        leagueList.setHonorScore(team.getHonorScore());
        leagueList.setChampionCount(team.getChampionCount());
        leagueList.setMemberCount(team.getMemberCount());
        leagueList.setTeamName(team.getTeamName());
        leagueList.setSubManagerName(team.getSubManagerName());

        leagueList.setLeagueName(league.getLeagueName());

        leagueList.setTeamAssist(leagueList.getTeamAssist());
        leagueList.setTeamGoals(leagueList.getTeamGoals());
        leagueList.setLeagueHonorScore(leagueList.getLeagueHonorScore());
        leagueList.setLeagueMatchCount(leagueList.getLeagueMatchCount());

//        비즈니스 로직
        league.setTeamCount(+1L);
        league.setMemberCount(team.getMemberCount()+leagueList.getMemberCount());
        leagueRepository.save(league);

        return leagueListRepository.save(leagueList);
    }

    public LeagueList createLeagueListByLeagueController(
            LeagueList leagueList, Long userId, Long teamId, Long leagueId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        League league = leagueService.findLeague(leagueId);

        leagueList.setUser(user);
        leagueList.setTeam(team);
        leagueList.setLeague(league);

        leagueList.setLeagueName(league.getLeagueName());
        leagueList.setLeagueWinRecord(0L);
        leagueList.setLeagueLoseRecord(0L);
        leagueList.setLeagueDrawRecord(0L);
        leagueList.setLeagueMatchPoints(0L);
        leagueList.setLeagueMatchCount(0L);

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
        leagueList.setCleanSheet(leagueList.getCleanSheet());

        leagueList.setManagerName(user.getName());

        return leagueListRepository.save(leagueList);
    }

    public LeagueList updateLeagueList(
            LeagueList leagueList,
            Long leagueListId) {

        LeagueList findLeagueList = findVerifiedLeagueList(leagueListId);

        Optional.ofNullable(leagueList.getFormation())
                .ifPresent(findLeagueList::setFormation);

        Optional.ofNullable(leagueList.getChampionCount())
                .ifPresent(findLeagueList::setChampionCount);

        Optional.ofNullable(leagueList.getMemberCount())
                .ifPresent(findLeagueList::setMemberCount);

        Optional.ofNullable(leagueList.getLeagueMatchCount())
                .ifPresent(findLeagueList::setLeagueMatchCount);

        Optional.ofNullable(leagueList.getLeagueWinRecord())
                .ifPresent(findLeagueList::setLeagueWinRecord);

        Optional.ofNullable(leagueList.getLeagueDrawRecord())
                .ifPresent(findLeagueList::setLeagueDrawRecord);

        Optional.ofNullable(leagueList.getLeagueLoseRecord())
                .ifPresent(findLeagueList::setLeagueLoseRecord);

        Optional.ofNullable(leagueList.getHonorScore())
                .ifPresent(findLeagueList::setHonorScore);

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

    public LeagueList updateForLeagueMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            ,Long homeTeamLeagueListId
            ,Long awayTeamLeagueListId
    ) {

        LeagueList findHomeTeamLeagueList = findVerifiedLeagueList(homeTeamLeagueListId);
        LeagueList findAwayTeamLeagueList = findVerifiedLeagueList(awayTeamLeagueListId);

//        homeTeam 승리한 경우
        if(homeTeamScore>awayTeamScore){
            findHomeTeamLeagueList.setHonorScore(findHomeTeamLeagueList.getHonorScore()+300L);
            findHomeTeamLeagueList.setLeagueMatchPoints(findHomeTeamLeagueList.getLeagueMatchPoints()+3L);
            findHomeTeamLeagueList.setLeagueWinRecord(findHomeTeamLeagueList.getLeagueWinRecord()+1L);
            findHomeTeamLeagueList.setLeagueMatchCount(findHomeTeamLeagueList.getLeagueMatchCount()+1L);

            findAwayTeamLeagueList.setHonorScore(findAwayTeamLeagueList.getHonorScore()+10L);
            findAwayTeamLeagueList.setLeagueLoseRecord(findAwayTeamLeagueList.getLeagueLoseRecord()+1L);
            findAwayTeamLeagueList.setLeagueMatchCount(findAwayTeamLeagueList.getLeagueMatchCount()+1L);

//        homeTeam 패배한 경우
        } else if(homeTeamScore<awayTeamScore){
            findHomeTeamLeagueList.setHonorScore(findHomeTeamLeagueList.getHonorScore()+10L);
            findHomeTeamLeagueList.setLeagueLoseRecord(findHomeTeamLeagueList.getLeagueLoseRecord()+1L);
            findHomeTeamLeagueList.setLeagueMatchCount(findHomeTeamLeagueList.getLeagueMatchCount()+1L);

            findAwayTeamLeagueList.setHonorScore(findAwayTeamLeagueList.getHonorScore()+300L);
            findAwayTeamLeagueList.setLeagueMatchPoints(findAwayTeamLeagueList.getLeagueMatchPoints()+3L);
            findAwayTeamLeagueList.setLeagueWinRecord(findAwayTeamLeagueList.getLeagueWinRecord()+1L);
            findAwayTeamLeagueList.setLeagueMatchCount(findAwayTeamLeagueList.getLeagueMatchCount()+1L);



//        무승부인 경우
        } else {
            findHomeTeamLeagueList.setHonorScore(findHomeTeamLeagueList.getHonorScore()+100L);
            findHomeTeamLeagueList.setLeagueMatchPoints(findHomeTeamLeagueList.getLeagueMatchPoints()+1L);
            findHomeTeamLeagueList.setLeagueDrawRecord(findHomeTeamLeagueList.getLeagueDrawRecord()+1L);
            findHomeTeamLeagueList.setLeagueMatchCount(findHomeTeamLeagueList.getLeagueMatchCount()+1L);

            findAwayTeamLeagueList.setHonorScore(findAwayTeamLeagueList.getHonorScore()+100L);
            findAwayTeamLeagueList.setLeagueMatchPoints(findAwayTeamLeagueList.getLeagueMatchPoints()+1L);
            findAwayTeamLeagueList.setLeagueDrawRecord(findAwayTeamLeagueList.getLeagueDrawRecord()+1L);
            findAwayTeamLeagueList.setLeagueMatchCount(findAwayTeamLeagueList.getLeagueMatchCount()+1L);

        }
        leagueListRepository.save(findHomeTeamLeagueList);
        return leagueListRepository.save(findAwayTeamLeagueList);
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
