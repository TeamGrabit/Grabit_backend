package grabit.grabit_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class NotFoundPassApprovalException extends RuntimeException {
	public NotFoundPassApprovalException(String message) {
		super(message);
	}
	public NotFoundPassApprovalException() {
		super("존재하지 않는 pass 요청입니다.");
	}
}
