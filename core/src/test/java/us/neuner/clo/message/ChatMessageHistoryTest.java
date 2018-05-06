package us.neuner.clo.message;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim Hanson <tim@timgineer.io>
 * @author Jarod Neuner <jarod@neuner.us>
 * Serialization/deserialization tests for @see ChatMessageHistory.
 */
public class ChatMessageHistoryTest {

	@Test
	public void testChatMessageHistorySerialization() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "12:01PM");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "12:02PM");
        
        List<ChatEntry> msgList = new ArrayList<>();
        msgList.add(msg1);
        msgList.add(msg2);
                
		ChatMessageHistory chm = new ChatMessageHistory(psid, msgList);
		
		String json = new ObjectMapper().writeValueAsString(chm);
		assertThat(json, containsString("chatHistory"));
		assertThat(json, containsString(msg1.getMsg()));
		assertThat(json, containsString(msg2.getMsg()));
	}

	@Test
	public void testChatMessageHistoryDeserialization() throws IOException {
		String psid = "ja84fgjk29f";
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "12:01PM");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "12:02PM");
                
		String json = "{\"type\":\"chatHistory\",\"psid\":\"" + psid +
                            "\",\"msgList\":[" + 
                            "{\"playerName\":\"" + msg1.getPlayerName() +
                            "\",\"msg\":\"" + msg1.getMsg() +
                            "\",\"time\":\"" + msg1.getTime() +
                            "\"},{" +
                            "\"playerName\":\"" + msg2.getPlayerName() +
                            "\",\"msg\":\"" + msg2.getMsg() +
                            "\",\"time\":\"" + msg2.getTime() +
                            "\"}]}";

		ChatMessageHistory chm = new ObjectMapper().readerFor(ChatMessageHistory.class).readValue(json);
		assertEquals(psid, chm.getPsid());
		assertEquals(msg1, chm.getEntry(0));
        assertEquals(msg2, chm.getEntry(1));
	}
}
