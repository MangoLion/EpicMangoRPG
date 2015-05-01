package com.mangolion.epicmangorpg.components;

import java.util.LinkedList;

public enum Elements {
	Ice("Ice", new Element("Fire", 1.5f),new Element("Lava", 2), new Element("Wind", 0.5f)),
	Water("Water", new Element("Fire", 0.8f),new Element("Lava", 2.5f)),
	Earth("Earth", new Element("Water", 2), new Element("Lightning", 0.5f), new Element("Fire", 0.5f),new Element("Lava", 1.5f), new Element("Wind", 0.5f)),
	Stone("Stone", new Element("Water", 0.5f), new Element("Fire", 0.5f),new Element("Lava", 2f), new Element("Wind", 0.5f)),
	Lightning("Lightning", new Element("Water", 2), new Element("Earth", 0.5f)),
	Fire("Fire", new Element("Lava", 0f),new Element("Water", 2), new Element("Wind", 2f), new Element("Earth", 1.5f)),
	Lava("Lava", new Element("Fire", 0f),new Element("Water", 2), new Element("Wind", 1.5f), new Element("Earth", 1.5f), new Element("Ice", 2)),
	Wind("Wind", new Element("Lava", 1.5f),new Element("Fire", 1.5f),  new Element("Earth", 0.5f)),
	Plant(false, "Plant", new Element("Fire", 2f),new Element("Lava", 2f) ,new Element("Water", 0.5f),new Element("Lightning", 2f),new Element("Light", -1f)),
	Furry(false, "Furry", new Element("Fire", 2f),new Element("Lava", 2f) ,new Element("Lightning", 2f)),
	ToughHide(false, "Tough Hide",new Element("Lightning", 2f),new Element("Fire", 0.5f),  new Element("Wind", 0.5f), new Element("Ice", 0.5f)),
	Metal(false, "Metal",new Element("Water", 1.5f),new Element("Lightning", 2f),new Element("Fire", 0.5f),  new Element("Wind", 0.5f), new Element("Ice", 0.5f)),
	ToughScales(false, "Tough Scales",new Element("Lightning", 2f) ,new Element("Fire", 0.5f),  new Element("Wind", 0.5f), new Element("Ice", 2f)),
	Feathered(false, "Feathered",new Element("Lightning", 2f) ,new Element("Fire", 1.5f),  new Element("Wind", 1.5f),  new Element("Earth", 0.5f)),
	Light("Light", new Element("Dark", 2f)),
	Dark("Dark", new Element("Light", 2f));
	
	public String name;
	boolean againstSelf = true;
	public Element[] elements;
	private Elements(String name, Element ... elements) {
		this.name = name;
		this.elements = elements;
	}
	
	private Elements(boolean as, String name, Element ... elements) {
		againstSelf = as;
		this.name = name;
		this.elements = elements;
	}
	
	public float calculate(Elements element){
		for (Element e: elements)
			if (e.type.toLowerCase().equals( element.name.toLowerCase()))
				return e.value;
			if (element == this && againstSelf)
				return 0;
		return 1;
	}
	
	public float calculate(String element){
		for (Element e: elements)
			if (e.type.toLowerCase().equals(element.toLowerCase()))
				return e.value;
			if (element.equals(this.name) && againstSelf)
				return 0;
		return 1;
	}
	
	public static  Elements getElement(String ele){
		ele = ele.toLowerCase();
		if (ele.equals("ice"))
			return Ice;
		else if (ele.equals("water"))
		return Water;
		else if (ele.equals("lightning"))
		return Lightning;
		else if (ele.equals("fire"))
		return Fire;
		else if (ele.equals("earth"))
			return Earth;
		else if (ele.equals("wind"))
			return Wind;
		else if (ele.equals("lava"))
			return Lava;
		else if (ele.equals("plant"))
			return Plant;
		else if (ele.equals("furry"))
			return Furry;
		else if (ele.equals("light"))
			return Light;
		else if (ele.equals("dark"))
			return Dark;
		else if (ele.equals("tough hide"))
			return ToughHide;
		else if (ele.equals("tough scales"))
			return ToughScales;
		else if (ele.equals("feathered"))
			return Feathered;
		else if (ele.equals("stone"))
			return Stone;
		else if (ele.equals("metal"))
			return Metal;
		return null;
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
