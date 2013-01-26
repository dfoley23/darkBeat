package com.me.darkbeat;

import com.me.darkbeat.StartScreen.Choice;

public class ScreenManager {
	
	public StartScreen intro;
	public GameScreen game;
	public boolean playing;
	
	ScreenManager(){
		intro = new StartScreen();
		game = new GameScreen();
	}
	
	public void update(){
		if(intro.getChoice() == Choice.start){
			playing = true;
		}
		if(playing){
			game.update();
		} else {
			intro.update();
		}
	}

}
