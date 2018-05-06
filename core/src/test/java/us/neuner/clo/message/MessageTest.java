package us.neuner.clo.message;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * @author Jarod Neuner <jarod@neuner.us>
 * Check for presence/correctness of certain JsonSubTypes.Type annotations.
 */
public class MessageTest {

	@Test
	public void testJsonSubTypesAnnotation() {
		final JsonSubTypes st = Message.class.getAnnotation(JsonSubTypes.class);
		assertNotNull(st);
		JsonSubTypes.Type[] types = st.value();
		assertTrue(contains(types, ClientJoinMessage.class, "clientJoin"));
		assertTrue(contains(types, GameSetupMessage.class, "gameSetup"));
		assertTrue(contains(types, EndGameMessage.class, "endGame"));
		assertTrue(contains(types, ChatMessage.class, "chat"));
		assertTrue(contains(types, ChatMessageHistory.class, "chatHistory"));
		assertTrue(contains(types, ErrorMessage.class, "error"));
	}
	
	private Boolean contains(JsonSubTypes.Type[] types, Class<? extends Message> messageClass, String messageType) {
		
		for (int i = 0; i < types.length; i++) {
			if (messageClass.equals(types[i].value()) && messageType.equals(types[i].name()))
				return true;
		}
		return false;
	}

}
