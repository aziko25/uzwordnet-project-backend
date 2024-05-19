package uzwordnet.uzwordnet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.ProficiencyLevels;

@Repository
public interface ProficiencyLevelsRepository extends JpaRepository<ProficiencyLevels, Integer> {

}