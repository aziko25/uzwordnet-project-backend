package uzwordnet.uzwordnet.Controllers.Uzwordnet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Configurations.JWTAuthorization.Authorization;
import uzwordnet.uzwordnet.Models.Uzwordnet.Words;
import uzwordnet.uzwordnet.Services.Uzwordnet.WordsDefinitionService;

@RestController
@RequestMapping("/api/words-definition")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class WordsDefinitionController {

    private final WordsDefinitionService wordsDefinitionService;

    @GetMapping("/getRandom")
    public ResponseEntity<?> getRandomWord() {

        return ResponseEntity.ok(wordsDefinitionService.getWord());
    }

    @GetMapping("/getByLemma/{lemma}")
    public ResponseEntity<?> getByLemma(@PathVariable String lemma) {

        return ResponseEntity.ok(wordsDefinitionService.getWordByLemma(lemma));
    }

    @Authorization(requiredRoles = {"ADMIN", "SUPERADMIN"})
    @PostMapping("/create")
    public ResponseEntity<?> createWord(@RequestBody Words word) {

        return ResponseEntity.ok(wordsDefinitionService.createWord(word));
    }

    @Authorization(requiredRoles = {"ADMIN", "SUPERADMIN"})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWord(@PathVariable Integer id, @RequestParam Boolean decision,
                                        @RequestBody(required = false) Words word) {

        return ResponseEntity.ok(wordsDefinitionService.updateWord(id, decision, word));
    }
}