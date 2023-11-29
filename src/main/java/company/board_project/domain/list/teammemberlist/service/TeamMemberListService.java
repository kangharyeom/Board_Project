package company.board_project.domain.list.teammemberlist.service;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.service.ApplyService;
import company.board_project.domain.list.teammemberlist.entity.TeamMemberList;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.global.constant.Position;
import company.board_project.global.constant.TeamMemberRole;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.list.teammemberlist.repository.TeamMemberListRepository;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TeamMemberListService {
    private final TeamMemberListRepository teamMemberListRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final ApplyService applyService;
    public TeamMemberList createTeamMemberList(
            TeamMemberList teamMemberList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

        teamMemberList.setUser(user);
        teamMemberList.setTeam(team);
        teamMemberList.setApply(apply);
        teamMemberList.setName(user.getName());
        teamMemberList.setTeamMemberRole(TeamMemberRole.MEMBER);

        return teamMemberListRepository.save(teamMemberList);
    }

    public TeamMemberList createTeamMemberListByTeamController(
            TeamMemberList teamMemberList, Long teamId, Long userId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        user.setTeamId(teamId);

        teamMemberList.setUser(user);
        teamMemberList.setTeam(team);

        teamMemberList.setName(user.getName());
        teamMemberList.setPosition(Position.FORWARDS);
        teamMemberList.setTeamMemberRole(TeamMemberRole.MANAGER);
        teamMemberList.setAgeType(team.getAgeType());
        teamMemberList.setLocationType(team.getLocationType());
        teamMemberList.setLevelType(team.getLevelType());
        teamMemberList.setFrequency(team.getFrequency());

        userRepository.save(user);

        log.info("teamId {}", teamId);
        log.info("getTeamId {}", user.getTeamId());

        return teamMemberListRepository.save(teamMemberList);
    }

    public TeamMemberList updateTeamMemberList(
            TeamMemberList teamMemberList,
            Long teamListId) {

        TeamMemberList findTeamMemberList = findVerifiedTeamMemberList(teamListId);

        Optional.ofNullable(teamMemberList.getPosition())
                .ifPresent(findTeamMemberList::setPosition);

        Optional.ofNullable(teamMemberList.getAgeType())
                .ifPresent(findTeamMemberList::setAgeType);

        Optional.ofNullable(teamMemberList.getLocationType())
                .ifPresent(findTeamMemberList::setLocationType);

        /*Optional.ofNullable(teamMemberList.getMostGoals())
                .ifPresent(findTeamMemberList::setMostGoals);

        Optional.ofNullable(teamMemberList.getMostAssist())
                .ifPresent(findTeamMemberList::setMostAssist);

        Optional.ofNullable(teamMemberList.getMostMom())
                .ifPresent(findTeamMemberList::setMostMom);*/

        return teamMemberListRepository.save(findTeamMemberList);
    }

    public TeamMemberList findTeamMemberList(long teamListId) {
        return findVerifiedTeamMemberList(teamListId);
    }

    public List<TeamMemberList> findTeamMemberListsNewest() {
        return teamMemberListRepository.findTeamListsNewest();
    }

    public List<TeamMemberList> findTeamMemberListsLatest() {
        return teamMemberListRepository.findTeamListsLatest();
    }

    public List<TeamMemberList> findHonorScore() {
        return teamMemberListRepository.findHonorScore();
    }


    public List<TeamMemberList> findAllTeamsByLeagueId(long leagueId) {
        return teamMemberListRepository.findAllTeamsByLeagueId(leagueId);
    }

    public List<TeamMemberList> findTeamMemberLists() {
        return teamMemberListRepository.findAll();
    }

    public TeamMemberList findTeamMemberList(int teamListId) {
        return findVerifiedTeamMemberList(teamListId);
    }

    public void deleteTeamMemberList(long teamListId) {
        TeamMemberList findTeamMemberList = findVerifiedTeamMemberList(teamListId);

        teamMemberListRepository.delete(findTeamMemberList);
    }

    public TeamMemberList findVerifiedTeamMemberList(long teamListId) {
        Optional<TeamMemberList> optionalTeam = teamMemberListRepository.findById(teamListId);
        TeamMemberList findTeamMemberList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findTeamMemberList;
    }
}
