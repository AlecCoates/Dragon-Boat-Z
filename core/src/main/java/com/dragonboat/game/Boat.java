package com.dragonboat.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

/**
 * Represents a Boat, controlled by either a Player or Opponent.
 * 
 * @see Player
 * @see Opponent
 */
public class Boat {
    /*
     * Direct representation based off the UML diagram
     * https://drive.google.com/file/d/15O95umnJIoApnsj8I9ejEtMxrDGYJWAC/view?usp=sharing
     */

    private int ROBUSTNESS;
    private float ACCELERATION, MANEUVERABILITY, MAXSPEED;
    private int durability;
    protected float yPosition, xPosition, penalties;
    protected int width, height;
    protected float currentSpeed, fastestLegTime, tiredness;
    protected Lane[] lanes;
    protected int laneNo;

    protected Texture[] textureFrames;
    protected int frameCounter = 0;
    public Texture texture;
    protected String name;
    protected char label;
    protected boolean finished;
    protected final int threshold = 5;
    public static float bankWidth = 40;

    static class BoatSpriteDescriptor {
        public int durability;
        public float yPosition, xPosition, penalties;
        public int width, height;
        public float currentSpeed, fastestLegTime, tiredness;
        public int laneNo;
        public int frameCounter;
        public String name;
        public boolean finished;
        public char label;

        public BoatSpriteDescriptor(){}

        public BoatSpriteDescriptor(Boat oldBoat) {
            this.durability = oldBoat.getDurability();
            this.yPosition = oldBoat.getY();
            this.xPosition = oldBoat.getX();
            this.penalties = oldBoat.getPenalty();
            this.width = oldBoat.width;
            this.height = oldBoat.getHeight();
            this.currentSpeed = oldBoat.getCurrentSpeed();
            this.fastestLegTime = oldBoat.getFastestTime();
            this.tiredness = oldBoat.tiredness;
            this.laneNo = oldBoat.laneNo;
            this.frameCounter = oldBoat.frameCounter;
            this.name = oldBoat.getName();
            this.finished = oldBoat.finished();
            this.label = oldBoat.label;
        }
    }

    /**
     * Creates an instance of the player boat.
     *
     * @param yPosition Y-position of the boat.
     * @param width     Width of the boat.
     * @param height    Height of the boat.
     * @param lanes     Lanes for the boat.
     * @param laneNo    Lane number for the boat.
     * @param name      Name of the boat.
     */
    public Boat(float yPosition, int width, int height, Lane[] lanes, int laneNo, String name) {
        this.xPosition = lanes[laneNo].getRightBoundary() - (lanes[laneNo].getRightBoundary() - lanes[laneNo].getLeftBoundary()) / 2.0f - width / 2.0f;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.currentSpeed = 0f;
        this.penalties = 0;
        this.durability = 50;
        this.tiredness = 0f;
        this.lanes = lanes;
        this.laneNo = laneNo;
        this.fastestLegTime = 0;
        this.textureFrames = new Texture[4];
        this.frameCounter = 0;
        this.name = name;
    }

    /**
     * Decreases the x-position of the boat respective to the boat's maneuverability
     * and speed, and decreases the speed by 3%.
     */
    public void SteerLeft() {
        if (this.xPosition > bankWidth) {
            this.xPosition -= this.MANEUVERABILITY * this.currentSpeed;
            this.currentSpeed *= 0.985;
        } else {
            this.xPosition = bankWidth;
        }
    }

    /**
     * Increases the x-position of the boat respective to the boat's maneuverability
     * and speed, and decreases the speed by 3%.
     */
    public void SteerRight() {
        if (this.xPosition + this.width < Gdx.graphics.getWidth() - bankWidth) {
            this.xPosition += this.MANEUVERABILITY * this.currentSpeed;
            this.currentSpeed *= 0.985;
        } else {
            this.xPosition = Gdx.graphics.getWidth() - bankWidth - this.width;
        }
    }

    /**
     * Increases the y-position of the boat respective to the boat's speed, and
     * decreases the speed by 0.08%.
     */
    public void MoveForward() {
        this.yPosition += this.currentSpeed;
        this.currentSpeed *= 0.9992;
    }

    /**
     * If the boat has enough stamina, increase the speed of the boat by the boat's
     * acceleration, if not, do nothing.
     */
    public void IncreaseSpeed() {
        if (this.tiredness <= 75) {
            this.currentSpeed = Math.min(this.currentSpeed + this.ACCELERATION, this.MAXSPEED);
        }
    }

    /**
     * Decreases the speed of the boat by 0.015 if the resulting speed is greater
     * than 0.
     */
    public void DecreaseSpeed() {
        /*
         * Very basic deceleration function, acting as water friction. could be updated
         * using functions from
         * https://denysalmaral.com/2019/05/boat-sim-notes-1-water-friction.html to be
         * more realistic.
         */
        this.currentSpeed = Math.max(this.currentSpeed - 0.015f, 0);
    }

