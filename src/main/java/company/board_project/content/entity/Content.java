package company.board_project.content.entity;

import company.board_project.comment.entity.Comment;
import company.board_project.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "CONTENTS")
public class Content {
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

    // 게시글 작성 시간
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 게시글 수정 시간
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;

    // 연관 관계 다대일 //
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 연관 관계 일대다 //
    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();
}
