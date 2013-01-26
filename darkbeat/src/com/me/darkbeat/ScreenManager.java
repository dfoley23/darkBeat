package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScreenManager {

	public StartScreen intro;
	public GameScreen game;
	public boolean playing = false;

	ScreenManager() {
		intro = new StartScreen();
		game = new GameScreen();

		Texture tex1 = new Texture(Gdx.files.internal("data/startItem.png"));
		Texture tex2 = new Texture(Gdx.files.internal("data/quitItem.png"));
		
		tex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region1 = new TextureRegion(tex1, 0, 0, 128, 64);
		TextureRegion region2 = new TextureRegion(tex2, 0, 0, 128, 64);
		
		Sprite startSprite = new Sprite(region1);
		startSprite.setSize(0.25f, 0.25f * startSprite.getHeight() / startSprite.getWidth());
		startSprite.setOrigin(0, 0);
		startSprite.setPosition(-startSprite.getWidth()/2-0.07f, -startSprite.getHeight()/2+0.07f);
		
		Sprite quitSprite = new Sprite(region2);
		quitSprite.setSize(0.25f, 0.25f * quitSprite.getHeight() / quitSprite.getWidth());
		quitSprite.setOrigin(0, 0);
		quitSprite.setPosition((-quitSprite.getWidth()/2)-0.07f, -quitSprite.getHeight()/2-0.07f);
		
		intro.addMenuItem( startSprite, Vector2.Zero);

		intro.addMenuItem( quitSprite, new Vector2(Gdx.graphics.getWidth() / 2.0f,
				(Gdx.graphics.getHeight() / 2.0f) + 50.0f));
	}

	public void update() {
		if (playing) {
			game.update();
		} else {
			intro.update();
			if (Gdx.input.isKeyPressed(Keys.SPACE)
					|| Gdx.input.isKeyPressed(Keys.ENTER)) {
				switch (intro.getChoice()) {
				case start:
					playing = true;
					intro.dispose();
					break;
				case quit:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		}
	}

	public void draw() {
		if(playing){
			game.draw();
		} else {
			intro.draw();
		}
	}

}
