package com.me.darkbeat;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StartScreen {
	public ArrayList<Sprite> uiElements;
	public ArrayList<Vector2> uiElementsPos;	
	private SpriteBatch batch = null;
	Choice currentChoice;
	public enum Choice {
		start, quit;
	}

	public StartScreen() {
		batch = new SpriteBatch( );
		uiElements = new ArrayList<Sprite>();
		uiElementsPos = new ArrayList<Vector2>();
	}

	public void update() {
		for (int i = 0; i < uiElements.size(); i++) {
			uiElements.get(i).setPosition(uiElementsPos.get(i).x,
					uiElementsPos.get(i).y);
		}
	}

	public Choice getChoice(){
		return currentChoice;
	}
	
	public void dispose() {
		batch.dispose(); 
	}

	public void render() {
		Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
		Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
		
		batch.begin(); 
		for(Sprite spr: uiElements){
			spr.draw(batch);
		}
		batch.end();

	}

	public void addElement(Sprite spr, Vector2 pos) {
		uiElements.add(spr);
		uiElementsPos.add(pos);
	}

}
