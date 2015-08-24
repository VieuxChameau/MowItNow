package com.mowitnow.instruction;

import com.mowitnow.domain.MowerInstruction;
import com.mowitnow.domain.Position;
import org.junit.Test;

import static com.mowitnow.domain.Orientation.*;

/**
 * Test class for the Turn Right Instruction
 */
public class MowerInstructionRightTest extends AbstractMowerInstructionTest {

	private static final int X_POSITION = 0;

	private static final int Y_POSITION = 0;

	public MowerInstructionRightTest() {
		super(MowerInstruction.RIGHT);
	}

	@Test
	public void testProcessFromNorth() {
		// INPUTS
		final Position position = new Position(X_POSITION, Y_POSITION, NORTH);

		// EXPECTED
		final Position expectedPosition = new Position(X_POSITION, Y_POSITION, EAST);

		actAssert(position, expectedPosition);
	}

	@Test
	public void testProcessFromEast() {
		// INPUTS
		final Position position = new Position(X_POSITION, Y_POSITION, EAST);

		// EXPECTED
		final Position expectedPosition = new Position(X_POSITION, Y_POSITION, SOUTH);

		actAssert(position, expectedPosition);
	}

	@Test
	public void testProcessFromSouth() {
		// INPUTS
		final Position position = new Position(X_POSITION, Y_POSITION, SOUTH);

		// EXPECTED
		final Position expectedPosition = new Position(X_POSITION, Y_POSITION, WEST);

		actAssert(position, expectedPosition);
	}

	@Test
	public void testProcessFromWest() {
		// INPUTS
		final Position position = new Position(X_POSITION, Y_POSITION, WEST);

		// EXPECTED
		final Position expectedPosition = new Position(X_POSITION, Y_POSITION, NORTH);

		actAssert(position, expectedPosition);
	}
}
