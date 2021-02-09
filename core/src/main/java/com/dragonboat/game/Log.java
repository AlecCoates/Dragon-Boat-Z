package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Represents a log obstacle on the course.
 */
public class Log extends Obstacle {

	/**
	 * Represents a savable/loadable log obstacle.
	 *
	 */
	static class LogSpriteDescriptor extends ObstacleSpriteDescriptor {
		/**
		 * Json requires an empty constructor to regenerate the class from a save file
		 */
		public LogSpriteDescriptor(){}

		/**
		 * Creates a json friendly instance of the log obstacle
		 *
		 * @param log The log obstacle data that needs to be converted to be stored properly
		 */
		public LogSpriteDescriptor(Log log) {
			super((Obstacle) log);
		}
	}

	/**
	 * Creates a log instance.
	 *
	 * @param textures  Reference to loaded textures
	 * @param xPosition X-position.
	 * @param yPosition Y-position.
	 */
	public Log(HashMap<String, Texture> textures, int xPosition, int yPosition) {
		super(textures, 15, xPosition, yPosition, null, null, "Log");
	}

}
