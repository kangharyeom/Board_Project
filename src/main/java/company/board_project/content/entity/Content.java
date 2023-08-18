package company.board_project.content.entity;

import company.board_project.audit.Auditable;
import company.board_project.comment.entity.Comment;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "CONTENTS")
public class Content extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_ID")
    private Long contentId;

    // 게시글 제목
    @Column(nullable = false)
    private String title;

    // 게시글 내용
    @Column(nullable = false)
    private String content;

    // 연관 관계 다대일 //
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 연관 관계 일대다 //
    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

}
