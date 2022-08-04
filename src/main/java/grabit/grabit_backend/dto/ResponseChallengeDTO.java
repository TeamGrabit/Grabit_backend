package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.Challenge;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ResponseChallengeDTO {

	private final Long id;
	private final String name;
	private final String description;
	private final String leader;
	private final Boolean isPrivate;
	private final List<String> member;

	public static ResponseChallengeDTO convertDTO(Challenge challenge){
		// 순환 참조를 방지하기 위해 데이터를 담아서 보냄.
		List<String> members = new ArrayList<>();
		challenge.getUserChallengeList().forEach(x -> members.add(x.getUser().getUserId()));

		return new ResponseChallengeDTO(
				challenge.getId(),
				challenge.getName(),
				challenge.getDescription(),
				challenge.getLeader().getUserId(),
				challenge.getIsPrivate(),
				members
		);
	}
}
