package grabit.grabit_backend.Domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "challenge")
public class Challenge extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "CHALLENGE_ID")
	private Long id;

	private String name;

	@Column(name = "CHALLENGE_DESC")
	private String challengeDesc;

	@ManyToOne(optional = false)
	@JoinColumn(name="LEADER_ID")
	private User leader;

	public Challenge() {}

	public Challenge(User leader, String name, String challengeDesc) {
		this.leader = leader;
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

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	public String getChallengeDesc() {
		return challengeDesc;
	}

	public void setChallengeDesc(String challengeDesc) {
		this.challengeDesc = challengeDesc;
	}
}
