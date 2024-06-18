package company.board_project.domain.match.leagueMatch.service;

import company.board_project.domain.league.service.LeagueService;
import company.board_project.domain.match.leagueMatch.entity.LeagueMatch;
import company.board_project.domain.match.leagueMatch.repository.LeagueMatchRepository;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueMatchService {
    private final LeagueMatchRepository leagueMatchRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final LeagueService leagueService;

    public LeagueMatch createLeagueMatch(LeagueMatch leagueMatch, long leagueId) {
        Team homeTeam = teamService.findTeamByTeamName(leagueMatch.getHomeTeamName());
        Team awayTeam = teamService.findTeamByTeamName(leagueMatch.getAwayTeamName());

        User homeTeamUser = userService.findUser(homeTeam.getUser().getUserId());
        User awayTeamUser = userService.findUser(awayTeam.getUser().getUserId());

        leagueMatch.setHomeTeamHonorScore(homeTeam.getHonorScore());
        leagueMatch.setHomeTeamName(homeTeam.getTeamName());
        leagueMatch.setHomeTeamManagerName(homeTeam.getManagerName());
        leagueMatch.setHomeTeamLevelType(homeTeam.getLevelType());
        leagueMatch.setHomeTeamAgeType(homeTeam.getAgeType());
        leagueMatch.setHomeTeamUniformType(homeTeam.getUniformType());

        leagueMatch.setAwayTeamHonorScore(awayTeam.getHonorScore());
        leagueMatch.setAwayTeamName(awayTeam.getTeamName());
        leagueMatch.setAwayTeamManagerName(awayTeam.getManagerName());
        leagueMatch.setAwayTeamLevelType(awayTeam.getLevelType());
        leagueMatch.setAwayTeamAgeType(awayTeam.getAgeType());
        leagueMatch.setAwayTeamUniformType(awayTeam.getUniformType());

        leagueMatch.setMatchType(leagueMatch.getMatchType());

        leagueMatchRepository.save(leagueMatch);

        return leagueMatch;
    }

}
