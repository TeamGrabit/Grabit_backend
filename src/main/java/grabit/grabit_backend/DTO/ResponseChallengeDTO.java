package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResponseChallengeDTO {

	private Long id;
	private String leaderId;
	private String name;
	private String challengeDesc;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public static ResponseChallengeDTO convertDTO(Challenge challenge){
		return new ResponseChallengeDTO(challenge.getId(), challenge.getLeader().getUserId(), challenge.getName(),
				challenge.getChallengeDesc(), challenge.getCreatedAt(), challenge.getModifiedAt());
	}

}
