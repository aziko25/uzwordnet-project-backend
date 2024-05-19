package uzwordnet.uzwordnet.Controllers.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Services.Authentication.LoginService;
import uzwordnet.uzwordnet.Services.Authentication.SignUpService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(maxAge = 3600)
public class AuthenticationController {

    private final LoginService loginService;
    private final SignUpService signUpService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        return ResponseEntity.ok(loginService.login(username, password));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestParam String username, @RequestParam String password,
                                    @RequestParam String rePassword) {

        return ResponseEntity.ok(signUpService.signUp(username, password, rePassword));
    }
}