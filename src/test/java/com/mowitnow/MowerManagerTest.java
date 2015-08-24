package com.mowitnow;

import com.google.common.collect.Lists;
import com.mowitnow.domain.*;
import org.junit.Test;

import java.util.List;

import static com.mowitnow.domain.MowerInstruction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for MowerManager method
 * Check that all instructions of all the mowers are executed
 */
public class MowerManagerTest {

	@Test
	public void testProcess() {

		// INPUT
		final LawnSurface lawnSurface = new LawnSurface(5, 5);

		final List<MowerInstruction> instructionsMower1 = Lists.newArrayList(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
		final LawnMower firstMower = new LawnMower(new Position(1, 2, Orientation.NORTH), instructionsMower1);

		final List<MowerInstruction> instructionsMower2 = Lists.newArrayList(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD);
		final LawnMower secondMower = new LawnMower(new Position(3, 3, Orientation.EAST), instructionsMower2);

		final MowerManager mowerManager = new MowerManager(lawnSurface, Lists.newArrayList(firstMower, secondMower));

		// EXPECTED
		final Position expectedFirstPosition = new Position(1, 3, Orientation.NORTH);
		final Position expectedSecondPosition = new Position(5, 1, Orientation.EAST);
		final List<Position> expectedPositions = Lists.newArrayList(expectedFirstPosition, expectedSecondPosition);

		// ACT

		mowerManager.process();

		// ASSERT

		testPositions(expectedPositions, mowerManager.getLawnMowers());
	}

	private void testPositions(List<Position> expectedPositions, List<LawnMower> mowers) {
		for (int i = 0; i < mowers.size(); ++i) {
			final Position finalPosition = mowers.get(i).getCurrentPosition();
			final Position expectedPosition = expectedPositions.get(i);
			testPosition(expectedPosition, finalPosition);
		}
	}

	/**
	 * Test and assert that position is equals to the expected position
	 *
	 * @param expectedPosition the expected position
	 * @param position         the position to test
	 */
	private void testPosition(Position expectedPosition, Position position) {
		assertNotNull(position);
		assertEquals(expectedPosition.getX(), position.getX());
		assertEquals(expectedPosition.getY(), position.getY());
		assertEquals(expectedPosition.getOrientation(), position.getOrientation());
	}
}
