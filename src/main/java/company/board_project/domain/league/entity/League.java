package company.board_project.domain.league.entity;

import company.board_project.global.audit.Auditable;
import company.board_project.domain.content.entity.Content;
import company.board_project.global.constant.*;
import company.board_project.domain.list.league.entity.LeagueList;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.team.entity.Team;
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
@Table(name = "LEAGUES")
public class League extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    @Column(nullable = false)
    private Long matchCount;

    // matchCount == leagueEndCount 일경우 리그 종료
    // leagueEndCount == (각 팀 경기수 총합/팀 수)
    @Column
    private Long leagueEndCount;

    @Column
    private Long teamCount;

    @Column
    private Long memberCount;

    @Column(nullable = false)
    private String leagueName;

    @Column(nullable = false)
    private String managerName;

    @Column
    private String managerTeamName;

    @Enumerated(EnumType.STRING)
    private SportsType sportsType;

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

    @Enumerated(EnumType.STRING)
    private SeasonType seasonType;

    @Column
    private Long honorScore;


    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<LeagueList> leagueLists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}


