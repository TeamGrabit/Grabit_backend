package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResponseChallengeDTO {

	private Long id;
	private String name;
	private String description;
	private User leader;
	private Boolean isPrivate;

	public static ResponseChallengeDTO convertDTO(Challenge challenge){
		return new ResponseChallengeDTO(challenge.getId(), challenge.getName(), challenge.getDescription(), challenge.getLeader(), challenge.getIsPrivate());
	}

}
