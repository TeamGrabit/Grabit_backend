package grabit.grabit_backend.handler;

import com.mysql.cj.Session;
import grabit.grabit_backend.AOP.APILoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ChattingHandler extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);

	private static List<Map<String, Session>> sessionList = new ArrayList<>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메세지 처리.
		String payload = message.getPayload();


	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 클라이언트 접속 후 처리.
		super.afterConnectionEstablished(session);

		// list.add(session);

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 클라이언트 접속 해제 후 처리
		super.afterConnectionClosed(session, status);

		// list.remove(session);
	}
}
