package company.board_project.league.service;

import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.league.entity.League;
import company.board_project.league.repository.LeagueRepository;
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
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final UserRepository userRepository;

    public League createLeague(League league) {
        leagueRepository.save(league);

        return league;
    }

    // 게시글 수정 //
    public League updateLeague(League league) {

        League findLeague = findVerifiedLeague(league.getLeagueId());

        Optional.ofNullable(league.getLeagueName())
                .ifPresent(findLeague::setLeagueName);

        Optional.ofNullable(league.getSportType())
                .ifPresent(findLeague::setSportType);

        Optional.ofNullable(league.getParticipantTeamName())
                .ifPresent(findLeague::setParticipantTeamName);

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

    // 게시글 단건 조회 //
    public League findLeague(Long leagueId) {
        return findVerifiedLeague(leagueId);
    }

    // 게시글 전체 조회 //
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