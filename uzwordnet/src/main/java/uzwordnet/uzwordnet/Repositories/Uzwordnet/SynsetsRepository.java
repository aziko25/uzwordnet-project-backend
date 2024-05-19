package uzwordnet.uzwordnet.Repositories.Uzwordnet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.Uzwordnet.Synsets;

@Repository
public interface SynsetsRepository extends JpaRepository<Synsets, Integer> {

    Synsets findFirstBySynsetId(String synsetId);
}
