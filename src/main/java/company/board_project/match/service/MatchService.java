package company.board_project.match.service;

import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.match.entity.Match;
import company.board_project.match.repository.MatchRepository;
import company.board_project.team.entity.Team;
import company.board_project.team.service.TeamService;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
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
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
//    private final LeagueListService leagueListService;

    public Match createMatch(Match match, Long userId, Long teamId) {
        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        match.setUser(user);
        match.setTeam(team);

        match.setHomeTeamScore(match.getHomeTeamScore());
        match.setHomeTeamHonorScore(team.getHonorScore());
        match.setHomeTeamName(team.getTeamName());
        match.setHomeTeamManagerName(team.getManagerName());
        match.setHomeTeamTotalWinRecord(team.getTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(team.getTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(team.getTotalLoseRecord());
        match.setHomeTeamRanking(team.getRanking());
        match.setHomeTeamLevelType(team.getLevelType());
        match.setHomeTeamAgeType(team.getAgeType());
        match.setHomeTeamUniformType(team.getUniformType());

        matchRepository.save(match);

        return match;
    }

    public Match createLeagueMatch(Match match, Long userId, Long teamId) {
        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        match.setUser(user);
        match.setTeam(team);

        match.setHomeTeamScore(match.getHomeTeamScore());
        match.setHomeTeamHonorScore(match.getHomeTeamHonorScore());
        match.setHomeTeamName(match.getHomeTeamName());
        match.setHomeTeamManagerName(match.getHomeTeamManagerName());
        match.setHomeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord());
        match.setHomeTeamLeagueMatchPoints(match.getHomeTeamLeagueMatchPoints());
        match.setHomeTeamLeagueWinRecord(match.getHomeTeamLeagueWinRecord());
        match.setHomeTeamLeagueDrawRecord(match.getHomeTeamLeagueDrawRecord());
        match.setHomeTeamLeagueLoseRecord(match.getHomeTeamLeagueLoseRecord());
        match.setHomeTeamRanking(match.getHomeTeamRanking());
        match.setHomeTeamLevelType(match.getHomeTeamLevelType());
        match.setHomeTeamAgeType(match.getHomeTeamAgeType());
        match.setHomeTeamUniformType(match.getHomeTeamUniformType());

        matchRepository.save(match);

        return match;
    }

    public Match updateMatch(Match match) {

        Match findMatch = findVerifiedMatch(match.getMatchId());

        Optional.ofNullable(match.getHomeTeamHonorScore())
                .ifPresent(findMatch::setHomeTeamHonorScore);

        Optional.ofNullable(match.getHomeTeamName())
                .ifPresent(findMatch::setHomeTeamName);

        Optional.ofNullable(match.getHomeTeamManagerName())
                .ifPresent(findMatch::setHomeTeamManagerName);

        Optional.ofNullable(match.getHomeTeamTotalWinRecord())
                .ifPresent(findMatch::setHomeTeamTotalWinRecord);

        Optional.ofNullable(match.getHomeTeamTotalDrawRecord())
                .ifPresent(findMatch::setHomeTeamTotalDrawRecord);

        Optional.ofNullable(match.getHomeTeamTotalLoseRecord())
                .ifPresent(findMatch::setHomeTeamTotalLoseRecord);

        Optional.ofNullable(match.getHomeTeamRanking())
                .ifPresent(findMatch::setHomeTeamRanking);

        Optional.ofNullable(match.getHomeTeamLevelType())
                .ifPresent(findMatch::setHomeTeamLevelType);

        Optional.ofNullable(match.getHomeTeamAgeType())
                .ifPresent(findMatch::setHomeTeamAgeType);

        Optional.ofNullable(match.getHomeTeamUniformType())
                .ifPresent(findMatch::setHomeTeamUniformType);

        Optional.ofNullable(match.getHomeTeamLeagueMatchPoints())
                .ifPresent(findMatch::setHomeTeamLeagueMatchPoints);

        Optional.ofNullable(match.getHomeTeamLeagueWinRecord())
                .ifPresent(findMatch::setHomeTeamLeagueWinRecord);

        Optional.ofNullable(match.getHomeTeamLeagueDrawRecord())
                .ifPresent(findMatch::setHomeTeamLeagueDrawRecord);

        Optional.ofNullable(match.getHomeTeamLeagueLoseRecord())
                .ifPresent(findMatch::setHomeTeamLeagueLoseRecord);


        Optional.ofNullable(match.getMatchType())
                .ifPresent(findMatch::setMatchType);

        Optional.ofNullable(match.getMatchTime())
                .ifPresent(findMatch::setMatchTime);

        Optional.ofNullable(match.getMatchStatus())
                .ifPresent(findMatch::setMatchStatus);

        Optional.ofNullable(match.getHomeTeamMatchResultStatus())
                .ifPresent(findMatch::setHomeTeamMatchResultStatus);

        Optional.ofNullable(match.getAwayTeamMatchResultStatus())
                .ifPresent(findMatch::setAwayTeamMatchResultStatus);


        return matchRepository.save(findMatch);
    }

    public Match findMatch(Long matchId) {
        return findVerifiedMatch(matchId);
    }

    public Page<Match> findMatches(int page, int size) {
        return matchRepository.findAll(PageRequest.of(page, size,
                Sort.by("matchId").descending()));
    }

    public List<Match> findAllSearch(String keyword){
        return matchRepository.findAllSearch(keyword);
    }

    public List<Match> findAllSearchByUserName(String name){
        return matchRepository.findAllSearchByUserName(name);
    }

    public List<Match> findMatchesNewest() {
        return matchRepository.findMatchesNewest();
    }

    public List<Match> findMatchesLatest() {
        return matchRepository.findMatchesLatest();
    }

    public void deleteMatch(Long matchId) {
        Match findMatch = findVerifiedMatch(matchId);

        matchRepository.delete(findMatch);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

    public Match findVerifiedMatch(Long matchId) {
        Optional<Match> optionalMatch = matchRepository.findById(matchId);

        Match findMatch =
                optionalMatch.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findMatch;
    }
}