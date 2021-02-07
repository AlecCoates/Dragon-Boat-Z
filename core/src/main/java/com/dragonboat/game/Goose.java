package com.dragonboat.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a goose obstacle on the course.
 */
public class Goose extends Obstacle {
	public String direction = "South"; // Facing south by default.
	public Lane[] lanes;
	public int laneNo;

	/**
	 * Represents a savable/loadable goose obstacle.
	 *
	 */
	static class GooseSpriteDescriptor extends ObstacleSpriteDescriptor {
		public String direction;
		public int laneNo;

		/**
		 * Json requires an empty constructor to regenerate the class from a save file
		 */
		public GooseSpriteDescriptor(){}

		/**
		 * Creates a json friendly instance of the goose obstacle
		 *
		 * @param goose The goose obstacle data that needs to be converted to be stored properly
		 */
		public GooseSpriteDescriptor(Goose goose) {
			super((Obstacle) goose);
			this.direction = goose.direction;
			this.laneNo = goose.laneNo;
		}
	}


	/**
	 * <p>
	 * Creates a goose instance.
	 * </p>
	 * <p>
	 * Geese can face North, East, South or West. Width and height switch when
	 * changing between North or South and East or West.
	 * </p>
	 * 
	 * @param xPosition X-position.
	 * @param yPosition Y-position.
	 * @param lanes     Lanes in the map.
	 * @param laneNo    Lane number the goose will spawn in.
	 */
	public Goose(HashMap<String, Texture> textures, int xPosition, int yPosition, Lane[] lanes, int laneNo) {
		super(textures, 10, xPosition, yPosition, null, null, "Goose");
		this.lanes = lanes;
		this.laneNo = laneNo;
	}

	/**
	 * Changes the direction of the Goose to an appropriate, random cardinal
	 * direction.
	 */
	public void changeDirection() {
		HashMap<String, ArrayList<String>> cardinals = new HashMap<>();

		cardinals.put("East", new ArrayList<>(Arrays.asList("South")));
		cardinals.put("South", new ArrayList<>(Arrays.asList("East", "West")));
		cardinals.put("West", new ArrayList<>(Arrays.asList("South")));

		direction = cardinals.get(direction).get(new Random().nextInt(cardinals.get(direction).size()));
	}

	/**
	 * Moves the goose.
	 * 
	 * @param moveVal          Distance to move Goose by.
	 * @param backgroundOffset Offset from screen to course coordinates.
	 */
	public void Move(float moveVal, int backgroundOffset) {

		boolean canGoEast, canGoWest;

		if (this.getX() > this.lanes[this.laneNo].getLeftBoundary() && this.getX() + this.width < this.lanes[this.laneNo].getRightBoundary()) {
			// Goose is within the lane boundaries.
			canGoEast = true;
			canGoWest = true;
		} else if (this.getX() <= this.lanes[this.laneNo].getLeftBoundary()) {
			// Goose is on left boundary.
			canGoEast = true;
			canGoWest = false;
		} else {
			// Goose is on right boundary.
			canGoEast = false;
			canGoWest = true;
		}

		//Move horizontally if able
		if (canGoEast && this.direction.equals("East")) {
			this.setX(this.getX() + moveVal);
		} else if (canGoWest && this.direction.equals("West")) {
			this.setX(this.getX() - moveVal);
		}
		//Move vertically if in limits
		if (backgroundOffset > 0 && backgroundOffset < 2160) {
			this.setY(this.getY() - moveVal);
		}

		// Chance of goose changing direction.
		int randomMove = 20;
		if (new Random().nextInt(randomMove) == randomMove - 1) {
			changeDirection();
		}
	}
}
