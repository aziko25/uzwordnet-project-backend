package uzwordnet.uzwordnet.Services.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uzwordnet.uzwordnet.Models.ProficiencyLevels;
import uzwordnet.uzwordnet.Repositories.ProficiencyLevelsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProficiencyLevelsService {

    private final ProficiencyLevelsRepository proficiencyLevelsRepository;

    public ProficiencyLevels createProficiencyLevel(String levelName) {

        try {

            ProficiencyLevels proficiencyLevel = ProficiencyLevels.builder()
                    .levelName(levelName)
                    .build();

            return proficiencyLevelsRepository.save(proficiencyLevel);
        }
        catch (DataIntegrityViolationException e) {

            throw new IllegalArgumentException("Level already exists");
        }
    }

    public ProficiencyLevels getProficiencyLevelById(Integer id) {

        return proficiencyLevelsRepository.findById(id).orElse(null);
    }

    public List<ProficiencyLevels> getAllProficiencyLevels() {

        return proficiencyLevelsRepository.findAll(Sort.by("id").ascending());
    }

    public ProficiencyLevels updateProficiencyLevel(Integer id, String levelName) {

        try {

            ProficiencyLevels proficiencyLevel = proficiencyLevelsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Level Not Found!"));

            proficiencyLevel.setLevelName(levelName);

            return proficiencyLevelsRepository.save(proficiencyLevel);
        }
        catch (DataIntegrityViolationException e) {

            throw new IllegalArgumentException("Level already exists");
        }
    }

    public String deleteProficiencyLevel(Integer id) {

        ProficiencyLevels proficiencyLevel = proficiencyLevelsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Level Not Found!"));

        proficiencyLevelsRepository.delete(proficiencyLevel);

        return "You Successfully Deleted Level: " + proficiencyLevel.getLevelName();
    }
}