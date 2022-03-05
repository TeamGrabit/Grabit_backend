package grabit.grabit_backend.Domain;

import javax.persistence.*;

@Entity(name = "challenge")
public class Challenge extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "CHALLENGE_ID")
	private Long id;

	@Column(name = "LEADER_ID")
	private String leaderId;
	private String name;

	@Column(name = "CHALLENGE_DESC")
	private String challengeDesc;

	public Challenge() {}

	public Challenge(String leaderId, String name, String challengeDesc) {
		this.leaderId = leaderId;
		this.name = name;

		this.challengeDesc = challengeDesc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChallengeDesc() {
		return challengeDesc;
	}

	public void setChallengeDesc(String challengeDesc) {
		this.challengeDesc = challengeDesc;
	}
}
