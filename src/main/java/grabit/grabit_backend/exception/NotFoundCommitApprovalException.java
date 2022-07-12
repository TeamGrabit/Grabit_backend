package grabit.grabit_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class NotFoundCommitApprovalException extends RuntimeException {
	public NotFoundCommitApprovalException(String message) {
		super(message);
	}
	public NotFoundCommitApprovalException() {
		super("존재하지 않는 승인요청입니다.");
	}
}
