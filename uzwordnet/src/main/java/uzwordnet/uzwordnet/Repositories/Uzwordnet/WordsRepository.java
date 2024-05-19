package uzwordnet.uzwordnet.Repositories.Uzwordnet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;

import java.util.List;

@Repository
public interface WordsRepository extends JpaRepository<Words, Integer> {

    Words findTopByOrderByWordIdDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM words ORDER BY RANDOM() LIMIT 1")
    Words findRandom();

    Words findByLemma(String lemma);

    List<Words> findAllByLemma(String lemma);

    @Query(nativeQuery = true, value = "SELECT * FROM words WHERE part_of_speech = ? AND " +
            "(lemma SIMILAR TO '%[(]%' OR lemma SIMILAR TO '%[)]%' OR lemma SIMILAR TO '%[#]%' " +
            "OR lemma SIMILAR TO '%[(]%' OR lemma SIMILAR TO '%[)]%' OR lemma SIMILAR TO '%[#]%') " +
            "ORDER BY RANDOM() LIMIT 1")
    Words findRandomWordByPartOfSpeechWithSpecialChars(String partOfSpeech);
}