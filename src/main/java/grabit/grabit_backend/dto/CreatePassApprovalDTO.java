package grabit.grabit_backend.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class CreatePassApprovalDTO {

	@NotNull
	private Long challengeId;

	@NotNull
	private LocalDate targetDate;

	@NotNull
	private String content;
}
