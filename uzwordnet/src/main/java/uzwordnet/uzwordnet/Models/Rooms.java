package uzwordnet.uzwordnet.Models;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rooms {

    private String id;

    private ProficiencyLevels proficiencyLevel;

    private List<Users> users = new ArrayList<>();
    private List<WebSocketSession> sessions = new ArrayList<>();

    private boolean isFull;
}