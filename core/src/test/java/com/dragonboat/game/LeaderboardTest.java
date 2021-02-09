package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LeaderboardTest {

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
    public void testUpdateOrder() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        testPlayer.fastestLegTime = 26;
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        testLeaderboard.UpdateOrder();
        float[] newTimes = new float[7];
        for (int i = 0; i < testLeaderboard.sortedBoats.length; i++) {
            newTimes[i] = testLeaderboard.sortedBoats[i].fastestLegTime;
        }
        assertArrayEquals(new float[]{1,2,3,4,5,6,26}, newTimes, 0.0002f);
    }

    @Test
    public void testGetTimes() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new String[]{"G: 1.79", "F: 2.79", "E: 3.79", "D: 4.79", "C: 5.79", "B: 6.79", "A: 26.5"}, testLeaderboard.getTimes(7));
    }

    @Test
    public void testGetFinalists() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new Boat[]{testOpponents[5], testOpponents[4], testOpponents[3], testOpponents[2], testOpponents[1], testOpponents[0], testPlayer}, testLeaderboard.getFinalists(7));
    }

    @Test
    public void testGetPodium() {
        lanes = new Lane[]{new Lane(0,20,10)};
        Player testPlayer = new Player(0,5,10, lanes, 0, "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        dynamicLanes = new Lane[testOpponents.length];
        for (int i = 0; i < testOpponents.length; i++) {
            dynamicLanes[i] = new Lane(i * 20,(i+1) * 20, 10);
            testOpponents[i] = new Opponent(0,5,10, dynamicLanes, i, Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new Boat[]{testOpponents[5], testOpponents[4], testOpponents[3]}, testLeaderboard.getPodium());
    }

}