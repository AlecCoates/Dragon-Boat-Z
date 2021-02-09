package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SaveGameScreen implements Screen {
    private DragonBoatGame game;
    private InputProcessor oldInputProcessor;
    private Stage stage;
    private String fileName1 = "SaveState1";
    private String fileName2 = "SaveState2";
    private String fileName3 = "SaveState3";

    /**
     * Creates a Save game screen using textbuttons that once pressed proceed
     * to either save or load a game
     *
     * @param game represents the initial state of DragonBoatGame
     */
    public SaveGameScreen(DragonBoatGame game){
        this.game = game;
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

        oldInputProcessor = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);

        /*
         * Creates all required buttons
         */
        Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhu/skin/pixthulhu-ui.json"));

        final FileHandle file1 = Gdx.files.local("saves/" + fileName1 + ".json");
        final FileHandle file2 = Gdx.files.local("saves/" + fileName2 + ".json");
        final FileHandle file3 = Gdx.files.local("saves/" + fileName3 + ".json");

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);
        final SaveGameScreen thisScreen = this;

        final TextButton save1Button = new TextButton("Save",skin);
        save1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame.saveGame(game, fileName1);
                thisScreen.reload();
            }
        });
        final TextButton save2Button = new TextButton("Save",skin);
        save2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame.saveGame(game, fileName2);
                thisScreen.reload();
            }
        });
        final TextButton save3Button = new TextButton("Save",skin);
        save3Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SaveLoadGame.saveGame(game, fileName3);
                thisScreen.reload();
            }
        });

        final TextButton load1Button = new TextButton("Load",skin);
        if (file1.exists() && !file1.isDirectory()) {
            load1Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    SaveLoadGame.loadGame(game, fileName1);
                }
            });
        } else {
            load1Button.setTouchable(Touchable.disabled);
            load1Button.getColor().a = 0.5f;
        }
        final TextButton load2Button = new TextButton("Load",skin);
        if (file2.exists() && !file2.isDirectory()) {
            load2Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    SaveLoadGame.loadGame(game, fileName2);
                }
            });
        } else {
            load2Button.setTouchable(Touchable.disabled);
            load2Button.getColor().a = 0.5f;
        }
        final TextButton load3Button = new TextButton("Load",skin);
        if (file3.exists() && !file3.isDirectory()) {
            load3Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    SaveLoadGame.loadGame(game, fileName3);
                }
            });
        } else {
            load3Button.setTouchable(Touchable.disabled);
            load3Button.getColor().a = 0.5f;
        }

        final TextButton delete1Button = new TextButton("Delete",skin);
        if (file1.exists() && !file1.isDirectory()) {
            delete1Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    file1.delete();
                    thisScreen.reload();
                }
            });
        } else {
            delete1Button.setTouchable(Touchable.disabled);
            delete1Button.getColor().a = 0.5f;
        }
        final TextButton delete2Button = new TextButton("Delete",skin);
        if (file2.exists() && !file2.isDirectory()) {
            delete2Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    file2.delete();
                    thisScreen.reload();
                }
            });
        } else {
            delete2Button.setTouchable(Touchable.disabled);
            delete2Button.getColor().a = 0.5f;
        }
        final TextButton delete3Button = new TextButton("Delete",skin);
        if (file3.exists() && !file3.isDirectory()) {
            delete3Button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    file3.delete();
                    thisScreen.reload();
                }
            });
        } else {
            delete3Button.setTouchable(Touchable.disabled);
            delete3Button.getColor().a = 0.5f;
        }

        final TextButton returnButton = new TextButton("Return",skin);
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                thisScreen.returnToGame();
            }
        });

        /*
        * Adds all necessary labels and buttons to table
         */
        table.add("Save/Load Game Select").colspan(2);
        table.row().pad(10,0,0,10);
        table.add("Save Slot 1:");
        table.add(save1Button);
        table.add(load1Button);
        table.add(delete1Button);
        table.row().pad(10,0,0,10);
        table.add("Save Slot 2:");
        table.add(save2Button);
        table.add(load2Button);
        table.add(delete2Button);
        table.row().pad(10,0,0,10);
        table.add("Save Slot 3:");
        table.add(save3Button);
        table.add(load3Button);
        table.add(delete3Button);
        table.row().pad(50,0,0,10);
        table.add(returnButton);

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

        //Checks for escape key press
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.returnToGame();
        }
    }

    /**
     * Returns to the main game screen and disposes this screen
     */
    private void returnToGame() {
        game.setScreen(game.gameScreen);
        Gdx.input.setInputProcessor(oldInputProcessor);
        this.dispose();
    }

    /**
     * Resizes viewport to new width and height
     *
     * @param width new width
     * @param height new height
     */
    @Override
    public void resize(int width, int height) {
        //informs stage screen size has changed, viewport should be recalculated
        stage.getViewport().update(width,height,true);
    }

    /**
     * Disposes this screen
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * Loads a new instance of this screen and disposes of the current instance
     */
    public void reload() {
        Gdx.input.setInputProcessor(oldInputProcessor);
        game.setScreen(new SaveGameScreen(game));
        this.dispose();
    }

    /**
     * Overrides the default pause() method to be blank
     */
    @Override
    public void pause() {

    }

    /**
     * Overrides the default resume() method to be blank
     */
    @Override
    public void resume() {

    }

    /**
     * Overrides the default hide() method to be blank
     */
    @Override
    public void hide() {

    }
}
