package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StompChatController {

	private final SimpMessagingTemplate template;

	@Autowired
	public StompChatController(SimpMessagingTemplate template){
		this.template = template;
	}

//	@MessageMapping(value = "/chat/enter/{id}")
//	public void enter(@AuthenticationPrincipal User user,
//					  @PathVariable(value = "id") String id,
//					  ChatMessageDTO message){
//		message.setMessage(user.getUserId()+"님이 입장하였습니다.");
//		template.convertAndSend("/sub/chat/room/"+id, message);
//	}

	@MessageMapping(value = "/chat/message/{id}")
	public void message(@AuthenticationPrincipal User user,
						@PathVariable(value = "id") String id,
						ChatMessageDTO message){
		template.convertAndSend("/sub/chat/room/"+id, message);
	}
}
