package us.neuner.clo.common;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class PieceInfoTest {

	@Test
	public void testIsSuspectScarlet() {
		Boolean result = PieceInfo.isSuspect(GameEntityId.MissScarlet);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsSuspectFailure() {
		Boolean result = PieceInfo.isSuspect(GameEntityId.MissScarletStart);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationTrue1() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MrGreenStart);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue2() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MrsPeacockStart);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue3() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MissScarletStart);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue4() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Kitchen);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue5() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Library);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue6() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Study);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue7() {
		Boolean result = PieceInfo.isLocation(GameEntityId.DiningRoom);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue8() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Conservatory);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationTrue9() {
		Boolean result = PieceInfo.isLocation(GameEntityId.BilliardRoom);
		org.junit.Assert.assertTrue(result);
	}

	@Test
	public void testIsLocationFalse1() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MrGreen);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse2() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MrsPeacock);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse3() {
		Boolean result = PieceInfo.isLocation(GameEntityId.MissScarlet);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse4() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Knife);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse5() {
		Boolean result = PieceInfo.isLocation(GameEntityId.CandleStick);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse6() {
		Boolean result = PieceInfo.isLocation(GameEntityId.LeadPipe);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse7() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Rope);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse8() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Wrench);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testIsLocationFalse9() {
		Boolean result = PieceInfo.isLocation(GameEntityId.Revolver);
		org.junit.Assert.assertFalse(result);
	}

	@Test
	public void testPieceInfoConstructor1() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.MrGreen, GameEntityId.MrGreenStart));
	}
	@Test
	public void testPieceInfoConstructor2() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.MrsPeacock, GameEntityId.MrsPeacockStart));
	}
	@Test
	public void testPieceInfoConstructor3() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.MissScarlet, GameEntityId.MissScarletStart));
	}
	@Test
	public void testPieceInfoConstructor4() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.Knife, GameEntityId.Kitchen));
	}
	@Test
	public void testPieceInfoConstructor5() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.CandleStick, GameEntityId.Library));
	}
	@Test
	public void testPieceInfoConstructor6() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.LeadPipe, GameEntityId.Study));
	}
	@Test
	public void testPieceInfoConstructor7() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.Rope, GameEntityId.DiningRoom));
	}
	@Test
	public void testPieceInfoConstructor8() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.Wrench, GameEntityId.Conservatory));
	}
	@Test
	public void testPieceInfoConstructor9() {
		org.junit.Assert.assertNotNull(new PieceInfo(GameEntityId.Revolver, GameEntityId.BilliardRoom));
	}
}
