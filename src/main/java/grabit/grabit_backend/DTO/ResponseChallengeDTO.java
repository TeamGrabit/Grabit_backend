package grabit.grabit_backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseChallengeDTO {

	private Long id;
	private String leaderId;
	private String name;
	private String challengeDesc;
}
