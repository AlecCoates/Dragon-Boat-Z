package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

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

        Lane.LaneSpriteDescriptor[] lanes = new Lane.LaneSpriteDescriptor[dragonBoatGame.lanes.length];
        for (int i = 0; i < dragonBoatGame.lanes.length; i++) {
            lanes[i] = new Lane.LaneSpriteDescriptor(dragonBoatGame.lanes[i]);
        }
        saveData.put("lanes", lanes);

        saveData.put("player", new Boat.BoatSpriteDescriptor(dragonBoatGame.player));

        Boat.BoatSpriteDescriptor[] opponents = new Boat.BoatSpriteDescriptor[dragonBoatGame.opponents.length];
        for (int i = 0; i < dragonBoatGame.opponents.length; i++) {
            opponents[i] = new Boat.BoatSpriteDescriptor(dragonBoatGame.opponents[i]);
        }
        saveData.put("opponents", opponents);

        saveData.put("progessBar", new ProgressBar.ProgressBarSpriteDescriptor(dragonBoatGame.progressBar));

        LinkedList<Integer[]> tempObstacleTimes = new LinkedList<>();
        for (int i = 0; i < dragonBoatGame.obstacleTimes.length; i++) {
            tempObstacleTimes.add(dragonBoatGame.obstacleTimes[i].toArray(new Integer[dragonBoatGame.obstacleTimes[i].size()]));
        }
        saveData.put("obstacleTimes", tempObstacleTimes.toArray());

        saveData.put("difficulty", dragonBoatGame.difficulty);
        saveData.put("selectedDifficulty", dragonBoatGame.selectedDifficulty);
        saveData.put("ended", dragonBoatGame.ended);

        String saveString = new Json().toJson(saveData);

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

        //difficulty
        dragonBoatGame.difficulty = (int) loadData.get("difficulty");
        dragonBoatGame.selectedDifficulty = (int) loadData.get("selectedDifficulty");
        dragonBoatGame.ended = (boolean) loadData.get("ended");

        //lanes and obstacles
        //System.out.println(json.prettyPrint(loadData.get(1)));
        Lane.LaneSpriteDescriptor[] loadLanes = (Lane.LaneSpriteDescriptor[]) loadData.get("lanes");
        dragonBoatGame.lanes = new Lane[loadLanes.length];
        for (Lane.LaneSpriteDescriptor loadLane : loadLanes) {
            Lane lane = new Lane(loadLane.LEFTBOUNDARY, loadLane.RIGHTBOUNDARY, dragonBoatGame.lanes, loadLane.laneNo);
            dragonBoatGame.lanes[loadLane.laneNo] = lane;
            ArrayList<Obstacle.ObstacleSpriteDescriptor> loadObstacles = loadLane.obstacles;
            for (Obstacle.ObstacleSpriteDescriptor loadObstacle : loadObstacles) {
                if (loadObstacle.name == "Goose") {
                    Goose.GooseSpriteDescriptor loadGoose = (Goose.GooseSpriteDescriptor) loadObstacle;
                    lane.obstacles.add(new Goose((int) loadGoose.xPosition, (int) loadGoose.yPosition, new Texture(Gdx.files.internal("gooseSouthsprite.png")), dragonBoatGame.lanes, loadLane.laneNo));
                } else if (loadObstacle.name == "Log") {
                    Log.LogSpriteDescriptor loadLog = (Log.LogSpriteDescriptor) loadObstacle;
                    lane.obstacles.add(new Log((int) loadObstacle.xPosition, (int) loadObstacle.yPosition, new Texture(Gdx.files.internal("logBig sprite.png"))));
                }
            }
        }

        //obstacle times
        Integer[][] loadObstacleTimes = (Integer[][]) loadData.get("obstacleTimes");
        ArrayList<Integer>[] obstacleTimes = new ArrayList[loadObstacleTimes.length];
        for (int i = 0; i < loadObstacleTimes.length; i++) {
            obstacleTimes[i] = new ArrayList<>(Arrays.asList(loadObstacleTimes[i]));
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
        Boat.BoatSpriteDescriptor[] loadOpponents = (Boat.BoatSpriteDescriptor[]) loadData.get("opponents");
        Opponent[] opponents = new Opponent[loadOpponents.length];
        for (Boat.BoatSpriteDescriptor loadOpponent : loadOpponents) {
            Opponent opponent = new Opponent((int) loadPlayer.yPosition, loadPlayer.width, loadPlayer.height, dragonBoatGame.lanes, loadPlayer.laneNo, loadPlayer.name);
            opponent.xPosition = loadOpponent.xPosition;
            opponent.penalties = loadOpponent.penalties;
            opponent.currentSpeed = loadOpponent.currentSpeed;
            opponent.fastestLegTime = loadOpponent.fastestLegTime;
            opponent.tiredness = loadOpponent.tiredness;
            opponent.frameCounter = loadOpponent.frameCounter;
            opponent.finished = loadOpponent.finished;
            opponent.label = loadOpponent.label;
            opponent.ChooseBoat(opponent.label);
        }

        //progress bar
        //System.out.println(json.prettyPrint(loadData.get(classCounter)));
        ProgressBar.ProgressBarSpriteDescriptor loadProgressBar = (ProgressBar.ProgressBarSpriteDescriptor) loadData.get("progressBar");
        ProgressBar progressBar = new ProgressBar(player, opponents);
        progressBar.timeSeconds = loadProgressBar.timeSeconds;
        progressBar.playerTime = loadProgressBar.playerTime;

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
