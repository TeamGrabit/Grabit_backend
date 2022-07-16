package grabit.grabit_backend.domain;

import grabit.grabit_backend.converter.PassApprovalListStatusConverter;
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
@Entity(name = "PASS_APPROVAL_LIST")
public class PassApprovalList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASS_APPROVAL_LIST_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "Pass_APPROVAL_ID")
	private PassApproval passApproval;

	@ManyToOne
	@JoinColumn(name = "CHALLENGE_ID")
	private Challenge challenge;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "STATUS")
	@Convert(converter = PassApprovalListStatusConverter.class)
	private String status;

}
