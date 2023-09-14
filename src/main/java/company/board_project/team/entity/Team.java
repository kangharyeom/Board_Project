package company.board_project.team.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.leagueteamlist.entity.LeagueTeamList;
import company.board_project.match.entity.Match;
import company.board_project.schedule.entity.Schedule;
import company.board_project.suggestion.entity.Suggestion;
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
@Table(name = "TEAMS")
public class Team extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private Long championCount;

    @Column
    private Long memberCount;

    @Column
    private Long leagueMatchCount;

    @Column
    private Long leagueWinRecord;

    @Column
    private Long leagueDrawRecord;

    @Column
    private Long leagueLoseRecord;

    @Column
    private Long totalMatchCount;

    @Column
    private Long totalWinRecord;

    @Column
    private Long totalDrawRecord;

    @Column
    private Long totalLoseRecord;

    @Column
    private Long honorScore;

    @Column
    private Long ranking;

    @Column
    private Long mostGoals;

    @Column
    private Long mostAssist;

    @Column
    private Long mostMom;

    @Column(nullable = false)
    private String teamName;

    @Column
    private String introduction;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private SportsType sportsType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Column(nullable = false)
    private String managerName;

    @Column
    private String subManagerName;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private UniformType uniformType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Suggestion> suggestions = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<LeagueTeamList> leagueTeamLists = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Match> matchs = new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.DETACH)
//    @JoinColumn(name = "MATCH_ID")
//    private Match match;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

}
