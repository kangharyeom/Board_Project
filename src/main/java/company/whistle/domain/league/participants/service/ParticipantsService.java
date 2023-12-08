package company.whistle.domain.league.participants.service;

import company.whistle.domain.apply.league.entity.LeagueApply;
import company.whistle.domain.apply.league.service.LeagueApplyService;
import company.whistle.domain.league.participants.entity.Participants;
import company.whistle.domain.league.participants.repository.ParticipantsRepository;
import company.whistle.domain.team.domain.repository.TeamRepository;
import company.whistle.domain.user.repository.UserRepository;
import company.whistle.global.constant.LeagueParticipantsStatus;
import company.whistle.global.constant.LeagueRole;
import company.whistle.global.constant.TeamMemberStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.league.domain.entity.League;
import company.whistle.domain.league.domain.repository.LeagueRepository;
import company.whistle.domain.league.domain.service.LeagueService;
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
public class ParticipantsService {
    private final ParticipantsRepository participantsRepository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final LeagueService leagueService;
    private final UserService userService;
    private final LeagueApplyService leagueApplyService;
    private final LeagueRepository leagueRepository;
    private final UserRepository userRepository;
    public Participants createParticipants(Participants participants, Long teamId) {
        try {
            Long loginUserId = userService.getLoginUser().getUserId();
            if (loginUserId == null || teamId == null) {
                log.info("loginUserId:{}", loginUserId);
                log.info("teamId:{}", teamId);
                throw new BusinessLogicException(Exceptions.IDS_ARE_NULL);
            }

            /*
            * teamId 중복 체크
            * participants DB에 teamId 가 있으면서 league_participants_status 가 ACTIVITY 상태라면 EXIST 를 던짐
            * */
            existByTeamId(teamId);

            /*
             * CHECK USER AUTHORIZATION
             * 유저가 관리자 권한을 가지고 접근하는지 검사
             * */
            League league = leagueService.findByUserId(loginUserId);
            if (!Objects.equals(userService.getLoginUser().getName(), league.getManagerName()))
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);

            // apply 한 유저와 팀 정보 검색 및 주입
            Team team = teamService.findByTeamId(teamId);
            User user = userService.findByUserId(team.getUser().getUserId());
            LeagueApply leagueApply = leagueApplyService.findByTeamId(teamId);

            participants.setUser(user);
            participants.setTeam(team);
            participants.setLeagueApply(leagueApply);
            participants.setLeague(league);
            participants.setManagerName(user.getName());
            participants.setHonorScore(team.getHonorScore());
            participants.setChampionCount(team.getChampionCount());
            participants.setMemberCount(team.getMemberCount());
            participants.setTeamName(team.getTeamName());
            participants.setSubManagerName(team.getSubManagerName());
            participants.setLeagueParticipantsStatus(LeagueParticipantsStatus.ACTIVITY);
            participants.setLeagueName(league.getLeagueName());

            team.setLeagueId(league.getLeagueId());

            league.setTeamCount(+1L);
            league.setMemberCount(team.getMemberCount()+ participants.getMemberCount());
            teamRepository.save(team);
            leagueRepository.save(league);
            participantsRepository.save(participants);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.PARTICIPANTS_NOT_CREATED);
        }
        return participants;
    }

    public Participants createParticipantsByLeagueController(
            Participants participants, Long userId, Long teamId, Long leagueId) {
        try {
            if (userId == null || teamId == null || leagueId == null) {
                log.info("userId:{}", userId);
                log.info("teamId:{}", teamId);
                log.info("leagueId:{}", leagueId);
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }

            /*
             * teamId 중복 체크
             * participants DB에 teamId 가 있으면서 league_participants_status 가 ACTIVITY 상태라면 EXIST 를 던짐
             * */
            existByTeamId(teamId);

            User user = userService.findByUserId(userId);
            Team team = teamService.findByTeamId(teamId);

            League league = leagueService.findByLeagueId(leagueId);

            participants.setUser(user);
            participants.setTeam(team);
            participants.setLeague(league);

            participants.setLeagueName(league.getLeagueName());
            participants.setLeagueWinRecord(0L);
            participants.setLeagueLoseRecord(0L);
            participants.setLeagueDrawRecord(0L);
            participants.setLeagueMatchPoints(0L);
            participants.setLeagueMatchCount(0L);

            participants.setChampionCount(team.getChampionCount());
            participants.setFormation(team.getFormation());
            participants.setHonorScore(team.getHonorScore());
            participants.setLeagueHonorScore(team.getHonorScore());
            participants.setMemberCount(team.getMemberCount());
            participants.setSubManagerName(team.getSubManagerName());
            participants.setTeamName(team.getTeamName());
            participants.setUniformType(team.getUniformType());

            participants.setManagerName(user.getName());
            user.setLeagueRole(LeagueRole.LEAGUE_MANAGER);
            participants.setLeagueParticipantsStatus(LeagueParticipantsStatus.ACTIVITY);
            team.setLeagueId(league.getLeagueId());

            teamRepository.save(team);
            userRepository.save(user);
            participantsRepository.save(participants);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.PARTICIPANTS_NOT_CREATED);
        }
        return participants;
    }

    public Participants updateParticipants(
            Participants participants,
            Long participantsId) {
        try {
            if (participantsId == null ) {
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            Participants findParticipants = findByParticipantsId(participantsId);

            Optional.ofNullable(participants.getFormation())
                    .ifPresent(findParticipants::setFormation);

            Optional.ofNullable(participants.getAgeType())
                    .ifPresent(findParticipants::setAgeType);

            Optional.ofNullable(participants.getLocationType())
                    .ifPresent(findParticipants::setLocationType);

            Optional.ofNullable(participants.getManagerName())
                    .ifPresent(findParticipants::setManagerName);

            Optional.ofNullable(participants.getSubManagerName())
                    .ifPresent(findParticipants::setSubManagerName);

            Optional.ofNullable(participants.getUniformType())
                    .ifPresent(findParticipants::setUniformType);

            participantsRepository.save(findParticipants);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.PARTICIPANTS_NOT_PATCHED);
        }
        return participants;
    }

    public void updateForLeagueMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            ,Long homeTeamLeagueListId
            ,Long awayTeamLeagueListId
    ) {
        try {
            if (homeTeamLeagueListId == null || awayTeamLeagueListId == null) {
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            Participants findHomeTeamParticipants = findByParticipantsId(homeTeamLeagueListId);
            Participants findAwayTeamParticipants = findByParticipantsId(awayTeamLeagueListId);

            //homeTeam 승리한 경우
            if(homeTeamScore>awayTeamScore){
                findHomeTeamParticipants.setHonorScore(findHomeTeamParticipants.getHonorScore()+300L);
                findHomeTeamParticipants.setLeagueMatchPoints(findHomeTeamParticipants.getLeagueMatchPoints()+3L);
                findHomeTeamParticipants.setLeagueWinRecord(findHomeTeamParticipants.getLeagueWinRecord()+1L);
                findHomeTeamParticipants.setLeagueMatchCount(findHomeTeamParticipants.getLeagueMatchCount()+1L);

                findAwayTeamParticipants.setHonorScore(findAwayTeamParticipants.getHonorScore()+10L);
                findAwayTeamParticipants.setLeagueLoseRecord(findAwayTeamParticipants.getLeagueLoseRecord()+1L);
                findAwayTeamParticipants.setLeagueMatchCount(findAwayTeamParticipants.getLeagueMatchCount()+1L);

                //homeTeam 패배한 경우
            } else if(homeTeamScore<awayTeamScore){
                findHomeTeamParticipants.setHonorScore(findHomeTeamParticipants.getHonorScore()+10L);
                findHomeTeamParticipants.setLeagueLoseRecord(findHomeTeamParticipants.getLeagueLoseRecord()+1L);
                findHomeTeamParticipants.setLeagueMatchCount(findHomeTeamParticipants.getLeagueMatchCount()+1L);

                findAwayTeamParticipants.setHonorScore(findAwayTeamParticipants.getHonorScore()+300L);
                findAwayTeamParticipants.setLeagueMatchPoints(findAwayTeamParticipants.getLeagueMatchPoints()+3L);
                findAwayTeamParticipants.setLeagueWinRecord(findAwayTeamParticipants.getLeagueWinRecord()+1L);
                findAwayTeamParticipants.setLeagueMatchCount(findAwayTeamParticipants.getLeagueMatchCount()+1L);

                //무승부인 경우
            } else {
                findHomeTeamParticipants.setHonorScore(findHomeTeamParticipants.getHonorScore()+100L);
                findHomeTeamParticipants.setLeagueMatchPoints(findHomeTeamParticipants.getLeagueMatchPoints()+1L);
                findHomeTeamParticipants.setLeagueDrawRecord(findHomeTeamParticipants.getLeagueDrawRecord()+1L);
                findHomeTeamParticipants.setLeagueMatchCount(findHomeTeamParticipants.getLeagueMatchCount()+1L);

                findAwayTeamParticipants.setHonorScore(findAwayTeamParticipants.getHonorScore()+100L);
                findAwayTeamParticipants.setLeagueMatchPoints(findAwayTeamParticipants.getLeagueMatchPoints()+1L);
                findAwayTeamParticipants.setLeagueDrawRecord(findAwayTeamParticipants.getLeagueDrawRecord()+1L);
                findAwayTeamParticipants.setLeagueMatchCount(findAwayTeamParticipants.getLeagueMatchCount()+1L);

            }
            participantsRepository.save(findHomeTeamParticipants);
            participantsRepository.save(findAwayTeamParticipants);
            log.info("LEAGUE_MATCH_END ABOUT HOME_TEAM TO PARTICIPANTS_REPOSITORY:{}", findHomeTeamParticipants);
            log.info("LEAGUE_MATCH_END ABOUT AWAY_TEAM TO PARTICIPANTS_REPOSITORY:{}", findAwayTeamParticipants);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.PARTICIPANTS_NOT_PATCHED);
        }
    }

    public List<Participants> findParticipantsNewest() {
        return participantsRepository.findLeagueParticipantsNewest();
    }

    public List<Participants> findParticipantsLatest() {
        return participantsRepository.findLeagueParticipantsLatest();
    }

    public List<Participants> findHonorScore() {
        return participantsRepository.findHonorScore();
    }

    public List<Participants> findAllParticipants() {
        return participantsRepository.findAll();
    }

    public void deleteParticipants(long participantsId) {
        try {
            Participants findParticipants = findByParticipantsId(participantsId);
            participantsRepository.delete(findParticipants);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.PARTICIPANTS_NOT_DELETED);
        }
    }

    public Participants findByParticipantsId(long participantsId) {
        Optional<Participants> optionalLeague = participantsRepository.findById(participantsId);
        return optionalLeague.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
    }

    public Participants findByTeamId(Long teamId) {
        Optional<Participants> optionalLeague = participantsRepository.findByTeamId(teamId);
        return optionalLeague.orElseThrow(() ->
                new BusinessLogicException(Exceptions.TEAM_NAME_NOT_FOUND));
    }

    public void existByTeamId(Long teamId) {
        Long teamIdOfParticipants = participantsRepository.existByTeamId(teamId);
        if(teamIdOfParticipants != null) {
            throw new BusinessLogicException(Exceptions.TEAM_EXISTS);
        }
    }

}
