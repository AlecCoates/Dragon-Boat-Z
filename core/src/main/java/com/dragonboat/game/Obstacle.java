package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

/**
 * Represents an obstacle on the course.
 * 
 * @see Log
 * @see Goose
 */
public class Obstacle {
	private String name;
	protected float yPosition, xPosition;
	private int damage;
	public int width, height;
	public Texture texture;

	static class ObstacleSpriteDescriptor {
		public float yPosition, xPosition;
		public int damage;
		public int width, height;
		public String name;

		//Used for the return from json file
		public ObstacleSpriteDescriptor(){}

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
	 * @param damage    Damage the obstacle can inflict on a boat.
	 * @param xPosition X-position.
	 * @param yPosition Y-position.
	 * @param width     Width of the obstacle.
	 * @param height    Height of the obstacle.
	 * @param texture   Texture asset for the obstacle.
	 */
	public Obstacle(int damage, int xPosition, int yPosition, int width, int height, Texture texture, String name) {
		this.damage = damage;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.name = name;
	}
	public Obstacle(ObstacleSpriteDescriptor info){
		//Json json = new Json();
		//Obstacle.ObstacleSpriteDescriptor disc = json.fromJson(Obstacle.ObstacleSpriteDescriptor.class,info);

		this.damage = info.damage;
		this.xPosition = info.xPosition;
		this.yPosition = info.yPosition;
		this.width = info.width;
		this.height = info.height;
		this.name = info.name;
	}

	public ObstacleSpriteDescriptor saveState() {
		ObstacleSpriteDescriptor disc = new ObstacleSpriteDescriptor(this);
		//return new Json().toJson(disc);
		return disc;
	}

	public String getName(){
		return this.name;
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