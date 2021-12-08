package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Actor {

    static final int RIGHT = 0;
    static final int LEFT = 1;
    int direction;

    Enemy(Texture texture, MyGdxGame game) {
        super(game.random.nextInt((int)game.w - texture.getWidth()),
                game.h + 100, texture, game);
        direction = game.random.nextInt(2);
    }

    @Override
    void execute(){
        sprite.translateY(-5);
        if (direction == RIGHT) {
            sprite.translateX(5);
            if (sprite.getX() >= game.w - sprite.getWidth()) {
                direction = LEFT;
            }
        } else {
            sprite.translateX(-5);
            if (sprite.getX() <= 0) {
                direction = RIGHT;
            }
        }
        if (sprite.getY() + sprite.getHeight() < 0){
            dead = true;
        }
    }

}
