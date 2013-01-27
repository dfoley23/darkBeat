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
		Texture tex3 = new Texture(Gdx.files.internal("data/title.png"));
		Texture tex4 = new Texture(Gdx.files.internal("data/credits.png"));
		
		tex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		tex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region1 = new TextureRegion(tex1, 0, 0, 117, 41);
		TextureRegion region2 = new TextureRegion(tex2, 0, 0, 107, 43);		
		TextureRegion region3 = new TextureRegion(tex3, 0, 0, 318, 55);
		TextureRegion region4 = new TextureRegion(tex4, 0, 0, 248, 184);
		
		Sprite startSprite = new Sprite(region1);
		startSprite.setSize(0.25f, 0.25f * startSprite.getHeight() / startSprite.getWidth());
		startSprite.setOrigin(0, 0);
		startSprite.setPosition(-startSprite.getWidth()/2-0.24f, -startSprite.getHeight()/2.0f);
		
		Sprite quitSprite = new Sprite(region2);
		quitSprite.setSize(0.25f, 0.25f * quitSprite.getHeight() / quitSprite.getWidth());
		quitSprite.setOrigin(0, 0);
		quitSprite.setPosition((-quitSprite.getWidth()/2)-0.24f, -quitSprite.getHeight()/2-0.1f);
		
		Sprite titleSprite = new Sprite(region3);
		titleSprite.setSize(0.75f, 0.75f * titleSprite.getHeight() / titleSprite.getWidth());
		titleSprite.setOrigin(0, 0);
		titleSprite.setPosition((-titleSprite.getWidth()/2)-0.04f, -titleSprite.getHeight()/2+0.22f);
		
		Sprite creditSprite = new Sprite(region4);
		creditSprite.setSize(0.25f, 0.25f * creditSprite.getHeight() / creditSprite.getWidth());
		creditSprite.setOrigin(0, 0);
		creditSprite.setPosition((-creditSprite.getWidth()/2)+0.17f, -creditSprite.getHeight()/2-0.1f);
		
		intro.addMenuItem( startSprite, Vector2.Zero);
		intro.addMenuItem( quitSprite, Vector2.Zero);
		intro.addMenuItem( titleSprite, Vector2.Zero);
		intro.addMenuItem( creditSprite, Vector2.Zero);
	}

	public void update() {
		if (playing) {
			game.update();
			if(game.switchScreen){
				playing = false;
			}
		} else {
			intro.update();
			if (Gdx.input.isKeyPressed(Keys.SPACE)
					|| Gdx.input.isKeyPressed(Keys.ENTER)) {
				switch (intro.getChoice()) {
				case start:
					playing = true;
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
