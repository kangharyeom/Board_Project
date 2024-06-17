package company.board_project.domain.user.mapper;

import company.board_project.constant.Position;
import company.board_project.domain.user.dto.UserPatchDto;
import company.board_project.domain.user.dto.UserPostDto;
import company.board_project.domain.user.dto.UserResponseDto;
import company.board_project.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:42:34+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostDtoToUser(UserPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        User user = new User();

        user.setTeamId( requestBody.getTeamId() );
        user.setMatchId( requestBody.getMatchId() );
        user.setLeagueId( requestBody.getLeagueId() );
        user.setLoginId( requestBody.getLoginId() );
        user.setEmail( requestBody.getEmail() );
        user.setName( requestBody.getName() );
        user.setPassword( requestBody.getPassword() );
        user.setPhone( requestBody.getPhone() );

        return user;
    }

    @Override
    public User userPatchDtoToUser(UserPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( requestBody.getUserId() );
        user.setTeamId( requestBody.getTeamId() );
        user.setLoginId( requestBody.getLoginId() );
        user.setEmail( requestBody.getEmail() );
        user.setName( requestBody.getName() );
        user.setPassword( requestBody.getPassword() );
        user.setPhone( requestBody.getPhone() );
        if ( requestBody.getPosition() != null ) {
            user.setPosition( Enum.valueOf( Position.class, requestBody.getPosition() ) );
        }

        return user;
    }

    @Override
    public List<UserResponseDto> usersToUsersResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponseDto( user ) );
        }

        return list;
    }
}
