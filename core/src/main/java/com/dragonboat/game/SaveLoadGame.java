package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
        try {
            this.fileName = fileName;
            this.dragonBoatGame = dragonBoatGame;

            HashMap<String, Object> saveData = new HashMap<>();

            saveData.put("rnd", dragonBoatGame.rnd);

            Lane.LaneSpriteDescriptor[] lanes = new Lane.LaneSpriteDescriptor[dragonBoatGame.lanes.length];
            for (int i = 0; i < dragonBoatGame.lanes.length; i++) {
                lanes[i] = dragonBoatGame.lanes[i].saveState();
            }
            saveData.put("lanes", lanes);

            saveData.put("player", dragonBoatGame.player.saveState());

            Boat.BoatSpriteDescriptor[] opponents = new Boat.BoatSpriteDescriptor[dragonBoatGame.opponents.length];
            for (int i = 0; i < dragonBoatGame.opponents.length; i++) {
                opponents[i] = dragonBoatGame.opponents[i].saveState();
            }
            saveData.put("opponents", opponents);

            saveData.put("progessBar", dragonBoatGame.progressBar.saveState());

            LinkedList<Integer[]> tempObstacleTimes = new LinkedList<>();
            for (int i = 0; i < dragonBoatGame.obstacleTimes.length; i++) {
                tempObstacleTimes.add(dragonBoatGame.obstacleTimes[i].toArray(new Integer[dragonBoatGame.obstacleTimes[i].size()]));
            }
            saveData.put("obstacleTimes", tempObstacleTimes.toArray());

            saveData.put("difficulty", dragonBoatGame.difficulty);
            saveData.put("selectedDifficulty", dragonBoatGame.selectedDifficulty);
            saveData.put("ended", dragonBoatGame.ended);

            Json json = new Json();
            String saveString = json.toJson(saveData);

            FileHandle file = Gdx.files.local(fileName + ".json");
            file.writeString(saveString, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGame(DragonBoatGame dragonBoatGame, String fileName){
        FileHandle file = Gdx.files.local(fileName + ".json");
        String fileNameContents = file.readString();
        Json json = new Json();
        ArrayList loadData = json.fromJson(ArrayList.class, fileNameContents);
        System.out.println("////////////////////////////////////////////////////");

        //System.out.println(loadData);

        int classCounter = 0;

        //Loading game seed
        //System.out.println(json.prettyPrint(loadData.get(0)));
        Random newRnd = (Random) loadData.get(classCounter);
        classCounter++;
        //lanes
        //System.out.println(json.prettyPrint(loadData.get(1)));
        Lane[] newLanes = new Lane[7];
        for(int i=0;i<7;i++){
            //System.out.println(loadData.get(i+1));
            newLanes[i] = new Lane((String) loadData.get(i+classCounter));
        }
        classCounter += 7;

        //player
        //System.out.println(json.prettyPrint(loadData.get(classCounter)));
        Player newPlayer = new Player((String) loadData.get(classCounter));
        newPlayer.setLane(newLanes[3]);
        classCounter++;

        //opponents
        Opponent[] newOpponents = new Opponent[(int) loadData.get(classCounter)];
        for(int j=0;j<newOpponents.length;j++){
            newOpponents[j] = new Opponent((String) loadData.get(classCounter+j+1));
            newOpponents[j].setLane(newLanes[j]);
        }
        classCounter += newOpponents.length+1;

        //progress bar
        System.out.println(json.prettyPrint(loadData.get(classCounter)));
        ProgressBar newProgressBar = json.fromJson(ProgressBar.class, (String) loadData.get(classCounter));
        newProgressBar.setPlayerBoat(newPlayer);
        newProgressBar.setOpponentBoats(newOpponents);
        classCounter ++;

        ArrayList<Integer>[] newObTimes = new ArrayList[(int) loadData.get(classCounter)];
        classCounter++;
        int innerLoop = (int)loadData.get(classCounter);
        classCounter++;

        for(int i=0; i<newObTimes.length;i++){
            newObTimes[i] = new ArrayList<>();
            for(int j=0; j<innerLoop;j++){
                newObTimes[i].add((Integer) loadData.get(classCounter));
                classCounter++;
            }
        }

        dragonBoatGame.loadSave(newRnd, newLanes, newPlayer,
                newOpponents, newProgressBar, newObTimes,
                (int) loadData.get(classCounter),
                (int) loadData.get(classCounter+1),
                (boolean) loadData.get(classCounter+2));
    }
}
