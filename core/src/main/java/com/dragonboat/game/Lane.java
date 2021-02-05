package com.dragonboat.game;

import java.util.ArrayList;
import java.util.Dictionary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

/**
 * Represents a lane on the course.
 */
public class Lane {
    public int LEFTBOUNDARY, RIGHTBOUNDARY;
    protected ArrayList<Obstacle> obstacles;
    private final int obstacleLimit;
    protected Lane[] lanes;
    protected int laneNo;

    static class LaneSpriteDescriptor {
        public int LEFTBOUNDARY, RIGHTBOUNDARY;
        public int obstacleLimit;
        public ArrayList<Obstacle.ObstacleSpriteDescriptor> obstacles;
        public int laneNo;

        //Used for the return from json file
        public LaneSpriteDescriptor (){}

        LaneSpriteDescriptor (Lane oldLane){
            LEFTBOUNDARY = oldLane.getLeftBoundary();
            RIGHTBOUNDARY = oldLane.getRightBoundary();
            obstacleLimit = oldLane.obstacleLimit;
            obstacles = new ArrayList<>();
            laneNo = oldLane.laneNo;
            for(Obstacle obstacle: oldLane.obstacles){
                if(obstacle.getName() == "Goose") {
                    System.out.println("Goose");
                    Goose goose = (Goose) obstacle;
                    obstacles.add(new Goose.GooseSpriteDescriptor(goose));
                }else{
                    System.out.println("Log");
                    Log log = (Log) obstacle;
                    obstacles.add(new Log.LogSpriteDescriptor(log));
                }
            }
        }
    }

    /**
     * Creates a lane instance.
     * 
     * @param leftBoundary  X-position for the left boundary of the lane.
     * @param rightBoundary X-position for the right boundary of the lane.
     */
    public Lane(int leftBoundary, int rightBoundary, Lane[] lanes, int laneNo) {
        this.LEFTBOUNDARY = leftBoundary;
        this.RIGHTBOUNDARY = rightBoundary;
        this.obstacleLimit = 10;
        this.lanes = lanes;
        this.laneNo = laneNo;

        obstacles = new ArrayList<>();
    }

    /**
     * Creates a lane instance.
     * 
     * @param leftBoundary  X-position for the left boundary of the lane.
     * @param rightBoundary X-position for the right boundary of the lane.
     * @param obstacleLimit Limit for the number of obstacles in the lane.
     */
    public Lane(int leftBoundary, int rightBoundary, int obstacleLimit) {
        this.LEFTBOUNDARY = leftBoundary;
        this.RIGHTBOUNDARY = rightBoundary;
        this.obstacleLimit = obstacleLimit;

        obstacles = new ArrayList<>();
    }

    /**
     * <p>
     * Spawns obstacle in the lane.
     * </p>
     * <p>
     * Spawns specified obstacle in the lane. Checks that the obstacle limit hasn't
     * been reached, if not checks the obstacle type for Goose or Log and
     * instantiates it as the corresponding obstacle, with the correct texture. Then
     * adds it to the Lane's obstacle list.
     * </p>
     * 
     * @param x            X-position for the obstacle spawn location.
     * @param y            Y-position for the obstacle spawn location.
     * @param obstacleType Obstacle type.
     */
    public void SpawnObstacle(int x, int y, String obstacleType) {
        if (this.obstacles.size() <= this.obstacleLimit) {
            if (obstacleType.equals("Goose")) {
                Goose goose = new Goose(x, y, new Texture(Gdx.files.internal("gooseSouthsprite.png")), this.lanes, this.laneNo);
                this.obstacles.add(goose);

            } else if (obstacleType.equals("Log")) {
                Log log = new Log(x, y, new Texture(Gdx.files.internal("logBig sprite.png")));
                this.obstacles.add(log);

            }
        } else
            System.out.println("Obstacle limit reached.");
    }

    /**
     * <p>
     * Removes obstacle from obstacle list.
     * </p>
     * <p>
     * Obstacle should be removed upon collision with boat or leaving the course.
     * area.
     * </p>
     * 
     * @param obstacle Obstacle to be removed.
     */
    public void RemoveObstacle(Obstacle obstacle) {
        this.obstacles.remove(obstacle);
    }

    // getters and setters

    /**
     * 
     * @return Int representing the x-position of the lane's left boundary.
     */
    public int getLeftBoundary() {
        return this.LEFTBOUNDARY;
    }

    /**
     * 
     * @return Int representing the x-position of the lane's right boundary.
     */
    public int getRightBoundary() {
        return this.RIGHTBOUNDARY;
    }
}
