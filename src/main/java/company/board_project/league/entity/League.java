package company.board_project.league.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.content.entity.Content;
import company.board_project.match.entity.Match;
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
@Table(name = "LEAGUES")
public class League extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    @Column(nullable = false)
    private Long matchCount;

    private Long teamCount;

    @Column(nullable = false)
    private String leagueName;

    @Column
    private String participantTeamName;

    @Enumerated(EnumType.STRING)
    private SportsType sportType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Column(nullable = false)
    private String period;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Column
    private String leagueRules;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Column
    private Long memberCount;

    @Column
    private Long leagueWinRecord;

    @Column
    private Long leagueDrawRecord;

    @Column
    private Long leagueLoseRecord;


    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Match> matchs = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}


