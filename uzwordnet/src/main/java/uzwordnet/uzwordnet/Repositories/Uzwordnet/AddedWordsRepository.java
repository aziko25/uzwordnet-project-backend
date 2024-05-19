package uzwordnet.uzwordnet.Repositories.Uzwordnet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.Uzwordnet.AddedWords;

@Repository
public interface AddedWordsRepository extends JpaRepository<AddedWords, Integer> {
}