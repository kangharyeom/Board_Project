package company.board_project.domain.match.league.service;

import company.board_project.domain.match.league.repository.LeagueMatchRepository;
import company.board_project.global.constant.MatchResultStatus;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.league.repository.LeagueRepository;
import company.board_project.domain.list.league.entity.LeagueList;
import company.board_project.domain.list.league.service.LeagueListService;
import company.board_project.domain.match.league.entity.LeagueMatch;
import company.board_project.domain.team.entity.Team;
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
public class LeagueMatchService {
    private final LeagueMatchRepository leagueMatchRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final LeagueListService leagueListService;
    private final LeagueRepository leagueRepository;

    public LeagueMatch createLeagueMatch(LeagueMatch leagueMatch
            , Long homeTeamUserId
            , Long awayTeamUserId
            , Long homeTeamId
            , Long awayTeamId
            , Long homeTeamLeagueListId
            , Long awayTeamLeagueListId
    ) {
        User homeTeamUser = userService.findUser(homeTeamUserId);
        User awayTeamUser = userService.findUser(awayTeamUserId);
        Team homeTeam = teamService.findTeam(homeTeamId);
        Team awayTeam = teamService.findTeam(awayTeamId);
        LeagueList homeTeamLeagueList = leagueListService.findLeagueList(homeTeamLeagueListId);
        LeagueList awayTeamLeagueList = leagueListService.findLeagueList(awayTeamLeagueListId);

        leagueMatch.setUser(homeTeamUser);
        leagueMatch.setUser(awayTeamUser);
        leagueMatch.setTeam(homeTeam);
        leagueMatch.setTeam(awayTeam);
        leagueMatch.setLeagueList(homeTeamLeagueList);
        leagueMatch.setLeagueList(awayTeamLeagueList);

        // 홈팀 정보 주입
        leagueMatch.setHomeTeamId(homeTeam.getTeamId());
        leagueMatch.setHomeTeamUserId(homeTeamUser.getUserId());
        leagueMatch.setHomeTeamLeagueListId(homeTeamLeagueList.getLeagueListId());

        // 어웨이팀 정보 주입
        leagueMatch.setAwayTeamId(awayTeam.getTeamId());
        leagueMatch.setAwayTeamUserId(awayTeamUser.getUserId());
        leagueMatch.setAwayTeamLeagueListId(awayTeamLeagueList.getLeagueListId());

        // 홈팀 정보 주입
        leagueMatch.setHomeTeamScore(leagueMatch.getHomeTeamScore());
        leagueMatch.setHomeTeamHonorScore(homeTeam.getHonorScore());
        leagueMatch.setHomeTeamName(homeTeam.getTeamName());
        leagueMatch.setHomeTeamManagerName(homeTeam.getManagerName());
        leagueMatch.setHomeTeamTotalWinRecord(homeTeam.getTotalWinRecord());
        leagueMatch.setHomeTeamTotalDrawRecord(homeTeam.getTotalDrawRecord());
        leagueMatch.setHomeTeamTotalLoseRecord(homeTeam.getTotalLoseRecord());
        leagueMatch.setHomeTeamLevelType(homeTeam.getLevelType());
        leagueMatch.setHomeTeamAgeType(homeTeam.getAgeType());
        leagueMatch.setHomeTeamUniformType(homeTeam.getUniformType());
        leagueMatch.setHomeTeamLeagueMatchPoints(homeTeamLeagueList.getLeagueMatchPoints());
        leagueMatch.setHomeTeamLeagueWinRecord(homeTeamLeagueList.getLeagueWinRecord());
        leagueMatch.setHomeTeamLeagueDrawRecord(homeTeamLeagueList.getLeagueDrawRecord());
        leagueMatch.setHomeTeamLeagueLoseRecord(homeTeamLeagueList.getLeagueLoseRecord());
        leagueMatch.setHomeTeamMatchResultStatus(leagueMatch.getHomeTeamMatchResultStatus());

        // 어웨이팀 정보 주입
        leagueMatch.setAwayTeamScore(leagueMatch.getAwayTeamScore());
        leagueMatch.setAwayTeamHonorScore(awayTeam.getHonorScore());
        leagueMatch.setAwayTeamName(awayTeam.getTeamName());
        leagueMatch.setAwayTeamManagerName(awayTeam.getManagerName());
        leagueMatch.setAwayTeamTotalWinRecord(awayTeam.getTotalWinRecord());
        leagueMatch.setAwayTeamTotalDrawRecord(awayTeam.getTotalDrawRecord());
        leagueMatch.setAwayTeamTotalLoseRecord(awayTeam.getTotalLoseRecord());
        leagueMatch.setAwayTeamLevelType(awayTeam.getLevelType());
        leagueMatch.setAwayTeamAgeType(awayTeam.getAgeType());
        leagueMatch.setAwayTeamUniformType(awayTeam.getUniformType());
        leagueMatch.setAwayTeamLeagueMatchPoints(awayTeamLeagueList.getLeagueMatchPoints());
        leagueMatch.setAwayTeamLeagueWinRecord(awayTeamLeagueList.getLeagueWinRecord());
        leagueMatch.setAwayTeamLeagueDrawRecord(awayTeamLeagueList.getLeagueDrawRecord());
        leagueMatch.setAwayTeamLeagueLoseRecord(awayTeamLeagueList.getLeagueLoseRecord());
        leagueMatch.setAwayTeamMatchResultStatus(leagueMatch.getAwayTeamMatchResultStatus());

        leagueMatchRepository.save(leagueMatch);

        return leagueMatch;
    }

