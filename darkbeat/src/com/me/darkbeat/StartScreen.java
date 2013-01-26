package com.me.darkbeat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StartScreen {
	public ArrayList<Sprite> uiElements;
	public ArrayList<Vector2> uiElementsPos;
	public ArrayList<Sprite> menuText;
	public ArrayList<Vector2> menuTextPos;
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

		uiElements = new ArrayList<Sprite>();
		uiElementsPos = new ArrayList<Vector2>();
		menuText = new ArrayList<Sprite>();
		menuTextPos = new ArrayList<Vector2>();
		currentChoice = Choice.start;
	}

	public void update() {
		for (int i = 0; i < uiElements.size(); i++) {
			uiElements.get(i).setPosition(uiElementsPos.get(i).x,
					uiElementsPos.get(i).y);
		}
		for (int i = 0; i < menuText.size(); i++) {
			//menuText.get(i).setPosition(menuTextPos.get(i).x,
				//	menuTextPos.get(i).y);
		}		
		if (keyReleased) {
			if (Gdx.input.isKeyPressed(Keys.W)) {
				switch (currentChoice) {
				case quit:
					currentChoice = Choice.start;
					break;
				default:
					break;
				}
			} else if (Gdx.input.isKeyPressed(Keys.S)) {
				switch (currentChoice) {
				case start:
					currentChoice = Choice.quit;
					break;
				default:
					break;
				}
			} else {
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
		for (Sprite spr : uiElements) {
			spr.draw(batch);
		}
		for (int i = 0; i < menuText.size(); i++) {
			switch (i) {
			case 0:
				if (currentChoice == Choice.start) {
					menuText.get(i).setColor(Color.RED);
					menuText.get(i).draw(batch);
				} else {
					menuText.get(i).setColor(Color.GRAY);
					menuText.get(i).draw(batch);
				}
				break;
			case 1:
				if (currentChoice == Choice.quit) {
					menuText.get(i).setColor(Color.RED);
					menuText.get(i).draw(batch);
				} else {
					menuText.get(i).setColor(Color.GRAY);
					menuText.get(i).draw(batch);
				}
				break;
			default:
				break;
			}
		}
		batch.end();
	}

	public void addElement(Sprite spr, Vector2 pos) {
		uiElements.add(spr);
		uiElementsPos.add(pos);
	}

	public void addMenuItem(Sprite spr, Vector2 pos) {
		menuText.add(spr);
		menuTextPos.add(pos);
	}
	
}
