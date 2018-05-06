package us.neuner.clo.message;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * @author Tim Hanson <tim@timgineer.io>
 * @author Jarod Neuner <jarod@neuner.us>
 * Serialization/deserialization tests for @see ChatMessageHistory.
 */
public class ChatMessageHistoryTest {

	@Test
	public void testChatMessageHistorySerialization1() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "2018/05/06 23:09:30");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "2018/05/06 23:09:51");
        
        List<ChatEntry> msgList = new ArrayList<>();
        msgList.add(msg1);
        msgList.add(msg2);
                
		ChatMessageHistory chm = new ChatMessageHistory(psid, msgList);
		
		String json = new ObjectMapper().writeValueAsString(chm);
		assertThat(json, containsString("chatHistory"));
		assertThat(json, containsString("psid"));
		assertThat(json, containsString(msg1.getMsg()));
		assertThat(json, containsString(msg2.getMsg()));
	}

	@Test
	public void testChatMessageHistoryDeserialization1() throws IOException {
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "2018/05/06 23:09:30");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "2018/05/06 23:09:51");
                
		String json = "{\"type\":\"chatHistory\",\"psid\":\"" + psid +
                			"\",\"mid\":\"" + mid.toString() +
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
		assertEquals(mid, chm.getMid());
		assertEquals(msg1, chm.getEntry(0));
        assertEquals(msg2, chm.getEntry(1));
	}

	@Test
	public void testChatMessageHistorySerialization2() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "2018/05/06 23:09:30");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "2018/05/06 23:09:53");
        
        List<ChatEntry> msgList = new ArrayList<>();
        msgList.add(msg1);
        msgList.add(msg2);
                
		ChatMessageHistory chm = new ChatMessageHistory(psid, mid, msgList);
		
		String json = new ObjectMapper().writeValueAsString(chm);
		assertThat(json, containsString("chatHistory"));
		assertThat(json, containsString(psid));
		assertThat(json, containsString(mid.toString()));
		assertThat(json, containsString(msg1.getMsg()));
		assertThat(json, containsString(msg2.getMsg()));
	}

	@Test
	public void testChatMessageHistoryDeserialization2() throws IOException {
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();
        ChatEntry msg1 = new ChatEntry("Jane", "up hill", "2018/05/06 23:09:30");
        ChatEntry msg2 = new ChatEntry("Jack", "down hill", "2018/05/06 23:09:58");
                
		String json = "{\"type\":\"chatHistory\",\"psid\":\"" + psid +
    						"\",\"mid\":\"" + mid.toString() +
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
		assertEquals(mid, chm.getMid());
		assertEquals(msg1, chm.getEntry(0));
        assertEquals(msg2, chm.getEntry(1));
	}
}
