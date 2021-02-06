package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ObstacleTest {

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
    public void testMove() {
        Obstacle testObstacle = new Obstacle(textures,5, 10, 30, 5, 8, "Test");
        testObstacle.Move(10, 20);
        assertEquals(30 - 10, testObstacle.yPosition, 0.0002);
    }

}