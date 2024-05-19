package uzwordnet.uzwordnet.Controllers.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Configurations.JWTAuthorization.Authorization;
import uzwordnet.uzwordnet.Models.ProficiencyLevels;
import uzwordnet.uzwordnet.Services.Users.ProficiencyLevelsService;

@RestController
@RequestMapping("/api/prof-levels")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class ProficiencyLevelsController {

    private final ProficiencyLevelsService proficiencyLevelsService;

    @Authorization(requiredRoles = {"SUPERADMIN"})
    @PostMapping("/create")
    public ResponseEntity<?> createProficiencyLevel(@RequestBody ProficiencyLevels proficiencyLevels) {

        return ResponseEntity.ok(proficiencyLevelsService.createProficiencyLevel(proficiencyLevels.getLevelName()));
    }

    @GetMapping("/getLevelById/{id}")
    public ResponseEntity<?> getProficiencyLevelById(@PathVariable Integer id) {

        return ResponseEntity.ok(proficiencyLevelsService.getProficiencyLevelById(id));
    }

    @GetMapping("/getAllLevels")
    public ResponseEntity<?> getAllProficiencyLevels() {

        return ResponseEntity.ok(proficiencyLevelsService.getAllProficiencyLevels());
    }

    @Authorization(requiredRoles = {"SUPERADMIN"})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestParam String levelName) {

        return ResponseEntity.ok(proficiencyLevelsService.updateProficiencyLevel(id, levelName));
    }

    @Authorization(requiredRoles = {"SUPERADMIN"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        return ResponseEntity.ok(proficiencyLevelsService.deleteProficiencyLevel(id));
    }
}