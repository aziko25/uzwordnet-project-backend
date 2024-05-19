package uzwordnet.uzwordnet.Controllers.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Configurations.JWTAuthorization.Authorization;
import uzwordnet.uzwordnet.Services.Users.UsersService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class UsersController {

    private final UsersService usersService;

    @Authorization(requiredRoles = {"USER", "ADMIN", "SUPERADMIN"})
    @GetMapping("/me")
    public ResponseEntity<?> me() {

        return ResponseEntity.ok(usersService.me());
    }

    @PutMapping("/setALevel/{id}")
    public ResponseEntity<?> setALevel(@PathVariable Integer id, @RequestParam Integer levelId, @RequestParam String role) {

        return ResponseEntity.ok(usersService.setALevel(id, levelId, role));
    }
}