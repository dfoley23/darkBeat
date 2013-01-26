package com.me.darkbeat;

public class Level{
	
	public int mapArray[];
	private int height;
	private int width;
	
	public Level(int levelWidth, int levelHeight, int level[]){
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