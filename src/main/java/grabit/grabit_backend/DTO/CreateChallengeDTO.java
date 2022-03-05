package grabit.grabit_backend.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateChallengeDTO {

	@NotBlank
	private String name;

	@NotNull
	private String description;

	@NotNull
	private Boolean isPrivate;
}
