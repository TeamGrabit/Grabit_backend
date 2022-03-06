package grabit.grabit_backend.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserChallenge {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_CHALLENGE_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "CHALLENGE_ID")
	private Challenge challenge;
}
