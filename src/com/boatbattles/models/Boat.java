package com.boatbattles.models;

import java.awt.Color;

public enum Boat {
	AIRCRAFT("Aircraft Carrier",5, Color.BLACK),
	BATTLESHIP("Battleship",4, Color.MAGENTA),
	DESTROYER("Destroyer",3, Color.ORANGE),
	PATROL("Patrol Boat",2, Color.BLUE);
	
	private String name;
	private int size;
	private Color colour;
	
	
	private Boat(String name, int size, Color c) {
		this.name = name;
		this.size = size;
		this.colour = c;
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
	
}
