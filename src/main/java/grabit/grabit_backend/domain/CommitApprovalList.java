package grabit.grabit_backend.domain;

import grabit.grabit_backend.converter.CommitApprovalListStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COMMIT_APPROVAL_LIST")
public class CommitApprovalList {

	@Id
	@GeneratedValue
	@Column(name = "COMMIT_APPROVAL_LIST_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "COMMIT_APPROVAL_ID")
	private CommitApproval commitApproval;

	@ManyToOne
	@JoinColumn(name = "CHALLENGE_ID")
	private Challenge challenge;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "STATUS")
	@Convert(converter = CommitApprovalListStatusConverter.class)
	private String status;

}
