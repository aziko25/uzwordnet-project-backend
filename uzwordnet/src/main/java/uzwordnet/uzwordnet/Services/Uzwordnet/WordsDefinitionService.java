package uzwordnet.uzwordnet.Services.Uzwordnet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Models.Uzwordnet.AddedWords;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;
import uzwordnet.uzwordnet.Models.Validations;
import uzwordnet.uzwordnet.Repositories.UsersRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.AddedWordsRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SensesRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SynsetsRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.WordsRepository;
import uzwordnet.uzwordnet.Repositories.ValidationsRepository;

import java.util.List;
import java.util.Random;

import static uzwordnet.uzwordnet.Services.Authentication.LoginService.USERNAME;
import static uzwordnet.uzwordnet.Services.Uzwordnet.WordsSpellingService.getWordWithDefinition;

@Service
@RequiredArgsConstructor
public class WordsDefinitionService {

    private final UsersRepository usersRepository;

    private final WordsRepository wordsRepository;
    private final SynsetsRepository synsetsRepository;
    private final SensesRepository sensesRepository;
    private final AddedWordsRepository addedWordsRepository;
    private final ValidationsRepository validationsRepository;

    public Words getWord() {

        return getWordWithDefinition(wordsRepository, sensesRepository, synsetsRepository);
    }

    public Words getWordByLemma(String lemma) {

        List<Words> words = wordsRepository.findAllByLemma(lemma);

        if (words.isEmpty()) {

            return null;
        }

        return words.get(new Random().nextInt(words.size()));
    }

    public Words createWord(Words wordToCreate) {

        Users user = usersRepository.findByUsername(USERNAME)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        if (user.getProficiencyLevelId() == null || user.getProficiencyLevelId().getId() == 1) {

            throw new IllegalArgumentException("You Are Beginner! You Can't Create A Word!");
        }

        Words word = new Words();

        word.setLemma(wordToCreate.getLemma());
        word.setPartOfSpeech(wordToCreate.getPartOfSpeech());
        word.setDefinition(wordToCreate.getDefinition());

        wordsRepository.save(word);
        word.setWordId("w" + word.getId());

        user.setValidationScore(user.getValidationScore() + 10);
        usersRepository.save(user);

        AddedWords addedWord = new AddedWords();

        addedWord.setAddedDefinition(word.getDefinition());
        addedWord.setWordId(word);
        addedWord.setUserId(user);

        addedWordsRepository.save(addedWord);

        return word;
    }

    public Words updateWord(Integer id, Boolean decision, Words wordToUpdate) {

        Users user = usersRepository.findByUsername(USERNAME)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        if (user.getProficiencyLevelId() == null || user.getProficiencyLevelId().getId() == 1) {

            throw new IllegalArgumentException("You Are Beginner! You Can't Modify The Word!");
        }

        Words word = wordsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Word Not Found!"));

        if (!decision) {

            user.setValidationScore(user.getValidationScore() + 1);

            return word;
        }
        else {

            user.setValidationScore(user.getValidationScore() + 10);
        }

        String lemma = null;
        if (wordToUpdate.getLemma() != null) {

            lemma = wordToUpdate.getLemma();
            word.setLemma(wordToUpdate.getLemma());
        }

        if (wordToUpdate.getPartOfSpeech() != null) {

            word.setPartOfSpeech(wordToUpdate.getPartOfSpeech());
        }

        String definition = null;
        if (wordToUpdate.getDefinition() != null) {

            definition = wordToUpdate.getDefinition();
            word.setDefinition(wordToUpdate.getDefinition());
        }

        wordsRepository.save(word);

        Validations validation = Validations.builder()
                .wordId(word)
                .userId(user)
                .correctedWordDefinition(definition)
                .correctedWordVersion(lemma)
                .isValidationSavedDirectly(true)
                .build();

        validationsRepository.save(validation);

        return word;
    }
}