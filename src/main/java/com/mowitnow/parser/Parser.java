package com.mowitnow.parser;

import com.mowitnow.MowerManager;

import java.io.IOException;

/**
 * Interface for all the parser
 */
public interface Parser {

	/**
	 * Parse the given file and create the environment
	 *
	 * @param fileName the file to parse
	 */
	MowerManager parse(String fileName) throws IOException;
}
