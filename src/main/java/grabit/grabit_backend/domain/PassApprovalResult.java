package grabit.grabit_backend.domain;

import grabit.grabit_backend.converter.PassApprovalResultStatusConverter;
import grabit.grabit_backend.enums.PassApprovalResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PASS_APPROVAL_RESULT")
public class PassApprovalResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASS_APPROVAL_RESULT_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PASS_APPROVAL_ID")
	private PassApproval passApproval;

	@ManyToOne
	@JoinColumn(name = "CHALLENGE_ID")
	private Challenge challenge;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "STATUS")
	@Convert(converter = PassApprovalResultStatusConverter.class)
	private PassApprovalResultStatus status;

}
