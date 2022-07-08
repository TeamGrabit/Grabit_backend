package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreateCommitApprovalDTO;
import grabit.grabit_backend.dto.ResponseCommitApprovalDTO;
import grabit.grabit_backend.service.CommitApprovalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("commit-approval")
public class CommitApprovalController {

	private final CommitApprovalService commitApprovalService;

	public CommitApprovalController(CommitApprovalService commitApprovalService) {
		this.commitApprovalService = commitApprovalService;
	}

	@PostMapping(value = "")
	public ResponseEntity<ResponseCommitApprovalDTO> createCommitApprovalAPI(@Valid @RequestBody CreateCommitApprovalDTO createCommitApprovalDTO,
																			 @AuthenticationPrincipal User user) {
		CommitApproval createdCommitApproval = commitApprovalService.createCommitApproval(createCommitApprovalDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseCommitApprovalDTO.convertDTO(createdCommitApproval));
	}

}
