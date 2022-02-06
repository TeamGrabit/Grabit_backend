package grabit.grabit_backend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity(name = "ResponseLog")
public class ResponseLog {

	@Id @GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "PROCESS_TIME")
	private Long processTime;

	@Column(name = "HTTP_STATUS")
	private String httpStatus;

	@Column(name = "RESPONSE_AT")
	private LocalDateTime responseAt;

	@OneToOne
	@JoinColumn(name = "REQUEST_ID")
	private RequestLog requestLog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getResponseAt() {
		return responseAt;
	}

	public void setResponseAt(LocalDateTime responseAt) {
		this.responseAt = responseAt;
	}

	public RequestLog getRequestLog() {
		return requestLog;
	}

	public void setRequestLog(RequestLog requestLog) {
		this.requestLog = requestLog;
	}
}
