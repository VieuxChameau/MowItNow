package com.mowitnow.domain;

import com.google.common.base.Preconditions;

/**
 * Represent all the Cardinal directions available for a mower
 */
public enum Orientation {

	/**
	 * Represent the NORTH direction
	 */
	NORTH('N'),
	/**
	 * Represent the EAST direction
	 */
	EAST('E'),
	/**
	 * Represent the SOUTH direction
	 */
	SOUTH('S'),
	/**
	 * Represent the WEST direction
	 */
	WEST('W');

	private char code;

	Orientation(char code) {
		this.code = code;
	}

	/**
	 * Return the first Orientation matching the code
	 *
	 * @param code the code Orientation
	 * @return the found Orientation
	 */
	public static Orientation valueOfByCode(String code) {
		Preconditions.checkArgument(code != null && !code.isEmpty());

		return valueOfByCode(code.charAt(0));
	}

	/**
	 * Return the first Orientation matching the code
	 *
	 * @param code the code Orientation
	 * @return the found Orientation
	 */
	public static Orientation valueOfByCode(char code) {
		for (final Orientation orientation : values()) {
			if (orientation.code == code) {
				return orientation;
			}
		}
		throw new IllegalArgumentException("Unknown code instruction");
	}

	public char getCode() {
		return code;
	}
}
