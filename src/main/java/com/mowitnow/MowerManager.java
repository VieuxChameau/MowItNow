package com.mowitnow;

import com.mowitnow.domain.LawnMower;
import com.mowitnow.domain.LawnSurface;
import com.mowitnow.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Manager of the environment : the lawn and the mowers
 */
public class MowerManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowerManager.class);

	/**
	 * The surface to mow
	 */
	private final LawnSurface lawnSurface;

	/**
	 * The mowers
	 */
	private final List<LawnMower> lawnMowers;

	public MowerManager(LawnSurface lawnSurface, List<LawnMower> lawnMowers) {
		this.lawnSurface = lawnSurface;
		this.lawnMowers = lawnMowers;
	}

	/**
	 * Launch all the mowers sequentially mowing the lawn
	 */
	public void process() {
		int mowerNumber = 1;
		for (final LawnMower lawnMower : lawnMowers) {
			final Position startPosition = lawnMower.getStartPosition();
			LOGGER.debug("Mower {} - Start Position ({}, {}) {}", new Object[]{mowerNumber, startPosition.getX(), startPosition.getY(), startPosition.getOrientation()});
			lawnMower.processInstructions(lawnSurface);
			final Position lastPosition = lawnMower.getCurrentPosition();
			LOGGER.info("Mower {} - Final Position ({}, {}) {}", new Object[]{mowerNumber, lastPosition.getX(), lastPosition.getY(), lastPosition.getOrientation()});
			++mowerNumber;
		}
	}

	public List<LawnMower> getLawnMowers() {
		return lawnMowers;
	}

	public LawnSurface getLawnSurface() {
		return lawnSurface;
	}
}
