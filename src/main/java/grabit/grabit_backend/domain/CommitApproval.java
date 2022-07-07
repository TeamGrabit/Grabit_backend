package grabit.grabit_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COMMIT_APPROVAL")
public class CommitApproval extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "COMMIT_APPROVAL_ID")
	private Long id;

	@Column(name = "TargetDate")
	private LocalDate targetDate;

	@Column(name = "CONTENT")
	private String content;

}
