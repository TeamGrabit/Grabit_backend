package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.PassApproval;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreatePassApprovalDTO;
import grabit.grabit_backend.dto.ResponsePassApprovalDTO;
import grabit.grabit_backend.service.PassApprovalResultService;
import grabit.grabit_backend.service.PassApprovalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("pass-approval")
public class PassApprovalController {

	private final PassApprovalService passApprovalService;
	private final PassApprovalResultService passApprovalResultService;

	public PassApprovalController(PassApprovalService passApprovalService, PassApprovalResultService passApprovalResultService) {
		this.passApprovalService = passApprovalService;
		this.passApprovalResultService = passApprovalResultService;
	}

	@PostMapping(value = "")
	public ResponseEntity<ResponsePassApprovalDTO> createPassApprovalAPI(@Valid @RequestBody CreatePassApprovalDTO createPassApprovalDTO,
																		   @AuthenticationPrincipal User user) {
		PassApproval createdPassApproval = passApprovalService.createPassApproval(createPassApprovalDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponsePassApprovalDTO.convertDTO(createdPassApproval));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ResponsePassApprovalDTO> readPassApprovalAPI (@PathVariable(value = "id") Long id) {
		PassApproval readedPassApproval = passApprovalService.readPassApproval(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponsePassApprovalDTO.convertDTO(readedPassApproval));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deletePassApprovalAPI(@PathVariable(value = "id") Long id,
														@AuthenticationPrincipal User user) {
		passApprovalService.deletePassApproval(id, user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping(value = "accept/{id}")
	public ResponseEntity<String> acceptPassApprovalAPI(@PathVariable(value = "id") Long id,
														  @AuthenticationPrincipal User user) {
		String result = passApprovalResultService.acceptPassApproval(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PatchMapping(value = "reject/{id}")
	public ResponseEntity<String> rejectPassApprovalAPI(@PathVariable(value = "id") Long id,
														  @AuthenticationPrincipal User user) {
		String result = passApprovalResultService.rejectPassApproval(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
