package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

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
	public Log(HashMap<String, Texture> textures, int xPosition, int yPosition) {
		super(textures, 15, xPosition, yPosition, null, null, "Log");
	}

}
