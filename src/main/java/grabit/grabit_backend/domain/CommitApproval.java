package grabit.grabit_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

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

	@OneToMany(mappedBy = "commitApproval", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CommitApprovalList> commitApprovalList;

}
