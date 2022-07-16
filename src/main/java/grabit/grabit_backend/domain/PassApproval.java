package grabit.grabit_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PASS_APPROVAL")
public class PassApproval extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASS_APPROVAL_ID")
	private Long id;

	@Column(name = "TargetDate")
	private LocalDate targetDate;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "CONTENT")
	private String content;

	@OneToMany(mappedBy = "passApproval", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<PassApprovalList> passApprovalList;

}
