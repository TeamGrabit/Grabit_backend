package grabit.grabit_backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
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

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
	private List<UserChallenge> userChallengeList = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name="LEADER_ID")
	private User leader;

	public Challenge() {}

	public Challenge(String name, String description, Boolean isPrivate, User leader) {
		this.name = name;
		this.description = description;
		this.leader = leader;
		this.isPrivate = isPrivate;
	}

	public void modifyChallenge(ModifyChallengeDTO modifyChallengeDTO, User leader) {
		this.name = modifyChallengeDTO.getName();
		this.description = modifyChallengeDTO.getDescription();
		this.isPrivate = modifyChallengeDTO.getIsPrivate();
		this.leader = leader;
	}
}
