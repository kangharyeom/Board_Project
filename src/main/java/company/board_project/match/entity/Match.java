package company.board_project.match.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.schedule.entity.Schedule;
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
@Table(name = "MATCHS")
public class Match extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    //유저 Input

    private Long homeScore = 0L ;

    private Long awayScore = 0L ;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Enumerated(EnumType.STRING)
    private SportsType sportType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Column(nullable = false)
    private String matchTime;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Column
    private String matchRules;



    // DB Input

    @Column(nullable = false)
    private String homeTeamName;

    @Column
    private String awayTeamName;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus = MatchStatus.BEFORE;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus homeTeamMatchResultStatus = MatchResultStatus.NONE;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus awayTeamMatchResultStatus = MatchResultStatus.NONE;




    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Team> teams = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

}
