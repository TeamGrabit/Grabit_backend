package grabit.grabit_backend.config.stomp;

import grabit.grabit_backend.auth.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;

@Configuration
public class StompConfig implements ChannelInterceptor {
	public JwtProvider jwtProvider;

	public StompConfig(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		return message;
	}
}
