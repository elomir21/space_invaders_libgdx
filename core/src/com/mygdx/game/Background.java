package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Background {
    MyGdxGame game;
    Texture texture;
    int ay, by;

    Background(Texture texture, MyGdxGame game) {
        this.game = game;
        this.texture = texture;
        ay = 0;
        by = texture.getHeight();
    }

    public void run() {
        ay -= 2;
        by -= 2;

        if (ay <= - texture.getHeight()) {
            ay = by + texture.getHeight();
        }
        if (by <= - texture.getHeight()) {
            by = ay + texture.getHeight();
        }
    }

    public void draw() {
        game.batch.draw(texture, 0, ay);
        game.batch.draw(texture, 0, by);
    }
}
