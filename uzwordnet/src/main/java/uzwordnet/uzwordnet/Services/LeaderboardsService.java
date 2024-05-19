package uzwordnet.uzwordnet.Services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Repositories.UsersRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LeaderboardsService {

    private final UsersRepository usersRepository;

    public static List<Map<String, Object>> usersLeaderboard;

    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    public void updateLeaderBoard() {

        List<Users> usersList = usersRepository.findAll(Sort.by("validationScore").descending());

        usersLeaderboard = new ArrayList<>();

        for (Users user : usersList) {

            Map<String, Object> result = new LinkedHashMap<>();

            result.put("username", user.getUsername());
            result.put("validationScore", user.getValidationScore());

            usersLeaderboard.add(result);
        }
    }

    public List<Map<String, Object>> getUsersLeaderboard() {

        return usersLeaderboard;
    }
}