    /**
     * Checks each obstacle in the Lane for a collision.
     * 
     * @param backgroundOffset How far up the course the player is.
     * @return Boolean representing if a collision occurs.
     */
    public boolean CheckCollisions(int backgroundOffset) {
        // Iterate through obstacles.
        ArrayList<Obstacle> obstacles = this.lanes[this.laneNo].obstacles;
        ArrayList<Integer> obstaclesToRemove = new ArrayList<>();
        for (Obstacle o : obstacles) {
            if (o.getX() > this.xPosition + threshold && o.getX() < this.xPosition + this.width - threshold) {
                if (o.getY() + backgroundOffset > this.yPosition + threshold
                        && o.getY() + backgroundOffset < this.yPosition + this.height - threshold) {
                    this.ApplyDamage(o.getDamage());
                    obstaclesToRemove.add(obstacles.indexOf(o));
                }
            }
        }
        for (int i : obstaclesToRemove) {
            this.lanes[this.laneNo].RemoveObstacle(obstacles.get(i));
            return true;
        }
        return false;
    }

    /**
     * Decreases the durability of the boat by the obstacle damage divided by the
     * boat's robustness.
     * 
     * @param obstacleDamage Amount of damage an Obstacle inflicts on the boat.
     * @return Boolean representing whether the durability of the boat is below 0.
     */
    public boolean ApplyDamage(int obstacleDamage) {
        this.durability -= obstacleDamage / this.ROBUSTNESS;
        this.currentSpeed *= 0.9;
        return this.durability <= 0;
    }

    /**
     * Checks if the boat is between the left boundary and the right boundary of the Lane.
     * 
     * @return Boolean representing whether the Boat is in the Lane.
     */
    public boolean CheckIfInLane() {
        return this.xPosition + threshold > this.lanes[this.laneNo].getLeftBoundary()
                && this.xPosition + this.width - threshold < this.lanes[this.laneNo].getRightBoundary();
    }

    /**
     * Updates the boat's fastest time.
     * 
     * @param elapsedTime Time it took the boat to finish the current race.
     */
    public void UpdateFastestTime(float elapsedTime) {
        if (this.fastestLegTime > elapsedTime + this.penalties || this.fastestLegTime == 0) {
            this.fastestLegTime = elapsedTime + this.penalties;
        }
    }

    /**
     * Increases the tiredness of the boat by 0.75 if the tiredness is less than
     * 100.
     */
    public void IncreaseTiredness() {
        this.tiredness += this.tiredness >= 100 ? 0 : 0.75f;
    }

    /**
     * Decreases the tiredness of the boat by 1 if the tiredness is greater than 0.
     */
    public void DecreaseTiredness() {
        this.tiredness -= this.tiredness <= 0 ? 0 : 1f;
    }

    /**
     * Keeps track of which frame of the animation the boat's texture is on, and
     * sets the texture accordingly.
     */
    public void AdvanceTextureFrame() {
        this.frameCounter = this.frameCounter == this.textureFrames.length - 1 ? 0 : this.frameCounter + 1;
        this.setTexture(this.textureFrames[this.frameCounter]);
    }

    /**
     * Generates all frames for animating the boat.
     * 
     * @param boatName Boat name, used to get correct asset.
     */
    public void GenerateTextureFrames(char boatName) {
        Texture[] frames = new Texture[4];
        for (int i = 1; i <= frames.length; i++) {
            frames[i - 1] = new Texture(Gdx.files.internal("boat" + boatName + " sprite" + i + ".png"));
        }
        this.setTextureFrames(frames);
    }

    /**
     * Resets necessary stats for the next race.
     */
    public void Reset() {
        this.xPosition = lanes[this.laneNo].getRightBoundary() - (lanes[this.laneNo].getRightBoundary() - lanes[this.laneNo].getLeftBoundary()) / 2.0f - width / 2.0f;
        this.yPosition = 0;
        this.currentSpeed = 0f;
        this.penalties = 0;
        this.durability = 50;
        this.tiredness = 0f;
        this.finished = false;

    }

    /**
     * Resets the boat's fastest leg time.
     */
    public void ResetFastestLegTime() {
        this.fastestLegTime = 0;
    }

    // getters and setters

    /**
     * 
     * @param t Texture to use.
     */
    public void setTexture(Texture t) {
        this.texture = t;
    }

    /**
     * 
     * @param frames Texture frames for animation.
     */
    public void setTextureFrames(Texture[] frames) {
        this.textureFrames = frames;
    }

    /**
     * 
     * @return Float representing fastest race/leg time.
     */
    public float getFastestTime() {
        return this.fastestLegTime;
    }

    /**
     * 
     * @return Int representing x-position of boat.
     */
    public int getX() {
        return Math.round(this.xPosition);
    }

