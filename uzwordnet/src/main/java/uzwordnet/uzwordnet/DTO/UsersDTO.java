package uzwordnet.uzwordnet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uzwordnet.uzwordnet.Models.Users;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Integer id;
    private String username;
    private String password;
    private LocalDateTime registrationTime;
    private Integer validationScore;
    private Integer proficiencyLevelId;
    private String proficiencyLevelName;

    public UsersDTO(Users user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.registrationTime = user.getRegistrationTime();
        this.validationScore = user.getValidationScore();

        if (user.getProficiencyLevelId() != null) {

            this.proficiencyLevelId = user.getProficiencyLevelId().getId();
            this.proficiencyLevelName = user.getProficiencyLevelId().getLevelName();
        }
    }
}