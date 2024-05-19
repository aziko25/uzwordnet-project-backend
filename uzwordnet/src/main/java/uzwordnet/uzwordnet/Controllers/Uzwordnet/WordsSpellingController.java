package uzwordnet.uzwordnet.Controllers.Uzwordnet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzwordnet.uzwordnet.Configurations.JWTAuthorization.Authorization;
import uzwordnet.uzwordnet.DTO.Requests.GetWordFilter;
import uzwordnet.uzwordnet.Services.Uzwordnet.WordsSpellingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words-spelling")
@CrossOrigin(maxAge = 3600)
public class WordsSpellingController {

    private final WordsSpellingService wordsSpellingService;

    @GetMapping("/getRandom")
    public ResponseEntity<?> getRandomWord(@RequestBody(required = false) GetWordFilter filter) {

        return ResponseEntity.ok(wordsSpellingService.getWordByFilter(filter));
    }

    @Authorization(requiredRoles = {"ADMIN", "SUPERADMIN"})
    @PutMapping("/update")
    public ResponseEntity<?> updateWord(@RequestParam(required = false) Integer wordId,
                                        @RequestParam Boolean decision, @RequestParam(required = false) String lemma) {

        return ResponseEntity.ok(wordsSpellingService.updateWordSpelling(wordId, decision, lemma));
    }
}