package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ProgressBarTest {

    private HashMap<String, Texture> textures;
    private Lane[] lanes;
    private Lane[] dynamicLanes;

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
    public void testStartTimer() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        testProgressBar.timeSeconds = 7f;
        testProgressBar.playerTime = 6f;
        testProgressBar.StartTimer();
        assertEquals(0f, testProgressBar.timeSeconds, 0.0002);
        assertEquals(0f, testProgressBar.playerTime, 0.0002);
    }

    @Test
    public void testIncrementTimer() {
        //Test Player Not Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        testProgressBar.timeSeconds = 12f;
        testProgressBar.playerTime = 12f;
        testProgressBar.playerBoat.finished = false;
        testProgressBar.IncrementTimer(2.5f);
        assertEquals(14.5f, testProgressBar.timeSeconds, 0.0002);
        assertEquals(14.5f, testProgressBar.playerTime, 0.0002);

        //Test Player Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        testPlayer = new Player(0,5,10, lanes, 0, "A");
        testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        testProgressBar.timeSeconds = 12f;
        testProgressBar.playerTime = 12f;
        testProgressBar.playerBoat.finished = true;
        testProgressBar.IncrementTimer(2.5f);
        assertEquals(14.5f, testProgressBar.timeSeconds, 0.0002);
        assertEquals(12f, testProgressBar.playerTime, 0.0002);
    }

    @Test
    public void testAllFinished() {
        //Test None Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0, 5, 10, lanes, 0, "A");
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0, 5, 10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test Player Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        testPlayer = new Player(80, 5, 10, lanes, 0, "A");
        testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0, 5, 10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test Opponents Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        testPlayer = new Player(0, 5, 10, lanes, 0, "A");
        testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(80, 5, 10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test All Finished
        lanes = new Lane[]{new Lane(0,20,10)};
        testPlayer = new Player(80, 5, 10, lanes, 0, "A");
        testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(80, 5, 10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertTrue(testProgressBar.allFinished(65));
    }

    @Test
    public void testGetProgress() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0, 5, 10, lanes, 0, "A");
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(i + 1, 5, 10, dynamicLanes, i, Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertArrayEquals(new float[]{0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f, 1f}, testProgressBar.getProgress(5), 0.0002f);
    }

}