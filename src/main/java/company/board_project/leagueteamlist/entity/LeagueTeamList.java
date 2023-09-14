package company.board_project.leagueteamlist.entity;

import company.board_project.league.entity.League;
import company.board_project.suggestion.entity.Suggestion;
import company.board_project.team.entity.Team;
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
@Table(name = "LEAGUETEAMLISTS")
public class LeagueTeamList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueTeamListId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Team_ID")
    private Team team;

    @OneToMany(mappedBy = "leagueTeamList", cascade = CascadeType.REMOVE)
    private List<Suggestion> suggestions = new ArrayList<>();
}
