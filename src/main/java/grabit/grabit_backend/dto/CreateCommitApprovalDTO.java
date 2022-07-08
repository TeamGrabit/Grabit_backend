package grabit.grabit_backend.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class CreateCommitApprovalDTO {

	@NotBlank
	private Long challengeId;

	@NotBlank
	private LocalDate targetDate;

	@NotNull
	private String content;
}
