package uzwordnet.uzwordnet.Models.Uzwordnet;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "synsets")
public class Synsets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "synset_id", unique = true)
    private String synsetId;

    private String definition;
}