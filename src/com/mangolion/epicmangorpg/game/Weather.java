package com.mangolion.epicmangorpg.game;

import java.util.Random;

public class Weather {

	public static Weather Calm = new Weather("Calm", 40, 65, 30, 60, 80, 100,
			0, 40),
			Stormy = new Weather("Stormy", 10, 60, 40, 70, 40, 80, 40, 80),
			Sunny = new Weather("Sunny", 40, 90, 10, 50, 85, 100, 0, 50),
			Cloudy = new Weather("Cloudy", 35, 70, 35, 70, 40, 85, 0, 60),
			Windy = new Weather("Windy", 30, 60, 0, 60, 70, 90, 50, 100),
			Typhoons = new Weather("Typhoon", 5, 45, 40, 80, 20, 60, 60, 100),
			SandStorm = new Weather("Sand Storm", 20, 80, 40, 90, 5, 60, 40, 90),
			SnowStorm = new Weather("Snow Storm", 0, 40, 0, 10, 10, 65, 40, 90),
			Tornaldo = new Weather("Tornaldo", 20, 70, 20, 60, 5, 50, 70, 100),
			Humid = new Weather("Humid", 45, 75, 60, 100, 80, 100, 0, 40),
			Foggy = new Weather("Foggy", 20, 60, 60, 90, 10, 65, 0, 30),
			Mist = new Weather("Mist", 10, 50, 50, 75, 10, 50, 0, 35),
			Snow = new Weather("Snow", 0, 40, 0, 40, 40, 85, 0, 60),
			Hail = new Weather("Hail", 0, 40, 0, 40, 5, 50, 40, 85),
			Sleet = new Weather("Sleet", 0, 50, 0, 40, 40, 85, 30, 60),
			Wildfire = new Weather("Wildfire", 60, 90, 0, 30, 5, 70, 0, 30),
			Blizzard = new Weather("Snow Storm", 0, 40, 0, 10, 10, 65, 40, 90),
			Hot = new Weather("Hot", 70, 100, 40, 80, 85, 100, 0, 30),
			Cold = new Weather("Cold", 0, 40, 0, 20, 70, 100, 0, 60),
			Dry = new Weather("Dry", 30, 70, 0, 10, 80, 100, 0, 40);

	public static Weather weathers[] = { Calm, Blizzard, Cloudy, Cold, Dry,
			Foggy, Hail, Hot, Humid, Windy, Wildfire, Typhoons, Tornaldo,
			Sunny, Stormy, SnowStorm, Snow, Sleet, SandStorm, Mist };

	public static Random rand = new Random();

	public static Weather getRandomWeather() {
		return weathers[rand.nextInt(weathers.length)].generateValues();
	}

	public String name;
	int tempMin, tempMax, humidMin, humidMax, visibilityMin, visibilityMax,
			windMin, windMax;
	public int temp, humid, visibility, wind;

	public Weather(String name, int tempMin, int tempMax, int humidMin,
			int humidMax, int visibilityMin, int visibilityMax, int windMin,
			int windMax) {
		this.name = name;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.humidMin = humidMin;
		this.humidMax = humidMax;
		this.visibilityMin = visibilityMin;
		this.visibilityMax = visibilityMax;
		this.windMin = windMin;
		this.windMax = windMax;
	}

	public Weather generateValues() {
		temp = tempMin + rand.nextInt(tempMax - tempMin);
		humid = humidMin + rand.nextInt(humidMax - humidMin);
		visibility = visibilityMin
				+ rand.nextInt(visibilityMax - visibilityMin);
		wind = windMin + rand.nextInt(windMax - windMin);
		return this;
	}

	public void syncTerrain(Terrain terrain) {
		temp = (temp + terrain.temp) / 2;
		humid = (humid + terrain.humid) / 2;
		visibility = (visibility + terrain.visibility) / 2;
		/*visibility -= Math.abs(terrain.visibility - visibility);
		if (visibility < 5)
			visibility = 5;*/
	}

	public void change() {
		int num;
		num = (rand.nextInt(5) == 0) ? 7 : 2;
		temp += rand.nextInt(num) - rand.nextInt(num);
		num = (rand.nextInt(5) == 0) ? 7 : 2;
		humid += rand.nextInt(num) - rand.nextInt(num);
		num = (rand.nextInt(5) == 0) ? 7 : 2;
		visibility += rand.nextInt(num) - rand.nextInt(num);
		num = (rand.nextInt(5) == 0) ? 7 : 2;
		wind += rand.nextInt(num) - rand.nextInt(num);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
