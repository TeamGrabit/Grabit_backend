package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ChallengeDTO {
	private Long id;
	private String name;
	private String description;
	private Boolean isPrivate;
	private User leader;
	private List<UserChallenge> members;

	public ChallengeDTO toDTO(Challenge challenge) {
		return ChallengeDTO.builder()
				.id(challenge.getId())
				.name(challenge.getName())
				.description(challenge.getDescription())
				.isPrivate(challenge.getIsPrivate())
				.leader(challenge.getLeader())
				.members(challenge.getUserChallengeList())
				.build();
	}
}
