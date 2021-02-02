package com.dragonboat.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ObstacleTest {

    @Test
    public void testMove() {
        Obstacle testObstacle = new Obstacle(5, 10, 30, 5, 8, new Texture(new Pixmap(1,1,Pixmap.Format.RGB888)));
        testObstacle.Move(10, 20);
        assertEquals(30 - 10, testObstacle.yPosition, 0.0002);
    }

}