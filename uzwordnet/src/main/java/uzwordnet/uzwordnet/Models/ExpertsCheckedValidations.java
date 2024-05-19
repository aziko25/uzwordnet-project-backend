package uzwordnet.uzwordnet.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "experts_checked_validations")
@IdClass(ExpertsCheckedValidations_Id.class)
public class ExpertsCheckedValidations {

    @Id
    @ManyToOne
    @JoinColumn(name = "validation_id")
    private Validations validationId;

    @Id
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Users expertId;

    private Boolean isValidationApprovedByExpert;
    private String expertsCorrectedVersion;
}