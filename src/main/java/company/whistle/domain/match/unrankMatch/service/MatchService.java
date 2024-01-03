package company.whistle.domain.match.unrankMatch.service;

import company.whistle.domain.match.unrankMatch.entity.Match;
import company.whistle.domain.match.unrankMatch.repository.MatchRepository;
import company.whistle.domain.team.team.repository.TeamRepository;
import company.whistle.domain.team.squad.entity.Squad;
import company.whistle.domain.team.squad.service.SquadService;
import company.whistle.global.constant.MatchResultStatus;
import company.whistle.global.constant.MatchStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.team.team.entity.Team;
import company.whistle.domain.team.team.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final SquadService squadService;
    private final TeamRepository teamRepository;

    public Match createHomeTeamToMatch(Match match) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (loginUserId == null) {
                throw new BusinessLogicException(Exceptions.USER_ID_IS_NULL);
            }

            Squad squad = squadService.findByUserId(loginUserId);
            Team team  = teamService.findByUserId(squad.getTeam().getTeamId());

            /*
             * CHECK TEAM EXIST
             * MATCH 를 CREATE 하는 팀이 이미 경기를 진행중이거나 진행 예정인지 확인
             * */
            existByTeamId(team.getTeamId());

            /*
             * CHECK USER AUTHORIZATION
             * 유저가 관리자 권한을 가지고 접근하는지 검사
             * */
            String loginUserName = loginUser.getName();
            if (!Objects.equals(team.getManagerName(), loginUserName)) {
                if (!Objects.equals(team.getSubManagerName(), loginUserName)) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            match.setUser(loginUser);
            match.setTeam(team);

            match.setHomeTeamName(team.getTeamName());
            match.setHomeTeamManagerName(team.getManagerName());
            match.setHomeTeamTotalWinRecord(team.getTotalWinRecord());
            match.setHomeTeamTotalDrawRecord(team.getTotalDrawRecord());
            match.setHomeTeamTotalLoseRecord(team.getTotalLoseRecord());
            match.setHomeTeamLevelType(team.getLevelType());
            match.setHomeTeamAgeType(team.getAgeType());
            match.setHomeTeamUniformType(team.getUniformType());

            matchRepository.save(match);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.MATCH_NOT_CREATED);
        }
        return match;
    }

    public Match postAwayTeamForMatch(Match match, Long matchId, Long matchApplyId, Long awayTeamUserId, Long awayTeamId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (matchId == null || loginUserId == null) {
                log.info("matchId:{}", matchId);
                log.info("loginUserId:{}", loginUserId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }

            Match findMatch = findByMatchId(match.getMatchId());
            if (findMatch.getMatchStatus() != MatchStatus.valueOf("RIVAL_RECRUIT")) {
                throw new BusinessLogicException(Exceptions.MATCH_IS_NOT_RECRUIT_RIVAL);
            }

            Team homeTeam = teamService.findByTeamId(findMatch.getHomeTeamId());
            String loginUserName = loginUser.getName();
            if (!Objects.equals(homeTeam.getManagerName(), loginUserName)) {
                if (!Objects.equals(homeTeam.getSubManagerName(), loginUserName)) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            Team team = teamService.findByTeamId(awayTeamId);
            userService.findByUserId(awayTeamUserId);

            findByMatchApplyId(matchApplyId);

            Optional.of(awayTeamId)
                    .ifPresent(findMatch::setAwayTeamId);

            Optional.of(awayTeamUserId)
                    .ifPresent(findMatch::setAwayTeamUserId);

            Optional.ofNullable(team.getHonorScore())
                    .ifPresent(findMatch::setAwayTeamHonorScore);

            Optional.ofNullable(team.getTeamName()).ifPresent(findMatch::setAwayTeamName);

            Optional.ofNullable(team.getManagerName())
                    .ifPresent(findMatch::setAwayTeamManagerName);

            Optional.ofNullable(team.getTotalWinRecord())
                    .ifPresent(findMatch::setAwayTeamTotalWinRecord);

            Optional.ofNullable(team.getTotalDrawRecord())
                    .ifPresent(findMatch::setAwayTeamTotalDrawRecord);

            Optional.ofNullable(team.getTotalLoseRecord())
                    .ifPresent(findMatch::setAwayTeamTotalLoseRecord);

            Optional.ofNullable(team.getLevelType())
                    .ifPresent(findMatch::setAwayTeamLevelType);

            Optional.ofNullable(team.getAgeType())
                    .ifPresent(findMatch::setAwayTeamAgeType);

            Optional.ofNullable(team.getUniformType())
                    .ifPresent(findMatch::setAwayTeamUniformType);

            findMatch.setMatchStatus(MatchStatus.valueOf("BEFORE"));

            matchRepository.save(findMatch);
            return findMatch;
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.MATCH_NOT_PATCHED);
        }
    }

    public Match updateMatch(Match match, Long matchId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (matchId == null || loginUserId == null) {
                log.info("matchId:{}", matchId);
                log.info("loginUserId:{}", loginUserId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }

            Team homeTeam = teamService.findByTeamId(match.getHomeTeamId());
            Team awayTeam = teamService.findByTeamId(match.getAwayTeamId());
            String loginUserName = loginUser.getName();
            if (!Objects.equals(homeTeam.getManagerName(), loginUserName)) {
                if (!Objects.equals(homeTeam.getSubManagerName(), loginUserName)) {
                    if (!Objects.equals(awayTeam.getSubManagerName(), loginUserName)) {
                        if (!Objects.equals(awayTeam.getSubManagerName(), loginUserName)) {
                            throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                        }
                        throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                    }
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }
            Match findMatch = findByMatchId(match.getMatchId());

            /*
            * MATCH INFO UPDATE
            * loginUserName 이 HOME_TEAM 이라면 HOME_TEAM 으로 분기처리 아니면 AWAY_TEAM 으로 분기처리
            * */
            if (Objects.equals(homeTeam.getManagerName(), loginUserName) || Objects.equals(homeTeam.getSubManagerName(), loginUserName)) {
                Optional.ofNullable(match.getHomeTeamScore())
                        .ifPresent(findMatch::setHomeTeamScore);

                Optional.ofNullable(match.getAwayTeamScore())
                        .ifPresent(findMatch::setAwayTeamScore);

                Optional.ofNullable(match.getHomeTeamLevelType())
                        .ifPresent(findMatch::setHomeTeamLevelType);

                Optional.ofNullable(match.getHomeTeamAgeType())
                        .ifPresent(findMatch::setHomeTeamAgeType);

                Optional.ofNullable(match.getHomeTeamUniformType())
                        .ifPresent(findMatch::setHomeTeamUniformType);

                Optional.ofNullable(match.getMatchTime())
                        .ifPresent(findMatch::setMatchTime);

            } else {
                Optional.ofNullable(match.getHomeTeamScore())
                        .ifPresent(findMatch::setHomeTeamScore);

                Optional.ofNullable(match.getAwayTeamScore())
                        .ifPresent(findMatch::setAwayTeamScore);

                Optional.ofNullable(match.getAwayTeamLevelType())
                        .ifPresent(findMatch::setAwayTeamLevelType);

                Optional.ofNullable(match.getAwayTeamAgeType())
                        .ifPresent(findMatch::setAwayTeamAgeType);

                Optional.ofNullable(match.getAwayTeamUniformType())
                        .ifPresent(findMatch::setAwayTeamUniformType);

                Optional.ofNullable(match.getMatchTime())
                        .ifPresent(findMatch::setMatchTime);
            }

            matchRepository.save(findMatch);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.MATCH_NOT_PATCHED);
        }
        return match;
    }

    public Match matchEnd(Match match, Long matchId, Long homeTeamScore, Long awayTeamScore,
                          Long homeTeamId, Long awayTeamId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            String loginUserName = loginUser.getName();
            if (matchId == null || homeTeamId == null || awayTeamId == null || loginUserId == null) {
                log.info("matchId: {}", matchId);
                log.info("homeTeamId: {}", homeTeamId);
                log.info("awayTeamId: {}", awayTeamId);
                log.info("loginUserId:{}", loginUserId);
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            Match findMatch = findByMatchId(match.getMatchId());

            if(findMatch.getMatchStatus()==MatchStatus.valueOf("END")){
                throw new BusinessLogicException(Exceptions.MATCH_ALREADY_END);
            }

            Team findHomeTeam = teamService.findByTeamId(homeTeamId);
            Team findAwayTeam = teamService.findByTeamId(awayTeamId);
            if (!Objects.equals(findHomeTeam.getManagerName(), loginUserName)) {
                if (!Objects.equals(findHomeTeam.getSubManagerName(), loginUserName)) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }
            findMatch.setTeam(findHomeTeam);
            findMatch.setTeam(findAwayTeam);

            /*
             * MATCH INFO UPDATE FOR MATCH_END
             * 승무패 결과 분기처리
             * */
            if(homeTeamScore>awayTeamScore){
                //homeTeam 승리한 경우
                findMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("WIN"));
                findMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));

                // teamRepository save
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+300L);
                findHomeTeam.setTotalWinRecord(findHomeTeam.getTotalWinRecord()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+10L);
                findAwayTeam.setTotalLoseRecord(findAwayTeam.getTotalLoseRecord()+1L);

                findMatch.setHomeTeamTotalWinRecord(findHomeTeam.getTotalWinRecord());
                findMatch.setAwayTeamTotalLoseRecord(findAwayTeam.getTotalLoseRecord());
            } else if(homeTeamScore<awayTeamScore){
                //homeTeam 패배한 경우
                findMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));
                findMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("WIM"));

                // teamRepository save
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+10L);
                findHomeTeam.setTotalLoseRecord(findHomeTeam.getTotalLoseRecord()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+300L);
                findAwayTeam.setTotalWinRecord(findAwayTeam.getTotalWinRecord()+1L);

                findMatch.setHomeTeamTotalWinRecord(findHomeTeam.getTotalLoseRecord());
                findMatch.setAwayTeamTotalLoseRecord(findAwayTeam.getTotalWinRecord());
            } else {
                //무승부인 경우
                findMatch.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));
                findMatch.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

                // teamRepository save
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+100L);
                findHomeTeam.setTotalDrawRecord(findHomeTeam.getTotalDrawRecord()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+100L);
                findAwayTeam.setTotalDrawRecord(findAwayTeam.getTotalDrawRecord()+1L);

                findMatch.setHomeTeamTotalWinRecord(findHomeTeam.getTotalDrawRecord());
                findMatch.setAwayTeamTotalLoseRecord(findAwayTeam.getTotalDrawRecord());
            }
            teamRepository.save(findHomeTeam);
            teamRepository.save(findAwayTeam);

            findMatch.setHomeTeamHonorScore(findHomeTeam.getHonorScore());
            findMatch.setAwayTeamHonorScore(findAwayTeam.getHonorScore());
            findMatch.setHomeTeamId(homeTeamId);
            findMatch.setHomeTeamScore(homeTeamScore);
            findMatch.setAwayTeamScore(awayTeamScore);

            findMatch.setMatchStatus(MatchStatus.valueOf("END"));

            matchRepository.save(findMatch);
            log.info("MATCH_END ABOUT AWAY_TEAM TO TEAM_REPOSITORY:{}", findMatch);
            return findMatch;
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.MATCH_NOT_PATCHED);
        }
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
        try {
            Match findMatch = findByMatchId(matchId);
            matchRepository.delete(findMatch);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.MATCH_NOT_DELETED);
        }
    }

    public Match findByMatchId(Long matchId) {
        Optional<Match> optionalMatch = matchRepository.findById(matchId);

        return optionalMatch.orElseThrow(() ->
                new BusinessLogicException(Exceptions.MATCH_NOT_FOUND));
    }

    public Match findByMatchApplyId(Long matchApplyId) {
        Optional<Match> optionalMatch = matchRepository.findByMatchApplyId(matchApplyId);

        return optionalMatch.orElseThrow(() ->
                new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_FOUND));
    }

    public void existByTeamId(Long matchId) {
        Long optionalMatch = matchRepository.existByTeamId(matchId);
        if (optionalMatch!=null) {
            throw new BusinessLogicException(Exceptions.MATCH_EXISTS);
        }
    }

}