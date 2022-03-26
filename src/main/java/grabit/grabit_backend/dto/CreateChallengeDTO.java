package grabit.grabit_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CreateChallengeDTO {

	@NotBlank
	private String name;

	@NotNull
	private String description;

	@NotNull
	private Boolean isPrivate;

}
