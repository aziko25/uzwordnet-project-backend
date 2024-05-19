package uzwordnet.uzwordnet.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;
    private LocalDateTime registrationTime;
    private Integer rating;
    private Integer validationScore;
    private String role;

    @ManyToOne
    @JoinColumn(name = "proficiency_level_id")
    private ProficiencyLevels proficiencyLevelId;
}