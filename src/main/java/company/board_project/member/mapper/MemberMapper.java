package company.board_project.member.mapper;

import company.board_project.member.dto.MemberPatchDto;
import company.board_project.member.dto.MemberPostDto;
import company.board_project.member.dto.MemberResponseDto;
import company.board_project.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto requestBody);

    Member memberPatchDtoToMember(MemberPatchDto requestBody);

    default MemberResponseDto memberToMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .phone(member.getPhone())
                .build();
    }
}
