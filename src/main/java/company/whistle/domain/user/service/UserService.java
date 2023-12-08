package company.whistle.domain.user.service;

import company.whistle.domain.user.repository.UserRepository;
import company.whistle.global.constant.LeagueRole;
import company.whistle.global.constant.LoginType;
import company.whistle.global.exception.BusinessLogicException;
import company.whistle.global.exception.Exceptions;
import company.whistle.global.security.utils.CustomAuthorityUtils;
import company.whistle.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        try {
            /*
             * 이메일 유효성 검사
             * 이메일이 repository 에 존재할 경우 예외 발생
             * */
            existUser(user.getEmail());

            List<String> roles = authorityUtils.createRoles(user.getEmail());
            user.setRoles(roles);

            String encryptPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptPassword);

            userRepository.save(user);
            log.info("USER POST COMPLETE: {}", user.toString());
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.USER_NOT_CREATED);
        }
        return user;
    }

    public User updateUser(User user) {
        try {
            User loginUser = getLoginUser();
            Long loginUserId = loginUser.getUserId();
            if (loginUserId == null) {
                throw new BusinessLogicException(Exceptions.ID_IS_NULL);
            }

            /*
             * 이메일 유효성 검사
             * 이메일이 repository 에 존재할 경우 예외 발생
             * */
            Optional.ofNullable(user.getEmail())
                    .ifPresent(newEmail -> {
                        existUser(loginUser.getEmail());
                        loginUser.setEmail(newEmail);
                    });

            Optional.ofNullable(user.getPassword())
                    .ifPresent(loginUser::setPassword);

            Optional.ofNullable(user.getName())
                    .ifPresent(loginUser::setName);
            userRepository.save(loginUser);
            log.info("USER PATCH COMPLETE: {}", user.toString());
        } catch (BusinessLogicException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessLogicException(Exceptions.USER_NOT_PATCHED);
        }
        return user;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        try {
            User findUser = findByUserId(userId);
            userRepository.delete(findUser);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusinessLogicException(Exceptions.USER_NOT_DELETED);
        }
    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException(Exceptions.USER_NOT_FOUND));
    }

    public User findByLeagueRole(LeagueRole leagueRole) {
        Optional<User> optionalUser = userRepository.findByLeagueRole(leagueRole);
        return optionalUser.orElseThrow(() ->
                new BusinessLogicException(Exceptions.UNAUTHORIZED));
    }

    /*
     * 이메일 유효성 검사
     * */
    public void existUser(String email) {
        Optional<User> userEmail = userRepository.findByEmail(email);
        if(userEmail.isPresent()) {
            throw new BusinessLogicException(Exceptions.EMAIL_EXISTS);
        }
    }

    /*
    * 로그인한 회원 검증 로직
    * getAuthentication()에서 email 값을 가져오면 userRepository 에서 일치 하는 값을 찾아 User 엔티티에 담아줌
    * */
    public User getLoginUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(Exceptions.USER_NOT_FOUND));
    }

    /*
     * CHECK LOGIN_USER
     * request 된 userId 값이 로그인한 유저와 id 값이 일치하는지 검증하는 로직
     * */
    public void checkJwtAndUser(Long userId) {
        if (!getLoginUser().getUserId().equals(userId)) {
            throw new BusinessLogicException(Exceptions.ACCESS_FORBIDDEN);
        }
    }

    /*
     * CHECK SOCIAL_USER
     * 소셜 로그인 검증 로직
     * */
    public void isSocialUser(User user) {
        if (user.getLoginType().equals(LoginType.SOCIAL)) {
            throw new BusinessLogicException(Exceptions.ACCESS_FORBIDDEN);
        }
    }

}
