package company.board_project.match.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.list.matchlist.entity.MatchList;
import company.board_project.schedule.entity.Schedule;
import company.board_project.apply.entity.Apply;
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
@Table(name = "MATCHES")
public class Match extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    //유저 Input

    @Column
    private Long homeTeamScore;

    @Column
    private Long homeTeamHonorScore;

    @Column
    private String homeTeamName;

    @Column
    private String homeTeamManagerName;

    @Column
    private Long homeTeamLeagueMatchPoints;

    @Column
    private Long homeTeamLeagueWinRecord;

    @Column
    private Long homeTeamLeagueDrawRecord;

    @Column
    private Long homeTeamLeagueLoseRecord;

    @Column
    private Long homeTeamTotalWinRecord;

    @Column
    private Long homeTeamTotalDrawRecord;

    @Column
    private Long homeTeamTotalLoseRecord;

    @Column
    private Long homeTeamRanking;

    @Enumerated(EnumType.STRING)
    private LevelType homeTeamLevelType;

    @Enumerated(EnumType.STRING)
    private AgeType homeTeamAgeType;

    @Enumerated(EnumType.STRING)
    private UniformType homeTeamUniformType;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Enumerated(EnumType.STRING)
    private SportsType sportType;

    @Column(nullable = false)
    private String matchTime;

    @Column
    private String matchRules;

    // DB Input

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus = MatchStatus.BEFORE;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus homeTeamMatchResultStatus = MatchResultStatus.NONE;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus awayTeamMatchResultStatus = MatchResultStatus.NONE;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<MatchList> matchList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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
