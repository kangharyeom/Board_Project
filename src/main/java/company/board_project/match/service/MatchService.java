package company.board_project.match.service;

import company.board_project.content.entity.Content;
import company.board_project.content.entity.ContentFile;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.match.entity.Match;
import company.board_project.match.repository.MatchRepository;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    public Match createMatch(Match match) {



        matchRepository.save(match);

        return match;
    }

    public Match updateMatch(Match match) {

        Match findMatch = findVerifiedMatch(match.getMatchId());

        Optional.ofNullable(match.getHomeScore())
                .ifPresent(findMatch::setHomeScore);

        Optional.ofNullable(match.getAwayScore())
                .ifPresent(findMatch::setAwayScore);

        Optional.ofNullable(match.getMatchType())
                .ifPresent(findMatch::setMatchType);

        Optional.ofNullable(match.getAgeType())
                .ifPresent(findMatch::setAgeType);

        Optional.ofNullable(match.getLocationType())
                .ifPresent(findMatch::setLocationType);

        Optional.ofNullable(match.getMatchTime())
                .ifPresent(findMatch::setMatchTime);

        Optional.ofNullable(match.getLevelType())
                .ifPresent(findMatch::setLevelType);

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

    public Page<Match> findMatchs(int page, int size) {
        return matchRepository.findAll(PageRequest.of(page, size,
                Sort.by("matchId").descending()));
    }

    public List<Match> findAllSearch(String keyword){
        return matchRepository.findAllSearch(keyword);
    }

    public List<Match> findAllSearchByUserName(String name){
        return matchRepository.findAllSearchByUserName(name);
    }

    public List<Match> findMatchsNewest() {
        return matchRepository.findMatchsNewest();
    }

    public List<Match> findMatchsLatest() {
        return matchRepository.findMatchsLatest();
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
        Optional<Match> optionalMatch = matchRepository.findByMatchId(matchId);

        Match findMatch =
                optionalMatch.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findMatch;
    }
}