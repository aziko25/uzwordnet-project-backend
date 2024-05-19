package uzwordnet.uzwordnet.Controllers.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzwordnet.uzwordnet.Services.LeaderboardsService;

@RestController
@RequestMapping("/api/leaderboards")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class LeaderboardsController {

    private final LeaderboardsService leaderboardsService;

    @GetMapping("/getAll")
    public ResponseEntity<?> fullLeaderboards() {

        return ResponseEntity.ok(leaderboardsService.getUsersLeaderboard());
    }
}