package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.characters.Characters;
import com.mangolion.epicmangorpg.commands.CmdUser;
import com.mangolion.epicmangorpg.commands.CommandHandler;
import com.mangolion.epicmangorpg.floors.CharacterLine;
import com.mangolion.epicmangorpg.floors.Floor;
import com.mangolion.epicmangorpg.floors.Floor2;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Terrain;
import com.mangolion.epicmangorpg.game.Weather;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.Weapons;

import javax.swing.ListModel;

public class FrameBattleCreate extends JInternalFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBattleCreate frame = new FrameBattleCreate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JComboBox<Character> cbPlayer;
	DefaultComboBoxModel<Character> mPlayer = new DefaultComboBoxModel<Character>();
	
	JList<Character> listAlly,
	listEnemy,
	listAll;
	DefaultListModel<Character> mLAlly = new DefaultListModel<Character>(),
			mLEnemy = new DefaultListModel<Character>(),
					 mAll = new DefaultListModel<Character>();
	
	JComboBox<Weather> cbWeather;
	JComboBox<Terrain> cbTerrain;
	DefaultComboBoxModel<Weather> mWeather = new DefaultComboBoxModel<Weather>();
	DefaultComboBoxModel<Terrain> mTerrain = new DefaultComboBoxModel<Terrain>();
	
	private JTextField tfScAlly;
	private JTextField tfScEnemy;
	public FrameBattleCreate() {
		//FrameGame.getInstance().viewMode = true;
		setTitle("Battle Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setBounds(266, 15, 46, 14);
		contentPane.add(lblPlayer);
		
		cbPlayer = new JComboBox<Character>(mPlayer);
		cbPlayer.setBounds(308, 11, 209, 22);
		contentPane.add(cbPlayer);
		
		JScrollPane scrAlly = new JScrollPane();
		scrAlly.setBounds(10, 86, 251, 162);
		contentPane.add(scrAlly);
		listAlly = new JList<Character>(mLAlly);
		scrAlly.setViewportView(listAlly);
		listAlly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					 new FrameCharacterInfo(listAlly.getSelectedValue());
				}
				super.mouseClicked(e);
			}
		});
		listAlly.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE)
					for (Character character: listAlly.getSelectedValuesList())
						mLAlly.removeElement(character);
				super.keyPressed(e);
			}
		});
		
		JLabel lblAllies = new JLabel("Allies");
		lblAllies.setHorizontalAlignment(SwingConstants.CENTER);
		scrAlly.setColumnHeaderView(lblAllies);
		
		JButton btnAddAlly = new JButton("Add");
		btnAddAlly.setBounds(208, 52, 53, 23);
		contentPane.add(btnAddAlly);
		btnAddAlly.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Character character = Characters.getCharacter(((Character)cbAlly.getSelectedItem()).name);
				for(Character character: listAll.getSelectedValuesList())
					if (character != null)
						if (!(character instanceof CharacterPlayer))
							mLAlly.addElement(Characters.getCharacter(character.name));
						else{
							character  = new CharacterPlayer("Player");
							 ((CharacterPlayer) character).init();
							character.ai = new AISimple(character);
							character.isPlayer = false;
							character.scale(2);
							mLAlly.addElement(character);
						}
			}
		});
		
		JScrollPane scrEnemy = new JScrollPane();
		scrEnemy.setBounds(523, 86, 251, 162);
		contentPane.add(scrEnemy);
		listEnemy = new JList<Character>(mLEnemy);
		scrEnemy.setViewportView(listEnemy);
		listEnemy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					 new FrameCharacterInfo(listEnemy.getSelectedValue());
				}
				super.mouseClicked(e);
			}
		});
		listEnemy.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE)
					for (Character character: listEnemy.getSelectedValuesList())
						mLEnemy.removeElement(character);
				super.keyPressed(e);
			}
		});
		
		JLabel lblEnemies = new JLabel("Enemies");
		lblEnemies.setHorizontalAlignment(SwingConstants.CENTER);
		scrEnemy.setColumnHeaderView(lblEnemies);
		
		JButton btnAddEnemy = new JButton("Add");
		btnAddEnemy.setBounds(523, 52, 53, 23);
		contentPane.add(btnAddEnemy);
		btnAddEnemy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Character character = Characters.getCharacter(((Character)listAll.getSelectedValue()).name);
				for (Character character: listAll.getSelectedValuesList())
				if (character != null)
					if (!(character instanceof CharacterPlayer))
						mLEnemy.addElement(Characters.getCharacter(character.name));
					else{
						 ((CharacterPlayer) character).init();
						character.ai = new AISimple(character);
						character.isPlayer = false;
						mLEnemy.addElement(character);
					}
			}
		});
		

		
		JLabel lblScale = new JLabel("Scale:");
		lblScale.setBounds(10, 259, 46, 14);
		contentPane.add(lblScale);
		
		tfScAlly = new JTextField();
		tfScAlly.setText("100");
		tfScAlly.setBounds(46, 256, 37, 20);
		contentPane.add(tfScAlly);
		tfScAlly.setColumns(10);
		
		tfScEnemy = new JTextField();
		tfScEnemy.setText("100");
		tfScEnemy.setColumns(10);
		tfScEnemy.setBounds(559, 256, 37, 20);
		contentPane.add(tfScEnemy);
		
		JLabel label = new JLabel("Scale:");
		label.setBounds(523, 259, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(93, 259, 23, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("%");
		label_2.setBounds(606, 259, 23, 14);
		contentPane.add(label_2);
		
		JLabel lblWeather = new JLabel("Weather:");
		lblWeather.setBounds(10, 284, 59, 14);
		contentPane.add(lblWeather);
		
		JLabel lblTerrain = new JLabel("Terrain:");
		lblTerrain.setBounds(10, 309, 46, 14);
		contentPane.add(lblTerrain);
		
		cbWeather = new JComboBox<Weather>(mWeather);
		cbWeather.setBounds(66, 279, 150, 22);
		contentPane.add(cbWeather);
		
		cbTerrain = new JComboBox<Terrain>(mTerrain);
		cbTerrain.setBounds(66, 304, 150, 22);
		contentPane.add(cbTerrain);
		
		JButton btnCreateBattle = new JButton("Create Battle!");
		btnCreateBattle.setBounds(321, 284, 124, 23);
		contentPane.add(btnCreateBattle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 86, 251, 162);
		contentPane.add(scrollPane);
		
		listAll = new JList<Character>(mAll);
		scrollPane.setViewportView(listAll);
		listAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					 new FrameCharacterInfo(listAll.getSelectedValue());
				}
				super.mouseClicked(e);
			}
		});
		
		JLabel lblAllCharacters = new JLabel("All Characters");
		lblAllCharacters.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAllCharacters);
		btnCreateBattle.addActionListener(new ActionListener() {
			Timer timer,
			timer2;
			Game game;
			Character player;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 player = (Character) cbPlayer.getSelectedItem();
				if (player.name.equalsIgnoreCase("none"))
					player = null;
				else{
				if (player instanceof CharacterPlayer)
					((CharacterPlayer) player).init();
				player.isPlayer = true;
				player.name += "(Player)";
				player.scale(Float.parseFloat(tfScAlly.getText())/100);
				player.isAllied = true;
				if (player.getSkill("wait") == null)
					player.addSkills(new SkillWait());
				CharacterPlayer.instance = player;
				}
				game = Game.getInstance();
				game.currentFloor = 2;
				game.begin();
				timer = new Timer(11, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						game.charsAllies.clear();
						game.charsEnemies.clear();
						if (player != null)
							game.addCharacter(player);
						for (Object  obj: mLAlly.toArray()){
							Character character = (Character) obj;
							character.isAllied = true;
							character.scale(Float.parseFloat(tfScAlly.getText())/100);
							game.addCharacter(character);
						}
						for (Object  obj: mLEnemy.toArray()){
							Character character = (Character) obj;
							character.isAllied = false;
							character.scale(Float.parseFloat(tfScEnemy.getText())/100);
							game.addCharacter(character);
						}
						
					//	player.nextAction();
						
						game.weather = (Weather) cbWeather.getSelectedItem();
						game.terrain = (Terrain) cbTerrain.getSelectedItem();
						timer.stop();

						timer2 = new Timer(100, new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.println("timed");
								//CommandHandler handler = FrameGame.getInstance().cmdHandler;
							//	if (handler == null)
								FrameGame.getInstance().setCommand(new CmdUser(null));
								/*CommandHandler handler = FrameGame.getInstance().cmdHandler;
								if (handler == null)
									FrameGame.getInstance().setCommand(new CmdUser(null));
								else
									FrameGame.getInstance().setCommand(handler);*/
								timer2.stop();
							}
						});
						timer2.start();
					}
				});
				timer.start();
			//FrameGame.getInstance().viewMode = false;
			}
		});
		
		reload();
		toFront();
	}
	
	public void reload(){
		LinkedList<Character> characters = Characters.getAllCharacters();
		characters.add(0,new Character("None", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new Barehands()));
		Iterator<Character> it = characters.iterator();
		while (it.hasNext()){
			Character character = it.next();
			mPlayer.addElement(character);
			if (character == CharacterPlayer.instance)
				continue;
			mAll.addElement(character);
			
			if (character instanceof CharacterLine){
				/*mAll.addElement(new Character("None", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new Barehands()));
				mPlayer.addElement(new Character("None", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new Barehands()));*/
				Character player = new CharacterPlayer("Standard Player");
				player.scale(2);
				mAll.addElement(player);
				mPlayer.addElement(player);
			}
		}

		
		for (Weather weather: Weather.weathers)
			mWeather.addElement(weather);
		for (Terrain terrain: Terrain.terrains)
			mTerrain.addElement(terrain);
	}
}
