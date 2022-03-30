package grabit.grabit_backend.dto;

import lombok.Getter;
import javax.validation.constraints.Min;

@Getter
public class UpdateUserDTO {
    @Min(value = 1)
    private int id;

    private String username;

    private String bio;
    private String profileImage;
}
