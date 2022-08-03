package grabit.grabit_backend.dto;

import grabit.grabit_backend.enums.SearchType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SearchChallengeDTO {

	@NotNull
	String type;

	@NotNull
	String content;

}
