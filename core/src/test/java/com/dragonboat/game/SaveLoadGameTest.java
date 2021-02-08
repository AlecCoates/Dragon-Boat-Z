package com.dragonboat.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SaveLoadGameTest {

    private HashMap<String, Texture> textures;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        textures = new HashMap<>();
        textures.put("Goose", new Texture(new Pixmap(5, 15, Pixmap.Format.RGB888)));
        textures.put("Log", new Texture(new Pixmap(15, 109, Pixmap.Format.RGB888)));
        textures.put("Test", new Texture(new Pixmap(15, 34, Pixmap.Format.RGB888)));
    }

    @Test
    public void testSaveGameFile() {
        FileHandle file = Gdx.files.local("saves/test.json");
        if (file.exists()) {
            if (file.isDirectory()) {
                file.deleteDirectory();
            } else {
                file.delete();
            }
        }
        String writeString = "{\"Testing\": 123}";
        try {
            SaveLoadGame.saveGameFile(writeString, "test");
            assertTrue(file.exists());
            assertEquals(writeString, file.readString());
        } catch (Exception e){
        } finally {
            if (file.exists()) file.delete();
        }
    }

    @Test
    public void testLoadGameFile() {
        FileHandle file = Gdx.files.local("saves/test.json");
        if (file.exists()) {
            if (file.isDirectory()) {
                file.deleteDirectory();
            } else {
                file.delete();
            }
        }
        String writeString = "{\"Testing\": 123}";
        file.writeString(writeString, false);
        try {
            assertEquals(writeString, SaveLoadGame.loadGameFile("test"));
        } catch (Exception e){
        } finally {
            if (file.exists()) file.delete();
        }
    }

}