package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Explosion extends Actor{
    int max = 3;
    int counter = 0;
    int position = 0;
    List<Texture> textures;
    Explosion(float x, float y, List<Texture> textures, MyGdxGame game) {
        super(x, y, textures.get(0), game);
        this.textures = textures;
    }

    @Override
    void execute(){
        counter++;
        if(counter>max){
            counter = 0;
            position ++;
        }
        if(position>= textures.size()){
            dead = true;
        }
    }

    @Override
    void draw() {
        if(dead) return;
        sprite.setTexture(textures.get(position));
        sprite.draw(game.batch);
    }
}
