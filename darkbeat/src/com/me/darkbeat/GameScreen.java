package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen {
	
	private SpriteBatch batch = null;
	public GameScreen(){
		batch = new SpriteBatch();
		
	}
	
	public void update(){
		
	}
	
	public void dispose(){
		
	}
	
	public void render(){
		Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
		Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
		
		batch.begin(); 

		batch.end();
		
	}

}
