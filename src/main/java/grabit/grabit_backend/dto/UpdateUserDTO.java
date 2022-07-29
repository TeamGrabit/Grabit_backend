package grabit.grabit_backend.dto;

import lombok.Getter;
import javax.validation.constraints.Min;

@Getter
public class UpdateUserDTO {
    private String username;
    private String bio;
    private String profileImg;
}
