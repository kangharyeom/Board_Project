package company.whistle.domain.team.domain.service;

import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.repository.TeamRepository;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;
    public Team createTeam(
            Team team, String teamName) {
        try {
            User loginUser = userService.getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (loginUserId == null || teamName == null) {
                log.info("userId:{}",loginUserId);
                log.info("teamName:{}",teamName);
                throw new BusinessLogicException(Exceptions.IDS_OR_NAMES_ARE_NULL);
            }

            /*
             * 팀 중복 가입 체크
             * 해당 유저가 이미 팀이 있는 경우 USER_ALREADY_HAVE_TEAM 를 던짐
             * */
            if (loginUser.getTeamId() != null) {
                throw new BusinessLogicException(Exceptions.USER_ALREADY_HAVE_TEAM);
            }

            /*
             * 팀 이름 중복 체크
             * 팀 이름이 중복된 경우 TEAM_NAME_EXISTS 를 던짐
             * */
            existByTeamName(teamName);

            team.setUser(loginUser);
            team.setManagerName(loginUser.getName());

            teamRepository.save(team);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.TEAM_NOT_CREATED);
        }
        return team;
    }

    public Team updateTeam(
            Team team, Long teamId) {
        try {
            Long userId = userService.getLoginUser().getUserId();
            if (userId == null || teamId == null) {
                log.info("userId:{}",userId);
                log.info("teamId:{}",teamId);
                throw new BusinessLogicException(Exceptions.USER_ID_IS_NULL);
            }
            String managerName = userService.getLoginUser().getName();

            Team findTeam = findByUserId(userId);
            if (!Objects.equals(findTeam.getTeamId(), teamId) && Objects.equals(findTeam.getManagerName(), managerName)){
                if (!Objects.equals(findTeam.getTeamId(), teamId) && Objects.equals(findTeam.getSubManagerName(), managerName)){
                     throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                 }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            Optional.ofNullable(team.getIntroduction())
                    .ifPresent(findTeam::setIntroduction);

            Optional.ofNullable(team.getAgeType())
                    .ifPresent(findTeam::setAgeType);

            Optional.ofNullable(team.getLocationType())
                    .ifPresent(findTeam::setLocationType);

            Optional.ofNullable(team.getUniformType())
                    .ifPresent(findTeam::setUniformType);

            teamRepository.save(findTeam);
            return findTeam;
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.TEAM_NOT_PATCHED);
        }
    }

    public void updateForLeagueMatchEnd(
            Long homeTeamScore,
            Long awayTeamScore,
            Long homeTeamId,
            Long awayTeamId
    ) {
        try {
            if (homeTeamId == null || awayTeamId == null) {
                log.info("homeTeamId: {}", homeTeamId);
                log.info("awayTeamId: {}", awayTeamId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }
            Team findHomeTeam = findByTeamId(homeTeamId);
            Team findAwayTeam = findByTeamId(awayTeamId);
            if(homeTeamScore>awayTeamScore){
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+300L);
                findHomeTeam.setTotalWinRecord(findHomeTeam.getTotalWinRecord()+1L);
                findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount()+1L);
                findHomeTeam.setLeagueMatchPoints(findHomeTeam.getLeagueMatchPoints()+3L);
                findHomeTeam.setLeagueWinRecord(findHomeTeam.getLeagueWinRecord()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+10L);
                findAwayTeam.setTotalLoseRecord(findAwayTeam.getTotalLoseRecord()+1L);
                findAwayTeam.setLeagueLoseRecord(findAwayTeam.getLeagueLoseRecord()+1L);
                findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount()+1L);

                //homeTeam 패배한 경우
            } else if(homeTeamScore<awayTeamScore){
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+10L);
                findHomeTeam.setTotalLoseRecord(findHomeTeam.getTotalLoseRecord()+1L);
                findHomeTeam.setLeagueLoseRecord(findHomeTeam.getLeagueLoseRecord()+1L);
                findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+300L);
                findAwayTeam.setTotalWinRecord(findAwayTeam.getTotalWinRecord()+1L);
                findAwayTeam.setLeagueMatchPoints(findAwayTeam.getLeagueMatchPoints()+3L);
                findAwayTeam.setLeagueWinRecord(findAwayTeam.getLeagueWinRecord()+1L);
                findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount()+1L);

                //무승부인 경우
            } else {
                findHomeTeam.setHonorScore(findHomeTeam.getHonorScore()+100L);
                findHomeTeam.setTotalDrawRecord(findHomeTeam.getTotalDrawRecord()+1L);
                findHomeTeam.setLeagueMatchPoints(findHomeTeam.getLeagueMatchPoints()+1L);
                findHomeTeam.setLeagueDrawRecord(findHomeTeam.getLeagueDrawRecord()+1L);
                findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount()+1L);

                findAwayTeam.setHonorScore(findAwayTeam.getHonorScore()+100L);
                findAwayTeam.setTotalDrawRecord(findAwayTeam.getTotalDrawRecord()+1L);
                findAwayTeam.setLeagueMatchPoints(findAwayTeam.getLeagueMatchPoints()+1L);
                findAwayTeam.setLeagueDrawRecord(findAwayTeam.getLeagueDrawRecord()+1L);
                findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount()+1L);
            }
            teamRepository.save(findHomeTeam);
            teamRepository.save(findAwayTeam);
            log.info("LEAGUE_MATCH_END ABOUT HOME_TEAM TO TEAM_REPOSITORY:{}", findHomeTeam);
            log.info("LEAGUE_MATCH_END ABOUT AWAY_TEAM TO TEAM_REPOSITORY:{}", findAwayTeam);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.TEAM_NOT_PATCHED);
        }
    }

    public List<Team> findAllTeamsByLeagueId(long leagueId) {return teamRepository.findAllTeamsByLeagueId(leagueId);}

    // 명예 점수 상위 조회
    public List<Team> findByHighestHonorScore() {return teamRepository.findByHighestHonorScore();}

    // 명예 점수 하위 조회
    public List<Team> findByLowestHonorScore() {return teamRepository.findByLowestHonorScore();}

    public Page<Team> findTeams(int page, int size) {
        return teamRepository.findAll(PageRequest.of(page, size,
                Sort.by("teamId").descending()));
    }

    public void deleteTeam(long teamId) {
        try {
            Team findTeam = findByTeamId(teamId);
            teamRepository.delete(findTeam);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.TEAM_NOT_DELETED);
        }
    }

    public Team findByTeamId(long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        return optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_NOT_FOUND));
    }

    public Team findByUserId(long userId) {
        Optional<Team> optionalTeam = teamRepository.findByUserId(userId);
        return optionalTeam.orElseThrow(() ->
                new BusinessLogicException(Exceptions.TEAM_NAME_NOT_FOUND));
    }

    public Team findByTeamName(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByTeamName(teamName);
        return optionalTeam.orElseThrow(() ->
                new BusinessLogicException(Exceptions.TEAM_NAME_NOT_FOUND));
    }

    public String findTeamManagerNameByUserId(long userId) {
        String teamManagerName = teamRepository.findTeamManagerNameByUserId(userId);
        if (teamManagerName == null) {
            throw new BusinessLogicException(Exceptions.TEAM_MANAGER_NAME_NOT_FOUND);
        }
        return teamManagerName;
    }

    public void existByTeamName(String managerTeamName) {
        String team = teamRepository.existByTeamName(managerTeamName);
        if(team != null) {
            throw new BusinessLogicException(Exceptions.TEAM_NAME_EXISTS);
        }
    }
}
