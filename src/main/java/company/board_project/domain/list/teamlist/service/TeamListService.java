package company.board_project.domain.list.teamlist.service;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.apply.service.ApplyService;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.global.constant.Position;
import company.board_project.global.constant.TeamMemberRole;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.list.teamlist.repository.TeamListRepository;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TeamListService {
    private final TeamListRepository teamListRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final ApplyService applyService;
    public TeamList createTeamList(
            TeamList teamList, Long userId, Long teamId, Long applyId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);
        Apply apply = applyService.findApply(applyId);

        teamList.setUser(user);
        teamList.setTeam(team);
        teamList.setApply(apply);
        teamList.setName(user.getName());
        teamList.setTeamMemberRole(TeamMemberRole.MEMBER);

        return teamListRepository.save(teamList);
    }

    public TeamList createTeamListByTeamController(
            TeamList teamList, Long teamId, Long userId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        user.setTeamId(teamId);

        teamList.setUser(user);
        teamList.setTeam(team);

        teamList.setName(user.getName());
        teamList.setPosition(Position.FORWARDS);
        teamList.setTeamMemberRole(TeamMemberRole.MANAGER);
        teamList.setAgeType(team.getAgeType());
        teamList.setLocationType(team.getLocationType());
        teamList.setLevelType(team.getLevelType());
        teamList.setFrequency(team.getFrequency());

        userRepository.save(user);

        log.info("teamId {}", teamId);
        log.info("getTeamId {}", user.getTeamId());

        return teamListRepository.save(teamList);
    }

    public TeamList updateTeamList(
            TeamList teamList,
            Long teamListId) {

        TeamList findTeamList = findVerifiedTeamList(teamListId);

        Optional.ofNullable(teamList.getPosition())
                .ifPresent(findTeamList::setPosition);

        Optional.ofNullable(teamList.getAgeType())
                .ifPresent(findTeamList::setAgeType);

        Optional.ofNullable(teamList.getLocationType())
                .ifPresent(findTeamList::setLocationType);

        /*Optional.ofNullable(teamList.getMostGoals())
                .ifPresent(findTeamList::setMostGoals);

        Optional.ofNullable(teamList.getMostAssist())
                .ifPresent(findTeamList::setMostAssist);

        Optional.ofNullable(teamList.getMostMom())
                .ifPresent(findTeamList::setMostMom);*/

        return teamListRepository.save(findTeamList);
    }

    public TeamList findTeamList(long teamListId) {
        return findVerifiedTeamList(teamListId);
    }

    public List<TeamList> findTeamListsNewest() {
        return teamListRepository.findTeamListsNewest();
    }

    public List<TeamList> findTeamListsLatest() {
        return teamListRepository.findTeamListsLatest();
    }

    public List<TeamList> findHonorScore() {
        return teamListRepository.findHonorScore();
    }


    public List<TeamList> findAllTeamsByLeagueId(long leagueId) {
        return teamListRepository.findAllTeamsByLeagueId(leagueId);
    }

    public List<TeamList> findTeamLists() {
        return teamListRepository.findAll();
    }

    public TeamList findTeamList(int teamListId) {
        return findVerifiedTeamList(teamListId);
    }

    public void deleteTeamList(long teamListId) {
        TeamList findTeamList = findVerifiedTeamList(teamListId);

        teamListRepository.delete(findTeamList);
    }

    public TeamList findVerifiedTeamList(long teamListId) {
        Optional<TeamList> optionalTeam = teamListRepository.findById(teamListId);
        TeamList findTeamList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findTeamList;
    }
}
