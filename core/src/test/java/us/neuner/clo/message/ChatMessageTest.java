package us.neuner.clo.message;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jarod Neuner <jarod@neuner.us>
 * Serialization/deserialization tests for @see ChatMessage.
 */
public class ChatMessageTest {

	@Test
	public void testChatMessageSerialization1() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
		String msg = "TEST MESSAGE";
		ChatMessage cm = new ChatMessage(psid, msg);
		
		String json = new ObjectMapper().writeValueAsString(cm);
		assertThat(json, containsString("chat"));
		assertThat(json, containsString("mid"));
		assertThat(json, containsString(psid));
		assertThat(json, containsString(msg));
	}

	@Test
	public void testChatMessageDeserialization1() throws IOException {
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();
		String msg = "TEST MESSAGE";
		String json = "{\"type\":\"chat\",\"psid\":\"" + psid + 
				"\",\"mid\":\"" + mid + 
				"\",\"msg\":\"" + msg + 
				"\"}";

		ChatMessage cm = new ObjectMapper().readerFor(ChatMessage.class).readValue(json);
		assertEquals(mid, cm.getMid());
		assertEquals(psid, cm.getPsid());
		assertEquals(msg, cm.getMsg());
	}
}
