package company.whistle.global.security.oauth;
import company.whistle.global.constant.AuthProvider;
import company.whistle.global.exception.BusinessLogicException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
@Getter
@Builder
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profileUrl;
    private String authProvider;

    public static OAuth2Attributes of(String registrationId,
                                      String userNameAttributeName,
                                      Map<String, Object> attributes) throws BusinessLogicException {
        if ("kakao".equals(registrationId)) {
            log.info("### 카카오 로그인 ###");
            return ofKakao("id", attributes);
        }
        else {
            log.info("### 구글 로그인 ###");
            return ofGoogle(userNameAttributeName, attributes);
        }
    }

    private static OAuth2Attributes ofKakao(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount =
                (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile =
                (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attributes.builder()
                .name((String)kakaoProfile.get("nickname"))
                .email((String)kakaoAccount.get("email"))
                .profileUrl((String)kakaoProfile.get("profile_image_url"))
                .authProvider(AuthProvider.KAKAO.toString())
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName,
                                             Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .profileUrl((String)attributes.get("picture"))
                .authProvider(AuthProvider.GOOGLE.toString())
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }
}