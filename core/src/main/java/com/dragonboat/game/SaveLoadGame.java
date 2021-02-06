package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import javax.swing.*;
import java.util.*;

class SaveLoadGame {
    private DragonBoatGame dragonBoatGame;
    private String fileName;

    SaveLoadGame(DragonBoatGame dragonBoatGame, String fileName, Boolean save){
        if(save){
            saveGame(dragonBoatGame,fileName);
        }else{
            loadGame(dragonBoatGame,fileName);
        }
    }

    public void saveGame(DragonBoatGame dragonBoatGame, String fileName){
        this.fileName = fileName;
        this.dragonBoatGame = dragonBoatGame;

        HashMap<String, Object> saveData = new HashMap<>();

        saveData.put("rnd", dragonBoatGame.rnd);

        ArrayList<Lane.LaneSpriteDescriptor> lanes = new ArrayList<>();
        for (int i = 0; i < dragonBoatGame.lanes.length; i++) {
            lanes.add(new Lane.LaneSpriteDescriptor(dragonBoatGame.lanes[i]));
        }
        saveData.put("lanes", lanes);

        saveData.put("player", new Boat.BoatSpriteDescriptor(dragonBoatGame.player));

        ArrayList<Boat.BoatSpriteDescriptor> opponents = new ArrayList<>();
        for (int i = 0; i < dragonBoatGame.opponents.length; i++) {
            opponents.add(new Boat.BoatSpriteDescriptor(dragonBoatGame.opponents[i]));
        }
        saveData.put("opponents", opponents);

        saveData.put("progressBar", new ProgressBar.ProgressBarSpriteDescriptor(dragonBoatGame.progressBar));

        LinkedList<Integer[]> tempObstacleTimes = new LinkedList<>();
        for (int i = 0; i < dragonBoatGame.obstacleTimes.length; i++) {
            tempObstacleTimes.add(dragonBoatGame.obstacleTimes[i].toArray(new Integer[dragonBoatGame.obstacleTimes[i].size()]));
        }
        saveData.put("obstacleTimes", tempObstacleTimes.toArray());

        saveData.put("difficulty", dragonBoatGame.difficulty);
        saveData.put("selectedDifficulty", dragonBoatGame.selectedDifficulty);
        saveData.put("ended", dragonBoatGame.ended);

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String saveString = json.toJson(saveData);

        FileHandle file = Gdx.files.local(fileName + ".json");
        file.writeString(saveString, false);
    }

