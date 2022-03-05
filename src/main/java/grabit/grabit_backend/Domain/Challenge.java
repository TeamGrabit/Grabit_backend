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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_challenges",
		joinColumns = @JoinColumn(name = "challenge_id"),
		inverseJoinColumns = @JoinColumn(name = "member_id"))
	Set<User> members = new HashSet<>();

	public Challenge() {}

	public Challenge(User leader, String name, String challengeDesc) {
		this.leader = leader;
		this.name = name;

		this.challengeDesc = challengeDesc;

		this.members.add(leader);
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

	public Set<User> getMembers() {
		return members;
	}
	public void setMembers(Set<User> members) {
		this.members = members;
	}
	public String getChallengeDesc() {
		return challengeDesc;
	}

	public void setChallengeDesc(String challengeDesc) {
		this.challengeDesc = challengeDesc;
	}
}
