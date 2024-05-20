package uzwordnet.uzwordnet.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import uzwordnet.uzwordnet.Models.ProficiencyLevels;
import uzwordnet.uzwordnet.Models.Rooms;
import uzwordnet.uzwordnet.Models.Users;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Map<ProficiencyLevels, ConcurrentLinkedQueue<Rooms>> rooms = new ConcurrentHashMap<>();

    public Rooms createRoom(Users user, WebSocketSession session) {

        Rooms room = new Rooms();

        room.setProficiencyLevel(user.getProficiencyLevelId());
        room.setFull(false);
        room.getUsers().add(user);
        room.getSessions().add(session);

        rooms.computeIfAbsent(user.getProficiencyLevelId(), k -> new ConcurrentLinkedQueue<>()).add(room);

        return room;
    }

    public Rooms joinRoom(Users user, WebSocketSession session) {

        ConcurrentLinkedQueue<Rooms> levelRooms = rooms.get(user.getProficiencyLevelId());

        if (levelRooms != null) {

            for (Rooms room : levelRooms) {

                if (!room.isFull()) {

                    room.getUsers().add(user);
                    room.getSessions().add(session);
                    room.setFull(true);

                    return room;
                }
            }
        }

        return createRoom(user, session);
    }
}