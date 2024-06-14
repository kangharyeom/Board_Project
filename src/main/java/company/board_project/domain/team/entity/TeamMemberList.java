package company.board_project.domain.team.entity;

import company.board_project.constant.*;
import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEAM_MEMBER_LISTS")
public class TeamMemberList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamMemberListId;

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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

    @Override
    public String toString() {
        return "TeamMemberList{" +
                "teamMemberListId=" + teamMemberListId +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", teamMemberRole=" + teamMemberRole +
                ", ageType=" + ageType +
                ", locationType=" + locationType +
                ", levelType=" + levelType +
                ", frequency=" + frequency +
                ", user=" + user +
                ", team=" + team +
                ", apply=" + apply +
                '}';
    }
}
