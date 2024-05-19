package uzwordnet.uzwordnet.Services.Uzwordnet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.DTO.Requests.GetWordFilter;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Models.Uzwordnet.Senses;
import uzwordnet.uzwordnet.Models.Uzwordnet.Synsets;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;
import uzwordnet.uzwordnet.Models.Validations;
import uzwordnet.uzwordnet.Repositories.UsersRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SensesRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SynsetsRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.WordsRepository;
import uzwordnet.uzwordnet.Repositories.ValidationsRepository;

import static uzwordnet.uzwordnet.Services.Authentication.LoginService.USERNAME;

@Service
@RequiredArgsConstructor
public class WordsSpellingService {

    private final UsersRepository usersRepository;

    private final WordsRepository wordsRepository;
    private final SynsetsRepository synsetsRepository;
    private final SensesRepository sensesRepository;
    private final ValidationsRepository validationsRepository;

    public Words getWordByFilter(GetWordFilter filter) {

        if (filter != null && filter.getPartOfSpeech() != null) {

            Words word = wordsRepository.findRandomWordByPartOfSpeechWithSpecialChars(filter.getPartOfSpeech());

            if (word == null) {

                throw new IllegalArgumentException("Word Not Found!");
            }

            Senses sense = sensesRepository.findFirstByWordId(word);

            if (word.getDefinition() == null) {

                Synsets synset = synsetsRepository.findFirstBySynsetId(sense.getSynsetId().getSynsetId());

                word.setDefinition(synset.getDefinition());
            }

            return word;
        }
        else {

            return getWordWithDefinition(wordsRepository, sensesRepository, synsetsRepository);
        }
    }

    public static Words getWordWithDefinition(WordsRepository wordsRepository, SensesRepository sensesRepository,
                                              SynsetsRepository synsetsRepository) {

        Words word = wordsRepository.findRandom();

        Senses sense = sensesRepository.findFirstByWordId(word);

        if (word.getDefinition() == null) {

            Synsets synset = synsetsRepository.findFirstBySynsetId(sense.getSynsetId().getSynsetId());

            word.setDefinition(synset.getDefinition());
        }

        return word;
    }

    public Words updateWordSpelling(Integer wordId, Boolean decision, String lemma) {

        Users user = usersRepository.findByUsername(USERNAME).orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        if (decision) {

            user.setValidationScore(user.getValidationScore() + 10);

            if (lemma != null && wordId != null) {

                Words word = wordsRepository.findById(wordId)
                        .orElseThrow(() -> new IllegalArgumentException("Word Not Found!"));

                word.setLemma(lemma);

                wordsRepository.save(word);
                usersRepository.save(user);

                Validations validation = Validations.builder()
                        .wordId(word)
                        .userId(user)
                        .correctedWordVersion(lemma)
                        .isValidationSavedDirectly(true)
                        .build();

                validationsRepository.save(validation);

                Senses sense = sensesRepository.findFirstByWordId(word);

                if (word.getDefinition() == null) {

                    Synsets synset = synsetsRepository.findFirstBySynsetId(sense.getSynsetId().getSynsetId());

                    word.setDefinition(synset.getDefinition());
                }

                return word;
            }
            else {

                throw new IllegalArgumentException("Word Id Or Lemma Are Null!");
            }
        }

        user.setValidationScore(user.getValidationScore() + 1);

        usersRepository.save(user);

        return null;
    }
}