package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Player nickCage;
	private Level testlevel;
	private int lWidth;
	private int lHeight;
	private int[] test = new int[100];

	
	public GameScreen(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		lWidth = 10;
		lHeight = 10;
	
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		for(int i = 0; i < 10; i++){
			 test[i] = 1;
		 }
		 for(int i = 10; i < 90; i++){
			 if(i % lWidth == 9 || i % lHeight == 0)
				 test[i] = 1;
			 else
				 test[i] = 0;
		 }
		 for(int i = 90; i < 100; i++){
			 test[i] = 1;
		 }
		 
		 nickCage = new Player(45,10);
		 
		 testlevel = new Level(lWidth, lHeight, test);
		 
		 testDraw();
		
	}
	
	public void update(){
		nickCage.update();
		if (nickCage.changed){
			int playerPos = nickCage.getPosition();
			int oldPlayerPos = nickCage.getOldPosition();
			switch(test[playerPos]){
				case 0: //empty
					test[oldPlayerPos] = 0;
					test[playerPos] = 2;
					break;
					
				default: //not empty
					nickCage.setPosition(nickCage.getOldPosition());
					break;
			}
			
			testDraw();
			nickCage.changed = false;
		}
	}
	
	public void dispose(){
		
	}
	
	public void draw(){
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();
		
	}
	
	public void testDraw(){
		int p = 0;
		for(int i = 0; i < lHeight; i++){
			for(int j = 0; j < lWidth; j++){
				System.out.print(test[p]);
				System.out.print(" ");
				p++;
			}
			System.out.print("\n");
		}
		System.out.println("Position: " + nickCage.getPosition());
		System.out.println("Facing: " + nickCage.getDirection());
	}

}
