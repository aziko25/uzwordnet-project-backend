package uzwordnet.uzwordnet.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpertsCheckedValidations_Id implements Serializable {

    private Integer validationId;
    private Integer expertId;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ExpertsCheckedValidations_Id that = (ExpertsCheckedValidations_Id) o;

        return Objects.equals(validationId, that.validationId) &&
                Objects.equals(expertId, that.expertId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(validationId, expertId);
    }
}