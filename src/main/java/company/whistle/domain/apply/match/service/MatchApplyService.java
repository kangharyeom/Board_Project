package company.whistle.domain.apply.match.service;

import company.whistle.domain.apply.match.entity.MatchApply;
import company.whistle.domain.apply.match.repository.MatchApplyRepository;
import company.whistle.domain.match.unrank.entity.Match;
import company.whistle.domain.match.unrank.service.MatchService;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/*
 * LeagueApplyService
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MatchApplyService {
    private final MatchApplyRepository matchApplyRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final MatchService matchService;

    /*
     * MatchApply 생성
     * user, team, match가 존재하는지 확인 후 team, user, match가 존재하면 applyRepository에 저장
     */
    public MatchApply createMatchApply(MatchApply matchApply, Long matchId, Long teamId) {
        try {
            // MatchApply에 중복된 teamId가 있는지 확인
            existByTeamId(teamId);

            // 로그인한 유저 정보 조회
            User loginUser = userService.getLoginUser();

            // 로그인한 유저 Id 주입
            Long loginUserId = loginUser.getUserId();
            if (loginUserId == null || matchId == null || teamId == null) {
                log.info("loginUserId:{}", loginUserId);
                log.info("matchId:{}", matchId);
                log.info("teamId:{}", teamId);
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }

            Team team = teamService.findByUserId(loginUserId);
            /*
             * user
             * 매니저 또는 부매니저가 매치를 생성할 수 있기 때문에
             * userId는 해당 팀의 매니저 userId를 주입
             * */
            User user = userService.findByUserId(team.getUser().getUserId());
            Match match = matchService.findByMatchId(matchId);

            // 로그인한 유저 권한 검증
            if (!Objects.equals(loginUser.getName(), team.getManagerName()) || team.getManagerName() == null) {
                if (!Objects.equals(loginUser.getName(), team.getSubManagerName()) || team.getManagerName() == null) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            matchApply.setUser(user);
            matchApply.setMatch(match);
            matchApply.setTeam(team);

            matchApply.setManagerName(user.getName());
            matchApply.setTeamName(team.getTeamName());
            matchApply.setLevelType(team.getLevelType());
            matchApply.setAgeType(team.getAgeType());
            matchApply.setApplyType(matchApply.getApplyType());

            matchApplyRepository.save(matchApply);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_CREATED);
        }
        log.info("MATCH_APPLY CREATED:{}", matchApply);
        return matchApply;
    }

    public List<MatchApply> findAllByTeamId(Long teamId){
        return matchApplyRepository.findAllByTeamId(teamId);
    }

    public List<MatchApply> findAllByMatchApplyId(Long matchApplyId){
        return matchApplyRepository.findAllByMatchApplyId(matchApplyId);
    }

    public void deleteMatchApply(Long matchApplyId) {
        try {
            MatchApply findMatchApply = findByMatchApplyId(matchApplyId);
            matchApplyRepository.delete(findMatchApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_DELETED);
        }
    }

    public MatchApply findByMatchApplyId(Long matchApplyId) {
        Optional<MatchApply> optionalApply = matchApplyRepository.findById(matchApplyId);

        MatchApply findMatchApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_FOUND));
        log.info("APPLY EXIST: {}", findMatchApply.toString());
        return findMatchApply;
    }

    public void existByTeamId(Long teamId) {
        Long checkTeamId = matchApplyRepository.existByTeamId(teamId);
        if (checkTeamId == null) {
            throw new BusinessLogicException(Exceptions.MATCH_APPLY_NOT_FOUND);
        }
    }
}
