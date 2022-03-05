package grabit.grabit_backend.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateChallengeDTO {

	@NotBlank
	private String name;

	@NotNull
	private String challengeDesc;
}
