package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.Role;
import grabit.grabit_backend.Domain.User;

import java.util.Map;

public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private Integer unique_id;
    private String user_id;
    private String user_name;
    private String user_email;

    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, Integer unique_id, String user_id, String user_name,
                           String user_email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.unique_id = unique_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
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

    // (2)
    public User toEntity() {
        return new User(unique_id, user_id, user_name, user_email, Role.GUEST);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Integer getUnique_id() {
        return unique_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getNameAttributeKey() {
        return nameAttributeKey;
    }
}
