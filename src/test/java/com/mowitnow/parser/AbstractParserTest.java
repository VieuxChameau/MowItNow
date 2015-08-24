package com.mowitnow.parser;

import com.mowitnow.domain.Position;
import org.junit.Before;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Abstract utility class for the parser test
 */
public class AbstractParserTest {

	protected Parser basicParser;

	@Before
	public void initObjects() {
		basicParser = new BasicParser();
	}

	/**
	 * Return the absolute path for a file in test resource src/test/resources
	 */
	protected String getAbsolutePath(String fileName) {
		final URL url = this.getClass().getResource(fileName);
		return url.getFile();
	}

	/**
	 * Test and assert that position is equals to the expected position
	 *
	 * @param expectedPosition the expected position
	 * @param position         the position to test
	 */
	protected void testPosition(Position expectedPosition, Position position) {
		assertNotNull(position);
		assertEquals(expectedPosition.getX(), position.getX());
		assertEquals(expectedPosition.getY(), position.getY());
		assertEquals(expectedPosition.getOrientation(), position.getOrientation());
	}
}
