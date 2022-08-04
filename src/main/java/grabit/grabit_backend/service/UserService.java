package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.UpdateUserDTO;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    @Autowired
    public UserService(UserRepository userRepository, ChallengeRepository challengeRepository) {
        this.userRepository=userRepository;
        this.challengeRepository = challengeRepository;
    }

    /**
     * 유저 업데이트
     * @param
     * @return
     */
    @Transactional
    public User updateUser(UpdateUserDTO updateUserDTO, User user) {
        if (updateUserDTO.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
        if (updateUserDTO.getBio() != null) user.setBio(updateUserDTO.getBio());
        if (updateUserDTO.getProfileImg() != null) user.setProfileImg(updateUserDTO.getProfileImg());

        return userRepository.save(user);
    }

    /**
     * 유저가 가입한 챌린지 목록
     */
    public Page<Challenge> findUserJoinedChallenges(User user, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return challengeRepository.findUserJoinedChallengeList(pageRequest, user);
    }
}
