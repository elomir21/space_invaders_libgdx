package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture textureBackground;
	Texture textureShip;
	float w, h;
	Background background;
	Actor avatar;
	
	@Override
	public void create () {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		textureBackground = new Texture("sea_sprite.png");
		background = new Background(textureBackground, this);
		textureShip = new Texture("USA1.png");
		avatar = new Actor(w / 2f - textureShip.getWidth() / 2f, 10, textureShip, this);
	}

	public void run() {
		background.run();
	}

	@Override
	public void render () {
		run();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		background.draw();
		avatar.draw();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textureBackground.dispose();
		textureShip.dispose();
	}
}
