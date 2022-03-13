package grabit.grabit_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyChallengeDTO {

	private String name;
	private String description;
	private String leader;
	private Boolean isPrivate;

}
