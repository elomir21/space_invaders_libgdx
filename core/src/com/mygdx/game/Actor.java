package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Actor {

    MyGdxGame game;
    Sprite sprite;
    boolean dead = false;

    Actor(float x, float y, Texture texture, MyGdxGame game){
        this.game = game;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x,y);
    }

    void execute(){

    }

    void run(){
        execute();
        sprite.setX(clamp(sprite.getX(), 0,game.w - sprite.getWidth()));
    }

    static float clamp(float value, float min, float max){
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    void draw() {
        sprite.draw(game.batch);
    }
}
