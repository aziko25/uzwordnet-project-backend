package uzwordnet.uzwordnet.Models.Uzwordnet;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "senses")
public class Senses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String senseId;

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "word_id")
    private Words wordId;

    @ManyToOne
    @JoinColumn(name = "synset_id", referencedColumnName = "synset_id")
    private Synsets synsetId;
}