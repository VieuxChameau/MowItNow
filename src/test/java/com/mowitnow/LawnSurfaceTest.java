package com.mowitnow;

import com.mowitnow.domain.LawnSurface;
import com.mowitnow.domain.Orientation;
import com.mowitnow.domain.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for LawnSurface method
 */
public class LawnSurfaceTest {
	/**
	 * The lawn surface is the same for all the test 5 * 5
	 */
	private static LawnSurface lawnSurface;

	private static final int MAX_X_LAWN = 5;
	private static final int MAX_Y_LAWN = 5;

	@BeforeClass
	public static void oneTimeSetUp() {
		lawnSurface = new LawnSurface(MAX_X_LAWN, MAX_Y_LAWN);
	}

	@Test
	public void testValidIsInSurface_00() {
		// INPUTS
		final Position position = new Position(2, 2, Orientation.NORTH);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertTrue(isInSurface);
	}

	@Test
	public void testValidIsInSurface_X0() {
		// INPUTS
		final Position position = new Position(0, 2, Orientation.WEST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertTrue(isInSurface);
	}

	@Test
	public void testValidIsInSurface_Y0() {
		// INPUTS
		final Position position = new Position(2, 0, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertTrue(isInSurface);
	}

	@Test
	public void testValidIsInSurface_X5() {
		// INPUTS
		final Position position = new Position(MAX_X_LAWN, 2, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertTrue(isInSurface);
	}

	@Test
	public void testValidIsInSurface_Y5() {
		// INPUTS
		final Position position = new Position(2, MAX_Y_LAWN, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertTrue(isInSurface);
	}

	@Test
	public void testInvalidIsInSurface_XNeg() {
		// INPUTS
		final Position position = new Position(-1, 2, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertFalse(isInSurface);
	}

	@Test
	public void testInvalidIsInSurface_YNeg() {
		// INPUTS
		final Position position = new Position(2, -1, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertFalse(isInSurface);
	}

	@Test
	public void testInvalidIsInSurface_XSup() {
		// INPUTS
		final Position position = new Position(MAX_X_LAWN + 1, 2, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertFalse(isInSurface);
	}

	@Test
	public void testInvalidIsInSurface_YSup() {
		// INPUTS
		final Position position = new Position(1, MAX_Y_LAWN + 1, Orientation.EAST);

		// ACT
		final boolean isInSurface = lawnSurface.isInSurface(position);

		// ASSERT
		assertFalse(isInSurface);
	}
}
