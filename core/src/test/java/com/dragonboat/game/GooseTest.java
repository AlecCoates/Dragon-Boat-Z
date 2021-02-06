package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class GooseTest {

    private HashMap<String, Texture> textures;
    private Lane[] lanes;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        textures = new HashMap<>();
        textures.put("Test", new Texture(new Pixmap(1,1, Pixmap.Format.RGB888)));
        lanes = new Lane[]{new Lane(textures, 0,10,10), new Lane(textures, 0,20,10)};
    }

    @Test
    public void testChangeDirection() {
        //Test facing South
        Goose testGoose = new Goose(textures, 0, 0, lanes, 1);
        testGoose.direction = "South";
        testGoose.changeDirection();
        assertTrue("East".equals(testGoose.direction) || "West".equals(testGoose.direction));

        //Test facing East
        testGoose = new Goose(textures, 0, 0, lanes, 1);
        testGoose.direction = "East";
        testGoose.changeDirection();
        assertEquals("South", testGoose.direction);

        //Test facing West
        testGoose = new Goose(textures, 0, 0, lanes, 1);
        testGoose.direction = "West";
        testGoose.changeDirection();
        assertEquals("South", testGoose.direction);
    }

    @Test
    public void testMove() {
        //Test facing South Within Boundary
        Goose testGoose = new Goose(textures, 8, 30, lanes, 1);
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8, testGoose.xPosition, 0.0002f);

        //Test facing East Within Boundary
        testGoose = new Goose(textures, 8, 30, lanes, 1);
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8 + 2, testGoose.xPosition, 0.0002f);

        //Test facing West Within Boundary
        testGoose = new Goose(textures, 8, 30, lanes, 1);
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8 - 2, testGoose.xPosition, 0.0002f);

        //Test facing South Left Boundary
        testGoose = new Goose(textures, -1, 30, lanes, 1);
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1, testGoose.xPosition, 0.0002f);

        //Test facing East Left Boundary
        testGoose = new Goose(textures, -1, 30, lanes, 1);
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1 + 2, testGoose.xPosition, 0.0002f);

        //Test facing West Left Boundary
        testGoose = new Goose(textures, -1, 30, lanes, 1);
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1, testGoose.xPosition, 0.0002f);

        //Test facing South Right Boundary
        testGoose = new Goose(textures, 21, 30, lanes, 1);
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21, testGoose.xPosition, 0.0002f);

        //Test facing East Right Boundary
        testGoose = new Goose(textures, 21, 30, lanes, 1);
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21, testGoose.xPosition, 0.0002f);

        //Test facing West Right Boundary
        testGoose = new Goose(textures, 21, 30, lanes, 1);
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21 - 2, testGoose.xPosition, 0.0002f);
    }

}