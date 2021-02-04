package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

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

        ArrayList<Object> saveData = new ArrayList<>();
        saveData.add(dragonBoatGame.rnd);
        for(Lane lane: dragonBoatGame.lanes){
            saveData.add(lane.saveState());
        }
        saveData.add(dragonBoatGame.player.saveState());
        saveData.add(dragonBoatGame.opponents.length);
        for(Opponent opponent: dragonBoatGame.opponents){
            saveData.add(opponent.saveState());
        }
        saveData.add(dragonBoatGame.progressBar.saveState());

        saveData.add(dragonBoatGame.obstacleTimes.length); //7
        saveData.add(dragonBoatGame.obstacleTimes[0].size()); //4
        System.out.println(dragonBoatGame.obstacleTimes);
        for(int i=0; i<dragonBoatGame.obstacleTimes.length;i++){
            for(int j=0; j<dragonBoatGame.obstacleTimes[i].size();j++){
                saveData.add((Integer) dragonBoatGame.obstacleTimes[i].get(j));
            }
        }

        //saveData.add(dragonBoatGame.obstacleTimes);
        saveData.add(dragonBoatGame.difficulty);
        saveData.add(dragonBoatGame.selectedDifficulty);
        saveData.add(dragonBoatGame.ended);
        //saveData.add(dragonBoatGame.getBatch()); //Don't think we need?

        Json json = new Json();
        String saveString = json.toJson(saveData);

        FileHandle file = Gdx.files.local(fileName + ".json");
        file.writeString(saveString, false);
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
