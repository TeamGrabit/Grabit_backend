package grabit.grabit_backend.controller;

import grabit.grabit_backend.service.CommitApprovalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("commit-approval")
public class CommitApprovalController {

	private final CommitApprovalService commitApprovalService;

	public CommitApprovalController(CommitApprovalService commitApprovalService) {
		this.commitApprovalService = commitApprovalService;
	}


}
