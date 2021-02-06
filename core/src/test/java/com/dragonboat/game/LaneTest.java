package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class LaneTest {

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
        lanes = new Lane[]{new Lane(textures, 0,10,10), new Lane(textures, 0,20,10)};
    }

    @Test
    public void testSpawnObstacle() {
        //Test Spawn Less Than Limit
        Lane testLane = lanes[1];
        for (int i = 0; i < 3; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Log");
        }
        for (int i = 0; i < 5; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Goose");
        }
        assertEquals(8, testLane.obstacles.size(), 0.0002);

        //Test Spawn Exactly Limit
        testLane = lanes[1];
        for (int i = 0; i < 4; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Log");
        }
        for (int i = 0; i < 6; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Goose");
        }
        assertEquals(10, testLane.obstacles.size(), 0.0002);

        //Test Spawn More Than Limit
        testLane = lanes[1];
        for (int i = 0; i < 8; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Log");
        }
        for (int i = 0; i < 6; i++) {
            testLane.SpawnObstacle(textures, 0, 0, "Goose");
        }
        assertEquals(10, testLane.obstacles.size(), 0.0002);
    }

    @Test
    public void testRemoveObstacle() {
        //Test Remove From Empty
        Lane testLane = lanes[1];
        testLane.obstacles = new ArrayList<>();
        testLane.RemoveObstacle(new Obstacle(textures, 5, 10, 30, 5, 8, "Test"));
        assertEquals(0, testLane.obstacles.size(), 0.0002);

        //Test Remove From Full
        testLane = lanes[1];
        Obstacle testObstacle = new Obstacle(textures, 5, 10, 30, 5, 8, "Test");
        testLane.obstacles.add(testObstacle);
        for (int i = 0; i < 9; i++) {
            testLane.obstacles.add(new Obstacle(textures, 5, 20, 40, 5, 8, "Test"));
        }
        testLane.RemoveObstacle(testObstacle);
        assertEquals(9, testLane.obstacles.size(), 0.0002);

        //Test Remove From Above Full
        testLane = lanes[1];
        testObstacle = new Obstacle(textures, 5, 10, 30, 5, 8, "Test");
        testLane.obstacles.add(testObstacle);
        for (int i = 0; i < 12; i++) {
            testLane.obstacles.add(new Obstacle(textures, 5, 20, 40, 5, 8, "Test"));
        }
        testLane.RemoveObstacle(testObstacle);
        assertEquals(12, testLane.obstacles.size(), 0.0002);
    }

}