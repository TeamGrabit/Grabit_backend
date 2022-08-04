package grabit.grabit_backend.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChallengeDTO {

	@NotBlank
	private String name;

	@NotNull
	private String description;

	@NotNull
	private Boolean isPrivate;

}
