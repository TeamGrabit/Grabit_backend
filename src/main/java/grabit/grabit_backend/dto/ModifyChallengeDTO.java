package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ModifyChallengeDTO {

	@NotBlank
	private String name;

	@NotNull
	private String description;

	@NotBlank
	private String leader;

	@NotNull
	private Boolean isPrivate;

}
