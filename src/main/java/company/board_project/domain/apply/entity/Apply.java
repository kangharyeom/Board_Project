package company.board_project.domain.apply.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.AgeType;
import company.board_project.constant.ApplyType;
import company.board_project.constant.LevelType;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.list.matchlist.entity.MatchList;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.match.normalmatch.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "applies")
public class Apply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    private Long userTeamApplyId;
    private Long userMatchApplyId;
    private Long userLeagueApplyId;

    @Column(nullable = false)
    private String managerName;

    private String teamName;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private ApplyType applyType;

    @OneToMany(mappedBy = "apply", cascade = CascadeType.REMOVE)
    private List<TeamList> teamLists = new ArrayList<>();

    @OneToMany(mappedBy = "apply", cascade = CascadeType.REMOVE)
    private List<MatchList> matchLists = new ArrayList<>();

    @OneToMany(mappedBy = "apply", cascade = CascadeType.REMOVE)
    private List<LeagueList> leagueLists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

}
