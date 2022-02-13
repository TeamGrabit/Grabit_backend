package grabit.grabit_backend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "challenge")
public class Challenge extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "CHALLENGE_ID")
	private Long id;

	@Column(name = "LEADER_ID")
	private String leaderId;
	private String name;

	public Challenge(){}

	public Challenge(String leaderId, String name) {
		this.leaderId = leaderId;
		this.name = name;
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

}
