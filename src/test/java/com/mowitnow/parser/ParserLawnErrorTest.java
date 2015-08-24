package com.mowitnow.parser;

import org.junit.Test;

import java.io.IOException;

/**
 * Test class to check error of parsing on the lawn line
 */
public class ParserLawnErrorTest extends AbstractParserTest {

	@Test(expected = IllegalStateException.class)
	public void testEmptyFile() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/emptyFile");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testLawnSizeWrongFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lawnSizeWrongFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = NumberFormatException.class)
	public void testLawnSizeXBadFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lawnSizeXBadFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = NumberFormatException.class)
	public void testLawnSizeYBadFormat() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lawnSizeYBadFormat");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testLawnSizeXNeg() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lawnSizeXNeg");

		// ACT
		basicParser.parse(fileName);
	}

	@Test(expected = IllegalStateException.class)
	public void testLawnSizeYNeg() throws IOException {
		// INPUTS
		final String fileName = getAbsolutePath("/lawnSizeYNeg");

		basicParser.parse(fileName);
	}
}
