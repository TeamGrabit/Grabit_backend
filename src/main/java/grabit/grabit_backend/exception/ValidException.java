package grabit.grabit_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ControllerAdvice
@RestController
public class ValidException {

	private static final Logger logger = LoggerFactory.getLogger(ValidException.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ObjectError>> validExceptionHandler(MethodArgumentNotValidException exception){
		BindingResult bindingResult = exception.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()){
			builder.append(fieldError.getField()+" is "+fieldError.getDefaultMessage()+" : ");
			builder.append("input = "+fieldError.getRejectedValue()+"\n");
		}
		logger.error("## Error UNAUTHORIZED parameter ##");
		logger.error(builder.toString());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(bindingResult.getAllErrors());
	}
}
