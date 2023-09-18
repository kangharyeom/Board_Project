package company.board_project.list.teamlist.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.apply.entity.Apply;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEAM_LISTS")
public class TeamList extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamListId;
    
    @Column
    private Long honorScore;

    @Column
    private Long memberCount;

    @Column
    private Long championCount;

    @Column
    private String teamName;

    @Column
    private Position position;

    @Column
    private String managerName;

    @Column
    private String subManagerName;
    
    @Column
    private String leagueName;
    
    @Column
    private String leagueRules;

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
    private Long leagueMatchPoints;

    @Column
    private Long leagueWinRecord;

    @Column
    private Long leagueDrawRecord;

    @Column
    private Long leagueLoseRecord;

    @Column
    private Long ranking;

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

//    @OneToMany(mappedBy = "teamList", cascade = CascadeType.REMOVE)
//    private List<League> leagues = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

}
