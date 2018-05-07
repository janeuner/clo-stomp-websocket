package us.neuner.clo.server;

import us.neuner.clo.message.*;
import us.neuner.clo.server.CloGameSession.State;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
public class CloGameServer {

    private Log log = LogFactory.getLog(CloGameServer.class);

    private CloGameSession session;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    public CloGameServer() {
    	
    	session = null;
    }
    
    @MessageMapping("/message")
    public void messageResourceHandler(us.neuner.clo.message.Message msg, SimpMessageHeaderAccessor sha) {
    	
    	PlayerDetail pd = PlayerDetail.getPlayerDetail(msg.getPsid());
    	String sid = sha.getSessionId();
    	UUID mid = msg.getMid();

    	assert(sid != null && !sid.isEmpty());
    	
    	if (mid == null) {
    		ErrorMessage error = new ErrorMessage(msg, "Malformed message from client: Missing or invalid message id.");
    		sendToClient(sid, error);
    	}
    	else {
    		
	    	if ((pd != null) && !sid.equals(pd.getSid())) {
	        	pd.setSid(sid);
	        }
	    	
	        if (msg instanceof ClientJoinMessage) {

	        	// Handling for newly-connected clients
	        	CloGameSession sess;
	        	
	        	synchronized (this) {
		        	
		        	// If necessary, allocate a new session
		        	if ((this.session == null) || (this.session.getState() == State.Cleanup))
		        		sess = this.session = new CloGameSession(this);
		        	else 
		        		sess = this.session;
	        	}
	        	
	        	sess.clientJoinHandler((ClientJoinMessage)msg, sid, pd);
	        	
	        }
	    	else if (pd == null) {
	    		ErrorMessage error = new ErrorMessage(msg, "Malformed message from client: Missing or invalid psid.");
	    		sendToClient(sid, error);
	    	}
	        else {
	        	CloGameSession sess = pd.getSession();
	        	
	        	//if (msg instanceof us.neuner.clo.message.)
	        	
	        	if (msg instanceof ChatMessage) {
		        	sess.chatMessageHandler((ChatMessage)msg, pd.getPlayerInfo().getPlayerName());
		        }
	        }
    	}
    }

    /*
	 * Sends a message to a specified player.
	 * @param pd the target player for this message
	 * @param msg the message to send
     */
    public void sendToClient(PlayerDetail pd, Message msg) { sendToClient(pd.getSid(), msg); }

	/*
	 * Sends a message to a specified client.
	 * @param sid the connection/session ID for this message
	 * @param msg the message to send 
	 */
    public void sendToClient(String sid, Message msg) {

        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
        accessor.setSessionId(sid);
        accessor.setLeaveMutable(true);

        // /queue/server - sends individual messages from server to clients
        messagingTemplate.convertAndSendToUser(sid, "/queue/server", msg, accessor.getMessageHeaders());
    }

    @EventListener
    private void onDisconnectEvent(SessionDisconnectEvent e) {
    	String sid = e.getSessionId();
    	log.info("Client disconnect: " + sid);
    	
    }
}
