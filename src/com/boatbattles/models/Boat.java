package com.boatbattles.models;

import java.awt.Color;

public class Boat {
	private String type; //the type of the boat: Aircraft Cramer, Battleship, Destroyer, Patrol Boat
    private int length; //the length of the ship:5,4,3,2
    private int width = 1; //the width of the ship, which is 1 grid wide
    private Color color; //the colour of the ship: 
    private String status; //the current status of the boat: untouched, hit, sunk
    
    //add start position to track where it is
    //add an array of occupied cells eg [1A,2A], [2A,2B,2C]

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public Boat() {
    }

    public Boat(String type, int length, Color color, String status) {
        this.type = type;
        this.length = length;
        this.color = color;
        this.status = status;
    }
    
}
