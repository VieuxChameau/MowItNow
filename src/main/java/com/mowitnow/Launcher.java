package com.mowitnow;

import com.mowitnow.parser.BasicParser;
import com.mowitnow.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Own the main method
 */
public final class Launcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

	private Launcher() {
	}

	public static void main(String[] argv) {
		final Parser parser = new BasicParser();
		for (String arg : argv) {
			try {
				final MowerManager manager = parser.parse(arg);

				manager.process();
			} catch (Exception ex) {
				LOGGER.warn("Error while reading the file {}", ex);
			}
		}
	}
}
