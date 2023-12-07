package company.whistle.domain.team.squad.service;

import company.whistle.domain.apply.team.entity.TeamApply;
import company.whistle.domain.apply.team.repository.TeamApplyRepository;
import company.whistle.domain.apply.team.service.TeamApplyService;
import company.whistle.domain.team.squad.entity.Squad;
import company.whistle.domain.user.repository.UserRepository;
import company.whistle.global.constant.ApplyStatus;
import company.whistle.global.constant.Position;
import company.whistle.global.constant.TeamMemberRole;
import company.whistle.global.constant.TeamMemberStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.team.squad.repository.SquadRepository;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class SquadService {
    private final SquadRepository squadRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final TeamApplyService teamApplyService;
    private final TeamApplyRepository teamApplyRepository;
    public Squad createSquad(
            Squad squad, Long teamId, Long userId) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            String loginUserName = loginUser.getName();
            if (teamId == null || userId == null || loginUserId == null || loginUserName == null) {
                log.info("teamId:{}", teamId);
                log.info("userId:{}", userId);
                log.info("loginUserId:{}", loginUserId);
                log.info("loginUserName:{}", loginUserName);
                throw new BusinessLogicException(Exceptions.IDS_OR_NAMES_ARE_NULL);
            }

            /*
             * userId 중복 체크
             * squad DB에 teamId 가 있으면서 Team_Member_status 값이 ACTIVITY 이면 EXIST 를 던짐
             * */
            checkDuplUserIdFromSquad(userId);

            /*
             * TeamApply 유효성 체크
             * TeamApply DB에 teamId 와  userId 값이 있으면서 ApplyStatus 값이 APPLIED 가 아닌 경우 TEAM_APPLY_NOT_FOUND 를 던짐
             * */
            TeamApply teamApply = teamApplyService.findTeamApplyByTeamIdAndUserId(teamId, userId);

            Team team = teamService.findVerifiedTeam(teamId);
            /*
             * SQUAD_CREATE(LOGIN 한 유저) 권한 검증
             * 해당 유저가 팀의 매니저이거나 부매니저가 아니라면 UNAUTHORIZED 를 던짐
             * */
            if (!Objects.equals(team.getManagerName(), loginUserName)) {
                if (Objects.equals(team.getSubManagerName(), loginUserName)) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            User user = userService.findUser(userId);

            user.setTeamMemberRole(TeamMemberRole.MANAGER);
            user.setTeamId(teamId);
            squad.setUser(user);
            squad.setTeam(team);
            squad.setTeamApply(teamApply);
            squad.setName(user.getName());
            squad.setTeamMemberRole(TeamMemberRole.MEMBER);
            squad.setTeamMemberStatus(TeamMemberStatus.ACTIVITY);

            teamApply.setApplyStatus(ApplyStatus.ACCEPTED);

            teamApplyRepository.save(teamApply);
            userRepository.save(user);
            squadRepository.save(squad);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.TEAM_NOT_PATCHED);
        }
        return squad;
    }

    public Squad createSquadByTeamController(
            Squad squad) {
        try {
            Long userId = userService.getLoginUser().getUserId();
            if (userId == null) {
                throw new BusinessLogicException(Exceptions.USER_ID_IS_NULL);
            }
            /*
             * userId 중복 체크
             * squad DB에 teamId 가 있으면서 Team_Member_status 값이 ACTIVITY 이면 EXIST 를 던짐
             * */
            checkDuplUserIdFromSquad(userId);

            User user = userService.findUser(userId);
            Team team = teamService.findTeamByUserId(userId);

            squad.setUser(user);
            squad.setTeam(team);

            squad.setName(user.getName());
            squad.setTeamMemberRole(TeamMemberRole.MANAGER);
            squad.setTeamMemberStatus(TeamMemberStatus.ACTIVITY);

            user.setTeamId(team.getTeamId());
            user.setTeamMemberRole(TeamMemberRole.MANAGER);

            userRepository.save(user);
            squadRepository.save(squad);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SQUAD_NOT_CREATED);
        }
        return squad;
    }

    public Squad updateSquad(
            Squad squad,
            Long squadId) {
        try {
            Long userId = userService.getLoginUser().getUserId();
            if ( squadId == null || userId == null) {
                log.info("userId: {}", userId);
                log.info("squadId: {}", squadId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }

            String loginUser = userService.findUser(userId).getName();
            Long teamId = findSquadByUserId(userId).getTeam().getTeamId();

            Team team = teamService.findVerifiedTeam(teamId);
            /*
             * SQUAD_CREATE(LOGIN 한 유저) 권한 검증
             * 해당 유저가 팀의 매니저이거나 부매니저가 아니라면 UNAUTHORIZED 를 던짐
             * */
            if (!Objects.equals(team.getManagerName(), loginUser)) {
                if (Objects.equals(team.getSubManagerName(), loginUser)) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            Squad findSquad = findVerifiedSquad(squadId);

            Optional.ofNullable(squad.getPosition())
                    .ifPresent(findSquad::setPosition);

            Optional.ofNullable(squad.getAgeType())
                    .ifPresent(findSquad::setAgeType);

            Optional.ofNullable(squad.getLocationType())
                    .ifPresent(findSquad::setLocationType);

            squadRepository.save(findSquad);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SQUAD_NOT_PATCHED);
        }
        return squad;
    }

    public Squad findSquad(long squadId) {
        return findVerifiedSquad(squadId);
    }

    public List<Squad> findSquadsNewest() {
        return squadRepository.findSquadNewest();
    }

    public List<Squad> findSquadsLatest() {
        return squadRepository.findSquadLatest();
    }

    public List<Squad> findHonorScore() {
        return squadRepository.findHonorScore();
    }


    public List<Squad> findAllTeamsByLeagueId(long leagueId) {
        return squadRepository.findAllTeamsByLeagueId(leagueId);
    }

    public List<Squad> findSquads() {
        return squadRepository.findAll();
    }

    public void deleteSquad(long squadId) {
        try {
            Squad findSquad = findVerifiedSquad(squadId);
            squadRepository.delete(findSquad);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.SQUAD_NOT_DELETED);
        }
    }

    public Squad findVerifiedSquad(long squadId) {
        Optional<Squad> optionalTeam = squadRepository.findById(squadId);
        return optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.SQUAD_NOT_FOUND));
    }

    public Squad findSquadByUserId(long userId) {
        Optional<Squad> optionalTeam = squadRepository.findById(userId);
        return optionalTeam.orElseThrow(() ->
                new BusinessLogicException(Exceptions.SQUAD_NOT_FOUND));
    }

    public Long findTeamIdOfSquadByUserId(long userId) {
        Long teamId = squadRepository.findTeamIdOfSquadByUserId(userId);
        if (teamId == null) {
            throw new BusinessLogicException(Exceptions.SQUAD_NOT_FOUND);
        }
        return teamId;
    }

    public void checkDuplUserIdFromSquad(long userId) {
        Long squad = squadRepository.checkDuplUserIdFromSquad(userId);
        if(squad != null) {
            throw new BusinessLogicException(Exceptions.USER_ALREADY_HAVE_TEAM);
        }
    }

}
