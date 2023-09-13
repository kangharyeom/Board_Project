package company.board_project.matchSuggestionList.Entity;

import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
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
@Table(name = "MATCHSUGGESTIONLISTS")
public class MatchSuggestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchSuggestionListId;

    private String levelType;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Team> teams = new ArrayList<>();
}
