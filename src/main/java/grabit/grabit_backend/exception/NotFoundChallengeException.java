package grabit.grabit_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundChallengeException extends RuntimeException{
	public NotFoundChallengeException(String message) {
		super(message);
	}
	public NotFoundChallengeException() {
		super("존재하지 않는 챌린지입니다.");
	}
}
