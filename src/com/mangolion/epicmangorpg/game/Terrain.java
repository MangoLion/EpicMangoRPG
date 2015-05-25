package com.mangolion.epicmangorpg.game;

import java.util.Random;

public class Terrain {

	public static Terrain Beach = new Terrain("Beach", 20, 50, 30, 90, 50, 90, 85, 100),
			Swamp = new Terrain("Swamp", 45, 90, 50, 85, 40, 85, 50, 75),
			Plain = new Terrain("Plain", 0, 20, 40, 60, 0, 50, 85, 100),
			HighMountain = new Terrain("High Mountain", 40, 80, 0, 40, 0, 30, 40, 75),
			Forest = new Terrain("Forest", 30, 70, 20, 80, 30, 70, 35, 70),
			RoofedForest = new Terrain("Roofed Forest", 40, 85, 30, 90, 50, 90, 20, 50),
			Jungle = new Terrain("Jungle", 50, 90, 30, 90, 50, 90, 30, 70),
			Taiga = new Terrain("Taiga", 10, 30, 20, 45, 0, 50, 85, 100),
			Tundra =  new Terrain("Tundra", 10, 30, 20, 45, 0, 50, 85, 100),
			Desert = new Terrain("Desert", 20, 60, 50, 85, 30, 70, 25, 85),
			Arctic = new Terrain("Arctic", 20, 60, 0, 40, 20, 40, 30, 90);
	
	public static Terrain terrains[] = {Beach, Swamp, Plain, HighMountain, Forest, RoofedForest, Jungle, Taiga, Tundra};
	
	public static Terrain getRandomTerrain(){
		return terrains[rand.nextInt(terrains.length)].generateValues();
	}
	public String name;
	static Random rand = new Random();
	
	int ruggedMin, ruggedMax,  tempMin, tempMax, humidMin, humidMax, visibilityMin, visibilityMax;
	public int  temp, humid, visibility, rugged;
	public Terrain(String name, int ruggedMin, int ruggedMax, int  tempMin, int tempMax, int humidMin, int humidMax, int visibilityMin, int visibilityMax) {
		this.name = name;
		 this.ruggedMin = ruggedMin;
		 this.ruggedMax = ruggedMax;
		 this. tempMin = tempMin;
		 this.tempMax = tempMax;
		 this.humidMin = humidMin;
		 this.humidMax = humidMax;
		 this.visibilityMin = visibilityMin;
		 this.visibilityMax = visibilityMax;
	}
	
	public Terrain generateValues(){
		temp =tempMin +  rand.nextInt(tempMax - tempMin);
		humid =humidMin +  rand.nextInt(humidMax - humidMin);
		visibility =visibilityMin +  rand.nextInt(visibilityMax - visibilityMin);
		temp =tempMin +  rand.nextInt(tempMax - tempMin);
		rugged =ruggedMin +  rand.nextInt(ruggedMax - ruggedMin);
		return this;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
