package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture textureBackground;
	Texture textureShip;
	Texture textureEnemy;
	Texture textureGameOver;
	Background background;
	List<Actor> actors = new ArrayList<>();
	Random random = new Random();
	Music mainTheme;
	BitmapFont font;
	float w, h;
	int enemyCount = 0;
	int enemyMax = 50;
	int score = 0;
	
	@Override
	public void create () {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		textureBackground = new Texture("sea_sprite.png");
		textureShip = new Texture("USA1.png");
		textureEnemy = new Texture("JPN3.png");
		textureGameOver = new Texture("game_over_screen.png");
		background = new Background(textureBackground, this);
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("main_theme.ogg"));
		actors.add(new Actor(w / 2f - textureShip.getWidth() / 2f, 10, textureShip, this));
		mainTheme.setLooping(true);
		mainTheme.play();
		font = new BitmapFont(
			Gdx.files.internal("verdana.fnt"), Gdx.files.internal("verdana.png"), false
		);
	}

	void generateEnemies() {
		enemyCount++;
		if (enemyCount >= enemyMax){
			actors.add(new Enemy(textureEnemy, this));
			enemyCount = 0;
			enemyMax = 30 + random.nextInt(50);
		}
	}

	void cleanEnemies() {
		List<Actor> aux = actors;
		actors = new ArrayList<>();
		for (Actor a : aux) if (!a.dead) actors.add(a);
	}

	public void run() {
		background.run();
		for (Actor a : actors) a.run();
		generateEnemies();
		cleanEnemies();
	}

	@Override
	public void render () {
		run();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		background.draw();
		for (Actor a : actors) a.draw();
		font.draw(batch, "SCORE: " + score, 1, h);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textureBackground.dispose();
		textureShip.dispose();
		textureEnemy.dispose();
		textureGameOver.dispose();
	}
}
