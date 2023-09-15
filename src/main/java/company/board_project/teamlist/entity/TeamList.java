package company.board_project.teamlist.entity;

import company.board_project.league.entity.League;
import company.board_project.apply.entity.Apply;
import company.board_project.team.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEAMLISTS")
public class TeamList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamListId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Team_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;
}
