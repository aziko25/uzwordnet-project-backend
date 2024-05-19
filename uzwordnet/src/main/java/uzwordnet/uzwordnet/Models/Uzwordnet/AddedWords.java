package uzwordnet.uzwordnet.Models.Uzwordnet;

import jakarta.persistence.*;
import lombok.*;
import uzwordnet.uzwordnet.Models.Users;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "added_words")
public class AddedWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Words wordId;

    private String addedDefinition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
}