package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class BoatUnitTest {

    private HashMap<String, Texture> textures;
    private Lane[] lanes;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        textures = new HashMap<>();
        textures.put("Goose", new Texture(new Pixmap(5, 15, Pixmap.Format.RGB888)));
        textures.put("Log", new Texture(new Pixmap(15, 109, Pixmap.Format.RGB888)));
        textures.put("Test", new Texture(new Pixmap(15, 34, Pixmap.Format.RGB888)));
    }

    @Test
    public void testSteerLeft() {
        assertEquals(1,2);
        //Test steering right-side of left bank
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth + 27;
        testBoat.currentSpeed = 3;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth + 27 - 7 * 3, testBoat.xPosition, 0.0002);
        assertEquals(3 * 0.985, testBoat.currentSpeed, 0.0002);

        //Test steering on edge of left bank
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth;
        testBoat.currentSpeed = 5;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth, testBoat.xPosition, 0.0002);
        assertEquals(5, testBoat.currentSpeed, 0.0002);

        //Test steering left-side of left bank
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Boat.bankWidth - 10;
        testBoat.currentSpeed = 7;
        testBoat.SteerLeft();
        assertEquals(Boat.bankWidth, testBoat.xPosition, 0.0002);
        assertEquals(7, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testSteerRight() {
        //Test steering left-side of right bank
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27;
        testBoat.currentSpeed = 3;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width - 27 + 7 * 3, testBoat.xPosition, 0.0002);
        assertEquals(3 * 0.985, testBoat.currentSpeed, 0.0002);

        //Test steering on edge of right bank
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width;
        testBoat.currentSpeed = 5;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - testBoat.width, testBoat.xPosition, 0.0002);
        assertEquals(5, testBoat.currentSpeed, 0.0002);

        //Test steering right-side of right bank
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.xPosition = Gdx.graphics.getWidth() - Boat.bankWidth - 5 + 10;
        testBoat.currentSpeed = 7;
        testBoat.SteerRight();
        assertEquals(Gdx.graphics.getWidth() - Boat.bankWidth - 5, testBoat.xPosition, 0.0002);
        assertEquals(7, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testMoveForward() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 3;
        testBoat.MoveForward();
        assertEquals(15 + 3, testBoat.yPosition, 0.0002);
        assertEquals(3 * 0.9992, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testIncreaseSpeed() {
        //Test tired
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 3;
        testBoat.tiredness = 80;
        testBoat.IncreaseSpeed();
        assertEquals(3, testBoat.currentSpeed, 0.0002);

        //Test not-tired below speed limit
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 3;
        testBoat.tiredness = 20;
        testBoat.IncreaseSpeed();
        assertEquals(3 + 10, testBoat.currentSpeed, 0.0002);

        //Test not-tired near speed limit
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 95;
        testBoat.tiredness = 20;
        testBoat.IncreaseSpeed();
        assertEquals(100, testBoat.currentSpeed, 0.0002);

        //Test not-tired above speed limit
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 120;
        testBoat.tiredness = 20;
        testBoat.IncreaseSpeed();
        assertEquals(100, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testDecreaseSpeed() {
        //Test above 0
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 3;
        testBoat.DecreaseSpeed();
        assertEquals(3 - 0.015f, testBoat.currentSpeed, 0.0002);

        //Test near 0
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = 0.0001f;
        testBoat.DecreaseSpeed();
        assertEquals(0, testBoat.currentSpeed, 0.00001);

        //Test below 0
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100,5,10,7);
        testBoat.currentSpeed = -3;
        testBoat.DecreaseSpeed();
        assertEquals(0, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testCheckCollisions() {
        Boat testBoat;
        Obstacle testObstacle1, testObstacle2;

        //Test 1 Obstacle Non-Collision
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 33;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 24, 16, 55, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        assertFalse(testBoat.CheckCollisions(1));
        assertEquals(1, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(33, testBoat.durability);
        assertEquals(13, testBoat.currentSpeed, 0.0002f);

        //Test 1 Obstacle Collision
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 33;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 24, 8, 33, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        assertTrue(testBoat.CheckCollisions(1));
        assertEquals(0, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(33 - 24 / 9, testBoat.durability);
        assertEquals(13 * 0.9f, testBoat.currentSpeed, 0.0002f);

        //Test 1 Obstacle Collision (Death)
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 10;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 24, 8, 33, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        assertTrue(testBoat.CheckCollisions(1));
        assertEquals(0, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(10 - 24 / 9, testBoat.durability);
        assertEquals(13 * 0.9f, testBoat.currentSpeed, 0.0002f);

        //Test 2 Obstacle Non-Collision
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 33;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 3, 16, 55, 5, 10, "Test");
        testObstacle2 = new Obstacle(textures, 4, -16, -55, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle2);
        assertFalse(testBoat.CheckCollisions(1));
        assertEquals(2, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(33, testBoat.durability);
        assertEquals(13, testBoat.currentSpeed, 0.0002f);

        //Test 1 Obstacle Collision, 1 Obstacle Non-Collision
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 33;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 3, 16, 55, 5, 10, "Test");
        testObstacle2 = new Obstacle(textures, 4, 8, 33, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle2);
        assertTrue(testBoat.CheckCollisions(1));
        assertEquals(1, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(33 - 4 / 9, testBoat.durability);
        assertEquals(13 * 0.9f, testBoat.currentSpeed, 0.0002f);

        //Test 2 Obstacle Collision
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(30, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 5;
        testBoat.durability = 33;
        testBoat.ROBUSTNESS = 9;
        testBoat.currentSpeed = 13;
        testBoat.threshold = 1;
        testObstacle1 = new Obstacle(textures, 3, 3, 27, 5, 10, "Test");
        testObstacle2 = new Obstacle(textures, 4, 8, 33, 5, 10, "Test");
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle1);
        testBoat.lanes[testBoat.laneNo].obstacles.add(testObstacle2);
        assertTrue(testBoat.CheckCollisions(1));
        assertEquals(0, testBoat.lanes[testBoat.laneNo].obstacles.size());
        assertEquals(33 - (3 / 9) - (4 / 9), testBoat.durability);
        assertEquals(13 * 0.9f * 0.9f, testBoat.currentSpeed, 0.0002f);
    }

    @Test
    public void testApplyDamage() {
        //Test above 0
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.durability = 6;
        testBoat.currentSpeed = 3;
        assertFalse(testBoat.ApplyDamage(5));
        assertEquals(6 - 5 / 5, testBoat.durability);
        assertEquals(3 * 0.9f, testBoat.currentSpeed, 0.0002);

        //Test reaching 0
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.durability = 1;
        testBoat.currentSpeed = 3;
        assertTrue(testBoat.ApplyDamage(10));
        assertEquals(1 - 10 / 5, testBoat.durability);
        assertEquals(3 * 0.9f, testBoat.currentSpeed, 0.0002);
    }

    @Test
    public void testCheckIfInLane() {
        //Test left of lane
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.threshold = 1;
        testBoat.xPosition = -10;
        assertFalse(testBoat.CheckIfInLane());

        //Test on left edge
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.threshold = 1;
        testBoat.xPosition = -2;
        assertFalse(testBoat.CheckIfInLane());

        //Test in lane
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.threshold = 1;
        testBoat.xPosition = 6;
        assertTrue(testBoat.CheckIfInLane());

        //Test on right edge
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.threshold = 1;
        testBoat.xPosition = 18;
        assertFalse(testBoat.CheckIfInLane());

        //Test right of lane
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.threshold = 1;
        testBoat.xPosition = 25;
        assertFalse(testBoat.CheckIfInLane());
    }

    @Test
    public void testUpdateFastestTime() {
        //Test slower than fastest time
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.fastestLegTime = 20;
        testBoat.penalties = 0;
        testBoat.UpdateFastestTime(25);
        assertEquals(20, testBoat.fastestLegTime, 0.0002);

        //Test slower than fastest time with penalties
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.fastestLegTime = 20;
        testBoat.penalties = 5;
        testBoat.UpdateFastestTime(18);
        assertEquals(20, testBoat.fastestLegTime, 0.0002);

        //Test faster than fastest time
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.fastestLegTime = 20;
        testBoat.penalties = 0;
        testBoat.UpdateFastestTime(10);
        assertEquals(10, testBoat.fastestLegTime, 0.0002);

        //Test faster than fastest time with penalties
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.fastestLegTime = 20;
        testBoat.penalties = 5;
        testBoat.UpdateFastestTime(10);
        assertEquals(15, testBoat.fastestLegTime, 0.0002);
    }

    @Test
    public void testIncreaseTiredness() {
        //Test below max tiredness
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = 20;
        testBoat.IncreaseTiredness();
        assertEquals(20.75f, testBoat.tiredness, 0.0002);

        //Test at max tiredness
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = 100;
        testBoat.IncreaseTiredness();
        assertEquals(100, testBoat.tiredness, 0.0002);

        //Test above max tiredness
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = 105;
        testBoat.IncreaseTiredness();
        assertEquals(105, testBoat.tiredness, 0.0002);
    }

    @Test
    public void testDecreaseTiredness() {
        //Test above 0
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = 20;
        testBoat.DecreaseTiredness();
        assertEquals(19, testBoat.tiredness, 0.0002);

        //Test at 0
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = 0;
        testBoat.DecreaseTiredness();
        assertEquals(0, testBoat.tiredness, 0.0002);

        //Test below 0
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.setStats(100, 5, 10, 7);
        testBoat.tiredness = -1;
        testBoat.DecreaseTiredness();
        assertEquals(-1, testBoat.tiredness, 0.0002);
    }

    @Test
    public void testReset() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.xPosition = 10000;
        testBoat.currentSpeed = 10000;
        testBoat.penalties = 10000f;
        testBoat.durability = 1;
        testBoat.tiredness = 95;
        testBoat.finished = true;
        testBoat.Reset();
        assertEquals(20 - 20 / 2.0f - 5 / 2.0f, testBoat.xPosition, 0.0002);
        assertEquals(0f, testBoat.yPosition, 0.0002);
        assertEquals(0f, testBoat.currentSpeed, 0.0002);
        assertEquals(0f, testBoat.penalties, 0.0002);
        assertEquals(50, testBoat.durability);
        assertEquals(0f, testBoat.tiredness, 0.0002);
        assertFalse(testBoat.finished);
    }

    @Test
    public void testResetFastestLegTime() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        testBoat.fastestLegTime = 200f;
        testBoat.ResetFastestLegTime();
        assertEquals(0f, testBoat.fastestLegTime, 0.0002);
    }

    @Test
    public void testGetProgress() {
        //Test Before Finish
        lanes = new Lane[]{new Lane(0,20,10)};
        Boat testBoat = new Boat(14, 5, 15, lanes, 0, "A");
        assertEquals(14f / 15f, testBoat.getProgress(15), 0.0002);

        //Test On Finish
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(15, 5, 15, lanes, 0, "A");
        assertEquals(1f, testBoat.getProgress(15), 0.0002);

        //Test After Finish
        lanes = new Lane[]{new Lane(0,20,10)};
        testBoat = new Boat(16, 5, 15, lanes, 0, "A");
        assertEquals(1f, testBoat.getProgress(15), 0.0002);
    }

}