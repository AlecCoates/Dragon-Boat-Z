package com.dragonboat.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;

/**
 * Difficulty Screen class for Dragon Boat Game. This is the select screen for difficulty.
 * Once difficulty clicked game will proceed to menu screen where to select a boat.
 * Selected difficulty chosen as integer and alters amount of obstacles and speed of them in GameScreen
 *
 * @see MenuScreen
 */
public class DifficultyScreen implements Screen {
    private DragonBoatGame parent;
    private Stage stage;
    private Label titleLabel;

    /**
     * Creates a difficulty screen using textbuttons tht once pressed proceed
     *
     * @param Game represents the initial state of DragonBoatGame
     */
    public DifficultyScreen(DragonBoatGame Game){
        parent = Game;
    }

    /**
     * Creates a difficulty screen using TextButtons that once pressed proceed to set difficulty and
     * proceed to boat select screen of menu screen
     */
    @Override
    public void show() {
        /*
        * Creates stage for buttons to display and act upon
         */
        stage = new Stage(new ScreenViewport());

        stage.clear();

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        /*
        * Creates buttons to add
         */

        Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        final TextButton easyButton = new TextButton("EASY",skin);
        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.selectedDifficulty = 1;
                parent.setScreen(new MenuScreen(parent));
            }
        });
        final TextButton mediumButton = new TextButton("MEDIUM",skin);
        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.selectedDifficulty = 2;
                parent.setScreen(new MenuScreen(parent));
            }
        });
        final TextButton hardButton = new TextButton("HARD",skin);
        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.selectedDifficulty = 3;
                parent.setScreen(new MenuScreen(parent));
            }
        });


        titleLabel = new Label("Difficulty Selection",skin);



        /*
        * Adds all appropriate labels and buttons to table
         */
        table.add(titleLabel).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(easyButton);
        table.row().pad(10,0,0,10);
        table.add(mediumButton);
        table.row().pad(10,0,0,10);
        table.add(hardButton);
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
