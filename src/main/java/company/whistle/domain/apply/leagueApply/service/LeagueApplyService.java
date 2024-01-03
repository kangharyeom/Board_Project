package company.whistle.domain.apply.leagueApply.service;

import company.whistle.domain.apply.leagueApply.entity.LeagueApply;
import company.whistle.domain.apply.leagueApply.repository.LeagueApplyRepository;
import company.whistle.domain.league.league.entity.League;
import company.whistle.domain.league.league.service.LeagueService;
import company.whistle.domain.team.team.entity.Team;
import company.whistle.domain.team.team.service.TeamService;
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
 * ApplyService
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LeagueApplyService {
    private final LeagueApplyRepository leagueApplyRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final LeagueService leagueService;

    /*
     * LeagueApply 생성
     */
    public LeagueApply createLeagueApply(LeagueApply leagueApply, Long leagueId) {
        try {
            // 로그인한 유저의 팀 정보 조회
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if ( leagueId == null || loginUserId == null) {
                log.info("leagueId:{}", leagueId);
                log.info("loginUserId:{}", loginUserId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }

            Team team = teamService.findByUserId(loginUserId);
            if (team.getLeagueId() != null) {
                throw new BusinessLogicException(Exceptions.LEAGUE_EXISTS);
            }

            //리그 apply에 신청한 팀 중복 검증
            existByTeamId(team.getTeamId());

            // team 매니저의 user정보 주입
            User user = userService.findByUserId(team.getUser().getUserId());
            // 로그인한 유저 권한 검증
            if (!Objects.equals(loginUser.getName(), team.getManagerName()) || team.getManagerName()==null) {
                if (!Objects.equals(loginUser.getName(), team.getSubManagerName())|| team.getManagerName()==null) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            League league = leagueService.findByLeagueId(leagueId);

            leagueApply.setLeague(league);
            leagueApply.setUser(user);
            leagueApply.setTeam(team);
            leagueApply.setManagerName(user.getName());
            leagueApply.setTeamName(team.getTeamName());
            leagueApply.setLevelType(team.getLevelType());
            leagueApply.setAgeType(team.getAgeType());
            leagueApply.setApplyType(leagueApply.getApplyType());

            leagueApplyRepository.save(leagueApply);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_CREATED);
        }
        log.info("LEAGUE_APPLY CREATED:{}", leagueApply);
        return leagueApply;
    }

    public List<LeagueApply> findAllByLeagueId(Long leagueId){
        return leagueApplyRepository.findAllByLeagueId(leagueId);
    }

    public void deleteApply(Long leagueApplyId) {
        try {
            LeagueApply findLeagueApply = findByLeagueApplyId(leagueApplyId);
            leagueApplyRepository.delete(findLeagueApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_DELETED);
        }
    }

    public LeagueApply findByLeagueApplyId(Long leagueApplyId) {
        Optional<LeagueApply> optionalApply = leagueApplyRepository.findById(leagueApplyId);

        LeagueApply findLeagueApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_FOUND));
        log.info("APPLY EXIST: {}", findLeagueApply.toString());
        return findLeagueApply;
    }

    public LeagueApply findByTeamId(long teamId) {
        LeagueApply optionalApply = leagueApplyRepository.findByTeamId(teamId);
        if (optionalApply == null) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_FOUND);
        }
        return optionalApply;
    }

    public void existByTeamId(long teamId) {
        Long checkTeamId = leagueApplyRepository.existByTeamId(teamId);
        if (checkTeamId != null) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_EXISTS);
        }
    }
}
