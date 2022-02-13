package grabit.grabit_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseChallengeDTO {

	private Long id;
	private String leaderId;
	private String name;
	private String challengeDesc;
}
