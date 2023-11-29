package company.board_project.domain.apply.league.entity;

import company.board_project.domain.league.entity.League;
import company.board_project.domain.list.league.entity.LeagueList;
import company.board_project.domain.list.match.entity.MatchList;
import company.board_project.domain.list.team.entity.TeamMemberList;
import company.board_project.domain.match.normal.entity.Match;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.user.entity.User;
import company.board_project.global.audit.Auditable;
import company.board_project.global.constant.AgeType;
import company.board_project.global.constant.ApplyType;
import company.board_project.global.constant.LevelType;
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
@Table(name = "LEAGUE_APPLIES")
public class LeagueApply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueApplyId;

    @Column(nullable = false)
    private String managerName;

    private String teamName;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private ApplyType applyType;

    @OneToMany(mappedBy = "leagueApply", cascade = CascadeType.REMOVE)
    private List<LeagueList> leagueLists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
}
