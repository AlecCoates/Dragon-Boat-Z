package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

/**
 * Represents the player's boat.
 */
public class Player extends Boat {

    /**
     * Creates an instance of the player boat.
     * 
     * @param yPosition Y-position of the boat.
     * @param width     Width of the boat.
     * @param height    Height of the boat.
     * @param lane      Lane for the boat.
     * @param name      Name of the boat.
     */
    public Player(int yPosition, int width, int height, Lane lane, String name) {
        super(yPosition, width, height, lane, name);
    }

    public Player(String info) {
        super(info);
    }

    /**
     * Moves the player based on key pressed (W, A, S, D).
     */
    public void GetInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.IncreaseSpeed();
            this.IncreaseTiredness();
        } else {
            this.DecreaseTiredness();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            // Call method associated
            this.DecreaseSpeed();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Call method associated
            this.SteerLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Call method associated
            this.SteerRight();
        }
    }

}
