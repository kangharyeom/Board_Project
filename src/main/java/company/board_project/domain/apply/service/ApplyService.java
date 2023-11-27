package company.board_project.domain.apply.service;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.repository.ApplyRepository;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.league.service.LeagueService;
import company.board_project.domain.match.normalmatch.entity.Match;
import company.board_project.domain.match.normalmatch.service.MatchService;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/*
 * ApplyService
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final MatchService matchService;
    private final LeagueService leagueService;

    /*
     * Apply 생성
     * user, team이 존재하는지 확인 후 team, user가 존재하면 applyRepository에 저장
     */
    @Transactional
    public Apply createApply(Apply apply, Long userId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            Team team = teamService.findTeam(teamId);

            apply.setTeam(team);
            apply.setUser(user);

            applyRepository.save(apply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.APPLY_NOT_CREATED);
        }
        log.info("APPLY CREATED:{}", apply);

        return apply;
    }

    /*
     * TeamApply 생성
     * user, team이 존재하는지 확인 후 team, user가 존재하면 applyRepository에 저장
     */
    public Apply createTeamApply(Apply apply, Long userId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            Team team = teamService.findTeam(teamId);
                apply.setTeam(team);
                apply.setUser(user);
                apply.setManagerName(user.getName());
                apply.setTeamName(team.getTeamName());
                apply.setAgeType(team.getAgeType());
                apply.setLevelType(team.getLevelType());
                apply.setUserTeamApplyId(team.getTeamId());
            applyRepository.save(apply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_CREATED);
        }
        log.info("TEAM_APPLY CREATED:{}", apply);
        return apply;
    }

    /*
     * MatchApply 생성
     * user, team, match가 존재하는지 확인 후 team, user, match가 존재하면 applyRepository에 저장
     */
    public Apply createMatchApply(Apply apply, Long userId, Long matchId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            Match match = matchService.findMatch(matchId);
            Team team = teamService.findTeam(teamId);

            apply.setUser(user);
            apply.setMatch(match);
            apply.setTeam(team);

            apply.setManagerName(user.getName());
            apply.setTeamName(team.getTeamName());
            apply.setLevelType(team.getLevelType());
            apply.setAgeType(team.getAgeType());
            apply.setApplyType(apply.getApplyType());
            apply.setUserMatchApplyId(match.getMatchId());

            applyRepository.save(apply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_CREATED);
        }
        log.info("MATCH_APPLY CREATED:{}", apply);
        return apply;
    }

    /*
     * LeagueApply 생성
     * user, team, league가 존재하는지 확인 후 team, user, league가 존재하면 applyRepository에 저장
     */
    public Apply createLeagueApply(Apply apply, Long userId, Long leagueId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            League league = leagueService.findLeague(leagueId);
            Team team = teamService.findTeam(teamId);

            apply.setLeague(league);
            apply.setUser(user);
            apply.setTeam(team);
            apply.setManagerName(user.getName());
            apply.setTeamName(team.getTeamName());
            apply.setLevelType(team.getLevelType());
            apply.setAgeType(team.getAgeType());
            apply.setApplyType(apply.getApplyType());
            apply.setUserLeagueApplyId(league.getLeagueId());

            applyRepository.save(apply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_CREATED);
        }
        log.info("LEAGUE_APPLY CREATED:{}", apply);
        return apply;
    }

    public Apply findApply(Long applyId) {
        return findVerifiedApply(applyId);
    }

    public List<Apply> findAllByTeamId(Long teamId){
        return applyRepository.findAllByTeamId(teamId);
    }

    public List<Apply> findAllByMatchId(Long matchId){
        return applyRepository.findAllByMatchId(matchId);
    }

    public List<Apply> findAllByLeagueId(Long leagueId){
        return applyRepository.findAllByLeagueId(leagueId);
    }

    public void deleteApply(Long applyId) {
        try {
            Apply findApply = findVerifiedApply(applyId);
            applyRepository.delete(findApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.APPLY_NOT_DELETED);
        }
    }

    /*
     * apply 검증 로직
     * repository에 Apply가 없는 경우 exception을 리턴
     */
    public Apply findVerifiedApply(Long applyId) {
        Optional<Apply> optionalApply = applyRepository.findById(applyId);

        Apply findApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.APPLY_NOT_FOUND));
        log.info("APPLY EXIST: {}", findApply.toString());
        return findApply;
    }
}
