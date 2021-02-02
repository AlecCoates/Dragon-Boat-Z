package com.dragonboat.game;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ProgressBarTest {

    @Test
    public void testStartTimer() {
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
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
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        testProgressBar.timeSeconds = 12f;
        testProgressBar.playerTime = 12f;
        testProgressBar.playerBoat.finished = false;
        testProgressBar.IncrementTimer(2.5f);
        assertEquals(14.5f, testProgressBar.timeSeconds, 0.0002);
        assertEquals(14.5f, testProgressBar.playerTime, 0.0002);

        //Test Player Finished
        testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
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
        Player testPlayer = new Player(0, 5, 10, new Lane(0, 20, 10), "A");
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0, 5, 10, new Lane(i * 20, (i + 1) * 20, 10), Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test Player Finished
        testPlayer = new Player(80, 5, 10, new Lane(0, 20, 10), "A");
        testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0, 5, 10, new Lane(i * 20, (i + 1) * 20, 10), Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test Opponents Finished
        testPlayer = new Player(0, 5, 10, new Lane(0, 20, 10), "A");
        testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(80, 5, 10, new Lane(i * 20, (i + 1) * 20, 10), Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertFalse(testProgressBar.allFinished(65));

        //Test All Finished
        testPlayer = new Player(80, 5, 10, new Lane(0, 20, 10), "A");
        testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(80, 5, 10, new Lane(i * 20, (i + 1) * 20, 10), Character.toString((char) (66 + i)));
        }
        testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertTrue(testProgressBar.allFinished(65));
    }

    @Test
    public void testGetProgress() {
        Player testPlayer = new Player(0, 5, 10, new Lane(0, 20, 10), "A");
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(i + 1, 5, 10, new Lane(i * 20, (i + 1) * 20, 10), Character.toString((char) (66 + i)));
        }
        ProgressBar testProgressBar = new ProgressBar(testPlayer, testOpponents, true);
        assertArrayEquals(new float[]{0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f, 1f}, testProgressBar.getProgress(5), 0.0002f);
    }

}