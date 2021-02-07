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

    static class GameSaveDescriptor{
        Random rnd;
        ArrayList<String> lanes;
        String player;
        ArrayList<String> opponents;
        String progressBar;
        ArrayList<ArrayList<Integer>> obstacleTimes;
        int difficulty, selectedDifficulty;
        boolean ended;

        GameSaveDescriptor(){}

        GameSaveDescriptor(DragonBoatGame oldGame){
            this.rnd = oldGame.rnd;
            this.lanes = new ArrayList<>();
            for(Lane lane: oldGame.lanes){
                this.lanes.add(lane.saveState());
            }
            this.player = oldGame.player.saveState();
            this.opponents = new ArrayList<>();
            for(Opponent opponent: oldGame.opponents){
                this.opponents.add(opponent.saveState());
            }
            this.progressBar = oldGame.progressBar.saveState();

            this.obstacleTimes = new ArrayList<>();
            for(ArrayList<Integer> time: oldGame.obstacleTimes){
                this.obstacleTimes.add(time);
            }

            /*
            saveData.add(dragonBoatGame.obstacleTimes.length); //7
            saveData.add(dragonBoatGame.obstacleTimes[0].size()); //4
            System.out.println(dragonBoatGame.obstacleTimes);
            for(int i=0; i<dragonBoatGame.obstacleTimes.length;i++){
                for(int j=0; j<dragonBoatGame.obstacleTimes[i].size();j++){
                    saveData.add((Integer) dragonBoatGame.obstacleTimes[i].get(j));
                }
            }
             */

            this.difficulty = oldGame.difficulty;
            this.selectedDifficulty = oldGame.selectedDifficulty;
            this.ended = oldGame.ended;
        }
    }

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

        /*
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

         */

        Json json = new Json();
        String saveString = json.toJson(new GameSaveDescriptor(dragonBoatGame));

        FileHandle file = Gdx.files.local(fileName + ".json");
        file.writeString(saveString, false);
    }

    public void loadGame(DragonBoatGame dragonBoatGame, String fileName){
        FileHandle file = Gdx.files.local(fileName + ".json");
        String fileNameContents = file.readString();
        Json json = new Json();
        GameSaveDescriptor loadData = json.fromJson(GameSaveDescriptor.class, fileNameContents);
        System.out.println("////////////////////////////////////////////////////");

        //lanes
        Lane[] newLanes = new Lane[loadData.lanes.size()];
        for(int k=0;k<loadData.lanes.size();k++){
            newLanes[k] = new Lane(loadData.lanes.get(k));
        }

        //player
        Player newPlayer = new Player(loadData.player);
        newPlayer.setLane(newLanes[3]);

        //opponents
        Opponent[] newOpponents = new Opponent[loadData.opponents.size()];
        for(int i =0;i<loadData.opponents.size();i++) {
            newOpponents[i] = new Opponent(loadData.opponents.get(i));
            if (loadData.opponents.size() < 7) {
                if (i < 2) {
                    newOpponents[i].setLane(newLanes[i]);
                } else {
                    newOpponents[i].setLane(newLanes[i + 1]);
                }
            } else {
                if (i < 3) {
                    newOpponents[i].setLane(newLanes[i]);
                } else {
                    newOpponents[i].setLane(newLanes[i + 1]);
                }
            }
        }

        //progress bar
        ProgressBar newProgressBar = json.fromJson(ProgressBar.class, loadData.progressBar);

        newProgressBar.setPlayerBoat(newPlayer);
        newProgressBar.setOpponentBoats(newOpponents);

        //obstacle times
        ArrayList<Integer>[] newObTimes = new ArrayList[loadData.obstacleTimes.size()];
        for(int j=0;j<loadData.obstacleTimes.size();j++){
            newObTimes[j] = loadData.obstacleTimes.get(j);
        }

        dragonBoatGame.loadSave(loadData.rnd, newLanes, newPlayer, newOpponents, newProgressBar,
                newObTimes, loadData.difficulty, loadData.selectedDifficulty, loadData.ended);
    }
}
