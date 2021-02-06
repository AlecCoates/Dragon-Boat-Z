package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a log obstacle on the course.
 */
public class Log extends Obstacle {

	static class LogSpriteDescriptor extends ObstacleSpriteDescriptor {
		//Used for the return from json file
		public LogSpriteDescriptor(){}

		public LogSpriteDescriptor(Log log) {
			super((Obstacle) log);
		}
	}

	/**
	 * Creates a log instance.
	 * 
	 * @param xPosition X-position.
	 * @param yPosition Y-position.
	 */
	public Log(int xPosition, int yPosition) {
		super(15, xPosition, yPosition, null, null, "Log");
	}

}
