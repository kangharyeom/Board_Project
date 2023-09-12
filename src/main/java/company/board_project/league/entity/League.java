package company.board_project.league.entity;

import company.board_project.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LEAGUES")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    @Column(nullable = false)
    private String leagueName;

    @Enumerated(EnumType.STRING)
    private String sportType;

    @Enumerated(EnumType.STRING)
    private String ageType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String period;

    @Enumerated(EnumType.STRING)
    private String levelType;

    @Column
    private String leagueRules;

    @Enumerated(EnumType.STRING)
    private String frequency;

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Match> matchs = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;


}
