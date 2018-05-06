package us.neuner.clo.message;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Tim Hanson <tim@timgineer.io>
 * Serialization/deserialization tests for @see ClientJoinMessage.
 */
public class ClientJoinMessageTest {

	@Test
	public void testClientJoinMessageSerialization() throws JsonProcessingException {
		String psid = "ja84fgjk29f";
                String pass = "hohoho";
                String name = "Santa Clause";
		ClientJoinMessage cjm = new ClientJoinMessage(psid, name, pass);
		
		String json = new ObjectMapper().writeValueAsString(cjm);
		assertThat(json, containsString("clientJoin"));
		assertThat(json, containsString(name));
		assertThat(json, containsString(pass));
	}

	@Test
	public void testClientJoinMessageDeserialization() throws IOException {
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
}
