package com.dragonboat.game;

import com.badlogic.gdx.utils.Json;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class SaveLoadGameTest {

    @Test
    public void testSaveGameString() {
        assertEquals(TestObjects.SaveString, SaveLoadGame.saveGameString(TestObjects.SaveMap()));
    }

    @Test
    public void testLoadGameString() {
        Json json = new Json();
        assertEquals(json.toJson(json.fromJson(HashMap.class, TestObjects.SaveString)), json.toJson(SaveLoadGame.loadGameString(TestObjects.SaveString)));
    }

}