package grabit.grabit_backend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "challenge")
public class Challenge {

	@Id @GeneratedValue
	@Column(name = "GROUP_ID")
	private Long id;

	@Column(name = "LEADER_ID")
	private String leaderId;
	private String name;
	private LocalDateTime createdAt;

	public Challenge(String leaderId, String name) {
		this.leaderId = leaderId;
		this.name = name;
		this.createdAt = LocalDateTime.now();
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
