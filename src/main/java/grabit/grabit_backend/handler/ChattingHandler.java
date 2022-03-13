package grabit.grabit_backend.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import grabit.grabit_backend.domain.Chatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChattingHandler extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);

	private static Map<Long, List<WebSocketSession>> sessionList = new HashMap<>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메세지 처리.
		String payload = message.getPayload();

		// Json to Chatting Object
		ObjectMapper objectMapper = new ObjectMapper();
		Chatting chatting = objectMapper.readValue(payload, Chatting.class);

		if(chatting.getChattingType().equals("ENTER")){
			if(!sessionList.containsKey(chatting.getChallengeId())){
				List<WebSocketSession> sessList = new ArrayList<>();
				sessList.add(session);
				sessionList.put(chatting.getChallengeId(), sessList);
			} else{
				List<WebSocketSession> sessList = sessionList.get(chatting.getChallengeId());
				sessList.add(session);
			}
		}else if(chatting.getChattingType().equals("MESSAGE")){
			List<WebSocketSession> sessList = sessionList.get(chatting.getChallengeId());
			for(WebSocketSession sess : sessList){
				sess.sendMessage(message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(Long chattingId : sessionList.keySet()){
			List<WebSocketSession> sessList = sessionList.get(chattingId);
			if(sessList.contains(session)){
				sessList.remove(session);
			}
		}
	}
}
