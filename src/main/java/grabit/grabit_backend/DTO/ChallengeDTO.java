package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChallengeDTO {
	private Long id;
	private String name;
	private String description;
	private User leader;
	private List<User> members;
	private Boolean isPrivate;

	public ChallengeDTO(Long id, String name, String description, User leader, List<User> members, Boolean isPrivate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.leader = leader;
		this.members = members;
		this.isPrivate = isPrivate;
	}
}
