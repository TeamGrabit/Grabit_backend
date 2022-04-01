package grabit.grabit_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserChallengePK.class)
public class UserChallenge {

	@Id
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "CHALLENGE_ID")
	@JsonBackReference
	private Challenge challenge;
}
