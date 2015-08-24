package com.mowitnow;

import com.google.common.collect.Lists;
import com.mowitnow.domain.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.mowitnow.domain.MowerInstruction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for LawnMower method
 * Check that all instructions are executed
 * Check that the mower move only when the position is inside the lawn
 */
public class LawnMowerTest {

	private static LawnSurface lawnSurface;

	@BeforeClass
	public static void oneTimeSetUp() {
		lawnSurface = new LawnSurface(5, 5);
	}

	@Test
	public void testProcessInstructions_00() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
		final LawnMower mower = new LawnMower(new Position(1, 2, Orientation.NORTH), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(1, 3, Orientation.NORTH);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
	}

	@Test
	public void testProcessInstructions_01() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD);
		final LawnMower mower = new LawnMower(new Position(3, 3, Orientation.EAST), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(5, 1, Orientation.EAST);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
	}

	@Test
	public void testProcessInstructions_02() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(FORWARD);
		final LawnMower mower = new LawnMower(new Position(0, 0, Orientation.SOUTH), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(0, 0, Orientation.SOUTH);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
	}

	@Test
	public void testProcessInstructions_03() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(FORWARD);
		final LawnMower mower = new LawnMower(new Position(0, 0, Orientation.WEST), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(0, 0, Orientation.WEST);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
	}

	@Test
	public void testProcessInstructions_04() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(FORWARD, LEFT, LEFT);
		final LawnMower mower = new LawnMower(new Position(5, 5, Orientation.NORTH), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(5, 5, Orientation.SOUTH);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
	}

	@Test
	public void testProcessInstructions_05() {
		// INPUT
		final List<MowerInstruction> instructionsMower = Lists.newArrayList(FORWARD, LEFT, LEFT, FORWARD, RIGHT);
		final LawnMower mower = new LawnMower(new Position(5, 0, Orientation.SOUTH), instructionsMower);

		// EXPECTED
		final Position expectedPosition = new Position(5, 1, Orientation.EAST);
		// ACT
		mower.processInstructions(lawnSurface);

		// ASSERT
		testPosition(expectedPosition, mower.getCurrentPosition());
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
