package com.mowitnow.domain;

/**
 * Represent the position of a mower and the orientation
 */
public class Position {

	private final int x;

	private final int y;

	private final Orientation orientation;

	public Position(int x, int y, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
