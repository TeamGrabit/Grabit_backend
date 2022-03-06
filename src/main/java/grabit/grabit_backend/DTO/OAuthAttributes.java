package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.User;

import java.util.Map;

public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private Integer id;
    private String userId;
    private String username;
    private String userEmail;

    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, Integer id, String userId, String username,
                           String userEmail) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
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
                (String) attributes.get("email"));
    }

    public User toEntity() {
        return new User(id, userId, username, userEmail);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Integer getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getNameAttributeKey() {
        return nameAttributeKey;
    }
}
