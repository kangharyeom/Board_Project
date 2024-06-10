package company.board_project.domain.list.teamlist.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.domain.apply.entity.Apply;
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
@Table(name = "TEAM_LISTS")
public class TeamList extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamListId;
    
    @Column
    private String name;


    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private TeamMemberRole teamMemberRole;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

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
