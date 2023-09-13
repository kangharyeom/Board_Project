package company.board_project.team.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.AgeType;
import company.board_project.constant.LocationType;
import company.board_project.constant.UniformType;
import company.board_project.league.entity.League;
import company.board_project.match.entity.Match;
import company.board_project.matchSuggestionList.Entity.MatchSuggestionList;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEAMS")
public class Team extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false)
    private Long winCount;

    @Column(nullable = false)
    private Long memberCount;

    @Column(nullable = false)
    private Long leagueWinRecord;

    @Column(nullable = false)
    private Long leagueDrawRecord;

    @Column(nullable = false)
    private Long leagueLoseRecord;

    @Column(nullable = false)
    private Long totalWinRecord;

    @Column(nullable = false)
    private Long totalDrawRecord;

    @Column(nullable = false)
    private Long totalLoseRecord;

    @Column(nullable = false)
    private Long honorScore;

    @Column(nullable = false)
    private Long ranking;

    @Column(nullable = false)
    private Long mostGoals;

    @Column(nullable = false)
    private Long mostAssist;

    @Column(nullable = false)
    private Long mostMom;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String introduction;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private String subManagerName;

    @Enumerated(EnumType.STRING)
    private UniformType uniform;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MATCHSUGGESTIONLIST_ID")
    private MatchSuggestionList matchSuggestionList;
}
