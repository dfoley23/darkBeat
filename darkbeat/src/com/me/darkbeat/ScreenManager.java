package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ScreenManager {

	public StartScreen intro;
	public GameScreen game;
	private CreditScreen creditScreen;
	public boolean playing = false;
	public boolean credits = false;

	ScreenManager() {
		intro = new StartScreen();
		game = new GameScreen();
	}

	public void update() {
		if (playing) {
			game.update();
			if(game.switchScreen){
				playing = false;
				credits = true;
				creditScreen = new CreditScreen();
				game = null;
			}
		} else if (!credits){
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
		} else {
			creditScreen.update();
			if (creditScreen.endCredits){
				credits = false;
				creditScreen.endCredits = false;
				creditScreen = null;
				game = new GameScreen();
			}
		}
	}

	public void draw() {
		if(playing){
			game.draw();
		} else if(!credits){
			intro.draw();
		} else{
			creditScreen.draw();
		}
	}

}
