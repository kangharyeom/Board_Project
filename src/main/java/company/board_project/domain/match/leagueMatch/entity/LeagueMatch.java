package company.board_project.domain.match.leagueMatch.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.domain.league.entity.League;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LEAGUE_MATCHES")
public class LeagueMatch extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueMatchId;

    // HOME TEAM INFO
    @Column
    private Long homeTeamId;

    @Column
    private Long homeTeamUserId;

    @Column
    private String homeTeamName;

    @Column
    private String homeTeamManagerName;

    @Column
    private Integer homeTeamHonorScore;

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
    private Long awayTeamId;

    @Column
    private Long awayTeamUserId;

    @Column
    private String awayTeamName;

    @Column
    private String awayTeamManagerName;

    @Column
    private Integer awayTeamHonorScore;

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
    private Integer round;

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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
}
