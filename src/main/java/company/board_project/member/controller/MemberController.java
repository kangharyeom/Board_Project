package company.board_project.member.controller;

import company.board_project.member.dto.MemberPatchDto;
import company.board_project.member.dto.MemberPostDto;
import company.board_project.member.mapper.MemberMapper;
import company.board_project.member.service.MemberService;
import company.board_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

        return new ResponseEntity(
                new SingleResponseDto<>(, HttpStatus.CREATED)
        );
    }

    // 회원 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") @Positive Long memberId,
                                      @RequestBody @Valid MemberPatchDto requestBody) {

        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 회원 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId") @Positive Long memberId) {
        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 회원 전체 조회
    @GetMapping
    public ResponseEntity getAllMember(@PathVariable("memberId") @Positive Long memberId) {
        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") @Positive Long memberId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
