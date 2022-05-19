package grabit.grabit_backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "challenge")
public class Challenge extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "CHALLENGE_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_PRIVATE")
	private Boolean isPrivate;

	@OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<UserChallenge> userChallengeList = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name="LEADER_ID")
	private User leader;

	public void modifyChallenge(ModifyChallengeDTO modifyChallengeDTO, User leader) {
		this.name = modifyChallengeDTO.getName();
		this.description = modifyChallengeDTO.getDescription();
		this.isPrivate = modifyChallengeDTO.getIsPrivate();
		this.leader = leader;
	}

	public static Challenge createChallenge(CreateChallengeDTO createChallengeDTO, User leader) {
		return Challenge.builder()
				.name(createChallengeDTO.getName())
				.description(createChallengeDTO.getDescription())
				.isPrivate(createChallengeDTO.getIsPrivate())
				.leader(leader)
				.build();
	}
}
