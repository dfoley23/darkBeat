package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Level{
	
	public int mapArray[];
	private int height;
	private int width;
	private Sound footSteps;
	private Sound ohYeah;
	private Sound openDoor;
	private Sound catMEOW;
	public int doorPos;
	
	public Level(int levelWidth, int levelHeight, int level[]){
		ohYeah = Gdx.audio.newSound(Gdx.files.internal("data/sounds/oh_yeah_wav_cut.wav"));
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
		footSteps = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/footsteps.ogg"));
		openDoor = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/openDoor.ogg"));
		catMEOW = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/Meow.ogg"));
		doorPos = -1;

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void switchEnemy(int newIndex, int oldIndex, int category) {
		mapArray[oldIndex] = 0;
		mapArray[newIndex] = category;
	}

	public Cat[] setCats() {
		int count = 0;
		for (int i = 0; i < (width * height); i++) {
			if (mapArray[i] == 3) {
				count++;
			}
		}
		Cat[] catList = new Cat[count];
		int catNumber = 0;
		for (int i = 0; i < (width * height); i++) {
			if (mapArray[i] == 3) {
				catList[catNumber] = new Cat(i, this);
				catNumber++;
			}
		}
		return catList;
	}

	/* Types
	 * 0: empty
	 * 1: wall
	 * 2: player 
	 * 3: cat
	 * 4: closed door
	 * 5: open door
	 * 6:
	 * 7:
	 * 8: Heart
	 * 9: End goal
	 */
	
	public void checkPlayer(Player player) {
		if(!player.isAtDoor && doorPos > 0 &&
				mapArray[player.getPosition()] != 5){ 
			mapArray[doorPos] = 4;
			doorPos = -1;
		}
		if (mapArray[player.getPosition()] == 0 || 
				mapArray[player.getPosition()] == 5) {
			if(mapArray[player.getPosition()] == 5){
				switch(player.getDirection()){
				case North:
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition()-width);
					break;
				case South:
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition()+width);
					break;
				case East:
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition()+1);
					break;
				case West:
					mapArray[player.getPosition()] = 4;
					player.setPosition(player.getPosition()-1);
					break;
				}
			} else {
				mapArray[player.getOldPosition()] = 0;
				mapArray[player.getPosition()] = 2;
				footSteps.play(0.3f);
			}
		} else {
			switch (mapArray[player.getPosition()]) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				catMEOW.play(0.6f);
				break;
			case 4:
				mapArray[player.getPosition()] = 5;
				openDoor.play(0.6f);
				doorPos = player.getPosition();
				player.isAtDoor = true;
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				mapArray[player.getPosition()] = 0;
				player.hasHeart = true;
				player.setVolume(1.0f);
				ohYeah.play();
				break;
			case 9:
				break;
			default:
				break;
			}
			player.setPosition(player.getOldPosition());
		}
	}

}