package company.whistle.domain.apply.team.service;

import company.whistle.domain.apply.team.entity.TeamApply;
import company.whistle.domain.apply.team.repository.TeamApplyRepository;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import company.whistle.global.constant.ApplyStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public TeamApply createTeamApply(TeamApply teamApply, Long teamId) {
        try {
            User loginUser = userService.getLoginUser();
            Long userId = userService.getLoginUser().getUserId();
            if (teamId == null || userId == null ) {
                log.info("teamId:{}",teamId);
                log.info("userId:{}",userId);
                throw new BusinessLogicException(Exceptions.USER_ID_IS_NULL);
            }

            /*
             * 팀 중복 가입 체크
             * 해당 유저가 이미 팀이 있는 경우 USER_ALREADY_HAVE_TEAM 를 던짐
             * */
            if (loginUser.getTeamId() != null) {
                throw new BusinessLogicException(Exceptions.USER_ALREADY_HAVE_TEAM);
            }

            /*
             * TeamApply 중복 체크
             * 해당 유저가 이미 TeamApply 에 APPLIED 하였고 user_id 값이 DB에 있는 경우 TEAM_APPLY_EXISTS 를 던짐
             * */
            checkDuplUserIdFromTeamApply(userId);

            Team team = teamService.findTeam(teamId);
            teamApply.setTeam(team);
            teamApply.setUser(loginUser);
            teamApply.setManagerName(loginUser.getName());
            teamApply.setTeamName(team.getTeamName());
            teamApply.setAgeType(team.getAgeType());
            teamApply.setLevelType(team.getLevelType());
            teamApply.setApplyStatus(ApplyStatus.APPLIED);
            teamApplyRepository.save(teamApply);
            log.info("TEAM_APPLY CREATED:{}", teamApply);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_CREATED);
        }
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
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
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

    public TeamApply findTeamApplyByUserId(Long userId) {
        TeamApply teamApply = teamApplyRepository.findTeamApplyByUserId(userId);
        if (teamApply == null) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_FOUND);
        }
        return teamApply;
    }

    public TeamApply findTeamApplyByTeamIdAndUserId(Long teamId, Long userId) {
        TeamApply teamApply = teamApplyRepository.checkTeamApplyByTeamIdAndUserId(teamId, userId);
        if (teamApply==null) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_NOT_FOUND);
        }
        return teamApply;
    }
    public void checkDuplUserIdFromTeamApply(Long userId) {
        String teamApply = teamApplyRepository.checkDuplUserIdFromTeamApply(userId);
        if (Objects.equals(teamApply, "APPLIED")) {
            throw new BusinessLogicException(Exceptions.TEAM_APPLY_EXISTS);
        }
    }

}
