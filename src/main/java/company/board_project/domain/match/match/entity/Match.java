package company.board_project.domain.match.match.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.apply.entity.Apply;
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
@Table(name = "MATCHES")
public class Match extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    // HOME TEAM INFO
    @Column
    private String homeTeamName;

    @Column
    private String homeTeamManagerName;

    @Column
    private Integer homeTeamHonorScore;

    @Column
    private Integer homeTeamTotalWinRecord;

    @Column
    private Integer homeTeamTotalDrawRecord;

    @Column
    private Integer homeTeamTotalLoseRecord;

    @Column
    private Integer homeTeamLeagueWinRecord;

    @Column
    private Integer homeTeamLeagueDrawRecord;

    @Column
    private Integer homeTeamLeagueLoseRecord;

    @Enumerated(EnumType.STRING)
    private LevelType homeTeamLevelType;

    @Enumerated(EnumType.STRING)
    private AgeType homeTeamAgeType;

    @Enumerated(EnumType.STRING)
    private UniformType homeTeamUniformType;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus homeTeamMatchResultStatus = MatchResultStatus.NONE;


    // AWAY TEAM INFO
    @Column
    private String awayTeamName;

    @Column
    private String awayTeamManagerName;

    @Column
    private Long awayTeamUserId;

    @Column
    private Long awayTeamId;

    @Column
    private Integer awayTeamHonorScore;

    @Column
    private Integer awayTeamTotalWinRecord;

    @Column
    private Integer awayTeamTotalDrawRecord;

    @Column
    private Integer awayTeamTotalLoseRecord;

    @Column
    private Integer awayTeamLeagueWinRecord;

    @Column
    private Integer awayTeamLeagueDrawRecord;

    @Column
    private Integer awayTeamLeagueLoseRecord;

    @Enumerated(EnumType.STRING)
    private LevelType awayTeamLevelType;

    @Enumerated(EnumType.STRING)
    private AgeType awayTeamAgeType;

    @Enumerated(EnumType.STRING)
    private UniformType awayTeamUniformType;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus awayTeamMatchResultStatus = MatchResultStatus.NONE;

    // COMMON INFO
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Enumerated(EnumType.STRING)
    private SportsType sportsType;

    @Column
    private String matchAddress;

    @Column
    private String matchTime;

    @Column
    private String matchRules;

    @Column
    private String leagueName;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus = MatchStatus.BEFORE;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
}
