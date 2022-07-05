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
public class JoinChallengeRequest {
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

    @Column(name = "status")
    @ColumnDefault(value = "0")
    private Integer status;
}
