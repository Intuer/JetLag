package com.JetLag.game.engine.graphics;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.BasicObject;
import com.JetLag.game.engine.graphics.sprites.Background;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Set;

/**
 * Created by nicklas on 5/13/16.
 */
public class Map {
    protected Background background;

    // Objects that won't be removed (ie. planets).
    protected Set<BasicObject> static_objects;

    public Map(String bgName, int rwidth, int rheight) {
        background = new Background(bgName, rwidth, rheight);
    }

    public Map() {
        this("space-background.png", 512, 512);
    }

    public void moveBackground(int x, int y) {
        background.moveRegion(x, y);
    }

    public void drawBackground(SpriteBatch sb) {
        sb.begin();
        sb.draw(background.getRegion(), 0, 0, JetLag.WIDTH, JetLag.HEIGHT);
        sb.end();
    }
}
