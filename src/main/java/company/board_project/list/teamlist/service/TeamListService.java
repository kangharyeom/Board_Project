package company.board_project.list.teamlist.service;

import company.board_project.apply.entity.Apply;
import company.board_project.apply.service.ApplyService;
import company.board_project.constant.Position;
import company.board_project.constant.TeamMemberType;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.list.teamlist.entity.TeamList;
import company.board_project.list.teamlist.repository.TeamListRepository;
import company.board_project.team.entity.Team;
import company.board_project.team.service.TeamService;
import company.board_project.user.entity.User;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamListService {
    private final TeamListRepository teamListRepository;
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
        teamList.setTeamMemberType(TeamMemberType.MEMBER);

        return teamListRepository.save(teamList);
    }

    public TeamList createTeamListByTeamController(
            TeamList teamList, Long userId, Long teamId) {

        User user = userService.findUser(userId);
        Team team = teamService.findTeam(teamId);

        teamList.setUser(user);
        teamList.setTeam(team);

        teamList.setName(user.getName());
        teamList.setPosition(Position.FORWARDS);
        teamList.setTeamMemberType(TeamMemberType.MANAGER);
        teamList.setAgeType(team.getAgeType());
        teamList.setLocationType(team.getLocationType());
        teamList.setLevelType(team.getLevelType());
        teamList.setFrequency(team.getFrequency());

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
