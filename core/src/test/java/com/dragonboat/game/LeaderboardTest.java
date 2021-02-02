package com.dragonboat.game;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LeaderboardTest {

    @Test
    public void testStartTimer() {
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        testPlayer.fastestLegTime = 26;
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
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
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new String[]{"G: 1.79", "F: 2.79", "E: 3.79", "D: 4.79", "C: 5.79", "B: 6.79", "A: 26.5"}, testLeaderboard.getTimes(7));
    }

    @Test
    public void testGetFinalists() {
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new Boat[]{testOpponents[5], testOpponents[4], testOpponents[3], testOpponents[2], testOpponents[1], testOpponents[0], testPlayer}, testLeaderboard.getFinalists(7));
    }

    @Test
    public void testGetPodium() {
        Player testPlayer = new Player(0,5,10, new Lane(0,20, 10), "A");
        testPlayer.fastestLegTime = 26.5f;
        Opponent[] testOpponents = new Opponent[6];
        for (int i = 0; i < testOpponents.length; i++) {
            testOpponents[i] = new Opponent(0,5,10, new Lane(i * 20,(i+1) * 20, 10), Character.toString((char) (66 + i)));
            testOpponents[i].fastestLegTime = testOpponents.length - i + 0.79222f;
        }
        Leaderboard testLeaderboard = new Leaderboard(testPlayer, testOpponents, true);
        assertArrayEquals(new Boat[]{testOpponents[5], testOpponents[4], testOpponents[3]}, testLeaderboard.getPodium());
    }

}