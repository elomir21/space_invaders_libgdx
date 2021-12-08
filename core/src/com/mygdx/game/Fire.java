package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Fire extends Actor{
    Fire(float x, float y, Texture texture, MyGdxGame game) {
        super(x, y, texture, game);
        game.sndFire.play();
    }

    @Override
    void execute() {
        sprite.translateY(5);
        if(sprite.getY() > game.h) dead = true;
        for(Actor a: game.actors){
            if(collide(a) && a instanceof Enemy){
                dead = true;
                a.dead = true;
                game.explosion(a.sprite.getX(), a.sprite.getY());
                game.score++;
            }
        }
    }
}
