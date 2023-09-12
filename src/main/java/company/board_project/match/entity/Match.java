package company.board_project.match.entity;

import company.board_project.league.entity.League;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MATCHS")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    private Long userId;
    private Long teamId;

    @Enumerated(EnumType.STRING)
    private String matchType;

    @Enumerated(EnumType.STRING)
    private String sportType;

    @Enumerated(EnumType.STRING)
    private String ageType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String matchTime;

    @Enumerated(EnumType.STRING)
    private String levelType;

    @Column
    private String leagueRules;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
