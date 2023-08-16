package company.board_project.member.controller;

import company.board_project.member.dto.MemberPatchDto;
import company.board_project.member.dto.MemberPostDto;
import company.board_project.member.dto.MemberResponseDto;
import company.board_project.member.entity.Member;
import company.board_project.member.mapper.MemberMapper;
import company.board_project.member.service.MemberService;
import company.board_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity postMember(@RequestBody @Validated MemberPostDto requestBody){
        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(requestBody));
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(member);

        return new ResponseEntity(
                new SingleResponseDto<>( memberResponseDto), HttpStatus.CREATED);
    }

    // 회원 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") @Positive Long memberId,
                                      @RequestBody @Valid MemberPatchDto requestBody) {
        requestBody.setMemberId(memberId);

        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));
        member.setMemberId(memberId);

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(member);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    // 회원 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId") @Positive Long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }

    // 회원 전체 조회
    @GetMapping
    public ResponseEntity getAllMember(@PathVariable("memberId") @Positive Long memberId) {
        List<Member> members = memberService.findAllMember();

        return new ResponseEntity<>(memberMapper.membersToMembersResponse(members), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") @Positive Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
