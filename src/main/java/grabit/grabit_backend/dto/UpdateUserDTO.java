package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserDTO {
    @Min(value = 1)
    private int id;

    private String username;

    private String bio;
    private String profileImage;
}
