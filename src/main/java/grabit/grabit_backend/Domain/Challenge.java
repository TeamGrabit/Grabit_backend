package grabit.grabit_backend.Domain;

import grabit.grabit_backend.DTO.ModifyChallengeDTO;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "challenge")
public class Challenge {

	@Id @GeneratedValue
	@Column(name = "CHALLENGE_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LEADER")
	private String leader;

	@Column(name = "IS_PRIVATE")
	private Boolean isPrivate;

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public Challenge(){}

	public Challenge(String name, String description, String leader, Boolean isPrivate) {
		this.name = name;
		this.description = description;
		this.leader = leader;
		this.isPrivate = isPrivate;
	}

	public void modifyChallenge(ModifyChallengeDTO modifyChallengeDTO){
		this.name = modifyChallengeDTO.getName();
		this.description = modifyChallengeDTO.getDescription();
		this.leader = modifyChallengeDTO.getLeader();
		this.isPrivate = modifyChallengeDTO.getIsPrivate();
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

	public Boolean getPrivate() {
		return isPrivate;
	}

	public void setPrivate(Boolean aPrivate) {
		isPrivate = aPrivate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
