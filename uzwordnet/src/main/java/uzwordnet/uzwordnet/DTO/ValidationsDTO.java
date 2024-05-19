package uzwordnet.uzwordnet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uzwordnet.uzwordnet.Models.Validations;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationsDTO {

    private Integer id;
    private String correctedWordVersion;
    private String correctedWordDefinition;
    private Boolean isApproved;
    private Boolean isValidationSavedDirectly;
    private Integer userId;
    private String username;
    private String userProficiencyLevelId;
    private Integer wordId;
    private String wordLemma;
    private String wordPartOfSpeech;
    private String wordDefinition;

    public ValidationsDTO(Validations validation) {

        this.id = validation.getId();
        this.correctedWordVersion = validation.getCorrectedWordVersion();
        this.correctedWordDefinition = validation.getCorrectedWordDefinition();
        this.isApproved = validation.getIsApproved();
        this.isValidationSavedDirectly = validation.getIsValidationSavedDirectly();
        this.userId = validation.getUserId().getId();
        this.username = validation.getUserId().getUsername();
        this.wordId = validation.getWordId().getId();
        this.wordLemma = validation.getWordId().getLemma();
        this.wordPartOfSpeech = validation.getWordId().getPartOfSpeech();
        this.wordDefinition = validation.getWordId().getDefinition();
    }
}