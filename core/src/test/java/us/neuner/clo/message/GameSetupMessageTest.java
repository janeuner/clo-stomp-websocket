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

import us.neuner.clo.common.GameEntityId;
import us.neuner.clo.common.PlayerInfo;

/*
 * @author Tim Hanson <tim@timgineer.io>
 * @author Jarod Neuner <jarod@neuner.us>
 * Serialization/deserialization tests for @see GameSetupMessage.
 */
public class GameSetupMessageTest {

	@Test
	public void testGameSetupMessageSerialization() throws JsonProcessingException {

		String playerName1 = "Billy Bob";
		String playerName2 = "Uncle Sam";
		PlayerInfo player1 = new PlayerInfo(playerName1);
		PlayerInfo player2 = new PlayerInfo(playerName2);               

		List<PlayerInfo> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);         
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();


		GameSetupMessage gsm = new GameSetupMessage(psid, mid, players);
		String json = new ObjectMapper().writeValueAsString(gsm);
		assertThat(json, containsString("gameSetup"));
		assertThat(json, containsString(psid));
		assertThat(json, containsString(mid.toString()));
		assertThat(json, containsString(player1.getPlayerName()));
		assertThat(json, containsString(player2.getPlayerName()));
		assertThat(json, containsString(player1.getPieceName().toString()));
		assertThat(json, containsString(player2.getPieceName().toString()));
		assertThat(json, containsString(player1.getActive().toString()));
		assertThat(json, containsString(player2.getActive().toString()));                
		assertThat(json, containsString(player1.getHasAccused().toString()));
		assertThat(json, containsString(player2.getHasAccused().toString()));
	}

	@Test
	public void testGameSetupMessageDeserialization() throws IOException {
		PlayerInfo player1 = new PlayerInfo("Billy Bob");
		PlayerInfo player2 = new PlayerInfo("Uncle Sam");
		String psid = "ja84fgjk29f";
		UUID mid = UUID.randomUUID();
		
		assertEquals(player1.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(player2.getPieceName(), GameEntityId.InvalidValue);
		assertFalse(player1.getActive());
		assertFalse(player2.getActive());
		assertFalse(player1.getHasAccused());
		assertFalse(player2.getHasAccused());

		String json = "{\"type\":\"gameSetup\",\"psid\":\"" + psid + "\",\"mid\":\"" + mid.toString() + "\",\"players\":[" + 
				"{\"playerName\":\"" + player1.getPlayerName() + "\",\"pieceName\":\"" + player1.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + player2.getPlayerName() + "\",\"pieceName\":\"" + player2.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}]}";


		GameSetupMessage gsm = new ObjectMapper().readerFor(GameSetupMessage.class).readValue(json);

		assertEquals(psid, gsm.getPsid());
		assertEquals(mid, gsm.getMid());
		PlayerInfo gsm1 = gsm.getPlayer(0);
		PlayerInfo gsm2 = gsm.getPlayer(1);
		assertEquals(player1, gsm1);
		assertEquals(player2, gsm2);

	}
}
