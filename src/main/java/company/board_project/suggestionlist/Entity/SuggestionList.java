package company.board_project.suggestionlist.Entity;

import company.board_project.constant.SuggestionType;
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
@Table(name = "SUGGESTIONLISTS")
public class SuggestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SuggestionListId;

    private String levelType;

    @Enumerated(EnumType.STRING)
    private SuggestionType suggestionType;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Team> teams = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}
