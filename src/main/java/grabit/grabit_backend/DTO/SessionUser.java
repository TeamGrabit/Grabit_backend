package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.User;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private Integer uniqueId;
    private String name;
    private String email;

    public SessionUser(User user) {
        this.uniqueId = user.getUnique_id();
        this.name = user.getUser_name();
        this.email = user.getUser_email();
    }
}
