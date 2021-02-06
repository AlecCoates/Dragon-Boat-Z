package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class GooseTest {

    /*@Test
    public void testChangeDirection() {
        //Test facing South
        Goose testGoose = new Goose(0, 0, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "South";
        testGoose.changeDirection();
        assertTrue("East".equals(testGoose.direction) || "West".equals(testGoose.direction));

        //Test facing East
        testGoose = new Goose(0, 0, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "East";
        testGoose.changeDirection();
        assertEquals("South", testGoose.direction);

        //Test facing West
        testGoose = new Goose(0, 0, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "West";
        testGoose.changeDirection();
        assertEquals("South", testGoose.direction);
    }

    @Test
    public void testMove() {
        //Test facing South Within Boundary
        Goose testGoose = new Goose(8, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8, testGoose.xPosition, 0.0002f);

        //Test facing East Within Boundary
        testGoose = new Goose(8, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8 + 2, testGoose.xPosition, 0.0002f);

        //Test facing West Within Boundary
        testGoose = new Goose(8, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(8 - 2, testGoose.xPosition, 0.0002f);

        //Test facing South Left Boundary
        testGoose = new Goose(-1, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1, testGoose.xPosition, 0.0002f);

        //Test facing East Left Boundary
        testGoose = new Goose(-1, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1 + 2, testGoose.xPosition, 0.0002f);

        //Test facing West Left Boundary
        testGoose = new Goose(-1, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(-1, testGoose.xPosition, 0.0002f);

        //Test facing South Right Boundary
        testGoose = new Goose(21, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "South";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21, testGoose.xPosition, 0.0002f);

        //Test facing East Right Boundary
        testGoose = new Goose(21, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "East";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21, testGoose.xPosition, 0.0002f);

        //Test facing West Right Boundary
        testGoose = new Goose(21, 30, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)), new Lane(0, 20, 10));
        testGoose.direction = "West";
        testGoose.Move(2f, 20);
        assertEquals(30 - 2, testGoose.yPosition, 0.0002f);
        assertEquals(21 - 2, testGoose.xPosition, 0.0002f);
    }*/

}