package uzwordnet.uzwordnet.Services.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Repositories.UsersRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UsersRepository usersRepository;

    public String signUp(String username, String password, String rePassword) {

        if (!password.equals(rePassword)) {

            throw new IllegalArgumentException("Passwords Didn't Match!");
        }

        if (usersRepository.findByUsername(username).isPresent()) {

            throw new IllegalArgumentException("Email Already Exists!");
        }

        Users user = new Users();

        user.setUsername(username);
        user.setPassword(password);
        user.setRole("USER");
        user.setRegistrationTime(LocalDateTime.now());
        user.setValidationScore(0);

        usersRepository.save(user);

        return "You Successfully Signed Up!";
    }
}