package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class LaneTest {

    /*@Test
    public void testSpawnObstacle() {
        //Test Spawn Less Than Limit
        Lane testLane = new Lane(0, 20, 10);
        for (int i = 0; i < 3; i++) {
            testLane.SpawnObstacle(0, 0, "Log", true);
        }
        for (int i = 0; i < 5; i++) {
            testLane.SpawnObstacle(0, 0, "Goose", true);
        }
        assertEquals(8, testLane.obstacles.size(), 0.0002);

        //Test Spawn Exactly Limit
        testLane = new Lane(0, 20, 10);
        for (int i = 0; i < 4; i++) {
            testLane.SpawnObstacle(0, 0, "Log", true);
        }
        for (int i = 0; i < 6; i++) {
            testLane.SpawnObstacle(0, 0, "Goose", true);
        }
        assertEquals(10, testLane.obstacles.size(), 0.0002);

        //Test Spawn More Than Limit
        testLane = new Lane(0, 20, 10);
        for (int i = 0; i < 8; i++) {
            testLane.SpawnObstacle(0, 0, "Log", true);
        }
        for (int i = 0; i < 6; i++) {
            testLane.SpawnObstacle(0, 0, "Goose", true);
        }
        assertEquals(10, testLane.obstacles.size(), 0.0002);
    }

    @Test
    public void testRemoveObstacle() {
        //Test Remove From Empty
        Lane testLane = new Lane(0, 20, 10);
        testLane.obstacles = new ArrayList<>();
        testLane.RemoveObstacle(new Obstacle(5, 10, 30, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888))));
        assertEquals(0, testLane.obstacles.size(), 0.0002);

        //Test Remove From Full
        testLane = new Lane(0, 20, 10);
        Obstacle testObstacle = new Obstacle(5, 10, 30, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)));
        testLane.obstacles.add(testObstacle);
        for (int i = 0; i < 9; i++) {
            testLane.obstacles.add(new Obstacle(5, 20, 40, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888))));
        }
        testLane.RemoveObstacle(testObstacle);
        assertEquals(9, testLane.obstacles.size(), 0.0002);

        //Test Remove From Above Full
        testLane = new Lane(0, 20, 10);
        testObstacle = new Obstacle(5, 10, 30, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)));
        testLane.obstacles.add(testObstacle);
        for (int i = 0; i < 12; i++) {
            testLane.obstacles.add(new Obstacle(5, 20, 40, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888))));
        }
        testLane.RemoveObstacle(testObstacle);
        assertEquals(12, testLane.obstacles.size(), 0.0002);
    }*/

}