package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.User;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private Integer uniqueId;
    private String name;
    private String email;

    public SessionUser(User user) {
        this.uniqueId = user.getUniqueId();
        this.name = user.getUserName();
        this.email = user.getUserEmail();
    }
}
