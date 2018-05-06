package us.neuner.clo.message;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serialization/deserialization tests for @see ClientJoinMessage.
 */
public class ClientJoinMessageTest {

	/*
	 * @author Tim Hanson <tim@timgineer.io>
	 */
	@Test
	public void testClientJoinMessageSerialization1() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
                String pass = "hohoho";
                String name = "Santa Clause";
		ClientJoinMessage cjm = new ClientJoinMessage(psid, name, pass);
		
		String json = new ObjectMapper().writeValueAsString(cjm);
		assertThat(json, containsString("clientJoin"));
		assertThat(json, containsString(name));
		assertThat(json, containsString(pass));
	}

	/*
	 * @author Tim Hanson <tim@timgineer.io>
	 */
	@Test
	public void testClientJoinMessageDeserialization1() throws IOException {
		String psid = "ja84fgjk29f";
                String pass = "hohoho";
                String name = "Santa Clause";
		String json = "{\"type\":\"clientJoin\",\"psid\":\"" + psid +
                        "\",\"sessionPassword\":\"" + pass + "\",\"playerName\":\"" + name + "\"}";

		ClientJoinMessage cjm = new ObjectMapper().readerFor(ClientJoinMessage.class).readValue(json);
		assertEquals(psid, cjm.getPsid());
		assertEquals(pass, cjm.getSessionPassword());
                assertEquals(name, cjm.getPlayerName());
	}

	/*
	 * @author Mag Faris <mfaris627@gmail.com>
	 */
	@Test
	public void testClientJoinMessageSerialization2() throws JsonProcessingException {
		String psid = "ja84fgjk30f";
		String sessionPassword = "changeme123";
		String playerName = "Jack Black";
		
		ClientJoinMessage cj = new ClientJoinMessage(psid, sessionPassword, playerName);
		String json = new ObjectMapper().writeValueAsString(cj);
		

		assertThat(json, containsString("clientJoin"));
		assertThat(json, containsString(psid));
		assertThat(json, containsString(sessionPassword));
		assertThat(json, containsString(playerName));

		
	}

	/*
	 * @author Mag Faris <mfaris627@gmail.com>
	 */
	@Test
	public void testClientJoinMessageDeserialization2() throws IOException {
		String psid = "ja84fgjk31f";
		String sessionPassword = "changeme123";
		String playerName = "Jack Black";

		String jsonFormat = "{''type'':''clientJoin'',''psid'':''%s'',''sessionPassword'':''%s'', ''playerName'':''%s''}";
		String json = String.format(jsonFormat, psid, sessionPassword,playerName);
		json = json.replace("''", "\"");
		
		ClientJoinMessage cj = new ObjectMapper().readerFor(ClientJoinMessage.class).readValue(json);
		assertEquals(psid, cj.getPsid());
		assertEquals(sessionPassword, cj.getSessionPassword());
		assertEquals(playerName, cj.getPlayerName());
		
	}
}
