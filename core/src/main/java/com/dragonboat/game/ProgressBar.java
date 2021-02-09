package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a progress bar.
 */
public class ProgressBar {

	public transient Texture texture, playerIcon, opponentIcon;

	protected transient Player playerBoat;
	protected transient Opponent[] opponentBoats;
	protected float timeSeconds = 0;
	protected float playerTime = 0;

	/**
	 * Represents a savable/loadable ProgressBar.
	 */
	static class ProgressBarSpriteDescriptor {
		public float timeSeconds;
		public float playerTime;

		/**
		 * Json requires an empty constructor to regenerate the class from a save file
		 */
		public ProgressBarSpriteDescriptor(){}

		/**
		 * Creates a json friendly instance of the obstacle
		 *
		 * @param oldProgressBar The ProgressBar data that needs to be converted to be stored properly
		 */
		public ProgressBarSpriteDescriptor(ProgressBar oldProgressBar) {
			this.timeSeconds = oldProgressBar.timeSeconds;
			this.playerTime = oldProgressBar.playerTime;
		}
	}

	/**
	 * Creates a progress bar that tracks the player and opponent boats progress
	 * along the course.
	 * 
	 * @param player    The player's boat.
	 * @param opponents Array of opponent boats.
	 */
	public ProgressBar(Player player, Opponent[] opponents) {
		this(player, opponents, false);
	}

	/**
	 * Creates a progress bar that tracks the player and opponent boats progress
	 * along the course.
	 *
	 * @param player    The player's boat.
	 * @param opponents Array of opponent boats.
	 * @param noTexture Debug parameter to stop LibGDX loading texture assets.
	 */
	public ProgressBar(Player player, Opponent[] opponents, boolean noTexture) {
		this.playerBoat = player;
		this.opponentBoats = opponents;
		if (!noTexture) {
			this.texture = new Texture(Gdx.files.internal("core/assets/top bar sprite.png"));
			this.playerIcon = new Texture(Gdx.files.internal("core/assets/progress icon player.png"));
			this.opponentIcon = new Texture(Gdx.files.internal("core/assets/progress icon enemy.png"));
		}
	}

	/**
	 * Resets the timer to zero.
	 */
	public void StartTimer() {
		this.timeSeconds = 0f;
		this.playerTime = 0f;
	}

	/**
	 * Increments the timer by the time passed.
	 * 
	 * @param timePassed The time delta from the last frame.
	 */
	public void IncrementTimer(float timePassed) {
		this.timeSeconds += timePassed;
		// Check player is still racing.
		if (!this.playerBoat.finished()) {
			this.playerTime = this.timeSeconds;
		}
	}

	// getters and setters

	/**
	 * Returns true if all boats have finished.
	 * 
	 * @param finishY Y-position of the finish line.
	 * @return Boolean representing if all boats have finished the course.
	 */
	public boolean allFinished(int finishY) {
		float[] progress = this.getProgress(finishY);
		for (float v : progress) {
			if (v != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the progress of all boats.
	 * 
	 * @param finishY Y-position of the finish line.
	 * @return Array of floats representing the percentage of the course covered by
	 *         each boat. First index stores player's progress.
	 */
	public float[] getProgress(int finishY) {
		float[] out = new float[opponentBoats.length + 1];
		out[0] = playerBoat.getProgress(finishY);
		for (int i = 0; i < opponentBoats.length; i++) {
			out[i + 1] = opponentBoats[i].getProgress(finishY);
		}

		return out;
	}

	/**
	 * Gets the time elapsed for the player in the current race.
	 * 
	 * @return Returns a float representing the player's current race time.
	 */
	public float getPlayerTime() {
		return this.playerTime;
	}

	/**
	 * Gets the time elapsed for the player in the current race and total penalty
	 * time.
	 * 
	 * @return String representing player time ":" penalty time.
	 */
	public String getPlayerTimeString() {
		return (float) Math.round(this.playerTime * 10) / 10 + " + "
				+ (float) Math.round(this.playerBoat.getPenalty() * 10) / 10;
	}

	/**
	 * Gets the time passed for the current race.
	 * 
	 * @return Returns a float representing the current race time.
	 */
	public float getTime() {
		return this.timeSeconds;
	}

	/**
	 * Gets the progress bar texture.
	 * 
	 * @return A Texture representing the sprite.
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Gets the player icon texture.
	 * 
	 * @return A Texture representing the sprite.
	 */
	public Texture getPlayerIcon() {
		return playerIcon;
	}

	/**
	 * Gets the opponent icon texture.
	 * 
	 * @return A Texture representing the sprite.
	 */
	public Texture getOpponentIcon() {
		return opponentIcon;
	}
}
