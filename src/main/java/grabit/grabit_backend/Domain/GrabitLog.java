package grabit.grabit_backend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "GrabitLog")
public class GrabitLog {

	@Id @GeneratedValue
	@Column(name = "LOG_ID")
	private Long id;

	@Column(name = "DATA")
	private String Data;

	@Column(name = "CALLED_AT")
	private LocalDateTime calledAt;

	@Column(name = "TYPE")
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public LocalDateTime getCalledAt() {
		return calledAt;
	}

	public void setCalledAt(LocalDateTime calledAt) {
		this.calledAt = calledAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
