package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SaveGameScreen implements Screen {
    private DragonBoatGame parent;
    private Stage stage;
    private Label titleLabel;
    private Label subtTitle1;
    private Label subtTitle2;
    private Label subtTitle3;
    private String fileName1 = "SaveState1";
    private String fileName2 = "SaveState2";
    private String fileName3 = "SaveState3";


    /**
     * Creates a Save game screen using textbuttons that once pressed proceed
     * to either save or load a game
     *
     * @param Game represents the initial state of DragonBoatGame
     */
    public SaveGameScreen(DragonBoatGame Game){
        parent = Game;
    }

    /**
     * Creates a load/save screen using TextButtons that once pressed proceed to save game and exit
     * or proceed to load a new game from previous save.
     */
    @Override
    public void show() {
        /*
        * creates stage for the buttons to be displayed adn act upon
         */
        stage = new Stage(new ScreenViewport());

        stage.clear();

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        /*
        * Creates all required buttons
         */
        Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhu/skin/pixthulhu-ui.json"));

        final TextButton save1Button = new TextButton("Save",skin);
        save1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame saveFile1 = new SaveLoadGame(parent, fileName1, true);
                System.out.println("Saved_1");
            }
        });
        final TextButton save2Button = new TextButton("Save",skin);
        save2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame saveFile2 = new SaveLoadGame(parent, fileName2, true);
                System.out.println("Saved_2");
            }
        });
        final TextButton save3Button = new TextButton("Save",skin);
        save3Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame saveFile3 = new SaveLoadGame(parent, fileName3, true);
                System.out.println("Saved_3");
            }
        });

        final TextButton load1Button = new TextButton("Load",skin);
        FileHandle file1 = Gdx.files.local(fileName1 + ".json");
        if (file1.exists() && !file1.isDirectory()) {
            load1Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    load1Button.setDisabled(false);
                    SaveLoadGame loadFile1 = new SaveLoadGame(parent, fileName1, false);
                    parent.setScreen(new GameScreen(parent, true));
                    System.out.println("Loaded_1");
                }
            });
        } else {
            load1Button.setTouchable(Touchable.disabled);
            Color color = load1Button.getColor();
            color.a = 0.5f;
            load1Button.setColor(color);
        }
        final TextButton load2Button = new TextButton("Load",skin);
        FileHandle file2 = Gdx.files.local(fileName2 + ".json");
        if (file2.exists() && !file2.isDirectory()) {
            load2Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    load2Button.setDisabled(false);
                    SaveLoadGame loadFile2 = new SaveLoadGame(parent, fileName2, false);
                    parent.setScreen(new GameScreen(parent, true));
                    System.out.println("Loaded_2");
                }
            });
        } else {
            load2Button.setTouchable(Touchable.disabled);
            Color color = load2Button.getColor();
            color.a = 0.5f;
            load2Button.setColor(color);
        }
        final TextButton load3Button = new TextButton("Load",skin);
        FileHandle file3 = Gdx.files.local(fileName3 + ".json");
        if (file3.exists() && !file3.isDirectory()) {
            load3Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    load1Button.setDisabled(false);
                    SaveLoadGame loadFile3 = new SaveLoadGame(parent, fileName3, false);
                    parent.setScreen(new GameScreen(parent, true));
                    System.out.println("Loaded_3");
                }
            });
        } else {
            load3Button.setTouchable(Touchable.disabled);
            Color color = load3Button.getColor();
            color.a = 0.5f;
            load3Button.setColor(color);
        }

        /*
        * Adds all necessary labels and buttons to table
         */

        titleLabel = new Label("Save/Load Game Select",skin);
        subtTitle1 = new Label("Save Slot 1: ",skin);
        subtTitle2 = new Label("Save Slot 2: ",skin);
        subtTitle3 = new Label("Save Slot 3: ",skin);

        table.add(titleLabel).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(subtTitle1);
        table.add(save1Button);
        table.add(load1Button);
        table.row().pad(10,0,0,10);
        table.add(subtTitle2);
        table.add(save2Button);
        table.add(load2Button);
        table.row().pad(10,0,0,10);
        table.add(subtTitle3);
        table.add(save3Button);
        table.add(load3Button);
        table.row().pad(10,0,0,10);
    }

    /**
     * Rendering function for the difficulty screen
     *
     * @param delta acts as time
     */
    @Override
    public void render(float delta) {
        //Clears screen, allowing next items to be drawn
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //tells stage to act and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //informs stage screen size has changed, viewport should be recalculated
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
