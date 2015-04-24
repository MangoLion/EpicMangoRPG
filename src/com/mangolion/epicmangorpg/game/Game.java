package com.mangolion.epicmangorpg.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ConcurrentDoublyLinkedList;
import com.mangolion.epicmangorpg.components.Tick;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.floors.Floor;
import com.mangolion.epicmangorpg.floors.Floor.Spawn;
import com.mangolion.epicmangorpg.frames.FrameCharacterInfo;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;

public class Game {
	public static Game game;
	Random rand = new Random();
	public String playerName = "";
	public LinkedList<Character> charsAllies = new LinkedList<Character>();
	public LinkedList<Character> charsEnemies = new LinkedList<Character>();
	public ConcurrentDoublyLinkedList<Tick> ticks = new ConcurrentDoublyLinkedList<Tick>();
	public ConcurrentDoublyLinkedList<Event> events = new ConcurrentDoublyLinkedList<Event>();
	public LinkedList<Floor>floors = new LinkedList<Floor>();
	public int currentFloor = 0;
	public Weather weather;
	public Terrain terrain;
	public float timePassed = 0, lastWeatherTick = 0, timeSinceTick = 0, floorPercent = 0;
	public boolean nextFloor = true, lastFloor = false; 
	public Timer timer = new Timer(100, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
	}),
	timer2;
	public int maxEnemies = 2;

	public Game() {
		game = this;
	}

	public LinkedList<Character> getAllChars() {
		LinkedList<Character> result = new LinkedList<Character>();
		result.addAll(charsAllies);
		result.addAll(charsEnemies);
		return result;
	}
	FrameGame frame = FrameGame.instance;
	public void begin() {
		if (lastFloor == true){
			if (currentFloor > 0 && floorPercent <= 0){
				currentFloor --;
				floorPercent = 100;
				//lastFloor = false;
			}
		//	floorPercent -= 20;
		}
		
		if (nextFloor == true){
				if (floorPercent >= 100){
					currentFloor ++;
					//nextFloor = false;
					floorPercent = 0;
				}
				//floorPercent += 20;
		}
		
		if (timer != null)
			timer.stop();
		
		timePassed = 0;
		ticks.clear();
		charsAllies.clear();
		charsEnemies.clear();
		
		floors.addAll(Floor.getFloors());
		weather = Weather.getRandomWeather();
		terrain = Terrain.getRandomTerrain();
		weather.generateValues();
		weather.syncTerrain(terrain);
		
		timer2 = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (playerName.equals(""))
					playerName = JOptionPane.showInputDialog("Enter your name:", "Mango");
				
				
				for (FrameCharacterInfo info : FrameGame.instance.charInfos)
					info.dispose();
				frame.tfNarration.setText("");
				frame.tfTime.setText("");
				frame.pbFloor.setValue((int) floorPercent);
				frame.pbFloor.setString("Floor: "+currentFloor  + " (" + floorPercent + "%)");
				frame.updateWeather(weather);
				frame.updateTerrain(terrain);
				frame.modelMonster.clear();
				for (int i = 0; i < floors.get(currentFloor).spawns.size(); i ++)
					frame.modelMonster.addElement(floors.get(currentFloor).getSpawn(i));
				
				if (CharacterPlayer.instance == null)
					new CharacterPlayer(playerName);
				CharacterPlayer.instance.reset();
				
				maxEnemies = charsAllies.size();
				charsAllies.add(CharacterPlayer.instance);
				charsEnemies.add(floors.get(currentFloor).getSpawn());
				for (int i = 0; i < maxEnemies - 1; i ++)
				//	if (rand.nextInt(4) == 0)
						charsEnemies.add(floors.get(currentFloor).getSpawn());
				
					//for (int i = 0 ; i <100; i ++)
					//	System.out.println(floors.get(currentFloor).getSpawn().name);
			LinkedList<Character> allChars = getAllChars();
				for (Character character : allChars) {
					addTick(character, rand.nextFloat(), Tick.ACTION);
				}
				
					frame.	updateInfoTab();
				/*for (Character character : charsEnemies) {
					addTick(character, rand.nextFloat()*2, Tick.ACTION);
				}
				addTick(charsAllies.getFirst(), 0, Tick.ACTION);*/
				//StylePainter.appendTime(0.0f);
				//Utility.narrate("The battle has started!\n");
				//nextTick(false);
				timer.start();
				frame.tfCommand.setText("use skill slash on " + charsEnemies.getFirst().name.replace(" ", ""));
				timer2.stop();
			}
		});
		timer2.start();
	}

	public void update() {
		timePassed = (float) (Math.round((timePassed + 0.01) * 100d) / 100d);
		updateAll(0.01f, null, true);
		for (Tick tick: ticks)
			if (tick.time < 0)
				System.out.println("out of sync");
	}

	public void updateTick(Character character, float time, int type) {
		for (Tick tick : ticks)
			if (tick.character == character) {
				tick.time = time;
				tick.type = type;
			}
	}

	public void addTick(Character character, float time, int type) {
		ticks.add(new Tick(character, time, type));
	}

	public void nextTick(boolean update) {

		Tick next = ticks.getFirst();
		for (Tick tick : ticks) {
			if (tick.time < next.time)
				next = tick;
		}
		ticks.remove(next);
		timePassed = Math.round((timePassed + next.time)*100f)/100;

		if (update)
			updateAll(next.time, next.character, false);

		tick(next);

		if (timePassed - lastWeatherTick > 5) {
			weather.change();
			FrameGame.instance.updateWeather(weather);
			lastWeatherTick = timePassed;
		}

		if (next.character != CharacterPlayer.instance
				|| (next.character == CharacterPlayer.instance && next.character.skillCurrent != null))
			nextTick(true);
		else
			timer.stop();
	}

	public Tick findTick(Character character) {
		for (Tick tick : ticks)
			if (tick.character == character)
				return tick;
		return null;
	}

	public void removeTick(Character character) {
		Iterator<Tick> iterator = ticks.iterator();
		while (iterator.hasNext())
			if (iterator.next().character == character)
				;
	}

	public void tick(Tick tick) {
		if (tick.character.isStunned()){
			//addTick(tick.character, 0.1f, Tick.ACTION);
			if (tick.character.skillCurrent != null)
				tick.character.skillCurrent.reset();
			return;
		}
		
		if (timeSinceTick != 0)
		//	StylePainter.nextTime = true;
		/*StylePainter.append(
				new Msg("[ $num ] ").getMessage(null, null, timeSinceTick));*/
		StylePainter.appendTime(timeSinceTick);
		timeSinceTick = 0;
		if (!tick.character.isDead)
			switch (tick.type) {
			case Tick.SKILL:
				if (tick.character.skillCurrent != null)
					tick.character.skillCurrent.execute();
				else
					tick.character.nextAction();
				break;
			case Tick.ACTION:
				tick.character.nextAction();
				break;
			default:
				break;
			}
		/*if (tick.character == CharacterPlayer.instance
				&& tick.character.skillCurrent == null)
			timer.stop();*/
	}

	public void updateAll(float deltaTime, Character exception,
			boolean executeTick) {
		// System.out.println("update");
		// Utility.narrate(String.valueOf(deltaTime) + " seconds have passed.");
		LinkedList<Tick> toExecute = new LinkedList<>();
		timeSinceTick = Math.round((timeSinceTick + deltaTime)*100f)/100f;
		FrameGame.instance.lblTimePassed.setText("Time passed: " + timePassed + " seconds");
		Iterator<Tick> it = ticks.iterator();
		while (it.hasNext()) {
			Tick tick = it.next();
			tick.time = (float) (Math.round((tick.time - deltaTime) * 100d) / 100d);
			if (executeTick && tick.time <= 0) {
				toExecute.add(tick);
				it.remove();
			}
		}
		for (Tick tick : toExecute) {
			tick(tick);
		}
		
		if (Game.getInstance().charsAllies.size() == 0 || Game.getInstance().charsEnemies.size() == 0){
			begin();
			return;
		}
		
		LinkedList<Event> evtoExecute = new LinkedList<>();
		Iterator<Event> it1 = events.iterator();
		while (it1.hasNext()) {
			Event event = it1.next();
			event.time = (float) (Math.round((event.time - deltaTime) * 100d) / 100d);
			if (executeTick && event.time <= 0) {
				evtoExecute.add(event);
				it1.remove();
			}
		}
		for (Event event: evtoExecute) {
			event.execute();
		}

		for (Character character : charsAllies)
			if (character != exception)
				character.tick(deltaTime);
		for (Character character : charsEnemies)
			if (character != exception)
				character.tick(deltaTime);
		FrameGame.instance.updateCharacterList();
		FrameGame.instance.updateEventList();
	}

	public void updateAll(float deltaTime) {
		updateAll(deltaTime, null, false);
	}

	public Character findEnemy(Character character) {

		
		if (character.isAllied){
			if (charsEnemies.size() > 1){
				for (Character c: charsEnemies)
					if (c.getTarget() == character)
						return c;
				return charsEnemies.get(rand.nextInt(charsEnemies.size()));
			}else
				charsEnemies.getFirst();
		}
		if (!character.isAllied){
			if (charsAllies.size() > 1){
				for (Character c: charsAllies)
					if (c.getTarget() == character)
						return c;
				return charsAllies.get(rand.nextInt(charsAllies.size()));
			}else
				return charsAllies.getFirst();
		}
		
		return null;
	}
	
	public Character findAlly(Character character) {

		if (!character.isAllied){
			if (charsEnemies.size() > 1){
				for (Character c: charsEnemies)
					if (c.target == character && c.getHp() < c.maxHP)
						return c;
				return charsEnemies.get(rand.nextInt(charsEnemies.size()));
			}else
				return charsEnemies.getFirst();
		}
		if (character.isAllied){
			if (charsAllies.size() > 1){
				for (Character c: charsAllies)
					if (c.getTarget() == character && c.getHp() < c.maxHP)
						return c;
				return charsAllies.get(rand.nextInt(charsAllies.size()));
			}else
				return charsAllies.getFirst();
		}
		return null;
	}

	public static Game getInstance() {
		return game;
	}

	public void removeChar(Character character) {
		charsAllies.remove(character);
		charsEnemies.remove(character);
	}

	public Character getCharacter(String target) {
		target = target.replace(" ", "");
		for (Character character : charsAllies)
			if (character.name.replace(" ", "").toLowerCase().equals(target.toLowerCase()))
				return character;
		for (Character character : charsEnemies){
			//System.out.println(character.name.toLowerCase() + " |  " + target.toLowerCase());
			if (character.name.replace(" ", "").toLowerCase().equals(target.toLowerCase()))
				return character;
		}
		return null;
	}
	
	Msg msgLearn = new Msg("$name has learned $targetskill from $targetname after watching $tp2 in action.");
	public void checkforObservation(Character character){
		for (Character c : getAllChars())
			if (c != character){				
				boolean learned = false;
				Skill learn = character.skillCurrent;
				for (Skill skill: c.skills)
					if (skill.name.equals(learn.name))
						learned = true;
				if (learned)
					continue;
				if (rand.nextFloat() <= learn.chanceObserve && rand.nextFloat() <= c.learnRate){
					learn = Utility.getInstance(learn.getClass());
					learn.character = c;
					for (Step step: learn.steps)
						step.prof = -0.1f;
					c.skills.add(learn);
					StylePainter.append( new Msg("$name has learned " + learn.name + " from $targetname after watching $tp2 in action.").getMessage(c, character, 0));
				}
			}
	}
}
