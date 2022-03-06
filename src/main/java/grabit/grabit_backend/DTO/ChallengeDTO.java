package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Domain.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

public class ChallengeDTO {
	private Long id;
	private String name;
	private String description;
	private String leader;
	private List<User> user;
	private Boolean isPrivate;

<<<<<<< HEAD
	public ChallengeDTO(Long id, String name, String description, String leader, List<User> user, Boolean isPrivate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.leader = leader;
		this.user = user;
		this.isPrivate = isPrivate;
=======
	public ChallengeDTO(Challenge challenge) {
		this.id = challenge.getId();
		this.leaderId = challenge.getLeader().getUserId();
		this.name = challenge.getName();
		this.createdAt = challenge.getCreatedAt();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{id: " + id + ", ");
		sb.append("leaderId: " + leaderId + ", ");
		sb.append("name: " + name + ", ");
		sb.append("createdAt: " + createdAt + "}");
		return sb.toString();
>>>>>>> develop
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public Boolean getPrivate() {
		return isPrivate;
	}

	public void setPrivate(Boolean aPrivate) {
		isPrivate = aPrivate;
	}
}
