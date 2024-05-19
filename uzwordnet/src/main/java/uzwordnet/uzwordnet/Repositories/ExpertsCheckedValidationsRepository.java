package uzwordnet.uzwordnet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.ExpertsCheckedValidations;
import uzwordnet.uzwordnet.Models.ExpertsCheckedValidations_Id;

@Repository
public interface ExpertsCheckedValidationsRepository extends JpaRepository<ExpertsCheckedValidations, ExpertsCheckedValidations_Id> {
}