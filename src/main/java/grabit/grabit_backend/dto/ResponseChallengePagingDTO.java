package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.Challenge;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ResponseChallengePagingDTO extends ResponsePagingDTO {
    public List<ResponseChallengeDTO> content;

    public static ResponseChallengePagingDTO convertDTO(Page<Challenge> challengePage){
        List<ResponseChallengeDTO> challengeDTOList = new ArrayList<>();
        challengePage.getContent().forEach(x -> challengeDTOList.add(ResponseChallengeDTO.convertDTO(x)));

        return ResponseChallengePagingDTO.builder()
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
