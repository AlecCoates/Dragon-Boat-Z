package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
        Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        final TextButton save1Button = new TextButton("Save",skin);
        save1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Saved");
            }
        });
        final TextButton save2Button = new TextButton("Save",skin);
        save2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Saved");
            }
        });
        final TextButton save3Button = new TextButton("Save",skin);
        save3Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Saved");
            }
        });

        final TextButton load1Button = new TextButton("Load",skin);
        load1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Loaded");
            }
        });
        final TextButton load2Button = new TextButton("Load",skin);
        load2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Loaded");
            }
        });
        final TextButton load3Button = new TextButton("Load",skin);
        load3Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Loaded");
            }
        });

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
