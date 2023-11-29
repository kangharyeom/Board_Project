package company.board_project.domain.apply.team.service;

import company.board_project.domain.apply.team.entity.TeamApply;
import company.board_project.domain.apply.team.repository.TeamApplyRepository;
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
 * LeagueApplyService
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TeamApplyService {
    private final TeamApplyRepository teamApplyRepository;
    private final UserService userService;
    private final TeamService teamService;

    /*
     * TeamApply 생성
     * user, team이 존재하는지 확인 후 team, user가 존재하면 applyRepository에 저장
     */
    public TeamApply createTeamApply(TeamApply teamApply, Long userId, Long teamId) {
        try {
            User user = userService.findUser(userId);
            Team team = teamService.findTeam(teamId);
                teamApply.setTeam(team);
                teamApply.setUser(user);
                teamApply.setManagerName(user.getName());
                teamApply.setTeamName(team.getTeamName());
                teamApply.setAgeType(team.getAgeType());
                teamApply.setLevelType(team.getLevelType());
            teamApplyRepository.save(teamApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_CREATED);
        }
        log.info("TEAM_APPLY CREATED:{}", teamApply);
        return teamApply;
    }

    public TeamApply findTeamApply(Long teamApplyId) {
        return findVerifiedTeamApply(teamApplyId);
    }

    public List<TeamApply> findAllByTeamApplyId(Long teamApplyId){
        return teamApplyRepository.findAllByTeamApplyId(teamApplyId);
    }

    public void deleteTeamApply(Long teamApplyId) {
        try {
            TeamApply findTeamApply = findVerifiedTeamApply(teamApplyId);
            teamApplyRepository.delete(findTeamApply);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_DELETED);
        }
    }

    /*
     * apply 검증 로직
     * repository에 Apply가 없는 경우 exception을 리턴
     */
    public TeamApply findVerifiedTeamApply(Long teamApplyId) {
        Optional<TeamApply> optionalApply = teamApplyRepository.findById(teamApplyId);

        TeamApply findTeamApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_FOUND));
        log.info("APPLY EXIST: {}", findTeamApply.toString());
        return findTeamApply;
    }
}
