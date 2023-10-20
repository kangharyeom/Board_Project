package company.board_project.domain.league.service;

import company.board_project.domain.league.repository.LeagueRepository;
import company.board_project.global.constant.LeagueRole;
import company.board_project.global.constant.SeasonType;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.list.leaguelist.repository.LeagueListRepository;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.repository.TeamRepository;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final LeagueListRepository leagueListRepository;

    /*
    * 리그 생성
    */
    public League createLeague(League league, Long userId, Long teamId) {
        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        league.setUser(user);
        user.setLeagueRole(LeagueRole.LEAGUE_MANAGER);
        league.setTeam(team);
        team.setLeagueName(league.getLeagueName());
        league.setManagerName(user.getName());
        league.setManagerTeamName(team.getTeamName());
        league.setHonorScore(team.getHonorScore());

        userRepository.save(user);
        teamRepository.save(team);
        leagueRepository.save(league);

        return league;
    }

    public League updateLeague(League league) {

        League findLeague = findVerifiedLeague(league.getLeagueId());

        // 리그 관리자만 수정 가능하도록
        User writer = userService.findVerifiedUserByLeagueRole(findLeague.getUser().getLeagueRole());
        if (userService.getLoginUser().getUserId() != writer.getUserId()) // 로그인한 유저와 관리자가 다른 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED);

        Optional.ofNullable(league.getLeagueName())
                .ifPresent(findLeague::setLeagueName);

        Optional.ofNullable(league.getManagerTeamName())
                .ifPresent(findLeague::setManagerTeamName);

        Optional.ofNullable(league.getSportsType())
                .ifPresent(findLeague::setSportsType);

        Optional.ofNullable(league.getMatchCount())
                .ifPresent(findLeague::setMatchCount);

        Optional.ofNullable(league.getTeamCount())
                .ifPresent(findLeague::setTeamCount);

        Optional.ofNullable(league.getAgeType())
                .ifPresent(findLeague::setAgeType);

        Optional.ofNullable(league.getLocationType())
                .ifPresent(findLeague::setLocationType);

        Optional.ofNullable(league.getPeriod())
                .ifPresent(findLeague::setPeriod);

        Optional.ofNullable(league.getLevelType())
                .ifPresent(findLeague::setLevelType);

        Optional.ofNullable(league.getLeagueRules())
                .ifPresent(findLeague::setLeagueRules);

        Optional.ofNullable(league.getFrequency())
                .ifPresent(findLeague::setFrequency);

        return leagueRepository.save(findLeague);
    }

    public League checkEndTheLeague(
            Long leagueId
    ) {
        League league = findVerifiedLeague(leagueId);
        long leagueEndCount = league.getLeagueEndCount();
        long teamCount = league.getTeamCount();
        long matchCount = league.getMatchCount();
        long endCount = (teamCount * matchCount) / 2;

        if(leagueEndCount == endCount){
            league.setSeasonType(SeasonType.valueOf("OFF_SEASON"));
            LeagueList leagueList = leagueListRepository.findWinnerByLeagueId(leagueId);
            Team team = teamService.findTeam(leagueList.getTeam().getTeamId());
            leagueList.setChampionCount(leagueList.getChampionCount()+1L);
            team.setChampionCount(team.getChampionCount()+1L);
        }

        return leagueRepository.save(league);
    }

    public League updateForLeagueMatchEnd(
            Long leagueId
    ) {
        League findLeague = findVerifiedLeague(leagueId);

        findLeague.setLeagueEndCount(findLeague.getLeagueEndCount()+1L);

        return leagueRepository.save(findLeague);
    }


    public League findLeague(Long leagueId) {
        return findVerifiedLeague(leagueId);
    }

    public Page<League> findLeagues(int page, int size) {
        return leagueRepository.findAll(PageRequest.of(page, size,
                Sort.by("leagueId").descending()));
    }

    public List<League> findLeaguesNewest() {
        return leagueRepository.findLeaguesNewest();
    }

    public List<League> findLeaguesLatest() {
        return leagueRepository.findLeaguesLatest();
    }

    public List<League> findHonorScore() {
        return leagueRepository.findHonorScore();
    }

    public void deleteLeague(Long leagueId) {
        League findLeague = findVerifiedLeague(leagueId);

        leagueRepository.delete(findLeague);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

    public League findVerifiedLeague(Long leagueId) {
        Optional<League> optionalLeague = leagueRepository.findByLeagueId(leagueId);

        League findLeague =
                optionalLeague.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findLeague;
    }
}