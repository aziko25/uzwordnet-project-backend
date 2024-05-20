package uzwordnet.uzwordnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableScheduling
@EnableWebSocket
public class UzwordnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(UzwordnetApplication.class, args);
	}
}