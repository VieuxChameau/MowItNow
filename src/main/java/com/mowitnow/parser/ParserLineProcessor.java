package com.mowitnow.parser;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;
import com.mowitnow.MowerManager;
import com.mowitnow.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Process the lines of the file and create required object.
 * One instance should be use per file
 */
public class ParserLineProcessor implements LineProcessor<MowerManager> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParserLineProcessor.class);

	/**
	 * Number of expected token for the lawn's description
	 */
	private static final int HEADER_SIZE = 2;

	/**
	 * Delimiter use for the lawn's description
	 */
	private static final char HEADER_SEPARATOR = ' ';

	/**
	 * Number of expected token for the mower's description
	 */
	private static final int MOWER_SIZE = 3;

	/**
	 * Delimiter use for the mower's description
	 */
	private static final char MOWER_SEPARATOR = ' ';

	/**
	 * The lawn surface build from the parsed lines
	 */
	private LawnSurface lawnSurface;

	/**
	 * The last position parsed
	 */
	private Position lastPosition;

	/**
	 * Mowers build from the parsed lines
	 */
	private List<LawnMower> lawnMowers = Lists.newArrayList();

	/**
	 * The line number of the current processing line
	 */
	private int lineNumber = 1;

	@Override
	public boolean processLine(String line) throws IOException {
		LOGGER.debug("New line {}", line);
		if (lineNumber == 1) {
			processLawnSize(line);
		} else {
			if (lastPosition == null) {
				processMowerPosition(line);
			} else {
				processMowerInstructions(line);
				lastPosition = null;
			}
		}
		++lineNumber;
		return true;
	}

	/**
	 * Parse a lawn surface from the line, call only once
	 * Expected format : Integer Integer , ex: 5 5
	 *
	 * @param header the header line
	 */
	private void processLawnSize(String header) {
		final List<String> tokens = Lists.newArrayList(Splitter.on(HEADER_SEPARATOR).split(header));
		LOGGER.debug("Lown header Size {}", tokens.size());

		Preconditions.checkState(tokens.size() == HEADER_SIZE, "Bad header format; Line {}", lineNumber);
		final int maxX = Integer.parseInt(tokens.get(0));
		final int maxY = Integer.parseInt(tokens.get(1));

		Preconditions.checkState(maxX > 0 && maxY > 0, "Lawn Size should be strictly positive; Line {}", lineNumber);
		LOGGER.info("Creating Lawn of {} * {}", maxX, maxY);
		lawnSurface = new LawnSurface(maxX, maxY);
	}

	/**
	 * Parse a mower position from the line
	 * Expected format : Integer Integer Orientation.code, ex: 1 2 N
	 *
	 * @param mowerDescription a mower description line
	 */
	private void processMowerPosition(String mowerDescription) {
		final List<String> tokens = Lists.newArrayList(Splitter.on(MOWER_SEPARATOR).split(mowerDescription));
		LOGGER.debug("Mower header Size {}", tokens.size());

		Preconditions.checkState(tokens.size() == MOWER_SIZE, "Bad mower format; Line {}", lineNumber);
		final int x = Integer.parseInt(tokens.get(0));
		final int y = Integer.parseInt(tokens.get(1));

		Preconditions.checkState(lawnSurface.isInSurface(x, y), "Lawn Mower not in the field; Line {}", lineNumber);

		final Orientation orientation = Orientation.valueOfByCode(tokens.get(2));

		LOGGER.info("Creating Position of ({},{}) {}", new Object[]{x, y, orientation});
		lastPosition = new Position(x, y, orientation);
	}

	/**
	 * Parse a mowers instruction
	 * Expected format : (MowerInstruction.code)+, ex: AADGAG
	 * <p>
	 * A mower is build from the last position parsed and the instructions
	 *
	 * @param mowerInstructions a line of mower instructions
	 */
	private void processMowerInstructions(String mowerInstructions) {
		final List<MowerInstruction> instructions = Lists.newArrayListWithCapacity(mowerInstructions.length());
		LOGGER.debug("Number of instructions for the mower {}", mowerInstructions.length());

		for (int i = 0; i < mowerInstructions.length(); ++i) {
			final Character charInstruction = mowerInstructions.charAt(i);
			final MowerInstruction instruction = MowerInstruction.valueOfByCode(charInstruction);
			instructions.add(instruction);
		}

		final LawnMower lawnMower = new LawnMower(lastPosition, instructions);
		lawnMowers.add(lawnMower);
	}

	@Override
	public MowerManager getResult() {
		Preconditions.checkState(lawnSurface != null, "Empty File");
		Preconditions.checkState(!lawnMowers.isEmpty(), "No mowers in the file");
		Preconditions.checkState(lastPosition == null, "Last mower doesn't have instructions; Line {}", lineNumber);
		return new MowerManager(lawnSurface, lawnMowers);
	}
}
