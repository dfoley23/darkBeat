package com.me.darkbeat;

public class Level{
	
	/*TileTypes:
		0: empty
		1: wall
		2: player
		3: open door
		4: close door
		5: cat
		6: wumpus
	*/
	
	public int mapArray[];
	
	private int size;
	private int height;
	private int width;
	private Player player;
	
	public Level(int levelWidth, int levelHeight, int level[], Player asshole){
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
		player = asshole;
		size = height * width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int h){
		height = h;
	}

	public void setWidth(int w){
		width = w;
	}
	
	public void update(){
		int playerPos = player.getPosition();
		switch(mapArray[playerPos]){
			case 0: //empty
				mapArray[playerPos] = 2;
				break;
				
			default: //not empty
				player.setPosition(player.getOldPosition());
				break;
			
		}
	}
}