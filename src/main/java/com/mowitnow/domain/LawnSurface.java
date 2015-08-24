package com.mowitnow.domain;

/**
 * Represent a rectangular lawn to mow
 */
public class LawnSurface {

	private final int maxX;

	private final int maxY;

	public LawnSurface(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Check if the position is inside the lawn surface
	 *
	 * @param position the position to check
	 * @return true if the position is in, otherwise false
	 */
	public boolean isInSurface(Position position) {
		return isInSurface(position.getX(), position.getY());
	}

	/**
	 * Check if the position is inside the lawn surface
	 *
	 * @return true if the position is in, otherwise false
	 */
	public boolean isInSurface(int x, int y) {
		return (0 <= x && x <= maxX) && (0 <= y && y <= maxY);
	}

	// -------------
	// getter-setter
	// -------------
	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}
}
