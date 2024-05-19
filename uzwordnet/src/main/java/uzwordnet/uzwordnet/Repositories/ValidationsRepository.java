package uzwordnet.uzwordnet.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.Validations;

@Repository
public interface ValidationsRepository extends JpaRepository<Validations, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Validations WHERE user_id != ? ORDER BY RANDOM() LIMIT 1")
    Validations findRandomValidation(Integer userId);
}