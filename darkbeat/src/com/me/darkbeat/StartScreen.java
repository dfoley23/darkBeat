package com.me.darkbeat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StartScreen {
	public ArrayList<Sprite> menuText;
	public ArrayList<Vector2> menuTextPos;
	private Sprite menuBall;
	Choice currentChoice;
	private boolean keyReleased = true;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	public enum Choice {
		start, quit;
	}

	public StartScreen() {	
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
	
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		menuText = new ArrayList<Sprite>();
		menuTextPos = new ArrayList<Vector2>();
		currentChoice = Choice.start;

		Texture tex1 = new Texture(Gdx.files.internal("data/startItem.png"));
		//Texture tex2 = new Texture(Gdx.files.internal("data/quitItem.png"));
		Texture tex3 = new Texture(Gdx.files.internal("data/title.png"));
		Texture tex4 = new Texture(Gdx.files.internal("data/credits.png"));
		
		tex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//tex2.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		tex3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region1 = new TextureRegion(tex1, 0, 0, 117, 41);
		//TextureRegion region2 = new TextureRegion(tex2, 0, 0, 107, 43);		
		TextureRegion region3 = new TextureRegion(tex3, 0, 0, 318, 55);
		TextureRegion region4 = new TextureRegion(tex4, 0, 0, 248, 184);
		
		Sprite startSprite = new Sprite(region1);
		startSprite.setSize(0.25f, 0.25f * startSprite.getHeight() / startSprite.getWidth());
		startSprite.setOrigin(0, 0);
		startSprite.setPosition(-startSprite.getWidth()/2-0.24f, -startSprite.getHeight()/2.0f);
		
//		Sprite quitSprite = new Sprite(region2);
//		quitSprite.setSize(0.25f, 0.25f * quitSprite.getHeight() / quitSprite.getWidth());
//		quitSprite.setOrigin(0, 0);
//		quitSprite.setPosition((-quitSprite.getWidth()/2)-0.24f, -quitSprite.getHeight()/2-0.1f);
//		
		Sprite titleSprite = new Sprite(region3);
		titleSprite.setSize(0.75f, 0.75f * titleSprite.getHeight() / titleSprite.getWidth());
		titleSprite.setOrigin(0, 0);
		titleSprite.setPosition((-titleSprite.getWidth()/2)-0.04f, -titleSprite.getHeight()/2+0.22f);
		
		Sprite creditSprite = new Sprite(region4);
		creditSprite.setSize(0.25f, 0.25f * creditSprite.getHeight() / creditSprite.getWidth());
		creditSprite.setOrigin(0, 0);
		creditSprite.setPosition((-creditSprite.getWidth()/2)+0.17f, -creditSprite.getHeight()/2-0.1f);
		
		addMenuItem( startSprite, Vector2.Zero);
		//addMenuItem( quitSprite, Vector2.Zero);
		addMenuItem( titleSprite, Vector2.Zero);
		addMenuItem( creditSprite, Vector2.Zero);
		
		tex1 = new Texture(Gdx.files.internal("data/menuBall.png"));
		tex1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		region1 = new TextureRegion(tex1, 0, 0, 32, 32);
		
		menuBall = new Sprite(region1);
		menuBall.setSize(0.05f, 0.05f * menuBall.getHeight() / menuBall.getWidth());
		menuBall.setOrigin(0, 0);		
		
		
	}

	public void update() {
		if (keyReleased) {
			if (Gdx.input.isKeyPressed(Keys.W) ||
					Gdx.input.isKeyPressed(Keys.UP)) {
				switch (currentChoice) {
				case quit:
					currentChoice = Choice.start;
					break;
				default:
					break;
				}
			} 
//			else if (Gdx.input.isKeyPressed(Keys.S) ||
//					Gdx.input.isKeyPressed(Keys.DOWN)) {
//				switch (currentChoice) {
//				case start:
//					currentChoice = Choice.quit;
//					break;
//				default:
//					break;
//				}
//			} 
			else {
				keyReleased = true;
			}
		}
	}

	public Choice getChoice() {
		return currentChoice;
	}

	public void dispose() {
		batch.dispose();
	}

	public void draw(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = 0; i < menuText.size(); i++) {
			switch (i) {
			case 0:
				if (currentChoice == Choice.start) {
					menuText.get(i).setColor(Color.WHITE);
					menuBall.setPosition(menuText.get(i).getX()-0.07f, menuText.get(i).getY());
					menuBall.draw(batch);
					menuText.get(i).draw(batch);
				} else {
					menuText.get(i).setColor(Color.GRAY);
					menuText.get(i).draw(batch);
				}
				break;
			case 1:
				if (currentChoice == Choice.quit) {
					menuText.get(i).setColor(Color.WHITE);
					menuBall.setPosition(menuText.get(i).getX()-0.07f, menuText.get(i).getY());
					menuBall.draw(batch);
					menuText.get(i).draw(batch);
				} else {
					menuText.get(i).setColor(Color.GRAY);
					menuText.get(i).draw(batch);
				}
				break;
			default:
				menuText.get(i).setColor(Color.GRAY);
				menuText.get(i).draw(batch);
				break;
			}
		}
		batch.end();
	}

	public void addMenuItem(Sprite spr, Vector2 pos) {
		menuText.add(spr);
		menuTextPos.add(pos);
	}
	
}
