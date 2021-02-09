package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Represents an obstacle on the course.
 * 
 * @see Log
 * @see Goose
 */
public class Obstacle {
	private String name;
	protected float yPosition, xPosition;
	protected int damage;
	public int width, height;
	public Texture texture;

	/**
	 * Represents a savable/loadable obstacle.
	 *
	 * @see Goose.GooseSpriteDescriptor
	 * @see Log.LogSpriteDescriptor
	 */
	static class ObstacleSpriteDescriptor {
		public float yPosition, xPosition;
		public int damage;
		public int width, height;
		public String name;

		/**
		 * Json requires an empty constructor to regenerate the class from a save file
		 */
		public ObstacleSpriteDescriptor(){}

		/**
		 * Creates a json friendly instance of the obstacle
		 *
		 * @param oldObstacle The obstacle data that needs to be converted to be stored properly
		 */
		public ObstacleSpriteDescriptor(Obstacle oldObstacle) {
			this.damage = oldObstacle.getDamage();
			this.xPosition = oldObstacle.getX();
			this.yPosition = oldObstacle.getY();
			this.width = oldObstacle.width;
			this.height = oldObstacle.getHeight();
			this.name = oldObstacle.name;
		}
	}

	/**
	 * Creates an obstacle instance.
	 *
	 * @param textures  Reference to loaded textures
	 * @param damage    Damage the obstacle can inflict on a boat.
	 * @param xPosition X-position.
	 * @param yPosition Y-position.
	 * @param width     Width of the obstacle.
	 * @param height    Height of the obstacle.
	 * @param name      Name of the obstacle.
	 */
	public Obstacle(HashMap<String, Texture> textures, int damage, int xPosition, int yPosition, Integer width, Integer height, String name) {
		this.damage = damage;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.name = name;
		this.texture = textures.get(name);
		if (width != null) {
			this.width = width;
		} else {
			this.width = this.texture.getWidth();
		}
		if (height != null) {
			this.height = height;
		} else {
			this.height = this.texture.getHeight();
		}
	}

	/**
	 * Moves the obstacle.
	 * 
	 * @param moveVal          Distance to move the object by.
	 * @param backgroundOffset Offset from screen to course coordinates.
	 */
	public void Move(float moveVal, int backgroundOffset) {
		this.setY(this.getY() - moveVal);
	}

	// getters and setters

	/**
	 * @return String representing the obstacle's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 
	 * @return Int representing damage the obstacle inflicts upon collision.
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * 
	 * @return Float representing the x-position.
	 */
	public float getX() {
		return this.xPosition;
	}

	/**
	 * 
	 * @return Float representing the y-position.
	 */
	public float getY() {
		return this.yPosition;
	}

	/**
	 * 
	 * @param yPosition Y-position.
	 */
	public void setY(float yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * 
	 * @param xPosition X-position.
	 */
	public void setX(float xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * 
	 * @return Texture asset for obstacle.
	 */
	public Texture getTexture() {
		return this.texture;
	}

	/**
	 * 
	 * @return Int representing the height of the obstacle.
	 */
	public int getHeight() {
		return this.height;
	}
}