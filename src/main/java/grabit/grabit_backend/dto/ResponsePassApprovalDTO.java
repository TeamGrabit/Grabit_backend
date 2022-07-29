package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.PassApproval;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ResponsePassApprovalDTO {

	private Long id;
	private LocalDate targetDate;
	private String content;

	public static ResponsePassApprovalDTO convertDTO(PassApproval passApproval) {
		return ResponsePassApprovalDTO.builder()
				.id(passApproval.getId())
				.targetDate(passApproval.getTargetDate())
				.content(passApproval.getContent())
				.build();
	}
}
