package com.me.darkbeat;

public class Level{
	
	public int mapArray[];
	
	private int height;
	private int width;
	private Player player;
	
	public Level(int levelWidth, int levelHeight, int level[], Player asshole){
		mapArray = level;
		height = levelHeight;
		width = levelWidth;
		player = asshole;
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
}