    public LeagueMatch updateLeagueMatch(LeagueMatch leagueMatch, Long leagueMatchId) {

        LeagueMatch findLeagueMatch = findVerifiedLeagueMatch(leagueMatchId);

        Optional.ofNullable(leagueMatch.getHomeTeamHonorScore())
                .ifPresent(findLeagueMatch::setHomeTeamHonorScore);

        Optional.ofNullable(leagueMatch.getHomeTeamName())
                .ifPresent(findLeagueMatch::setHomeTeamName);

        Optional.ofNullable(leagueMatch.getHomeTeamManagerName())
                .ifPresent(findLeagueMatch::setHomeTeamManagerName);

        Optional.ofNullable(leagueMatch.getHomeTeamTotalWinRecord())
                .ifPresent(findLeagueMatch::setHomeTeamTotalWinRecord);

        Optional.ofNullable(leagueMatch.getHomeTeamTotalDrawRecord())
                .ifPresent(findLeagueMatch::setHomeTeamTotalDrawRecord);

        Optional.ofNullable(leagueMatch.getHomeTeamTotalLoseRecord())
                .ifPresent(findLeagueMatch::setHomeTeamTotalLoseRecord);


        Optional.ofNullable(leagueMatch.getHomeTeamLevelType())
                .ifPresent(findLeagueMatch::setHomeTeamLevelType);

        Optional.ofNullable(leagueMatch.getHomeTeamAgeType())
                .ifPresent(findLeagueMatch::setHomeTeamAgeType);

        Optional.ofNullable(leagueMatch.getHomeTeamUniformType())
                .ifPresent(findLeagueMatch::setHomeTeamUniformType);

        Optional.ofNullable(leagueMatch.getHomeTeamLeagueMatchPoints())
                .ifPresent(findLeagueMatch::setHomeTeamLeagueMatchPoints);

        Optional.ofNullable(leagueMatch.getHomeTeamLeagueWinRecord())
                .ifPresent(findLeagueMatch::setHomeTeamLeagueWinRecord);

        Optional.ofNullable(leagueMatch.getHomeTeamLeagueDrawRecord())
                .ifPresent(findLeagueMatch::setHomeTeamLeagueDrawRecord);

        Optional.ofNullable(leagueMatch.getHomeTeamLeagueLoseRecord())
                .ifPresent(findLeagueMatch::setHomeTeamLeagueLoseRecord);

        Optional.ofNullable(leagueMatch.getAwayTeamHonorScore())
                .ifPresent(findLeagueMatch::setAwayTeamHonorScore);

        Optional.ofNullable(leagueMatch.getAwayTeamName())
                .ifPresent(findLeagueMatch::setAwayTeamName);

        Optional.ofNullable(leagueMatch.getAwayTeamManagerName())
                .ifPresent(findLeagueMatch::setAwayTeamManagerName);

        Optional.ofNullable(leagueMatch.getAwayTeamTotalWinRecord())
                .ifPresent(findLeagueMatch::setAwayTeamTotalWinRecord);

        Optional.ofNullable(leagueMatch.getAwayTeamTotalDrawRecord())
                .ifPresent(findLeagueMatch::setAwayTeamTotalDrawRecord);

        Optional.ofNullable(leagueMatch.getAwayTeamTotalLoseRecord())
                .ifPresent(findLeagueMatch::setAwayTeamTotalLoseRecord);


        Optional.ofNullable(leagueMatch.getAwayTeamLevelType())
                .ifPresent(findLeagueMatch::setAwayTeamLevelType);

        Optional.ofNullable(leagueMatch.getAwayTeamAgeType())
                .ifPresent(findLeagueMatch::setAwayTeamAgeType);

        Optional.ofNullable(leagueMatch.getAwayTeamUniformType())
                .ifPresent(findLeagueMatch::setAwayTeamUniformType);

        Optional.ofNullable(leagueMatch.getAwayTeamLeagueMatchPoints())
                .ifPresent(findLeagueMatch::setAwayTeamLeagueMatchPoints);

        Optional.ofNullable(leagueMatch.getAwayTeamLeagueWinRecord())
                .ifPresent(findLeagueMatch::setAwayTeamLeagueWinRecord);

        Optional.ofNullable(leagueMatch.getAwayTeamLeagueDrawRecord())
                .ifPresent(findLeagueMatch::setAwayTeamLeagueDrawRecord);

        Optional.ofNullable(leagueMatch.getAwayTeamLeagueLoseRecord())
                .ifPresent(findLeagueMatch::setAwayTeamLeagueLoseRecord);

        Optional.ofNullable(leagueMatch.getMatchType())
                .ifPresent(findLeagueMatch::setMatchType);

        Optional.ofNullable(leagueMatch.getMatchTime())
                .ifPresent(findLeagueMatch::setMatchTime);

        Optional.ofNullable(leagueMatch.getMatchStatus())
                .ifPresent(findLeagueMatch::setMatchStatus);

        Optional.ofNullable(leagueMatch.getHomeTeamMatchResultStatus())
                .ifPresent(findLeagueMatch::setHomeTeamMatchResultStatus);

        Optional.ofNullable(leagueMatch.getAwayTeamMatchResultStatus())
                .ifPresent(findLeagueMatch::setAwayTeamMatchResultStatus);


        return leagueMatchRepository.save(findLeagueMatch);
    }

