package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class BoatTest {

    @Test
    public void steerLeft() {
        Lane[] lanes = new Lane[]{new Lane(10,10,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth + 27;
        testBoat.currentSpeed = 3;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth + 27 - 7 * 3, testBoat.xPosition, 0.0002);
        assertEquals(3 * 0.985, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth;
        testBoat.currentSpeed = 5;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth, testBoat.xPosition, 0.0002);
        assertEquals(5, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth - 10;
        testBoat.currentSpeed = 7;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth, testBoat.xPosition, 0.0002);
        assertEquals(7, testBoat.getCurrentSpeed(), 0.0002);
    }

    @Test
    public void steerRight() {

        Lane[] lanes = new Lane[]{new Lane(10,10,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27;
        testBoat.currentSpeed = 3;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27 + 7 * 3, testBoat.xPosition, 0.0002);
        assertEquals(3 * 0.985, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width;
        testBoat.currentSpeed = 5;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width, testBoat.xPosition, 0.0002);
        assertEquals(5, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width + 10;
        testBoat.currentSpeed = 7;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width, testBoat.xPosition, 0.0002);
        assertEquals(7, testBoat.getCurrentSpeed(), 0.0002);
    }

    @Test
    public void moveForward() {

        Lane[] lanes = new Lane[]{new Lane(10,10,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27;
        testBoat.currentSpeed = 3;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27 + 7 * 3, testBoat.xPosition, 0.0002);
        assertEquals(3 * 0.985, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width;
        testBoat.currentSpeed = 5;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width, testBoat.xPosition, 0.0002);
        assertEquals(5, testBoat.getCurrentSpeed(), 0.0002);

        
        lanes = new Lane[]{new Lane(10,10,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width + 10;
        testBoat.currentSpeed = 7;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width, testBoat.xPosition, 0.0002);
        assertEquals(7, testBoat.getCurrentSpeed(), 0.0002);
    }

}