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
 * Serialization/deserialization tests for @see GameSetupMessage.
 */
public class GameSetupMessageTest {

	/*
	 * @author Tim Hanson <tim@timgineer.io>
	 * @author Jarod Neuner <jarod@neuner.us>
	 */
	@Test
	public void testGameSetupMessageSerialization1() throws JsonProcessingException {

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

	/*
	 * @author Tim Hanson <tim@timgineer.io>
	 * @author Jarod Neuner <jarod@neuner.us>
	 */
	@Test
	public void testGameSetupMessageDeserialization1() throws IOException {
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

	/*
	 * @author Mag Faris <mfaris627@gmail.com>
	 */
	@Test
	public void testGameSetupMessageSerialization2() throws JsonProcessingException {
		String psid = "ja84fgjk30f";
		String p1 = "James Green";
		String p2 = "123 456";
		String p3 = "What if";
		String p4 = "Al Bundy";
		String p5 = "Not Name";
		String p6 = "No way";
		
		PlayerInfo pl1 = new PlayerInfo(p1);
		PlayerInfo pl2 = new PlayerInfo(p2);
		PlayerInfo pl3 = new PlayerInfo(p3);
		PlayerInfo pl4 = new PlayerInfo(p4);
		PlayerInfo pl5 = new PlayerInfo(p5);
		PlayerInfo pl6 = new PlayerInfo(p6);
				
		List<PlayerInfo> players = new ArrayList<>();
		players.add(pl1);
		players.add(pl2);
		players.add(pl3);
		players.add(pl4);
		players.add(pl5);
		players.add(pl6);
		
		
		GameSetupMessage gs = new GameSetupMessage(psid, players);
		String json = new ObjectMapper().writeValueAsString(gs);
		assertThat(json, containsString("gameSetup"));
		assertThat(json, containsString(psid));
		assertThat(json, containsString(pl1.getPlayerName()));
		assertThat(json, containsString(pl2.getPlayerName()));
		assertThat(json, containsString(pl3.getPlayerName()));
		assertThat(json, containsString(pl4.getPlayerName()));
		assertThat(json, containsString(pl5.getPlayerName()));
		assertThat(json, containsString(pl6.getPlayerName()));
		assertThat(json, containsString(pl1.getPieceName().toString()));
		assertThat(json, containsString(pl2.getPieceName().toString()));
		assertThat(json, containsString(pl3.getPieceName().toString()));
		assertThat(json, containsString(pl4.getPieceName().toString()));
		assertThat(json, containsString(pl5.getPieceName().toString()));
		assertThat(json, containsString(pl6.getPieceName().toString()));
		assertThat(json, containsString(pl1.getActive().toString()));
		assertThat(json, containsString(pl2.getActive().toString()));
		assertThat(json, containsString(pl3.getActive().toString()));
		assertThat(json, containsString(pl4.getActive().toString()));
		assertThat(json, containsString(pl5.getActive().toString()));
		assertThat(json, containsString(pl6.getActive().toString()));
		assertThat(json, containsString(pl1.getHasAccused().toString()));
		assertThat(json, containsString(pl2.getHasAccused().toString()));
		assertThat(json, containsString(pl3.getHasAccused().toString()));
		assertThat(json, containsString(pl4.getHasAccused().toString()));
		assertThat(json, containsString(pl5.getHasAccused().toString()));
		assertThat(json, containsString(pl6.getHasAccused().toString()));	
	}

	/*
	 * @author Mag Faris <mfaris627@gmail.com>
	 */
	@Test
	public void GameSetupMessageDeserialization2() throws IOException {
		String psid = "ja84fgjk31f";
		String p1 = "James Green";
		String p2 = "123 456";
		String p3 = "What if";
		String p4 = "Al Bundy";
		String p5 = "Not Name";
		String p6 = "No way";
		
		PlayerInfo pl1 = new PlayerInfo(p1);
		PlayerInfo pl2 = new PlayerInfo(p2);
		PlayerInfo pl3 = new PlayerInfo(p3);
		PlayerInfo pl4 = new PlayerInfo(p4);
		PlayerInfo pl5 = new PlayerInfo(p5);
		PlayerInfo pl6 = new PlayerInfo(p6);
		
		
		assertEquals(pl1.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(pl2.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(pl3.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(pl4.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(pl5.getPieceName(), GameEntityId.InvalidValue);
		assertEquals(pl6.getPieceName(), GameEntityId.InvalidValue);
		assertFalse(pl1.getActive());
		assertFalse(pl2.getActive());
		assertFalse(pl3.getActive());
		assertFalse(pl4.getActive());
		assertFalse(pl5.getActive());
		assertFalse(pl6.getActive());
		assertFalse(pl1.getHasAccused());
		assertFalse(pl2.getHasAccused());
		assertFalse(pl3.getHasAccused());
		assertFalse(pl4.getHasAccused());
		assertFalse(pl5.getHasAccused());
		assertFalse(pl6.getHasAccused());

		String json = "{\"type\":\"gameSetup\",\"psid\":\"" + psid + "\",\"players\":[" + 
				"{\"playerName\":\"" + pl1.getPlayerName() + "\",\"pieceName\":\"" + pl1.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + pl2.getPlayerName() + "\",\"pieceName\":\"" + pl2.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + pl3.getPlayerName() + "\",\"pieceName\":\"" + pl3.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + pl4.getPlayerName() + "\",\"pieceName\":\"" + pl4.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + pl5.getPlayerName() + "\",\"pieceName\":\"" + pl5.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}," + 
				"{\"playerName\":\"" + pl6.getPlayerName() + "\",\"pieceName\":\"" + pl6.getPieceName().toString() +
				"\",\"active\":\"false\",\"hasAccused\":\"false\"}]}";
		
		GameSetupMessage gs = new ObjectMapper().readerFor(GameSetupMessage.class).readValue(json);
		assertEquals(psid, gs.getPsid());
		PlayerInfo gs1 = gs.getPlayer(0);
		PlayerInfo gs2 = gs.getPlayer(1);
		PlayerInfo gs3 = gs.getPlayer(2);
		PlayerInfo gs4 = gs.getPlayer(3);
		PlayerInfo gs5 = gs.getPlayer(4);
		PlayerInfo gs6 = gs.getPlayer(5);
		assertEquals (pl1, gs1);
		assertEquals (pl2, gs2);
		assertEquals (pl3, gs3);
		assertEquals (pl4, gs4);
		assertEquals (pl5, gs5);
		assertEquals (pl6, gs6);
		
	}	
}
