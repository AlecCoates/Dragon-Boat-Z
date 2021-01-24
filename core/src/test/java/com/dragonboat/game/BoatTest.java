package com.dragonboat.game;

import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoatTest {
    private int ROBUSTNESS, MAXSPEED;
    private float ACCELERATION, MANEUVERABILITY;

    private int durability;
    protected float yPosition, xPosition, penalties;
    protected int width, height;
    private float currentSpeed, fastestLegTime, tiredness;
    protected Lane lane;
    private Texture[] textureFrames;
    private int frameCounter;
    public Texture texture;
    private String name;
    private boolean finished;
    private final int threshold = 5;

    private static final float bankWidth = 40;

    @Test
    void steerLeft() {
        assertEquals(1, 2 / 2);
    }

    @Test
    void steerRight() {
    }

    @Test
    void moveForward() {
    }

    @Test
    void increaseSpeed() {
    }

    @Test
    void decreaseSpeed() {
    }

    @Test
    void checkCollisions() {
    }

    @Test
    void applyDamage() {
    }

    @Test
    void checkIfInLane() {
    }

    @Test
    void updateFastestTime() {
    }

    @Test
    void increaseTiredness() {
    }

    @Test
    void decreaseTiredness() {
    }

    @Test
    void advanceTextureFrame() {
    }

    @Test
    void generateTextureFrames() {
    }

    @Test
    void reset() {
    }

    @Test
    void resetFastestLegTime() {
    }

    @Test
    void setTexture() {
    }

    @Test
    void setTextureFrames() {
    }

    @Test
    void getFastestTime() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getName() {
    }

    @Test
    void finished() {
    }

    @Test
    void setFinished() {
    }

    @Test
    void getCurrentSpeed() {
    }

    @Test
    void getProgress() {
    }

    @Test
    void setStats() {
    }

    @Test
    void testSetStats() {
    }

    @Test
    void getManeuverability() {
    }

    @Test
    void getAcceleration() {
    }

    @Test
    void getRobustness() {
    }

    @Test
    void getDurability() {
    }

    @Test
    void getMaxSpeed() {
    }

    @Test
    void getTiredness() {
    }

    @Test
    void getPenalty() {
    }

    @Test
    void applyPenalty() {
    }

    @Test
    void setLane() {
    }
}