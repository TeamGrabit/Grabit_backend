package grabit.grabit_backend.config.websocket;

import grabit.grabit_backend.handler.ChattingHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class WebsocketConfig implements WebSocketConfigurer {
	private final ChattingHandler chattingHandler;

	WebsocketConfig(ChattingHandler chattingHandler){
		this.chattingHandler = chattingHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chattingHandler, "/ws/chat").setAllowedOrigins("*");
	}
}
