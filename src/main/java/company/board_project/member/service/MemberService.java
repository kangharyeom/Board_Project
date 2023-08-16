package company.board_project.member.service;

import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.member.entity.Member;
import company.board_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public Member createMember(Member member) {
        // 이메일 유무 확인
        verifiedMember(member.getEmail());

        // repository에 회원 등록
        memberRepository.save(member);

        return member;
    }

    // 회원 수정
    public Member updateMember(Member member) {
        // 회원 유무 확인
        Member findMember = existMember(member.getMemberId());

        // 새로 변경할 이메일 존재 유무 확인
        Optional.ofNullable(member.getEmail())
                .ifPresent(newEmail -> {
                    verifiedMember(findMember.getEmail());
                    findMember.setEmail(newEmail);
                });

        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);

        Optional.ofNullable(member.getName())
                .ifPresent(findMember::setName);

        return memberRepository.save(findMember);
    }

    // 회원 단건 조회
    public Member findMember(Long memberId) {
        return existMember(memberId);
    }

    // 회원 전체 조회
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    // 회원 탈퇴
    public void deleteMember(Long memberId) {
        Member findMember = existMember(memberId);
        memberRepository.delete(findMember);
    }

    // 회원 존재 유무 확인 메서드
    public Member existMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(Exceptions.MEMBER_NOT_FOUND));
    }

    // 이메일 중복 검사
    public void verifiedMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) {
            throw new BusinessLogicException(Exceptions.EMAIL_EXISTS);
        }
    }
}
