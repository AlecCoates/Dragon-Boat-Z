package com.dragonboat.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a opponent boat with AI.
 */
public class Opponent extends Boat {

    public String steering = "None";
    private ArrayList<Obstacle> sortedIncomingObstacles;

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
    public Opponent(int yPosition, int width, int height, Lane[] lanes, int laneNo, String name) {
        super(yPosition, width, height, lanes, laneNo, name);
        sortedIncomingObstacles = new ArrayList<>();
    }

    /**
     * <p>
     * Controls the AI behaviour of the boat.
     * </p>
     * <p>
     * Changes the movement path of the boat.
     * </p>
     * <p>
     * AI new path selection:
     * </p>
     * <p>
     * 1) If not in lane, go back to lane.
     * </p>
     * <p>
     * 2) If obstacle ahead, avoid the obstacle. If dead ahead, slow down.
     * </p>
     * <p>
     * 3) If nothing, speed up.
     * </p>
     * 
     * @param backgroundOffset Background offset
     */
    public void ai(int backgroundOffset) {

        int leftSide = Math.round(xPosition);
        int rightSide = Math.round(xPosition + width);

        int arbitrary = 50;
        int fov = Math.round(arbitrary * this.getManeuverability()); // Determine a good field of view for the Opponents
                                                                     // to start reacting to incoming obstacles.
        int visionDistance = Math.round(yPosition + height) + fov;

        ArrayList<Obstacle> allIncomingObstacles = this.lanes[this.laneNo].obstacles;

        boolean noNewPath = true; // Set to false whenever the Opponent has decided on a new path.

        if (this.steering.equals("Left")) {
            this.SteerLeft();
            this.steering = "None";
            noNewPath = false;
        } else if (this.steering.equals("Right")) {
            this.SteerRight();
            this.steering = "None";
            noNewPath = false;
        }

        /*
         * 1) If not in lane, go back to lane.
         */
        if (!this.CheckIfInLane() || !noNewPath) {
            // Commence route back into lane.
            if (leftSide - this.lanes[this.laneNo].getLeftBoundary() <= 0) {
                // Will only be negative if the boat is further left than the left boundary of the lane.
                this.SteerRight();
                this.steering = "Right";
            } else if (rightSide - this.lanes[this.laneNo].getRightBoundary() >= 0) {
                // Will only be positive if the boat is further right than the right boundary of the lane.
                this.SteerLeft();
                this.steering = "Left";
            }
            noNewPath = false;
        }

        /*
         * 2) If obstacle ahead, avoid the obstacle. If dead ahead, slow down.
         */
        if (noNewPath) { // If still no new path, if there is one, skip all this code for speed's sake.

            // Insertion sort Obstacles in incomingObstacles from lowest to highest
            // y-position (proximity to the Boat, even).
            for (Obstacle obs : allIncomingObstacles) {
                if (obs.getY() + obs.height + backgroundOffset > this.getY()) {
                    if (sortedIncomingObstacles.size() == 0) {
                        sortedIncomingObstacles.add(obs);
                    } else {
                        boolean inserted = false;
                        int index = 0;
                        while (!inserted) {
                            Obstacle thisObstacle;
                            if (index < sortedIncomingObstacles.size()) {
                                thisObstacle = sortedIncomingObstacles.get(index);
                                // If not reached end of sortedIncomingObstacles.
                                if (thisObstacle.getY() > obs.getY()) {
                                    // Keep looking for place to insert.
                                    index += 1;
                                } else {
                                    // Found place to insert!
                                    sortedIncomingObstacles.add(index, obs);
                                    inserted = true;
                                }
                            } else {
                                // End of ArrayList reached.
                                sortedIncomingObstacles.add(obs);
                                inserted = true;
                            }
                        }
                    }
                }
            }

            for (Obstacle obs : sortedIncomingObstacles) {

                if (obs.getY() + backgroundOffset <= visionDistance && obs.getY() + backgroundOffset > this.yPosition) {
                    // The obstacle is visible from the boat.
                    if (obs.getX() + obs.width < leftSide) {
                        // The obstacle is far left of the boat.

                        if ((obs.getClass() == Goose.class) && ((Goose) obs).direction.equals("East")) {
                            this.SteerRight();
                            this.steering = "Right";
                        }

                    } else if (obs.getX() > rightSide) {
                        /*
                         * The obstacle is far right of the boat.
                         * 
                         * Do nothing? A moving Obstacle (Goose) might currently be far right but
                         * heading left on a collision course. Maybe check type of Obstacle then; if
                         * it's a Goose, check the direction. If it's headed your way, do something
                         * about that.
                         */

                        if ((obs.getClass() == Goose.class) && ((Goose) obs).direction.equals("West")) {
                            this.SteerLeft();
                            this.steering = "Left";
                        }
                    } else {
                        /*
                         * Part or all of the obstacle is on a collision course with the boat. Move in
                         * the appropriate direction. Depends on whether the Obstacle is to the left or
                         * right, on whether you are close to the Lane boundary, etc. If the obstacle is
                         * dead ahead, slow down also. If there is a goose ahead and it is moving
                         * horizontally, steer around it and opposite it.
                         */

                        if ((obs.getClass() == Goose.class) && ((Goose) obs).direction.equals("East")) {
                            this.SteerLeft();
                            this.steering = "Left";
                        }

                        else if ((obs.getClass() == Goose.class) && ((Goose) obs).direction.equals("West")) {
                            this.SteerRight();
                            this.steering = "Right";
                        }

                        else {
                            // For objects moving vertically, calculate whether steering left of it or right
                            // of it is the easiest course.
                            int leftMargin = Math.round(leftSide - obs.getX());
                            int rightMargin = Math.round(obs.getX()) + obs.width - rightSide;

                            // Check to slow down.
                            if ((leftMargin <= 0 && rightMargin <= 0) || (leftMargin >= 0 && rightMargin >= 0)) {
                                // Obstacle is dead ahead. Slow down.
                                this.DecreaseSpeed();
                            }

                            // Check to go left or right.
                            if (leftMargin <= rightMargin) {
                                // If easier to dodge to the left of the obstacle, steer to the left of the
                                // obstacle.
                                this.SteerLeft();
                                this.steering = "Left";
                            } else {
                                // If not, steer to the right of the obstacle.
                                this.SteerRight();
                                this.steering = "Right";
                            }
                        }

                        noNewPath = false;
                        break;
                    }
                }
            }
        }

        /*
         * 2.5) Move to middle.
         */
        if (noNewPath) {
            int middle = lanes[this.laneNo].getRightBoundary() - (lanes[this.laneNo].getRightBoundary() - lanes[this.laneNo].getLeftBoundary()) / 2
                    - this.width / 2;
            if (Math.abs(leftSide - middle) < 0.1) {
                steering = "None";
            } else if (leftSide < middle) {
                this.SteerRight();
                steering = "Right";
            } else {
                this.SteerLeft();
                steering = "Left";
            }
        }

        // 3) If nothing, speed up.
        if (this.getTiredness() < 70)
            this.IncreaseSpeed();
    }

    /**
     * <p>
     * Assigns a random boat template to the boat.
     * </p>
     * <p>
     * This includes stats and texture.
     * </p>
     * 
     * @param possibleBoats List of remaining boat templates that haven't been
     *                      assigned yet.
     * @return Int representing the index of the boat template that was assigned.
     */
    public int SetRandomBoat(ArrayList<Integer> possibleBoats) {
        Random rnd = new Random();
        int randIndex = rnd.nextInt(possibleBoats.size());
        this.ChooseBoat(possibleBoats.get(randIndex));
        return randIndex;
    }
}
