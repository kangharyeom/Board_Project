package company.board_project.domain.list.leaguelist.entity;

import company.board_project.domain.apply.entity.Apply;
import company.board_project.global.audit.Auditable;
import company.board_project.global.constant.*;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LEAGUE_LISTS")
public class LeagueList extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueListId;

    @Column
    private Long leagueHonorScore;

    @Column
    private Long honorScore;

    @Column
    private Long memberCount;

    @Column
    private Long championCount;

    @Column
    private String teamName;

    @Column
    private String managerName;

    @Column
    private String subManagerName;
    
    @Column
    private String leagueName;

    @Enumerated(EnumType.STRING)
    private Formation formation;
    
    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private UniformType uniformType;

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
    private Long teamGoals;

    @Column
    private Long teamAssist;

    @Column
    private Long cleanSheet;

    /*@Column
    private Long mostGoals;
    @Column
    private Long mostAssists;
    @Column
    private Long mostMoMs;*/

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

}