    /**
     * 
     * @return Int representing y-position of boat.
     */
    public int getY() {
        return Math.round(this.yPosition);
    }

    /**
     * 
     * @return Int representing the y coordinate range of the boat (length).
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @return String representing name of the boat.
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return Boolean representing if the boat has finished the current race.
     */
    public boolean finished() {
        return this.finished;
    }

    /**
     * 
     * @param f Boolean representing if the boat has finished the race.
     */
    public void setFinished(boolean f) {
        this.finished = f;
    }

    /**
     * 
     * @return Float representing the current speed of the boat.
     */
    public float getCurrentSpeed() {
        return this.currentSpeed;
    }

    /**
     * 
     * @param finishY Y-position of the finish line.
     * @return Float representing the progress of the boat from 0 to 1.
     */
    public float getProgress(int finishY) {
        return Math.min((this.yPosition) / finishY, 1);
    }

    /**
     * Implicitly sets the stats of the boat, given each attribute.
     * 
     * @param maxspeed        Top speed the boat can reach.
     * @param robustness      How resilient to obstacle damage the boat is.
     * @param acceleration    How much the speed increases each frame.
     * @param maneuverability How easily the boat can move left or right.
     */
    public void setStats(float maxspeed, int robustness, float acceleration, float maneuverability) {
        this.MAXSPEED = maxspeed;
        this.ROBUSTNESS = robustness;
        this.ACCELERATION = acceleration;
        this.MANEUVERABILITY = maneuverability;
    }

    /**
     * Interpolates predetermined stats from a boat label, and sets the stats based
     * on those.
     * 
     * @param boatLabel A character between A-G representing a specific boat.
     */
    public void setStats(char boatLabel) {
        float[] maxspeeds = { 2.5f, 2.0f, 2.5f, 2.5f, 2.0f, 3.5f, 2.5f };
        int[] robustnesses = { 2, 4, 1, 4, 8, 3, 5 };
        float[] accelerations = { 0.09375f, 0.03125f, 0.125f, 0.06254f, 0.046875f, 0.021875f, 0.03125f };
        float[] maneuverabilities = { 0.375f, 1.0f, 0.375f, 0.5f, 0.25f, 0.125f, 0.625f };

        int boatNo = boatLabel - 65;

        this.setStats(maxspeeds[boatNo], robustnesses[boatNo], accelerations[boatNo], maneuverabilities[boatNo]);
    }

    /**
     * 
     * @return Float representing the maneuverability of the boat.
     */
    public float getManeuverability() {
        return this.MANEUVERABILITY;
    }

    /**
     * 
     * @return Float representing the acceleration of the boat.
     */
    public float getAcceleration() {
        return this.ACCELERATION;
    }

    /**
     * 
     * @return Int representing the robustness of the boat.
     */
    public int getRobustness() {
        return this.ROBUSTNESS;
    }

    /**
     * 
     * @return Int representing the durability of the boat.
     */
    public int getDurability() {
        return this.durability;
    }

    /**
     * 
     * @return Int representing the maximum speed of the boat.
     */
    public float getMaxSpeed() {
        return this.MAXSPEED;
    }

    /**
     * 
     * @return Float representing the tiredness of the boat crew.
     */
    public float getTiredness() {
        return this.tiredness;
    }

    /**
     * 
     * @return Float representing the time penalty incurred for the current race.
     */
    public float getPenalty() {
        return this.penalties;
    }

    /**
     * 
     * @param penalty Float to add to the boat's penalty total for the current race.
     */
    public void applyPenalty(float penalty) {
        this.penalties += penalty;
    }

    /**
     * 
     * @param lanes Lanes object for the boat.
     */
    public void setLane(Lane[] lanes, int laneNo) {
        this.lanes = lanes;
        this.laneNo = laneNo;
        this.xPosition = this.lanes[this.laneNo].getRightBoundary() - (this.lanes[this.laneNo].getRightBoundary() - this.lanes[this.laneNo].getLeftBoundary()) / 2.0f - width / 2.0f;
    }

    /**
     * <p>
     * Assigns the selected boat template to the boat.
     * </p>
     * <p>
     * This includes stats and texture.
     * </p>
     *
     * @param boatNo Number of the boat template selected.
     */
    public void ChooseBoat(int boatNo) {
        ChooseBoat((char) (65 + boatNo));
    }

    /**
     * <p>
     * Assigns the selected boat template to the boat.
     * </p>
     * <p>
     * This includes stats and texture.
     * </p>
     *
     * @param boatChar Character of the boat template selected.
     */
    public void ChooseBoat(char boatChar) {
        this.label = boatChar;
        this.setTexture(new Texture(Gdx.files.internal("boat" + label + " sprite1.png")));
        this.GenerateTextureFrames(label);
        this.setStats(label);
    }
}
