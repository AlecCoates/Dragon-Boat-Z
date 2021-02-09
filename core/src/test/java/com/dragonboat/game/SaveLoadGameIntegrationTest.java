package com.dragonboat.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SaveLoadGameIntegrationTest {

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