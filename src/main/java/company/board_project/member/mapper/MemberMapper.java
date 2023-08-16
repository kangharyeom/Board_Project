package company.board_project.member.mapper;

import company.board_project.member.dto.MemberListDto;
import company.board_project.member.dto.MemberPatchDto;
import company.board_project.member.dto.MemberPostDto;
import company.board_project.member.dto.MemberResponseDto;
import company.board_project.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto requestBody);

    Member memberPatchDtoToMember(MemberPatchDto requestBody);

    // 회원 단건 조회
    default MemberResponseDto memberToMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .phone(member.getPhone())
                .build();
    }

    // 회원 전체 List
    default MemberListDto membersToMemberListResponse(List<Member> memberList) {
        return MemberListDto.builder()
                .memberResponseDto(membersToMembersResponse(memberList))
                .build();
                
    }

    // 회원 response 리스트화
    default List<MemberResponseDto> membersToMembersResponse(List<Member> members){
        return members.stream()
                .map(member -> MemberResponseDto.builder()
                        .memberId(member.getMemberId())
                        .email(member.getEmail())
                        .name(member.getName())
                        .password(member.getPassword())
                        .phone(member.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

}
