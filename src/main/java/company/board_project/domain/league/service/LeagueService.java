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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

        findVerifiedExistsLeagueByTeamId(team.getTeamId());

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


    public League findLeague(Long leagueId) {return findVerifiedLeague(leagueId);}

    public Page<League> findLeagues(int page, int size) {
        return leagueRepository.findAll(PageRequest.of(page, size,
                Sort.by("leagueId").descending()));
    }

    // 최신 등록된 리그 순서 조회
    public List<League> findLeaguesNewest() {
        return leagueRepository.findLeaguesNewest();
    }

    // 오래된 순서 리그 조회
    public List<League> findLeaguesLatest() {
        return leagueRepository.findLeaguesLatest();
    }

    // 명예 점수 고득점 순서 조회
    public List<League> findHonorScore() {
        return leagueRepository.findHonorScore();
    }

    // 시즌 단위 조회 (시즌 진행중)
    public List<League> findLeagueOnSeason() {return leagueRepository.findLeagueOnSeason();}

    // 시즌 단위 조회 (시즌 종료)
    public List<League> findLeagueOffSeason() {return leagueRepository.findLeagueOffSeason();}

    // 시즌 단위 조회 (팀 모집)
    public List<League> findLeagueRecruit() {return leagueRepository.findLeagueRecruit();}

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

    public League findVerifiedExistsLeagueByTeamId(long teamId) {
        League league = leagueRepository.findByVerifiedTeamId(teamId);
        if(league ==null) {
            try {
            } catch (NoSuchElementException ex) {
                throw new BusinessLogicException(Exceptions.LEAGUE_EXISTS);
            }
            return league;
        }
        throw new BusinessLogicException(Exceptions.LEAGUE_EXISTS);
    }
}