    public LeagueMatch updateLeagueMatchEnd(LeagueMatch leagueMatch
            , Long leagueMatchId
    ) {

        LeagueMatch findLeagueMatch = findVerifiedLeagueMatch(leagueMatchId);

        Optional.ofNullable(leagueMatch.getHomeTeamScore())
                .ifPresent(findLeagueMatch::setHomeTeamScore);

        Optional.ofNullable(leagueMatch.getAwayTeamScore())
                .ifPresent(findLeagueMatch::setAwayTeamScore);

        Optional.ofNullable(leagueMatch.getMatchStatus())
                .ifPresent(findLeagueMatch::setMatchStatus);

        return leagueMatchRepository.save(findLeagueMatch);
    }

    public LeagueMatch updateForLeagueMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            , Long leagueMatchId
    ) {
//        리그 매치 정보 수정
        LeagueMatch findLeagueMatch = findVerifiedLeagueMatch(leagueMatchId);

//        homeTeam 이긴 경우
        if(homeTeamScore>awayTeamScore){
            findLeagueMatch.setHomeTeamHonorScore(findLeagueMatch.getHomeTeamHonorScore()+300L);
            findLeagueMatch.setHomeTeamTotalWinRecord(findLeagueMatch.getHomeTeamTotalWinRecord()+1L);
            findLeagueMatch.setHomeTeamLeagueMatchPoints(findLeagueMatch.getHomeTeamLeagueMatchPoints()+3L);
            findLeagueMatch.setHomeTeamLeagueWinRecord(findLeagueMatch.getHomeTeamLeagueWinRecord()+1L);
            findLeagueMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("WIN"));

            findLeagueMatch.setAwayTeamHonorScore(findLeagueMatch.getAwayTeamHonorScore()+10L);
            findLeagueMatch.setAwayTeamTotalLoseRecord(findLeagueMatch.getAwayTeamTotalLoseRecord()+1L);
            findLeagueMatch.setAwayTeamLeagueLoseRecord(findLeagueMatch.getAwayTeamLeagueLoseRecord()+1L);
            findLeagueMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));



