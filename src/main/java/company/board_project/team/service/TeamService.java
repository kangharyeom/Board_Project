package company.board_project.team.service;

import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.team.entity.Team;
import company.board_project.team.repository.TeamRepository;
import company.board_project.user.entity.User;
import company.board_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;
    public Team createTeam(
            Team team, Long userId) {

        User user = userService.findUser(userId);

        team.setUser(user);
        team.setManagerName(user.getName());

        return teamRepository.save(team);
    }

    public Team updateTeam(
            Team team,
            Long teamId) {


        Team findTeam = findVerifiedTeam(teamId); //ID로 멤버 존재 확인하고 comment 정보 반환


        Optional.ofNullable(team.getChampionCount())
                .ifPresent(findTeam::setChampionCount);

        Optional.ofNullable(team.getMemberCount())
                .ifPresent(findTeam::setMemberCount);

        Optional.ofNullable(team.getLeagueWinRecord())
                .ifPresent(findTeam::setLeagueWinRecord);

        Optional.ofNullable(team.getLeagueDrawRecord())
                .ifPresent(findTeam::setLeagueDrawRecord);

        Optional.ofNullable(team.getLeagueLoseRecord())
                .ifPresent(findTeam::setLeagueLoseRecord);

        Optional.ofNullable(team.getTotalWinRecord())
                .ifPresent(findTeam::setTotalWinRecord);

        Optional.ofNullable(team.getTotalDrawRecord())
                .ifPresent(findTeam::setTotalDrawRecord);

        Optional.ofNullable(team.getTotalLoseRecord())
                .ifPresent(findTeam::setTotalLoseRecord);

        Optional.ofNullable(team.getHonorScore())
                .ifPresent(findTeam::setHonorScore);

        Optional.ofNullable(team.getRanking())
                .ifPresent(findTeam::setRanking);

        Optional.ofNullable(team.getMostGoals())
                .ifPresent(findTeam::setMostGoals);

        Optional.ofNullable(team.getMostAssist())
                .ifPresent(findTeam::setMostAssist);

        Optional.ofNullable(team.getMostMom())
                .ifPresent(findTeam::setMostMom);

        Optional.ofNullable(team.getIntroduction())
                .ifPresent(findTeam::setIntroduction);

        Optional.ofNullable(team.getAgeType())
                .ifPresent(findTeam::setAgeType);

        Optional.ofNullable(team.getLocationType())
                .ifPresent(findTeam::setLocationType);

        Optional.ofNullable(team.getManagerName())
                .ifPresent(findTeam::setManagerName);

        Optional.ofNullable(team.getSubManagerName())
                .ifPresent(findTeam::setSubManagerName);

        Optional.ofNullable(team.getUniformType())
                .ifPresent(findTeam::setUniformType);

        return teamRepository.save(findTeam);
    }

    public Team findTeam(long teamId) {
        return findVerifiedTeam(teamId);
    }

    public List<Team> findAllTeamsByLeagueId(long leagueId) {
        return teamRepository.findAllTeamsByLeagueId(leagueId);
    }

    public Page<Team> findTeams(int page, int size) {
        return teamRepository.findAll(PageRequest.of(page, size,
                Sort.by("teamId").descending()));
    }

    public Team findTeam(int teamId) {
        return findVerifiedTeam(teamId);
    }

    public void deleteTeam(long teamId) {
        Team findTeam = findVerifiedTeam(teamId);

        teamRepository.delete(findTeam);
    }

    public Team findVerifiedTeam(long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Team findTeam =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findTeam;
    }
}
