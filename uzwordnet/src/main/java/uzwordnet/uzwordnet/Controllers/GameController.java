package uzwordnet.uzwordnet.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketSession;
import uzwordnet.uzwordnet.Models.Rooms;
import uzwordnet.uzwordnet.Models.Users;
import uzwordnet.uzwordnet.Services.GameService;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    private final GameService gameService;

    @MessageMapping("/create")
    @SendTo("/topic/rooms")
    public Rooms createRoom(Users user, WebSocketSession session) {

        System.out.println("create");

        return gameService.createRoom(user, session);
    }

    @MessageMapping("/join")
    @SendTo("/topic/rooms")
    public Rooms joinRoom(Users user, WebSocketSession session) {

        System.out.println("join");

        return gameService.joinRoom(user, session);
    }
}