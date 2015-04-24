package com.mangolion.epicmangorpg.components;

import java.util.LinkedList;

public enum Elements {
	Ice("Ice", new Element(Elements.getFire(), 1.5f),new Element(Elements.getLava(), 2), new Element(Elements.getWind(), 0.5f)),
	Water("Water", new Element(Elements.getFire(), 0.8f),new Element(Elements.getLava(), 2.5f)),
	Earth("Earth", new Element(Elements.Water, 2), new Element(Elements.getLightning(), 0.5f), new Element(Elements.getFire(), 0.5f),new Element(Elements.getLava(), 1.5f), new Element(Elements.getWind(), 0.5f)),
	Lightning("Lightning", new Element(Elements.Water, 2), new Element(Elements.Earth, 0.5f)),
	Fire("Fire", new Element(Elements.getLava(), 0f),new Element(Elements.getWater(), 2), new Element(Elements.getWind(), 2f), new Element(Elements.getEarth(), 1.5f)),
	Lava("Lava", new Element(Elements.getFire(), 0f),new Element(Elements.getWater(), 2), new Element(Elements.getWind(), 1.5f), new Element(Elements.getEarth(), 1.5f), new Element(Elements.getIce(), 2)),
	Wind("Wind", new Element(Elements.getLava(), 1.5f),new Element(Elements.getFire(), 1.5f),  new Element(Elements.getEarth(), 0.5f)),
	Plant("Plant", new Element(Elements.getFire(), 2f),new Element(Elements.getLava(), 2f) ,new Element(Elements.getWater(), 0.5f),new Element(Elements.getLightning(), 2f),new Element(Elements.getLight(), -1f)),
	Furry("Furry", new Element(Elements.getFire(), 2f),new Element(Elements.getLava(), 2f) ,new Element(Elements.getLightning(), 2f)),
	Light("Light", new Element(Elements.getDark(), 2f)),
	Dark("Dark", new Element(Elements.getLight(), 2f));
	
	public String name;
	public Element[] elements;
	private Elements(String name, Element ... elements) {
		this.name = name;
		this.elements = elements;
	}
	
	public float calculate(Elements element){
		for (Element e: elements)
			if (e.type == element)
				return e.value;
		return 1;
	}
	
	public static Elements getIce(){
		return Ice;
	}
	
	public static Elements getWater(){
		return Water;
	}
	public static Elements getEarth(){
		return Earth;
	}
	public static Elements getLightning(){
		return Lightning;
	}
	public static Elements getFire(){
		return Fire;
	}
	
	public static Elements getLava(){
		return Lava;
	}
	
	public static Elements getWind(){
		return Wind;
	}
	
	public static Elements getLight(){
		return Light;
	}
	
	public static Elements getDark(){
		return Dark;
	}
}
