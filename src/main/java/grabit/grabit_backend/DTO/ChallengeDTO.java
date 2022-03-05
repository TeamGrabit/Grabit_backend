package grabit.grabit_backend.DTO;

import grabit.grabit_backend.Domain.Challenge;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class ChallengeDTO {
	private Long id;
	private String leaderId;
	private String name;
	private LocalDateTime createdAt;

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
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
