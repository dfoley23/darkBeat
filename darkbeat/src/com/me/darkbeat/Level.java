package com.me.darkbeat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Level{
	
	public int mapArray[];
	private int height;
	private int width;
	
	private Sound ohYeah;
	
	public Level(int levelWidth, int levelHeight, int level[]){
		ohYeah = Gdx.audio.newSound(Gdx.files.internal("data/sounds/oh_yeah_wav_cut.wav"));
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void switchEnemy(int newIndex, int oldIndex, int category){
		mapArray[oldIndex] = 0;
		mapArray[newIndex] = category;
	}
	
	public Cat[] setCats(){
		int count = 0;
		for(int i = 0; i < (width * height); i++){
			if(mapArray[i] == 3){
				count++;
			}
		}
		Cat[] catList = new Cat[count];
		int catNumber = 0;
		for(int i = 0; i < (width * height); i++){
			if(mapArray[i] == 3){
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
	
	public void checkPlayer(Player player){
		if(mapArray[player.getPosition()] == 0) {
			mapArray[player.getOldPosition()] = 0;
			mapArray[player.getPosition()] = 2;
		} else {
			switch (mapArray[player.getPosition()]){
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
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