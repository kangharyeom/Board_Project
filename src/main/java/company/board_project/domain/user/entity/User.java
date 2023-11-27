package company.board_project.domain.user.entity;

import company.board_project.global.audit.Auditable;
import company.board_project.domain.comment.entity.Comment;
import company.board_project.global.constant.*;
import company.board_project.domain.content.entity.Content;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.list.leaguelist.entity.LeagueList;
import company.board_project.domain.list.matchlist.entity.MatchList;
import company.board_project.domain.list.teamlist.entity.TeamList;
import company.board_project.domain.match.normalmatch.entity.Match;
import company.board_project.domain.schedule.entity.Schedule;
import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long teamId;
    private Long matchId;
    private Long leagueId;

    private Long userTeamApplyId;
    private Long userMatchApplyId;
    private Long userLeagueApplyId;

    @Column(nullable = false, unique = true, updatable = false)
    private String loginId;

    @Email
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Enumerated(EnumType.STRING)
    private LeagueRole leagueRole;

    @Enumerated(EnumType.STRING)
    private TeamMemberRole teamMemberRole;

    @Column
    @Enumerated(EnumType.STRING)
    private LoginType loginType = LoginType.BASIC;

    @Column
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<TeamList> teamLists = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<MatchList> matchLists = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<LeagueList> leagueLists = new ArrayList<>();

}