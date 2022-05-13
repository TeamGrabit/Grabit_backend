package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public static ResponsePagingDTO convertPageDTO(Page<Challenge> challengePage){
		List<ResponseChallengeDTO> challengeDTOList = new ArrayList<>();
		challengePage.getContent().forEach(x -> challengeDTOList.add(convertDTO(x)));

		return ResponsePagingDTO.builder()
				.content(challengeDTOList)
				.pageable(challengePage.getPageable())
				.totalPages(challengePage.getTotalPages())
				.totalElements(challengePage.getTotalElements())
				.first(challengePage.isFirst())
				.last(challengePage.isLast())
				.numberOfElements(challengePage.getNumberOfElements())
				.size(challengePage.getSize())
				.number(challengePage.getNumber())
				.sort(challengePage.getSort())
				.build();
	}

}
