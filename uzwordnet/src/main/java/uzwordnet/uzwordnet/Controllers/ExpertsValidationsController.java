package uzwordnet.uzwordnet.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Configurations.JWTAuthorization.Authorization;
import uzwordnet.uzwordnet.Services.ExpertsValidationsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experts-validations")
@CrossOrigin(maxAge = 3600)
public class ExpertsValidationsController {

    private final ExpertsValidationsService expertsValidationsService;

    @Authorization(requiredRoles = {"ADMIN", "SUPERADMIN"})
    @GetMapping("/getRandom")
    public ResponseEntity<?> getRandomValidation() {

        return ResponseEntity.ok(expertsValidationsService.getRandomValidation());
    }

    @Authorization(requiredRoles = {"ADMIN", "SUPERADMIN"})
    @PutMapping("/modifyValidation/{id}")
    public ResponseEntity<?> modifyValidation(@PathVariable Integer id, @RequestParam Boolean decision,
                                              @RequestParam(required = false) String correctedVersion) {

        return ResponseEntity.ok(expertsValidationsService.decideValidation(id, decision, correctedVersion));
    }
}