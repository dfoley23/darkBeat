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
	
	public Level(int levelWidth, int levelHeight, int level[]){
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
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
	}
}