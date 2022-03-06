package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.User;

public class UserResDTO {
    private int id;
    private String githubId;
    private String username;
    private String userEmail;

    public UserResDTO(User user) {
        this.id = user.getId();
        this.githubId = user.getUserId();
        this.username = user.getUsername();
        this.userEmail = user.getUserEmail();
    }

    public int getId() {
        return id;
    }

    public String getGithubId() {
        return githubId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
