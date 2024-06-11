package company.board_project.domain.apply.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.AgeType;
import company.board_project.constant.AcceptType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchType;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.match.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "APPLIES")
public class Apply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applyId;

    @Column
    private long hostTeamId;

    @Column
    private long hostMatchId;

    @Column
    private long hostLeagueId;

    @Column
    private String applierName;

    @Column
    private String teamName;

    @Column
    private String applyMessage;

    @Column
    long age;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Enumerated(EnumType.STRING)
    private AcceptType acceptType = AcceptType.NONE;

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
