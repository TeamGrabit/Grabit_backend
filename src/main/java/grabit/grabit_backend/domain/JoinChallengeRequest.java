package grabit.grabit_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "joinChallengeRequest")
@Table(indexes = @Index(name="unique_idx_user_challenge", columnList = "USER_ID, CHALLENGE_ID", unique = true))
public class JoinChallengeRequest extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "JOIN_CHALLENGE_REQUEST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHALLENGE_ID")
    @JsonBackReference
    private Challenge challenge;

    public static JoinChallengeRequest createJoinChallengeRequest(Challenge challenge, User user) {
        return JoinChallengeRequest.builder().
                user(user).
                challenge(challenge).
                build();
    }
}

