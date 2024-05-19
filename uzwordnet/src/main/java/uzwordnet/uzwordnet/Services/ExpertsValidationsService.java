package uzwordnet.uzwordnet.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.DTO.ValidationsDTO;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Models.Uzwordnet.Senses;
import uzwordnet.uzwordnet.Models.Uzwordnet.Synsets;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;
import uzwordnet.uzwordnet.Models.Validations;
import uzwordnet.uzwordnet.Repositories.ExpertsCheckedValidationsRepository;
import uzwordnet.uzwordnet.Repositories.UsersRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SensesRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.SynsetsRepository;
import uzwordnet.uzwordnet.Repositories.Uzwordnet.WordsRepository;
import uzwordnet.uzwordnet.Repositories.ValidationsRepository;

import static uzwordnet.uzwordnet.Services.Authentication.LoginService.USERNAME;

@Service
@RequiredArgsConstructor
public class ExpertsValidationsService {

    private final ExpertsCheckedValidationsRepository expertsCheckedValidationsRepository;
    private final ValidationsRepository validationsRepository;
    private final UsersRepository usersRepository;
    private final WordsRepository wordsRepository;
    private final SensesRepository sensesRepository;
    private final SynsetsRepository synsetsRepository;

    public ValidationsDTO getRandomValidation() {

        Users user = usersRepository.findByUsername(USERNAME).orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        Validations validation = validationsRepository.findRandomValidation(user.getId());

        if (validation == null) {

            throw new IllegalArgumentException("Validations Not Found!");
        }

        Words word = wordsRepository.findById(validation.getWordId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Word Not Found!"));

        Senses sense = sensesRepository.findFirstByWordId(word);

        if (word.getDefinition() == null) {

            Synsets synset = synsetsRepository.findFirstBySynsetId(sense.getSynsetId().getSynsetId());

            word.setDefinition(synset.getDefinition());
        }

        ValidationsDTO validationsDTO = new ValidationsDTO(validation);

        validationsDTO.setWordDefinition(word.getDefinition());

        return validationsDTO;
    }

    public String decideValidation(Integer validationId, Boolean decision, String correctedVersion) {

        Validations validation = validationsRepository.findById(validationId)
                .orElseThrow(() -> new IllegalArgumentException("Validation Not Found!"));

        if (decision) {

            if (correctedVersion == null) {

                throw new IllegalArgumentException("Write Your Correction!");
            }

            validation.setIsApproved(false);

            Words word = wordsRepository.findById(validation.getWordId().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Word Not Found!"));

            word.setLemma(correctedVersion);
        }
        else {

            validation.setIsApproved(true);
        }

        validationsRepository.save(validation);

        return "You Successfully Modified Validation!";
    }
}