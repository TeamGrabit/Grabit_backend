package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.JoinChallengeRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@SuperBuilder
public class ResponseJoinChallengeRequestPagingDTO extends ResponsePagingDTO{
    public List<ResponseJoinChallengeRequestDTO> content;

    public static ResponseJoinChallengeRequestPagingDTO convertDTO(Page<JoinChallengeRequest> joinChallengeRequestPage) {
        List<ResponseJoinChallengeRequestDTO> joinChallengeRequestDTOList = new ArrayList<>();
        joinChallengeRequestPage.getContent().forEach(x -> joinChallengeRequestDTOList.add(ResponseJoinChallengeRequestDTO.convertDTO(x)));

        return ResponseJoinChallengeRequestPagingDTO.builder()
                .content(joinChallengeRequestDTOList)
                .pageable(joinChallengeRequestPage.getPageable())
                .totalPages(joinChallengeRequestPage.getTotalPages())
                .totalElements(joinChallengeRequestPage.getTotalElements())
                .first(joinChallengeRequestPage.isFirst())
                .last(joinChallengeRequestPage.isLast())
                .numberOfElements(joinChallengeRequestPage.getNumberOfElements())
                .size(joinChallengeRequestPage.getSize())
                .number(joinChallengeRequestPage.getNumber())
                .sort(joinChallengeRequestPage.getSort())
                .build();
    }
}
