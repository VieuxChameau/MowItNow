package com.mowitnow.domain;

import java.util.List;

public class LawnMower {

	/**
	 * the starting position on the lawn surface
	 */
	private final Position startPosition;

	/**
	 * the current position on the lawn surface
	 */
	private Position currentPosition;

	/**
	 * The instructions that the mower should exec
	 */
	private final List<MowerInstruction> instructions;

	public LawnMower(Position startPosition, List<MowerInstruction> instructions) {
		this.startPosition = startPosition;
		this.currentPosition = startPosition;
		this.instructions = instructions;
	}

	/**
	 * Execute all the instructions of the mower on the surface
	 *
	 * @param surface the lawn surface to mow
	 */
	public void processInstructions(LawnSurface surface) {
		for (final MowerInstruction mowerInstruction : instructions) {
			final Position computePosition = mowerInstruction.process(currentPosition);
			if (surface.isInSurface(computePosition)) {
				currentPosition = computePosition;
			}
		}
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public List<MowerInstruction> getInstructions() {
		return instructions;
	}

	public Position getStartPosition() {
		return startPosition;
	}
}
