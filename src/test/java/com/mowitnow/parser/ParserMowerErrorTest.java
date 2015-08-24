package com.mowitnow.parser;

import org.junit.Test;

import java.io.IOException;

/**
 * Test class to check error of parsing on the mower lines and mower instructions
 */
public class ParserMowerErrorTest extends AbstractParserTest {

	@Test(expected = IllegalStateException.class)
	public void testNoMowers() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/noMowers");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testMowerWrongFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerWrongFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = NumberFormatException.class)
	public void testMowerXBadFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerXBadFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = NumberFormatException.class)
	public void testMowerYBadFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerYBadFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testMowerXNotInSurface() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerXNotInSurface");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testMowerYNotInSurface() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerYNotInSurface");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMowerBadOrientation() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerBadOrientation");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testMowerWithoutInstructions() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/mowerWithoutInstructions");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testLastMowerWithoutInstructions() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lastMowerWithoutInstructions");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadMowerInstructions() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/badMowerInstructions");

		// ACT
		basicParser.parse(fileName);
	}
}
