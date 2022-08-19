package grabit.grabit_backend.dto;

import grabit.grabit_backend.domain.JoinChallengeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseJoinChallengeRequestDTO {
    private final Long id;
    private final ResponseUserDTO user;
    private final ResponseChallengeDTO challenge;

    public static ResponseJoinChallengeRequestDTO convertDTO(JoinChallengeRequest joinChallengeRequest) {
        if (joinChallengeRequest == null) {
            return null;
        }
        return new ResponseJoinChallengeRequestDTO(
                joinChallengeRequest.getId(),
                ResponseUserDTO.convertDTO(joinChallengeRequest.getUser()),
                ResponseChallengeDTO.convertDTO(joinChallengeRequest.getChallenge())
        );
    }
}
