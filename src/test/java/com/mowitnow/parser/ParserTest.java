package com.mowitnow.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mowitnow.MowerManager;
import com.mowitnow.domain.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.mowitnow.domain.MowerInstruction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class to check the generated values
 */
public class ParserTest extends AbstractParserTest {

	private static List<LawnMower> expectedMowers;

	private static final String FILE_NAME = "/goodFile";

	@BeforeClass
	public static void oneTimeSetUp() {
		final List<MowerInstruction> instructionsMower1 = Lists.newArrayList(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
		final LawnMower firstMower = new LawnMower(new Position(1, 2, Orientation.NORTH), instructionsMower1);

		final List<MowerInstruction> instructionsMower2 = Lists.newArrayList(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD);
		final LawnMower secondMower = new LawnMower(new Position(3, 3, Orientation.EAST), instructionsMower2);

		expectedMowers = ImmutableList.of(firstMower, secondMower);
	}

	@Test
	public void testParsedMowerManager() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath(FILE_NAME);

		// ACT
		final MowerManager mowerManager = basicParser.parse(fileName);

		// ASSERT
		assertNotNull(mowerManager);
	}

	@Test
	public void testParsedLawnSurface() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath(FILE_NAME);

		// EXPECTED
		final int expectedMaxX = 5;
		final int expectedMaxY = 5;

		// ACT
		final MowerManager mowerManager = basicParser.parse(fileName);

		// ASSERT
		final LawnSurface surface = mowerManager.getLawnSurface();
		assertNotNull(surface);
		assertEquals(expectedMaxX, surface.getMaxX());
		assertEquals(expectedMaxY, surface.getMaxY());
	}

	@Test
	public void testParsedMowers() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath(FILE_NAME);

		// ACT
		final MowerManager mowerManager = basicParser.parse(fileName);

		// ASSERT
		final List<LawnMower> mowers = mowerManager.getLawnMowers();
		assertNotNull(mowers);
		int numberOfMowers = mowers.size();
		assertEquals(expectedMowers.size(), numberOfMowers);
	}

	@Test
	public void testEachParsedMowers() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath(FILE_NAME);

		// ACT
		final MowerManager mowerManager = basicParser.parse(fileName);

		// ASSERT
		final List<LawnMower> mowers = mowerManager.getLawnMowers();

		for (int i = 0; i < expectedMowers.size(); ++i) {
			final LawnMower expectedMower = expectedMowers.get(i);
			final LawnMower mower = mowers.get(i);
			assertNotNull(mower);
			testPosition(expectedMower.getStartPosition(), mower.getStartPosition());
			testMowerInstructions(expectedMower.getInstructions(), mower.getInstructions());
		}
	}

	private void testMowerInstructions(List<MowerInstruction> expectedInstructions, List<MowerInstruction> instructions) {
		int numberOfInstructions = instructions.size();
		assertNotNull(instructions);
		assertEquals(expectedInstructions.size(), numberOfInstructions);

		for (int i = 0; i < numberOfInstructions; ++i) {
			assertEquals(expectedInstructions.get(i), instructions.get(i));
		}
	}
}
