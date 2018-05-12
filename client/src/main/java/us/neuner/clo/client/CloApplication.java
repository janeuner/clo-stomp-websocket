package us.neuner.clo.client;

import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import us.neuner.clo.message.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;

public class CloApplication {

	private final static List<Transport> tport;
	private final static SockJsClient sockJsClient;

	private final static CloFrameHandler frameHandler;

	private final static Object lock = new Object();
	private final static List<CloServerMessageNotifier> notifierList = new ArrayList<CloServerMessageNotifier>(); 
	
	private static StompSession session;
	private static ClientJoinMessage joinMessage;
	private static Message currentStatus;
	private static String psid;

	static {
		// Initialize tport
		tport = new ArrayList<Transport>(1);
		tport.add(new WebSocketTransport(new StandardWebSocketClient()));
		
		// Initialize sockJsClient
		sockJsClient = new SockJsClient(tport);
		
		// Only need one frame handler for all of CLO
		frameHandler = new CloFrameHandler();
		
		// Initialize static state variables
		session = null;
		psid = null;
	}
	
	private static class CloSessionHandler extends StompSessionHandlerAdapter {
				
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
			session.subscribe("/user/queue/server", frameHandler);
			session.send("/client/message", joinMessage);
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
			String psid = msg.getPsid();

			CloServerMessageNotifier[] notifiers;
			
			synchronized (CloApplication.lock) {
				
				//TODO: add PlayStateMessage here
				if ((msg instanceof GameSetupMessage) || (msg instanceof EndGameMessage))
					CloApplication.currentStatus = msg;
				
				notifiers = new CloServerMessageNotifier[CloApplication.notifierList.size()];
				notifiers = CloApplication.notifierList.toArray(notifiers);
				
				if ((psid != null) && !psid.isEmpty())
					CloApplication.psid = psid;
			}
			
			for (CloServerMessageNotifier n : notifiers) {
				n.onMessageReceived(msg);
			}
		}
	}
	
	/*
	 * Register a @see CloServerMessageNotifier
	 */
	public static void registerMessageNotifier(CloServerMessageNotifier n) {

		synchronized (CloApplication.lock) {
			CloApplication.notifierList.add(n);
		}
	}
	
	/*
	 * Unregister a @see CloServerMessageNotifier
	 */
	public static void unregisterMessageNotifier(CloServerMessageNotifier n) {

		synchronized (CloApplication.lock) {
			CloApplication.notifierList.remove(n);
		}
	}
	
	/*
	 * 
	 */
	public static Message getCurrentStatusMessage() {
		synchronized (CloApplication.lock) {
			return CloApplication.currentStatus;
		}
	}

	/*
	 * Connect to a CLO server
	 */
	public static void connect(String uname, String pword, String host) throws InterruptedException, ExecutionException {
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		joinMessage = new ClientJoinMessage(psid, pword, uname);
		String url = String.format("ws://%s/clo", host);
		CloSessionHandler sessionHandler = new CloSessionHandler();
		CloApplication.session = stompClient.connect(url, sessionHandler).get();
	}
	
	public static void sendChat(String text) {
		ChatMessage msg = new ChatMessage(psid, text);
		session.send("/client/message", msg);
	}
}