    public void loadGame(DragonBoatGame dragonBoatGame, String fileName){
        FileHandle file = Gdx.files.local(fileName + ".json");
        //String fileNameContents = file.readString();
        HashMap<String, Object> loadData = new Json().fromJson(HashMap.class, file);
        System.out.println("////////////////////////////////////////////////////");

        //game seed
        //System.out.println(json.prettyPrint(loadData.get(0)));
        Random newRnd = (Random) loadData.get("rnd");
        dragonBoatGame.rnd = newRnd;

        //difficulty
        dragonBoatGame.difficulty = (int) loadData.get("difficulty");
        dragonBoatGame.selectedDifficulty = (int) loadData.get("selectedDifficulty");
        dragonBoatGame.ended = (boolean) loadData.get("ended");

        //lanes and obstacles
        Array<Lane.LaneSpriteDescriptor> loadLanes = (Array<Lane.LaneSpriteDescriptor>) loadData.get("lanes");
        Lane[] lanes = new Lane[loadLanes.size];
        for (int i = 0; i < loadLanes.size; i++) {
            Lane.LaneSpriteDescriptor loadLane = loadLanes.get(i);
            Lane lane = new Lane(dragonBoatGame.spriteTextures, loadLane.LEFTBOUNDARY, loadLane.RIGHTBOUNDARY, dragonBoatGame.lanes, loadLane.laneNo);
            lane.obstacleLimit = loadLane.obstacleLimit;
            ArrayList<Obstacle.ObstacleSpriteDescriptor> loadObstacles = loadLane.obstacles;
            ArrayList<Obstacle> obstacles = new ArrayList<>();
            for (int j = 0; j < loadObstacles.size(); j++) {
                Obstacle.ObstacleSpriteDescriptor loadObstacle = loadObstacles.get(j);
                if (loadObstacle.name.equals("Goose")) {
                    Goose.GooseSpriteDescriptor loadGoose = (Goose.GooseSpriteDescriptor) loadObstacle;
                    obstacles.add(new Goose(dragonBoatGame.spriteTextures, (int) loadGoose.xPosition, (int) loadGoose.yPosition, dragonBoatGame.lanes, loadGoose.laneNo));
                } else if (loadObstacle.name.equals("Log")) {
                    Log.LogSpriteDescriptor loadLog = (Log.LogSpriteDescriptor) loadObstacle;
                    obstacles.add(new Log(dragonBoatGame.spriteTextures, (int) loadLog.xPosition, (int) loadLog.yPosition));
                }
                //Obstacle obstacle = new Obstacle(loadObstacle.damage, (int) loadObstacle.xPosition, (int) loadObstacle.yPosition, loadObstacle.width, loadObstacle.height, loadObstacle.name);
                //obstacles.add(obstacle);
            }
            lane.obstacles = obstacles;
            lanes[i] = lane;
        }
        dragonBoatGame.lanes = lanes;

        /*
        System.out.println(loadData.get("lanes").getClass());
        Array<Object> arrayLoadLanes = (Array<Object>) loadData.get("lanes");
        Lane.LaneSpriteDescriptor[] loadLanes = new Lane.LaneSpriteDescriptor[arrayLoadLanes.size];
        for (int i = 0; i < arrayLoadLanes.size; i++) {
            loadLanes[i] = (Lane.LaneSpriteDescriptor) arrayLoadLanes.get(i);
        }
        dragonBoatGame.lanes = new Lane[loadLanes.length];
        for (Lane.LaneSpriteDescriptor loadLane : loadLanes) {
            Lane lane = new Lane(loadLane.LEFTBOUNDARY, loadLane.RIGHTBOUNDARY, dragonBoatGame.lanes, loadLane.laneNo);
            dragonBoatGame.lanes[loadLane.laneNo] = lane;
            System.out.println(11);
            ArrayList<Obstacle.ObstacleSpriteDescriptor> loadObstacles = loadLane.obstacles;
            System.out.println(12);
            for (Obstacle.ObstacleSpriteDescriptor loadObstacle : loadObstacles) {
                System.out.println(13);
                if (loadObstacle.name == "Goose") {
                    Goose.GooseSpriteDescriptor loadGoose = (Goose.GooseSpriteDescriptor) loadObstacle;
                    lane.obstacles.add(new Goose((int) loadGoose.xPosition, (int) loadGoose.yPosition, dragonBoatGame.lanes, loadLane.laneNo));
                } else if (loadObstacle.name == "Log") {
                    Log.LogSpriteDescriptor loadLog = (Log.LogSpriteDescriptor) loadObstacle;
                    lane.obstacles.add(new Log((int) loadObstacle.xPosition, (int) loadObstacle.yPosition));
                }
            }
        }
        */

        //obstacle times
        Array loadObstacleTimes = (Array) loadData.get("obstacleTimes");
        ArrayList<Integer>[] obstacleTimes = new ArrayList[loadObstacleTimes.size];
        for (int i = 0; i < loadObstacleTimes.size; i++) {
            Array loadObstacleTime = (Array) loadObstacleTimes.get(i);
            ArrayList<Integer> obstacleTime = new ArrayList<>();
            for (int j = 0; j < loadObstacleTime.size; j++) {
                Float loadObstacleTimeI = (Float) loadObstacleTime.get(j);
                obstacleTime.add(Math.round(loadObstacleTimeI));
            }
            obstacleTimes[i] = obstacleTime;
        }
        dragonBoatGame.obstacleTimes = obstacleTimes;

        //player
        //System.out.println(json.prettyPrint(loadData.get(classCounter)));
        Boat.BoatSpriteDescriptor loadPlayer = (Boat.BoatSpriteDescriptor) loadData.get("player");
        Player player = new Player((int) loadPlayer.yPosition, loadPlayer.width, loadPlayer.height, dragonBoatGame.lanes, loadPlayer.laneNo, loadPlayer.name);
        player.xPosition = loadPlayer.xPosition;
        player.penalties = loadPlayer.penalties;
        player.currentSpeed = loadPlayer.currentSpeed;
        player.fastestLegTime = loadPlayer.fastestLegTime;
        player.tiredness = loadPlayer.tiredness;
        player.frameCounter = loadPlayer.frameCounter;
        player.finished = loadPlayer.finished;
        player.label = loadPlayer.label;
        player.ChooseBoat(player.label);
        dragonBoatGame.player = player;

        //opponents
        Array loadOpponents = (Array) loadData.get("opponents");
        Opponent[] opponents = new Opponent[loadOpponents.size];
        for (int i = 0; i < loadOpponents.size; i++) {
            Boat.BoatSpriteDescriptor loadOpponent = (Boat.BoatSpriteDescriptor) loadOpponents.get(i);
            Opponent opponent = new Opponent((int) loadOpponent.yPosition, loadOpponent.width, loadOpponent.height, dragonBoatGame.lanes, loadOpponent.laneNo, loadOpponent.name);
            opponent.xPosition = loadOpponent.xPosition;
            opponent.penalties = loadOpponent.penalties;
            opponent.currentSpeed = loadOpponent.currentSpeed;
            opponent.fastestLegTime = loadOpponent.fastestLegTime;
            opponent.tiredness = loadOpponent.tiredness;
            opponent.frameCounter = loadOpponent.frameCounter;
            opponent.finished = loadOpponent.finished;
            opponent.label = loadOpponent.label;
            opponent.ChooseBoat(opponent.label);
            opponents[i] = opponent;
        }
        dragonBoatGame.opponents = opponents;

        //progress bar
        //System.out.println(json.prettyPrint(loadData.get(classCounter)));
        ProgressBar.ProgressBarSpriteDescriptor loadProgressBar = (ProgressBar.ProgressBarSpriteDescriptor) loadData.get("progressBar");
        ProgressBar progressBar = new ProgressBar(player, opponents);
        progressBar.timeSeconds = loadProgressBar.timeSeconds;
        progressBar.playerTime = loadProgressBar.playerTime;
        dragonBoatGame.progressBar = progressBar;

        /*
        ArrayList<Integer>[] newObTimes = new ArrayList[(int) loadData.get(classCounter)];
        int innerLoop = (int)loadData.get(classCounter);

        for(int i=0; i<newObTimes.length;i++){
            newObTimes[i] = new ArrayList<>();
            for(int j=0; j<innerLoop;j++){
                newObTimes[i].add((Integer) loadData.get(classCounter));
            }
        }
        dragonBoatGame.loadSave(newRnd, newLanes, newPlayer,
                newOpponents, newProgressBar, newObTimes,
                (int) loadData.get(classCounter),
                (int) loadData.get(classCounter+1),
                (boolean) loadData.get(classCounter+2));
         */


    }
}
