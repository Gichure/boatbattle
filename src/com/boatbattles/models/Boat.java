package com.boatbattles.models;

import java.awt.Color;

public enum Boat {
	AIRCRAFT("Aircraft Carrier",5, Color.BLACK,0,0,0),
	BATTLESHIP("Battleship",4, Color.MAGENTA,0,0,0),
	DESTROYER("Destroyer",3, Color.CYAN,0,0,0),
	PATROL("Patrol Boat",2, Color.BLUE,0,0,0);
	
	private String name;
	private int size;
	private Color colour;
	private int startXPosition;
	private int startYPosition;
	private int orientation;
	
	private Boat(String name, int size, Color c, int x, int y, int orientation) {
		this.name = name;
		this.size = size;
		this.colour = c;
		this.startXPosition = x;
		this.startYPosition = y;
		this.orientation = orientation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public int getStartXPosition() {
		return startXPosition;
	}

	public void setStartXPosition(int startXPosition) {
		this.startXPosition = startXPosition;
	}

	public int getStartYPosition() {
		return startYPosition;
	}

	public void setStartYPosition(int startYPosition) {
		this.startYPosition = startYPosition;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	
}
