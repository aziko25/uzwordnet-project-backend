package uzwordnet.uzwordnet.Models;

import jakarta.persistence.*;
import lombok.*;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "validations")
public class Validations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String correctedWordVersion;
    private String correctedWordDefinition;
    private Boolean isApproved;
    private Boolean isValidationSavedDirectly;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "word_id")
    private Words wordId;
}