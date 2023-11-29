package company.board_project.domain.apply.league.service;

import company.board_project.domain.apply.league.entity.LeagueApply;
import company.board_project.domain.apply.league.repository.LeagueApplyRepository;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.league.service.LeagueService;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.match.normal.service.MatchService;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.domain.user.service.UserService;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import lombok.RequiredArgsConstructor;
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
public class LeagueApplyService {
    private final LeagueApplyRepository leagueApplyRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final LeagueService leagueService;

    /*
     * LeagueApply 생성
     * user, team, league가 존재하는지 확인 후 team, user, league가 존재하면 applyRepository에 저장
     */
    public LeagueApply createLeagueApply(LeagueApply leagueApply, Long userId, Long leagueId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            League league = leagueService.findLeague(leagueId);
            Team team = teamService.findTeam(teamId);

            leagueApply.setLeague(league);
            leagueApply.setUser(user);
            leagueApply.setTeam(team);
            leagueApply.setManagerName(user.getName());
            leagueApply.setTeamName(team.getTeamName());
            leagueApply.setLevelType(team.getLevelType());
            leagueApply.setAgeType(team.getAgeType());
            leagueApply.setApplyType(leagueApply.getApplyType());

            leagueApplyRepository.save(leagueApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_CREATED);
        }
        log.info("LEAGUE_APPLY CREATED:{}", leagueApply);
        return leagueApply;
    }

    public LeagueApply findLeagueApply(Long applyId) {
        return findVerifiedLeagueApply(applyId);
    }

    public List<LeagueApply> findAllByLeagueId(Long leagueId){
        return leagueApplyRepository.findAllByLeagueId(leagueId);
    }

    public void deleteApply(Long applyId) {
        try {
            LeagueApply findLeagueApply = findVerifiedLeagueApply(applyId);
            leagueApplyRepository.delete(findLeagueApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_DELETED);
        }
    }

    /*
     * apply 검증 로직
     * repository에 Apply가 없는 경우 exception을 리턴
     */
    public LeagueApply findVerifiedLeagueApply(Long applyId) {
        Optional<LeagueApply> optionalApply = leagueApplyRepository.findById(applyId);

        LeagueApply findLeagueApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.LEAGUE_APPLY_NOT_FOUND));
        log.info("APPLY EXIST: {}", findLeagueApply.toString());
        return findLeagueApply;
    }
}
