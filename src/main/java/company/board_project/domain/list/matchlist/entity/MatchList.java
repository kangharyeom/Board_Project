package company.board_project.domain.list.matchlist.entity;

import company.board_project.constant.*;
import company.board_project.domain.apply.entity.Apply;
import company.board_project.audit.Auditable;
import company.board_project.domain.match.match.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MATCH_LISTS")
public class MatchList extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchListId;

    @Column
    private Long awayTeamUserId;

    @Column
    private Long homeTeamId;

    @Column
    private Long awayTeamId;

    @Column
    private Long homeTeamScore;

    @Column
    private Long awayTeamScore;

    @Column
    private Long homeTeamHonorScore;

    @Column
    private Long awayTeamHonorScore;

    @Column
    private String homeTeamName;

    @Column
    private String awayTeamName;

    @Column
    private String homeTeamManagerName;

    @Column
    private String awayTeamManagerName;

    @Column
    private Long homeTeamTotalWinRecord;

    @Column
    private Long awayTeamTotalWinRecord;

    @Column
    private Long homeTeamTotalDrawRecord;

    @Column
    private Long awayTeamTotalDrawRecord;

    @Column
    private Long homeTeamTotalLoseRecord;

    @Column
    private Long awayTeamTotalLoseRecord;

    @Column
    private String matchTime;

    @Column
    private String matchDate;

    @Enumerated(EnumType.STRING)
    private LevelType homeTeamLevelType;

    @Enumerated(EnumType.STRING)
    private LevelType awayTeamLevelType;

    @Enumerated(EnumType.STRING)
    private AgeType homeTeamAgeType;

    @Enumerated(EnumType.STRING)
    private AgeType awayTeamAgeType;

    @Enumerated(EnumType.STRING)
    private UniformType homeTeamUniformType;

    @Enumerated(EnumType.STRING)
    private UniformType awayTeamUniformType;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus homeTeamMatchResultStatus;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus awayTeamMatchResultStatus;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MATCH_ID")
    private Match match;
}