//        homeTeam 패배한 경우
        } else if(homeTeamScore<awayTeamScore){
            findLeagueMatch.setHomeTeamHonorScore(findLeagueMatch.getHomeTeamHonorScore()+10L);
            findLeagueMatch.setHomeTeamTotalLoseRecord(findLeagueMatch.getHomeTeamTotalLoseRecord()+1L);
            findLeagueMatch.setHomeTeamLeagueLoseRecord(findLeagueMatch.getHomeTeamLeagueLoseRecord()+1L);
            findLeagueMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));

            findLeagueMatch.setAwayTeamHonorScore(findLeagueMatch.getAwayTeamHonorScore()+300L);
            findLeagueMatch.setAwayTeamTotalWinRecord(findLeagueMatch.getAwayTeamTotalWinRecord()+1L);
            findLeagueMatch.setAwayTeamLeagueMatchPoints(findLeagueMatch.getAwayTeamLeagueMatchPoints()+3L);
            findLeagueMatch.setAwayTeamLeagueWinRecord(findLeagueMatch.getAwayTeamLeagueWinRecord()+1L);
            findLeagueMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("WIM"));



//        무승부인 경우
        } else {
            findLeagueMatch.setHomeTeamHonorScore(findLeagueMatch.getHomeTeamHonorScore()+100L);
            findLeagueMatch.setHomeTeamTotalDrawRecord(findLeagueMatch.getHomeTeamTotalDrawRecord()+1L);
            findLeagueMatch.setHomeTeamLeagueMatchPoints(findLeagueMatch.getHomeTeamLeagueMatchPoints()+1L);
            findLeagueMatch.setHomeTeamLeagueDrawRecord(findLeagueMatch.getHomeTeamLeagueDrawRecord()+1L);
            findLeagueMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

            findLeagueMatch.setAwayTeamHonorScore(findLeagueMatch.getAwayTeamHonorScore()+100L);
            findLeagueMatch.setAwayTeamTotalDrawRecord(findLeagueMatch.getAwayTeamTotalDrawRecord()+1L);
            findLeagueMatch.setAwayTeamLeagueMatchPoints(findLeagueMatch.getAwayTeamLeagueMatchPoints()+1L);
            findLeagueMatch.setAwayTeamLeagueDrawRecord(findLeagueMatch.getAwayTeamLeagueDrawRecord()+1L);
            findLeagueMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

        }

        return leagueMatchRepository.save(findLeagueMatch);
    }

    public LeagueMatch findLeagueMatch(Long LeagueMatchId) {
        return findVerifiedLeagueMatch(LeagueMatchId);
    }

    public Page<LeagueMatch> findLeagueMatches(int page, int size) {
        return leagueMatchRepository.findAll(PageRequest.of(page, size,
                Sort.by("leagueMatchId").descending()));
    }

    public List<LeagueMatch> findAllSearch(String keyword){
        return leagueMatchRepository.findAllSearch(keyword);
    }

    public List<LeagueMatch> findAllSearchByUserName(String name){
        return leagueMatchRepository.findAllSearchByUserName(name);
    }

    public List<LeagueMatch> findLeagueMatchesNewest() {
        return leagueMatchRepository.findLeagueMatchesNewest();
    }

    public List<LeagueMatch> findLeagueMatchesLatest() {
        return leagueMatchRepository.findLeagueMatchesLatest();
    }

    public void deleteLeagueMatch(Long leagueMatchId) {
        LeagueMatch findLeagueMatch = findVerifiedLeagueMatch(leagueMatchId);

        leagueMatchRepository.delete(findLeagueMatch);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

    public LeagueMatch findVerifiedLeagueMatch(Long leagueMatchId) {
        Optional<LeagueMatch> optionalLeagueMatch = leagueMatchRepository.findById(leagueMatchId);

        LeagueMatch findLeagueMatch =
                optionalLeagueMatch.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findLeagueMatch;
    }

    public League findVerifiedLeague(Long leagueId) {
        Optional<League> optionalMatch = leagueRepository.findById(leagueId);

        League findMatch =
                optionalMatch.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findMatch;
    }

}