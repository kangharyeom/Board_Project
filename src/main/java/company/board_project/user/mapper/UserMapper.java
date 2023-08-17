package company.board_project.user.mapper;

import company.board_project.user.dto.UserListDto;
import company.board_project.user.dto.UserPatchDto;
import company.board_project.user.dto.UserPostDto;
import company.board_project.user.dto.UserResponseDto;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostDtoToUser(UserPostDto requestBody);

    User userPatchDtoToUser(UserPatchDto requestBody);

    // 회원 단건 조회
    default UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }

    // 회원 전체 List
    default UserListDto usersToUserListResponse(List<User> userList) {
        return UserListDto.builder()
                .userResponseDto(usersToUsersResponse(userList))
                .build();
    }

    // 회원 response 리스트화
    default List<UserResponseDto> usersToUsersResponse(List<User> users){
        return users.stream()
                .map(user -> UserResponseDto.builder()
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .password(user.getPassword())
                        .phone(user.getPhone())
                        .createdAt(user.getCreatedAt())
                        .modifiedAt(user.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
