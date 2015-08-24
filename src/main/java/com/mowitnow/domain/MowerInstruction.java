package com.mowitnow.domain;

/**
 * Represent all the available instruction of a lawn mower
 */
public enum MowerInstruction {

	/**
	 * Turn Left Instruction
	 */
	LEFT('G') {
		@Override
		public Position process(Position initialPosition) {
			final Orientation currentOrientation = initialPosition.getOrientation();

			Orientation computeOrientation = currentOrientation;
			switch (currentOrientation) {
				case NORTH:
					computeOrientation = Orientation.WEST;
					break;
				case EAST:
					computeOrientation = Orientation.NORTH;
					break;
				case SOUTH:
					computeOrientation = Orientation.EAST;
					break;
				case WEST:
					computeOrientation = Orientation.SOUTH;
					break;
			}
			return new Position(initialPosition.getX(), initialPosition.getY(), computeOrientation);
		}
	},
	/**
	 * Turn Right Instruction
	 */
	RIGHT('D') {
		@Override
		public Position process(Position initialPosition) {
			final Orientation currentOrientation = initialPosition.getOrientation();

			Orientation computeOrientation = currentOrientation;
			switch (currentOrientation) {
				case NORTH:
					computeOrientation = Orientation.EAST;
					break;
				case EAST:
					computeOrientation = Orientation.SOUTH;
					break;
				case SOUTH:
					computeOrientation = Orientation.WEST;
					break;
				case WEST:
					computeOrientation = Orientation.NORTH;
					break;
			}
			return new Position(initialPosition.getX(), initialPosition.getY(), computeOrientation);
		}
	},
	/**
	 * Forward Instruction
	 */
	FORWARD('A') {
		@Override
		public Position process(Position initialPosition) {
			final Orientation currentOrientation = initialPosition.getOrientation();

			int x = initialPosition.getX();
			int y = initialPosition.getY();

			switch (currentOrientation) {
				case NORTH:
					++y;
					break;
				case EAST:
					++x;
					break;
				case SOUTH:
					--y;
					break;
				case WEST:
					--x;
					break;
			}
			return new Position(x, y, currentOrientation);
		}
	};

	/**
	 * The short version of the instruction
	 */
	private final char code;

	MowerInstruction(char code) {
		this.code = code;
	}

	/**
	 * Compute the next position when processing the instruction
	 *
	 * @return the compute position
	 */
	public abstract Position process(Position initialPosition);

	/**
	 * Return the first MowerInstruction matching the code
	 *
	 * @param code the code instruction
	 * @return the found MowerInstruction
	 */
	public static MowerInstruction valueOfByCode(char code) {
		// Pourrait être un switch vu le peu de valeurs disponibles
		for (final MowerInstruction instruction : values()) {
			if (instruction.code == code) {
				return instruction;
			}
		}
		throw new IllegalArgumentException("Unknown code instruction");
	}

	public char getShortVersion() {
		return code;
	}
}
