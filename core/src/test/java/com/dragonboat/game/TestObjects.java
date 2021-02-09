package com.dragonboat.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class TestObjects {

    public static String SaveString = "{\n" +
            "\"difficulty\": {\n" +
            "\t\"class\": \"java.lang.Integer\",\n" +
            "\t\"value\": 1\n" +
            "},\n" +
            "\"selectedDifficulty\": {\n" +
            "\t\"class\": \"java.lang.Integer\",\n" +
            "\t\"value\": 2\n" +
            "},\n" +
            "\"obstacleTimes\": [\n" +
            "\t[],\n" +
            "\t[ 1 ],\n" +
            "\t[ 2, 3 ],\n" +
            "\t[ 3, 4, 5 ],\n" +
            "\t[ 4, 5, 6, 7 ],\n" +
            "\t[ 5, 6, 7, 8, 9 ],\n" +
            "\t[ 6, 7, 8, 9, 10, 11 ]\n" +
            "],\n" +
            "\"totalDeltaTime\": {\n" +
            "\t\"class\": \"java.lang.Float\",\n" +
            "\t\"value\": 5.5\n" +
            "},\n" +
            "\"opponents\": [],\n" +
            "\"progressBar\": {\n" +
            "\t\"class\": \"com.dragonboat.game.ProgressBar$ProgressBarSpriteDescriptor\",\n" +
            "\t\"timeSeconds\": 20.5,\n" +
            "\t\"playerTime\": 19.5\n" +
            "},\n" +
            "\"lanes\": [\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"RIGHTBOUNDARY\": 20,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": []\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 20,\n" +
            "\t\t\"RIGHTBOUNDARY\": 40,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 27,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 1\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 1\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 40,\n" +
            "\t\t\"RIGHTBOUNDARY\": 60,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 47,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 2\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 48,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 2\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 60,\n" +
            "\t\t\"RIGHTBOUNDARY\": 80,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 67,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 3\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 68,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 67,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 3\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 3\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 80,\n" +
            "\t\t\"RIGHTBOUNDARY\": 100,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 87,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 4\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 88,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 87,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 4\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 88,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 4\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 100,\n" +
            "\t\t\"RIGHTBOUNDARY\": 120,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 107,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 5\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 108,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 107,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 5\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 108,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 107,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 5\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 5\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"class\": \"com.dragonboat.game.Lane$LaneSpriteDescriptor\",\n" +
            "\t\t\"LEFTBOUNDARY\": 120,\n" +
            "\t\t\"RIGHTBOUNDARY\": 140,\n" +
            "\t\t\"obstacleLimit\": 10,\n" +
            "\t\t\"obstacles\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 127,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 6\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 128,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 127,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 6\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 128,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Goose$GooseSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 2,\n" +
            "\t\t\t\t\"xPosition\": 127,\n" +
            "\t\t\t\t\"damage\": 10,\n" +
            "\t\t\t\t\"width\": 5,\n" +
            "\t\t\t\t\"height\": 15,\n" +
            "\t\t\t\t\"name\": \"Goose\",\n" +
            "\t\t\t\t\"direction\": \"South\",\n" +
            "\t\t\t\t\"laneNo\": 6\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"class\": \"com.dragonboat.game.Log$LogSpriteDescriptor\",\n" +
            "\t\t\t\t\"yPosition\": 1,\n" +
            "\t\t\t\t\"xPosition\": 128,\n" +
            "\t\t\t\t\"damage\": 11,\n" +
            "\t\t\t\t\"width\": 6,\n" +
            "\t\t\t\t\"height\": 50,\n" +
            "\t\t\t\t\"name\": \"Log\"\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"laneNo\": 6\n" +
            "\t}\n" +
            "],\n" +
            "\"ended\": {\n" +
            "\t\"class\": \"java.lang.Boolean\",\n" +
            "\t\"value\": false\n" +
            "},\n" +
            "\"rnd\": {\n" +
            "\t\"class\": \"java.util.Random\",\n" +
            "\t\"seed\": {\n" +
            "\t\t\"value\": 25212990981\n" +
            "\t}\n" +
            "},\n" +
            "\"started\": {\n" +
            "\t\"class\": \"java.lang.Boolean\",\n" +
            "\t\"value\": true\n" +
            "},\n" +
            "\"backgroundOffset\": {\n" +
            "\t\"class\": \"java.lang.Integer\",\n" +
            "\t\"value\": 5\n" +
            "},\n" +
            "\"player\": {\n" +
            "\t\"class\": \"com.dragonboat.game.Boat$BoatSpriteDescriptor\",\n" +
            "\t\"durability\": 38,\n" +
            "\t\"yPosition\": 3,\n" +
            "\t\"xPosition\": 7,\n" +
            "\t\"penalties\": 1.5,\n" +
            "\t\"width\": 6,\n" +
            "\t\"height\": 16,\n" +
            "\t\"currentSpeed\": 5,\n" +
            "\t\"fastestLegTime\": 18,\n" +
            "\t\"tiredness\": 0.5,\n" +
            "\t\"lastFrameY\": 2,\n" +
            "\t\"name\": \"Player\",\n" +
            "\t\"label\": \"A\"\n" +
            "}\n" +
            "}";

    public static HashMap<String, Object> SaveMap(){
        HashMap<String, Object> saveData = new HashMap<>();
        Random rnd = new Random();
        rnd.setSeed(2314344L);
        saveData.put("rnd", rnd);

        ArrayList<Lane.LaneSpriteDescriptor> lanes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Lane.LaneSpriteDescriptor laneSpriteDescriptor = new Lane.LaneSpriteDescriptor();
            laneSpriteDescriptor.LEFTBOUNDARY = 20 * i;
            laneSpriteDescriptor.RIGHTBOUNDARY = 20 * (i + 1);
            laneSpriteDescriptor.laneNo = i;
            laneSpriteDescriptor.obstacleLimit = 10;
            ArrayList<Obstacle.ObstacleSpriteDescriptor> obstacles = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j % 2 == 0) {
                    Goose.GooseSpriteDescriptor gooseSpriteDescriptor = new Goose.GooseSpriteDescriptor();
                    gooseSpriteDescriptor.damage = 10;
                    gooseSpriteDescriptor.xPosition = 20 * i + 7;
                    gooseSpriteDescriptor.yPosition = 2;
                    gooseSpriteDescriptor.width = 5;
                    gooseSpriteDescriptor.height = 15;
                    gooseSpriteDescriptor.name = "Goose";
                    gooseSpriteDescriptor.direction = "South";
                    gooseSpriteDescriptor.laneNo = i;
                    obstacles.add(gooseSpriteDescriptor);
                } else {
                    Log.LogSpriteDescriptor logSpriteDescriptor = new Log.LogSpriteDescriptor();
                    logSpriteDescriptor.damage = 11;
                    logSpriteDescriptor.xPosition = 20 * i + 8;
                    logSpriteDescriptor.yPosition = 1;
                    logSpriteDescriptor.width = 6;
                    logSpriteDescriptor.height = 50;
                    logSpriteDescriptor.name = "Log";
                    obstacles.add(logSpriteDescriptor);
                }
            }
            laneSpriteDescriptor.obstacles = obstacles;
            lanes.add(laneSpriteDescriptor);
        }
        saveData.put("lanes", lanes);

        Boat.BoatSpriteDescriptor player = new Boat.BoatSpriteDescriptor();
        player.durability = 77;
        player.laneNo = 0;
        player.yPosition = 3;
        player.xPosition = 7;
        player.penalties = 1.5f;
        player.durability = 38;
        player.width = 6;
        player.height = 16;
        player.currentSpeed = 5f;
        player.fastestLegTime = 18f;
        player.tiredness = 0.5f;
        player.frameCounter = 0;
        player.lastFrameY = 2;
        player.name = "Player";
        player.finished = false;
        player.label = 'A';
        saveData.put("player", player);

        ArrayList<Boat.BoatSpriteDescriptor> opponents = new ArrayList<>();
        for (int i = 0; i < opponents.size(); i++) {
            Boat.BoatSpriteDescriptor opponent = new Boat.BoatSpriteDescriptor();
            player.durability = 49;
            player.laneNo = i + 1;
            player.yPosition = 4;
            player.xPosition = 8;
            player.penalties = 1.75f;
            player.durability = 39;
            player.width = 7;
            player.height = 17;
            player.currentSpeed = 4.5f;
            player.fastestLegTime = 19f;
            player.tiredness = 0.25f;
            player.frameCounter = 1;
            player.lastFrameY = 3;
            player.name = "Opponent " + i;
            player.finished = false;
            player.label = (char) (66 + i);
            opponents.add(player);
        }
        saveData.put("opponents", opponents);

        ProgressBar.ProgressBarSpriteDescriptor progressBar = new ProgressBar.ProgressBarSpriteDescriptor();
        progressBar.playerTime = 19.5f;
        progressBar.timeSeconds = 20.5f;
        saveData.put("progressBar", progressBar);

        LinkedList<Integer[]> obstacleTimes = new LinkedList<>();
        for (int i = 0; i < lanes.size(); i++) {
            Integer[] obstacleTime = new Integer[lanes.get(i).obstacles.size()];
            for (int j = 0; j < obstacleTime.length; j++) {
                obstacleTime[j] = j + i;
            }
            obstacleTimes.add(obstacleTime);
        }
        saveData.put("obstacleTimes", obstacleTimes.toArray());

        saveData.put("difficulty", 1);
        saveData.put("selectedDifficulty", 2);
        saveData.put("ended", false);

        saveData.put("started", true);
        saveData.put("backgroundOffset", 5);
        saveData.put("totalDeltaTime", 5.5f);
        return saveData;
    }

}
