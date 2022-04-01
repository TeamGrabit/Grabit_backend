package grabit.grabit_backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private Integer id;
    private String userId;
    private String username;
    private String userEmail;
    private String bio;
    private String profileImg;

    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, Integer id, String userId, String username,
                           String userEmail, String bio, String profileImg) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.bio = bio;
        this.profileImg = profileImg;
    }
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("github".equals(registrationId)) {
            return ofGithub("id", attributes);
        }

        return ofGithub(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofGithub(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return new OAuthAttributes(
                attributes,
                userNameAttributeName,
                (Integer) attributes.get("id"),
                (String) attributes.get("login"),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("bio"),
                (String) attributes.get("avatar_url"));
    }

    public User toEntity() {
        return new User(id, userId, username, userEmail, bio, profileImg);
    }
}
