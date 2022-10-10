package grabit.grabit_backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserCommit;
import grabit.grabit_backend.dto.UpdateUserDTO;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.UserRepository;
import grabit.grabit_backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final RedisUtil redisUtil;

    @Value("${grabit.crawling.url}")
    private String CrawlingURL;

    @Autowired
    public UserService(UserRepository userRepository, ChallengeRepository challengeRepository, RedisUtil redisUtil) {
        this.userRepository=userRepository;
        this.challengeRepository = challengeRepository;
        this.redisUtil = redisUtil;
    }

    @Transactional
    public User updateUser(UpdateUserDTO updateUserDTO, User user) {
        if (updateUserDTO.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
        if (updateUserDTO.getBio() != null) user.setBio(updateUserDTO.getBio());
        if (updateUserDTO.getProfileImg() != null) user.setProfileImg(updateUserDTO.getProfileImg());

        return userRepository.save(user);
    }

    public Page<Challenge> findUserJoinedChallenges(User user, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return challengeRepository.findUserJoinedChallengeList(pageRequest, user);
    }

    public List<UserCommit> getCommitData(User user) {
        String key = "commit:" + user.getUserId();
        Optional<List> result = redisUtil.getData(key, List.class);

        if (result.isEmpty()) {
            String commitResult = getUserCommitForHttp(user.getUserId());

            List<UserCommit> commits = parsingUserCommit(commitResult);
            redisUtil.saveData(key, commits);
            return commits;
        }

        return result.get();
    }

    public String getUserCommitForHttp(String userId) {
        try {
            URL url = new URL(CrawlingURL+userId);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET"); // optional default is GET

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );

            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            con.disconnect();

            return content.toString();
        } catch (Exception e) {
            System.out.println("http get Error");
            e.printStackTrace();
        }
        return "empty";
    }

    public List<UserCommit> parsingUserCommit(String commits) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<UserCommit> userCommits = objectMapper.readValue(commits, List.class);
            return userCommits;
        } catch (Exception e) {
            System.out.println("Json Parsing Error for commits");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
