package grabit.grabit_backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserChallengePK implements Serializable {
	private Integer user;
	private Long challenge;
}
