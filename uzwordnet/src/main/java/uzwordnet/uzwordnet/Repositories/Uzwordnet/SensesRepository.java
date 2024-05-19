package uzwordnet.uzwordnet.Repositories.Uzwordnet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.Uzwordnet.Senses;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;

@Repository
public interface SensesRepository extends JpaRepository<Senses, Integer> {

    Senses findFirstByWordId(Words word);
}