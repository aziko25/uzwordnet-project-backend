package uzwordnet.uzwordnet.Models.Uzwordnet;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "word_id", unique = true)
    private String wordId;

    private String lemma;
    private String partOfSpeech;
    private String definition;
}