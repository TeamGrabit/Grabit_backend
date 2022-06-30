package grabit.grabit_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserChallengePK.class)
public class UserChallenge {
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CHALLENGE_ID")
	@JsonBackReference
	private Challenge challenge;

	@Column
	private boolean isBookmarked;

	public static UserChallenge createUserChallenge(Challenge challenge, User user) {
		return UserChallenge.builder()
				.user(user)
				.challenge(challenge)
				.isBookmarked(false)
				.build();
	}

	public boolean getIsBookmarked() {
		return this.isBookmarked;
	}
	public void setIsBookmarked(boolean isBookmarked){
		this.isBookmarked = isBookmarked;
	}
}
