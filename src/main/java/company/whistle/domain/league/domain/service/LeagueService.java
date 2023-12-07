package company.whistle.domain.league.domain.service;

import company.whistle.domain.league.domain.repository.LeagueRepository;
import company.whistle.domain.league.participants.entity.Participants;
import company.whistle.global.constant.LeagueParticipantsStatus;
import company.whistle.global.constant.LeagueRole;
import company.whistle.global.constant.LeagueSeasonStatus;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.domain.league.domain.entity.League;
import company.whistle.domain.league.participants.repository.ParticipantsRepository;
import company.whistle.domain.team.domain.entity.Team;
import company.whistle.domain.team.domain.repository.TeamRepository;
import company.whistle.domain.team.domain.service.TeamService;
import company.whistle.domain.user.entity.User;
import company.whistle.domain.user.repository.UserRepository;
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
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final ParticipantsRepository participantsRepository;

    /*
    * 리그 생성
    */
    public League createLeague(League league, String leagueName, String managerTeamName) {
        try {
            Long userId = userService.getLoginUser().getUserId();
            if (userId == null || managerTeamName ==null || leagueName== null) {
                log.info("userId:{}", userId);
                log.info("teamName:{}", managerTeamName);
                log.info("leagueName:{}", leagueName);
                throw new BusinessLogicException(Exceptions.USER_ID_IS_NULL);
            }
            // 리그 정보에 managerTeamName이 있으면서도 league_season_status 가 ON_SEASON 이면 EXIST 를 던진다.
            checkDuplManagerTeamName(managerTeamName);
            checkDuplLeagueName(leagueName);

            User user = userService.findUser(userId);
            Team team = teamService.findTeamByTeamName(managerTeamName);

            // 팀에 이미 leagueId 값이 있다면 예외 발생
            if (team.getLeagueId() != null) {
                throw new BusinessLogicException(Exceptions.TEAM_HAS_LEAGUE);
            }

            if (!Objects.equals(user.getName(), team.getManagerName()) || team.getManagerName()==null) {
                if (!Objects.equals(user.getName(), team.getSubManagerName())|| team.getManagerName()==null) {
                    throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
                }
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }
            checkDuplTeamId(team.getTeamId());

            league.setUser(user);
            league.setTeam(team);
            team.setLeagueName(league.getLeagueName());
            league.setManagerName(user.getName());
            league.setManagerTeamName(team.getTeamName());
            league.setHonorScore(team.getHonorScore());
            league.setMemberCount(team.getMemberCount());
            user.setLeagueRole(LeagueRole.LEAGUE_MANAGER);

            userRepository.save(user);
            teamRepository.save(team);
            leagueRepository.save(league);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.LEAGUE_NOT_CREATED);
        }
        return league;
    }

    public League updateLeague(League league, Long leagueId) {
        try {
            Long userId = userService.getLoginUser().getUserId();
            if(leagueId==null || userId == null){
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            League findLeague = findLeagueByUserId(userId);
            // 리그 관리자만 수정 가능하도록
            User writer = userService.findVerifiedUserByLeagueRole(findLeague.getUser().getLeagueRole());
            if (!Objects.equals(userService.getLoginUser().getUserId(), writer.getUserId())) { // 로그인한 유저와 관리자가 다른 경우
                throw new BusinessLogicException(Exceptions.UNAUTHORIZED);
            }

            Optional.ofNullable(league.getManagerTeamName())
                    .ifPresent(findLeague::setManagerTeamName);

            Optional.ofNullable(league.getSportsType())
                    .ifPresent(findLeague::setSportsType);

            Optional.ofNullable(league.getMatchCount())
                    .ifPresent(findLeague::setMatchCount);

            Optional.ofNullable(league.getTeamCount())
                    .ifPresent(findLeague::setTeamCount);

            Optional.ofNullable(league.getAgeType())
                    .ifPresent(findLeague::setAgeType);

            Optional.ofNullable(league.getLocationType())
                    .ifPresent(findLeague::setLocationType);

            Optional.ofNullable(league.getPeriod())
                    .ifPresent(findLeague::setPeriod);

            Optional.ofNullable(league.getLevelType())
                    .ifPresent(findLeague::setLevelType);

            Optional.ofNullable(league.getLeagueRules())
                    .ifPresent(findLeague::setLeagueRules);

            Optional.ofNullable(league.getFrequency())
                    .ifPresent(findLeague::setFrequency);
            leagueRepository.save(findLeague);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.LEAGUE_NOT_PATCHED);
        }
        return league;
    }

    public void checkEndTheLeague(Long leagueId) {
        try {
            if(leagueId==null){
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            League league = findVerifiedLeague(leagueId);
            long leagueEndCount = league.getLeagueEndCount();
            long teamCount = league.getTeamCount();
            long matchCount = league.getMatchCount();
            long endCount = (teamCount * matchCount) / 2;

            if (leagueEndCount == endCount) {
                league.setLeagueSeasonStatus(LeagueSeasonStatus.valueOf("OFF_SEASON"));
                Participants participants = participantsRepository.findWinnerByLeagueId(leagueId);

                // 우승한 팀에게 우승 점수 부여
                Team team = teamService.findTeam(participants.getTeam().getTeamId());
                participants.setChampionCount(participants.getChampionCount() + 1L);
                team.setChampionCount(team.getChampionCount() + 1L);
                team.setHonorScore(team.getHonorScore()+1000L);
                participantsRepository.save(participants);
                teamRepository.save(team);
            }

            // 모든 팀 leagueId값 초기화
            List<Team> teamList = teamRepository.findAllTeamByLeagueId(leagueId);
            for (Team team : teamList) {
                team.setLeagueId(null);
                teamRepository.save(team);
            }

            // 모든 팀 LeagueParticipantsStatus 값 League_END로 변경
            List<Participants> participantsList = participantsRepository.findAllParticipantsByLeagueId(leagueId);
            for (Participants participants : participantsList) {
                participants.setLeagueParticipantsStatus(LeagueParticipantsStatus.LEAGUE_END);
                participantsRepository.save(participants);
            }

            leagueRepository.save(league);
            log.info("CHECK LEAGUE_MATCH_COUNT FOR LEAGUE_END TO LEAGUE_REPOSITORY:{}", endCount);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    public void updateForLeagueMatchEnd(Long leagueId) {
        try {
            if(leagueId==null){
                log.info("leagueId: {}", leagueId);
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }
            League findLeague = findVerifiedLeague(leagueId);
            findLeague.setLeagueEndCount(findLeague.getLeagueEndCount()+1L);
            leagueRepository.save(findLeague);
            log.info("LEAGUE_MATCH_END FOR LEAGUE_END_COUNT TO LEAGUE_REPOSITORY:{}", findLeague);
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    public League findLeague(Long leagueId) {return findVerifiedLeague(leagueId);}

    public Page<League> findLeagues(int page, int size) {
        return leagueRepository.findAll(PageRequest.of(page, size,
                Sort.by("leagueId").descending()));
    }

    // 최신 등록된 리그 순서 조회
    public List<League> findLeaguesNewest() {
        return leagueRepository.findLeaguesNewest();
    }

    // 오래된 순서 리그 조회
    public List<League> findLeaguesLatest() {
        return leagueRepository.findLeaguesLatest();
    }

    // 명예 점수 고득점 순서 조회
    public List<League> findHonorScore() {
        return leagueRepository.findHonorScore();
    }

    // 시즌 단위 조회 (시즌 진행중)
    public List<League> findLeagueOnSeason() {return leagueRepository.findLeagueOnSeason();}

    // 시즌 단위 조회 (시즌 종료)
    public List<League> findLeagueOffSeason() {return leagueRepository.findLeagueOffSeason();}

    // 시즌 단위 조회 (팀 모집)
    public List<League> findLeagueRecruit() {return leagueRepository.findLeagueRecruit();}

    public void deleteLeague(Long leagueId) {
        try {
            League findLeague = findVerifiedLeague(leagueId);
            leagueRepository.delete(findLeague);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.LEAGUE_NOT_DELETED);
        }

    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
    }

    public League findVerifiedLeague(Long leagueId) {
        Optional<League> optionalLeague = leagueRepository.findByLeagueId(leagueId);

        return optionalLeague.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));
    }


    public League findLeagueByUserId(Long userId) {
        League optionalLeague = leagueRepository.findByUserId(userId);

        if (optionalLeague == null) {
            throw new BusinessLogicException(Exceptions.LEAGUE_NOT_FOUND);
        }
        return optionalLeague;
    }

    public String findMgrNameOFLeagueByUserId(Long userId) {
        String managerName = leagueRepository.findMgrNameOFLeagueByUserId(userId);
        if (managerName == null) {
            throw new BusinessLogicException(Exceptions.LEAGUE_NOT_FOUND);
        }
        return managerName;
    }

    public void checkDuplTeamId(long teamId) {
        Long league = leagueRepository.checkDuplTeamId(teamId);
        if(league !=null) {
            throw new BusinessLogicException(Exceptions.YOUR_TEAM_ALREADY_HAS_LEAGUE);
        }
    }

    public void checkDuplLeagueName(String leagueName) {
        String league = leagueRepository.checkDuplLeagueName(leagueName);
        if(league !=null) {
            throw new BusinessLogicException(Exceptions.LEAGUE_NAME_EXISTS);
        }
    }

    public void checkDuplManagerTeamName(String teamName) {
        String checkTeamName = leagueRepository.checkDuplManagerTeamName(teamName);
        if(checkTeamName !=null) {
            throw new BusinessLogicException(Exceptions.TEAM_NAME_EXISTS);
        }
    }
}