package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.User;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private Integer Id;
    private String name;
    private String email;

    public SessionUser(User user) {
        this.Id = user.getId();
        this.name = user.getUsername();
        this.email = user.getUserEmail();
    }
}
