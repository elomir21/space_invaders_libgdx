package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	Texture textureFire;
	Texture textureGameOver;
	List<Texture> explosion = new ArrayList<>();
	Background background;
	List<Actor> actors = new ArrayList<>();
	List<Actor> novos = new ArrayList<>();
	Random random = new Random();
	Music mainTheme;
	Sound sndFire;
	BitmapFont font;
	float w, h;
	int enemyCount = 0;
	int enemyMax = 50;
	int score = 0;
	boolean finished = false;
	boolean pause = false;

	@Override
	public void create () {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		textureBackground = new Texture("sea_sprite.png");
		textureShip = new Texture("USA5.png");
		textureEnemy = new Texture("JPN3.png");
		textureFire = new Texture("bullets1.png");
		textureGameOver = new Texture("game_over_screen.png");
		explosion.add(new Texture("Explosion1.png"));
		explosion.add(new Texture("Explosion2.png"));
		explosion.add(new Texture("Explosion3.png"));
		background = new Background(textureBackground, this);
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("main_theme.ogg"));
		actors.add(new Player(w / 2f - textureShip.getWidth() / 2f, 10, textureShip, this));
		mainTheme.setLooping(true);
		mainTheme.play();
		sndFire = Gdx.audio.newSound(Gdx.files.internal("Fire.ogg"));
		font = new BitmapFont(
			Gdx.files.internal("verdana.fnt"), Gdx.files.internal("verdana.png"), false
		);
	}

	void shoot(float x, float y){
		novos.add(new Fire(x - (textureFire.getWidth() /2f), y, textureFire, this));
	}

	void generateEnemies() {
		if(pause) return;
		enemyCount++;
		if (enemyCount >= enemyMax){
			actors.add(new Enemy(textureEnemy, this));
			enemyCount = 0;
			enemyMax = 30 + random.nextInt(50);
		}
	}

	void explosion(float x, float y){
		novos.add(new Explosion(x, y, explosion, this));
	}

	void clean() {
		List<Actor> aux = actors;
		actors = new ArrayList<>();
		for (Actor a : aux) if (!a.dead) actors.add(a);
		actors.addAll(novos);
		novos.clear();
	}

	public void run() {
		background.run();
		for (Actor a : actors) a.run();
		generateEnemies();
		clean();
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
			pause = !pause;
			if(pause){
				mainTheme.stop();
			}else{
				mainTheme.play();
			}
		}
		if(finished){
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
				finished = false;
				actors.clear();
				actors.add(new Player(w / 2f - textureShip.getWidth() / 2f, 10, textureShip, this));
				score = 0;
			}
		}
	}

	@Override
	public void render () {
		run();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		background.draw();
		for (Actor a : actors) a.draw();
		font.draw(batch, "SCORE: " + score, 1, h);
		if(finished) {
			batch.draw(textureGameOver, w / 2f - textureGameOver.getWidth() / 2f,
					h / 4f);
		}
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
