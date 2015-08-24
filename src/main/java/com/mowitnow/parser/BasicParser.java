package com.mowitnow.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.mowitnow.MowerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * BasicParser for basic txt file format
 */
public class BasicParser implements Parser {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicParser.class);

	public BasicParser() {

	}

	@Override
	public MowerManager parse(String fileName) throws IOException {
		LOGGER.debug("Starting parsing of file {}", fileName);
		return Files.readLines(new File(fileName), Charsets.UTF_8, new ParserLineProcessor());
	}
}
