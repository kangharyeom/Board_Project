package company.board_project.domain.team.entity;

import company.board_project.domain.apply.team.entity.TeamApply;
import company.board_project.global.audit.Auditable;
import company.board_project.global.constant.*;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.list.league.entity.LeagueList;
import company.board_project.domain.list.match.entity.MatchList;
import company.board_project.domain.list.team.entity.TeamMemberList;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.user.entity.User;
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
    private Long leagueMatchPoints;

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
    private Long mostGoals;

    @Column
    private Long mostAssist;

    @Column
    private Long mostMom;

    @Column(nullable = false)
    private String teamName;

    @Column
    private String introduction;

    @Column(nullable = false)
    private String managerName;

    @Column
    private String leagueName;

    @Column
    private String subManagerName;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private SportsType sportsType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private UniformType uniformType;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<TeamApply> teamApplies = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<TeamMemberList> teamMemberLists = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<MatchList> matchLists = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<LeagueList> leagueLists = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Match> matches = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

}
