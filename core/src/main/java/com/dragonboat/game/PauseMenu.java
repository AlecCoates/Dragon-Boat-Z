package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * PauseMenu class is the basis of pause menu for the main game.
 */
public class PauseMenu extends Window {
    private DragonBoatGame parent;
    private static Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhu/skin/pixthulhu-ui.json"));

    /**
     * Constructor creates the structure of pause menu containing title and save game text button
     *
     * @param game Reference to the current game screen
     */
    public PauseMenu(final DragonBoatGame game){
        super("Pause Menu",skin);
        parent = game;

        final TextButton resumeButton = new TextButton("Resume",skin);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                actor.getParent().setVisible(!actor.getParent().isVisible());
                ((GameScreen) game.getScreen()).ispaused = !((GameScreen) game.getScreen()).ispaused;
            }
        });

        final TextButton saveButton = new TextButton("Save/Load",skin);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new SaveGameScreen(parent));
            }
        });

        final TextButton closeGameButton = new TextButton("Exit",skin);
        closeGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        super.row().pad(10,0,0,10);
        super.add(resumeButton);
        super.row().pad(10,0,0,10);
        super.add(saveButton);
        super.row().pad(10,0,0,10);
        super.add(closeGameButton);
        super.row().pad(10,0,0,10);
        super.add("Press Esc to resume game");
    }

}