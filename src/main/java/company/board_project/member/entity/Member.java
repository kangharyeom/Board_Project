package company.board_project.member.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 60)
    @Length(min = 1, max = 60, message = "사이즈를 확인하세요.")
    @NotBlank(message = "이메일은 필수 값 입니다.")
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

}