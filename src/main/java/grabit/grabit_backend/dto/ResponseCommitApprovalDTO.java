package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.CommitApproval;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ResponseCommitApprovalDTO {

	private Long id;
	private LocalDate targetDate;
	private String content;

	public static ResponseCommitApprovalDTO convertDTO(CommitApproval commitApproval) {
		return ResponseCommitApprovalDTO.builder()
				.id(commitApproval.getId())
				.targetDate(commitApproval.getTargetDate())
				.content(commitApproval.getContent())
				.build();
	}
}
