package us.neuner.clo.client;

import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import us.neuner.clo.message.ClientJoinMessage;
import us.neuner.clo.message.Message;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;

public class Application {

	private final static List<Transport> tport;
	private final static SockJsClient sockJsClient;
	
	private static StompSession session;
	private static CloSessionHandler sessionHandler;
	
	static {
		// Initialize tport
		tport = new ArrayList<Transport>(1);
		tport.add(new WebSocketTransport(new StandardWebSocketClient()));
		
		// Initialize sockJsClient
		sockJsClient = new SockJsClient(tport);
		
		// Initialize static state variables
		session = null;
		sessionHandler = null;
	}
	
	private static class CloSessionHandler extends StompSessionHandlerAdapter {
		
		private String uname;
		private String pword;

		public CloSessionHandler(String uname, String pword) {
			this.uname = uname;
			this.pword = pword;
		}
		
		@Override
		public Type getPayloadType(StompHeaders headers) {
			// TODO Auto-generated method stub
			return super.getPayloadType(headers);
		}

		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			// TODO Auto-generated method stub
			super.handleFrame(headers, payload);
		}

		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			ClientJoinMessage cjm = new ClientJoinMessage(null, pword, uname);
			session.send("/client/message", cjm);
			//TODO: error handling for all this...
		}

		@Override
		public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
				Throwable exception) {
			// TODO Auto-generated method stub
			super.handleException(session, command, headers, payload, exception);
		}

		@Override
		public void handleTransportError(StompSession session, Throwable exception) {
			// TODO Auto-generated method stub
			super.handleTransportError(session, exception);
		}
	}
	
	private static class CloFrameHandler implements StompFrameHandler {

		@Override
		public Type getPayloadType(StompHeaders headers) {
			return Message.class;
		}

		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			Message msg = (Message)payload;
			
		}
	}

	public static void connect(String uname, String pword, String host) throws InterruptedException, ExecutionException {
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		
		String url = String.format("ws://%s/clo", host);
		CloSessionHandler sessionHandler = new CloSessionHandler(uname, pword);
		session = stompClient.connect(url, sessionHandler).get();
	}
}
