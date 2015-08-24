package com.mowitnow.instruction;

import com.mowitnow.domain.MowerInstruction;
import com.mowitnow.domain.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Abstract class for all the instruction test class
 */
public abstract class AbstractMowerInstructionTest {

	private final MowerInstruction instruction;

	public AbstractMowerInstructionTest(MowerInstruction instruction) {
		this.instruction = instruction;
	}

	protected void actAssert(final Position position, final Position expectedPosition) {
		// ACT
		final Position resultPosition = instruction.process(position);

		// ASSERT
		assertNotNull(resultPosition);
		assertEquals(expectedPosition.getX(), resultPosition.getX());
		assertEquals(expectedPosition.getY(), resultPosition.getY());
		assertEquals(expectedPosition.getOrientation(), resultPosition.getOrientation());
	}

	public MowerInstruction getInstruction() {
		return instruction;
	}
}
