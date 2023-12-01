package company.whistle.domain.match.schedule.service;

import company.whistle.domain.apply.match.entity.MatchApply;
import company.whistle.domain.apply.match.service.MatchApplyService;
import company.whistle.domain.match.schedule.entity.Schedule;
import company.whistle.domain.match.schedule.repository.ScheduleRepository;
import company.whistle.domain.user.repository.UserRepository;
import company.whistle.global.constant.MatchResultStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.match.unrank.entity.Match;
import company.whistle.domain.match.unrank.service.MatchService;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.service.UserService;
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
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final MatchApplyService matchApplyService;
    private final MatchService matchService;

    public Schedule createSchedule(
            Schedule schedule, Long awayTeamUserId, Long matchApplyId, Long awayTeamId, Long matchId) {
        try {
            if (awayTeamUserId == null || matchApplyId == null || awayTeamId == null || matchId == null) {
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            User user = userService.findUser(awayTeamUserId);
            MatchApply matchApply = matchApplyService.findMatchApply(matchApplyId);
            Team team = teamService.findTeam(awayTeamId);
            Match match = matchService.findMatch(matchId);

            schedule.setUser(user);
            schedule.setMatchApply(matchApply);
            schedule.setTeam(team);
            schedule.setMatch(match);

            schedule.setHomeTeamHonorScore(match.getHomeTeamHonorScore());
            schedule.setHomeTeamScore(schedule.getHomeTeamScore());
            schedule.setHomeTeamName(match.getHomeTeamName());
            schedule.setHomeTeamManagerName(match.getHomeTeamManagerName());
            schedule.setHomeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord());
            schedule.setHomeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord());
            schedule.setHomeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord());
            schedule.setHomeTeamLevelType(match.getHomeTeamLevelType());
            schedule.setHomeTeamAgeType(match.getHomeTeamAgeType());
            schedule.setHomeTeamUniformType(match.getHomeTeamUniformType());

            schedule.setAwayTeamHonorScore(schedule.getAwayTeamScore());
            schedule.setAwayTeamHonorScore(team.getHonorScore());
            schedule.setAwayTeamName(team.getTeamName());
            schedule.setAwayTeamManagerName(team.getManagerName());
            schedule.setAwayTeamTotalWinRecord(team.getTotalWinRecord());
            schedule.setAwayTeamTotalDrawRecord(team.getTotalDrawRecord());
            schedule.setAwayTeamTotalLoseRecord(team.getTotalLoseRecord());
            schedule.setAwayTeamLevelType(team.getLevelType());
            schedule.setAwayTeamAgeType(team.getAgeType());
            schedule.setAwayTeamUniformType(team.getUniformType());

            schedule.setAwayTeamScore(schedule.getAwayTeamScore());

            schedule.setHomeTeamName(match.getHomeTeamName());
            scheduleRepository.save(schedule);
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_CREATED);
        }
        return schedule;
    }

    public Schedule createScheduleByMatchController(
            Schedule schedule, Long userId, Long teamId, Long matchId) {
        try {
            User user = userService.findUser(userId);
            Team team = teamService.findTeam(teamId);
            Match match = matchService.findMatch(matchId);

            user.setMatchId(matchId);

            schedule.setUser(user);
            schedule.setTeam(team);
            schedule.setMatch(match);

            schedule.setHomeTeamId(team.getTeamId());
            schedule.setHomeTeamHonorScore(match.getHomeTeamHonorScore());
            schedule.setHomeTeamName(match.getHomeTeamName());
            schedule.setHomeTeamManagerName(match.getHomeTeamManagerName());
            schedule.setHomeTeamTotalWinRecord(match.getHomeTeamTotalWinRecord());
            schedule.setHomeTeamTotalDrawRecord(match.getHomeTeamTotalDrawRecord());
            schedule.setHomeTeamTotalLoseRecord(match.getHomeTeamTotalLoseRecord());
            schedule.setHomeTeamLevelType(match.getHomeTeamLevelType());
            schedule.setHomeTeamAgeType(match.getHomeTeamAgeType());
            schedule.setHomeTeamUniformType(match.getHomeTeamUniformType());

            schedule.setAwayTeamHonorScore(team.getHonorScore());
            schedule.setAwayTeamName(team.getTeamName());
            schedule.setAwayTeamManagerName(team.getManagerName());
            schedule.setAwayTeamTotalWinRecord(team.getTotalWinRecord());
            schedule.setAwayTeamTotalDrawRecord(team.getTotalDrawRecord());
            schedule.setAwayTeamTotalLoseRecord(team.getTotalLoseRecord());
            schedule.setAwayTeamLevelType(team.getLevelType());
            schedule.setAwayTeamAgeType(team.getAgeType());
            schedule.setAwayTeamUniformType(team.getUniformType());

            schedule.setAwayTeamScore(schedule.getAwayTeamScore());

            schedule.setHomeTeamName(match.getHomeTeamName());

            userRepository.save(user);
            scheduleRepository.save(schedule);
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_PATCHED);
        }
        return schedule;
    }

    public Schedule updateSchedule(
            Schedule schedule,
            Long teamListId) {

        try {
            Schedule findSchedule = findVerifiedSchedule(teamListId);

            Optional.ofNullable(schedule.getHomeTeamHonorScore())
                    .ifPresent(findSchedule::setHomeTeamHonorScore);

            Optional.ofNullable(schedule.getHomeTeamName())
                    .ifPresent(findSchedule::setHomeTeamName);

            Optional.ofNullable(schedule.getHomeTeamManagerName())
                    .ifPresent(findSchedule::setHomeTeamManagerName);

            Optional.ofNullable(schedule.getHomeTeamTotalWinRecord())
                    .ifPresent(findSchedule::setHomeTeamTotalWinRecord);

            Optional.ofNullable(schedule.getHomeTeamTotalDrawRecord())
                    .ifPresent(findSchedule::setHomeTeamTotalDrawRecord);

            Optional.ofNullable(schedule.getHomeTeamTotalLoseRecord())
                    .ifPresent(findSchedule::setHomeTeamTotalLoseRecord);

            Optional.ofNullable(schedule.getHomeTeamLevelType())
                    .ifPresent(findSchedule::setHomeTeamLevelType);

            Optional.ofNullable(schedule.getHomeTeamAgeType())
                    .ifPresent(findSchedule::setHomeTeamAgeType);

            Optional.ofNullable(schedule.getHomeTeamUniformType())
                    .ifPresent(findSchedule::setHomeTeamUniformType);

            Optional.ofNullable(schedule.getAwayTeamHonorScore())
                    .ifPresent(findSchedule::setAwayTeamHonorScore);

            Optional.ofNullable(schedule.getAwayTeamUserId())
                    .ifPresent(findSchedule::setAwayTeamUserId);

            Optional.ofNullable(schedule.getAwayTeamId())
                    .ifPresent(findSchedule::setAwayTeamId);

            Optional.ofNullable(schedule.getAwayTeamName())
                    .ifPresent(findSchedule::setAwayTeamName);

            Optional.ofNullable(schedule.getAwayTeamManagerName())
                    .ifPresent(findSchedule::setAwayTeamManagerName);

            Optional.ofNullable(schedule.getAwayTeamTotalWinRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalWinRecord);

            Optional.ofNullable(schedule.getAwayTeamTotalDrawRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalDrawRecord);

            Optional.ofNullable(schedule.getAwayTeamTotalLoseRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalLoseRecord);

            Optional.ofNullable(schedule.getAwayTeamLevelType())
                    .ifPresent(findSchedule::setAwayTeamLevelType);

            Optional.ofNullable(schedule.getAwayTeamAgeType())
                    .ifPresent(findSchedule::setAwayTeamAgeType);

            Optional.ofNullable(schedule.getAwayTeamUniformType())
                    .ifPresent(findSchedule::setAwayTeamUniformType);

        /*Optional.ofNullable(teamList.getMostGoals())
                .ifPresent(findTeamList::setMostGoals);

        Optional.ofNullable(teamList.getMostAssist())
                .ifPresent(findTeamList::setMostAssist);

        Optional.ofNullable(teamList.getMostMom())
                .ifPresent(findTeamList::setMostMom);*/
            scheduleRepository.save(findSchedule);
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_PATCHED);
        }
        return schedule;
    }

    public Schedule updateScheduleWithAwayTeam(
            Schedule schedule
            ,Long scheduleId
    ) {
        try {
            Schedule findSchedule = findVerifiedSchedule(scheduleId);

            Optional.ofNullable(schedule.getAwayTeamHonorScore())
                    .ifPresent(findSchedule::setAwayTeamHonorScore);

            Optional.ofNullable(schedule.getAwayTeamUserId())
                    .ifPresent(findSchedule::setAwayTeamUserId);

            Optional.ofNullable(schedule.getAwayTeamId())
                    .ifPresent(findSchedule::setAwayTeamId);

            Optional.ofNullable(schedule.getAwayTeamName())
                    .ifPresent(findSchedule::setAwayTeamName);

            Optional.ofNullable(schedule.getAwayTeamManagerName())
                    .ifPresent(findSchedule::setAwayTeamManagerName);

            Optional.ofNullable(schedule.getAwayTeamTotalWinRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalWinRecord);

            Optional.ofNullable(schedule.getAwayTeamTotalDrawRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalDrawRecord);

            Optional.ofNullable(schedule.getAwayTeamTotalLoseRecord())
                    .ifPresent(findSchedule::setAwayTeamTotalLoseRecord);

            Optional.ofNullable(schedule.getAwayTeamLevelType())
                    .ifPresent(findSchedule::setAwayTeamLevelType);

            Optional.ofNullable(schedule.getAwayTeamAgeType())
                    .ifPresent(findSchedule::setAwayTeamAgeType);

            Optional.ofNullable(schedule.getAwayTeamUniformType())
                    .ifPresent(findSchedule::setAwayTeamUniformType);

        /*Optional.ofNullable(teamList.getMostGoals())
                .ifPresent(findTeamList::setMostGoals);

        Optional.ofNullable(teamList.getMostAssist())
                .ifPresent(findTeamList::setMostAssist);

        Optional.ofNullable(teamList.getMostMom())
                .ifPresent(findTeamList::setMostMom);*/
            scheduleRepository.save(findSchedule);
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_PATCHED);
        }
        return schedule;
    }

    public Schedule updateMatchEnd(Schedule schedule
            , Long scheduleId
    ) {

        try {
            Schedule findSchedule = findVerifiedSchedule(scheduleId);

            Optional.ofNullable(schedule.getHomeTeamScore())
                    .ifPresent(findSchedule::setHomeTeamScore);

            Optional.ofNullable(schedule.getAwayTeamScore())
                    .ifPresent(findSchedule::setAwayTeamScore);

            Optional.ofNullable(schedule.getMatchStatus())
                    .ifPresent(findSchedule::setMatchStatus);
            scheduleRepository.save(findSchedule);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return schedule;
    }

    public void updateForMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            , Long scheduleId
    ) {

        try {
            //리그 매치 정보 수정
            Schedule findSchedule = findVerifiedSchedule(scheduleId);

            //homeTeam 이긴 경우
            if (homeTeamScore > awayTeamScore) {
                findSchedule.setHomeTeamHonorScore(findSchedule.getHomeTeamHonorScore() + 300L);
                findSchedule.setHomeTeamTotalWinRecord(findSchedule.getHomeTeamTotalWinRecord() + 1L);
                findSchedule.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("WIN"));

                findSchedule.setAwayTeamHonorScore(findSchedule.getAwayTeamHonorScore() + 10L);
                findSchedule.setAwayTeamTotalLoseRecord(findSchedule.getAwayTeamTotalLoseRecord() + 1L);
                findSchedule.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));

                //homeTeam 패배한 경우
            } else if (homeTeamScore < awayTeamScore) {
                findSchedule.setHomeTeamHonorScore(findSchedule.getHomeTeamHonorScore() + 10L);
                findSchedule.setHomeTeamTotalLoseRecord(findSchedule.getHomeTeamTotalLoseRecord() + 1L);
                findSchedule.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("LOSE"));

                findSchedule.setAwayTeamHonorScore(findSchedule.getAwayTeamHonorScore() + 300L);
                findSchedule.setAwayTeamTotalWinRecord(findSchedule.getAwayTeamTotalWinRecord() + 1L);
                findSchedule.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("WIM"));

                //무승부인 경우
            } else {
                findSchedule.setHomeTeamHonorScore(findSchedule.getHomeTeamHonorScore() + 100L);
                findSchedule.setHomeTeamTotalDrawRecord(findSchedule.getHomeTeamTotalDrawRecord() + 1L);
                findSchedule.setHomeTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

                findSchedule.setAwayTeamHonorScore(findSchedule.getAwayTeamHonorScore() + 100L);
                findSchedule.setAwayTeamTotalDrawRecord(findSchedule.getAwayTeamTotalDrawRecord() + 1L);
                findSchedule.setAwayTeamMatchResultStatus(MatchResultStatus.valueOf("DRAW"));

            }
            scheduleRepository.save(findSchedule);
            log.info("UPDATE_FOR_MATCH_END TO SCHEDULE_REPOSITORY FINISHED:{}", findSchedule);
        } catch (BusinessLogicException e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(e.getExceptions());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_PATCHED);
        }
    }

    public Schedule findSchedule(long scheduleId) {
        return findVerifiedSchedule(scheduleId);
    }

    public List<Schedule> findSchedulesNewest() {
        return scheduleRepository.findSchedulesNewest();
    }

    public List<Schedule> findSchedulesLatest() {
        return scheduleRepository.findSchedulesLatest();
    }

    public List<Schedule> findHonorScore() {
        return scheduleRepository.findHonorScore();
    }


    public List<Schedule> findAllSchedulesByLeagueId(long leagueId) {
        return scheduleRepository.findAllSchedulesByLeagueId(leagueId);
    }

    public List<Schedule> findSchedules() {
        return scheduleRepository.findAll();
    }

    public void deleteSchedule(long scheduleId) {
        try {
            Schedule findSchedule = findVerifiedSchedule(scheduleId);
            scheduleRepository.delete(findSchedule);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.SCHEDULE_NOT_DELETED);
        }
    }

    public Schedule findVerifiedSchedule(long scheduleId) {
        Optional<Schedule> optionalTeam = scheduleRepository.findById(scheduleId);
        return optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
    }
}
