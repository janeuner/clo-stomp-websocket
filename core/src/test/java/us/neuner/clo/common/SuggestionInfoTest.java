package us.neuner.clo.common;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class SuggestionInfoTest {

	@Test
	public void testGetters1() {
		GameEntityId location = GameEntityId.BallRoom;
		GameEntityId suspect = GameEntityId.MrsPeacock;
		GameEntityId weapon = GameEntityId.Wrench;
		SuggestionInfo si = new SuggestionInfo(location, suspect, weapon);
		assertEquals(location, si.getLocation()); 
		assertEquals(suspect, si.getSuspect());
		assertEquals(weapon, si.getWeapon());
	}

	@Test
	public void testGetters2() {
		GameEntityId location = GameEntityId.MissScarletStart;
		GameEntityId suspect = GameEntityId.MissScarlet;
		GameEntityId weapon = GameEntityId.Revolver;
		SuggestionInfo si = new SuggestionInfo(location, suspect, weapon);
		assertEquals(location, si.getLocation()); 
		assertEquals(suspect, si.getSuspect());
		assertEquals(weapon, si.getWeapon());
	}

	@Test
	public void testGetters3() {
		GameEntityId location = GameEntityId.Study;
		GameEntityId suspect = GameEntityId.MrGreen;
		GameEntityId weapon = GameEntityId.Rope;
		SuggestionInfo si = new SuggestionInfo(location, suspect, weapon);
		assertEquals(location, si.getLocation()); 
		assertEquals(suspect, si.getSuspect());
		assertEquals(weapon, si.getWeapon());
	}

	@Test
	public void testEquals1() {
		SuggestionInfo si1 = new SuggestionInfo(GameEntityId.BallRoom, GameEntityId.MrGreen, GameEntityId.Rope);
		SuggestionInfo si2 = new SuggestionInfo(GameEntityId.Study, GameEntityId.MrsPeacock, GameEntityId.Wrench);
		SuggestionInfo si3 = new SuggestionInfo(GameEntityId.Study, GameEntityId.MrGreen, GameEntityId.Rope);
		SuggestionInfo si4 = new SuggestionInfo(GameEntityId.Study, GameEntityId.ColonelMustard, GameEntityId.Knife);
		SuggestionInfo si5 = new SuggestionInfo(GameEntityId.Study, GameEntityId.MrGreen, GameEntityId.Rope);
		
		assertTrue(si5.equals(si3));
		assertTrue(si3.equals(si5));
		assertFalse(si1.equals(si2));
		assertFalse(si1.equals(si3));
		assertFalse(si1.equals(si4));
		assertFalse(si1.equals(si5));
	}

	@Test
	public void testRandom() {

		for (int i = 0; i < 500; i++) {
			SuggestionInfo si = SuggestionInfo.Random();
			assertTrue(PieceInfo.isRoom(si.getLocation()));
			assertTrue(PieceInfo.isSuspect(si.getSuspect()));
			assertTrue(PieceInfo.isWeapon(si.getWeapon()));
		}
	}

}
