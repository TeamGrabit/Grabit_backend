package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDTO {
    private int id;
    private String githubId;
    private String username;
    private String userEmail;
    private String bio;
    private String profileImg;

    public ResponseUserDTO(User user) {
        this.id = user.getId();
        this.githubId = user.getUserId();
        this.username = user.getUsername();
        this.userEmail = user.getUserEmail();
        this.bio = user.getBio();
        this.profileImg = user.getProfileImg();
    }
}
