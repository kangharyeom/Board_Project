package company.board_project.user.controller;

import company.board_project.user.dto.UserPatchDto;
import company.board_project.user.dto.UserPostDto;
import company.board_project.user.dto.UserResponseDto;
import company.board_project.user.entity.User;
import company.board_project.user.mapper.UserMapper;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
import company.board_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity postUser(@RequestBody @Validated UserPostDto requestBody){
        log.info(requestBody.toString());

        User user = userService.createUser(userMapper.userPostDtoToUser(requestBody));
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);

        return new ResponseEntity(
                new SingleResponseDto<>(userResponseDto), HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity patchUser(@PathVariable("userId") @Positive Long userId,
                                      @RequestBody @Valid UserPatchDto requestBody) {
        requestBody.setUserId(userId);

        User user = userService.updateUser(userMapper.userPatchDtoToUser(requestBody));
        user.setUserId(userId);

        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 회원 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") @Positive Long userId) {
        User user = userService.findUser(userId);

        return new ResponseEntity<>(userMapper.userToUserResponseDto(user), HttpStatus.OK);
    }

    // 회원 전체 조회
    @GetMapping
    public ResponseEntity getAllUser() {
        List<User> users = userService.findAllUser();

        return new ResponseEntity<>(userMapper.usersToUsersResponse(users), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") @Positive Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // 세션 로그인 구현
    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/addUserForm";
        }

        userRepository.save(user);
        return "redirect:/";
    }
}