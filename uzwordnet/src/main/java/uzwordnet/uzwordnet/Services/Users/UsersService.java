package uzwordnet.uzwordnet.Services.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.DTO.UsersDTO;
import uzwordnet.uzwordnet.Models.ProficiencyLevels;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Repositories.ProficiencyLevelsRepository;
import uzwordnet.uzwordnet.Repositories.UsersRepository;

import static uzwordnet.uzwordnet.Services.Authentication.LoginService.USERNAME;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final ProficiencyLevelsRepository proficiencyLevelsRepository;

    public UsersDTO me() {

        return usersRepository.findByUsername(USERNAME).stream().map(UsersDTO::new).findFirst().orElse(null);
    }

    public UsersDTO setALevel(Integer userId, Integer levelId, String role) {

        try {

            Users user = usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

            ProficiencyLevels proficiencyLevel = proficiencyLevelsRepository.findById(levelId)
                    .orElseThrow(() -> new IllegalArgumentException("Proficiency Level not found"));

            user.setProficiencyLevelId(proficiencyLevel);
            user.setRole(role.toUpperCase());

            usersRepository.save(user);

            return new UsersDTO(user);
        }
        catch (EmptyResultDataAccessException e) {

            throw new IllegalArgumentException("Level Id Does Not Exist");
        }
    }
}