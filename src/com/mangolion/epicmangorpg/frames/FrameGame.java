package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.commands.CmdUse;
import com.mangolion.epicmangorpg.commands.Command;
import com.mangolion.epicmangorpg.commands.CommandHandler;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.MenuHelp;
import com.mangolion.epicmangorpg.components.MenuSetting;
import com.mangolion.epicmangorpg.components.TextPaneMango;
import com.mangolion.epicmangorpg.components.Themes;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Terrain;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.game.Weather;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Status;

public class FrameGame extends JFrame {
 
	public LinkedList<FrameCharacterInfo> charInfos = new LinkedList<FrameCharacterInfo>();
	
	public static FrameGame instance;
	static Timer start;
	public static File infoFile = new File(
			System.getProperty("user.dir") + "\\\\" + "gameinfo.txt"),
			profileFile = new File(
					System.getProperty("user.dir") + "\\\\" + "profiles");;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		start();
	}
	
	public static void start(){

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					FrameGame.getInstance();
					start = null;
					start = new Timer(50, new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							init();
							Themes.setTheme("Dust Coffee");
							instance.updateCharacterList();
							start.stop();
							instance.setVisible(true);
							
					//new FrameGameInfo();
						}
					});
					start.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static FrameGame getInstance() {
		if (instance == null)
			instance = new FrameGame(); 
		return instance;
	}
	static Game game;
	public static void init(){
		//new Game();
		StylePainter.init();
		game = Game.getInstance();
	/*	game.charsEnemies.add(new FangFox());
		game.charsAllies.add(new CharacterPlayer());*/
		
		game.begin();
		
		instance.updateWeather(game.weather);
		instance.updateTerrain(game.terrain);

	}
	public boolean viewMode = false;
	DefaultListModel<String> modelAllies = new DefaultListModel<String>();
	DefaultListModel<String> modelEnemies = new DefaultListModel<String>();
	public DefaultListModel<Character> modelMonster = new DefaultListModel<Character>();
	public DefaultListModel<LogMsg> modelLog = new DefaultListModel<LogMsg>();
	DefaultListModel<Event> modelEvent = new DefaultListModel<Event>();
	public JTabbedPane tabInfo;
	public JPanel panelAction, paneCommand = new JPanel(new FlowLayout());
	public void updateCharacterList(){
		modelAllies.clear();
		modelEnemies.clear();
		for (Character character : game.charsAllies)
			modelAllies.addElement(character.name);
		for (Character character : game.charsEnemies)
			modelEnemies.addElement(character.name);
		
		for (FrameCharacterInfo info : charInfos )
			info.refresh();
		if (CharacterPlayer.instance != null)
			updateInfoTab();
	}
	
	public void updateWeather(Weather weather){
		lblWeather.setText(weather.name);
		pbHumid.setValue(weather.humid);
		pbTemp.setValue(weather.temp);
		pbVis.setValue(weather.visibility);
		pbWind.setValue(weather.wind);
	}
	
	public void updateTerrain(Terrain terrain){
		lblTerrain.setText(terrain.name);
		pbRugged.setValue(terrain.rugged);
	}
	
	public TextPaneMango tfNarration;
	public JList<LogMsg> listLog;
	JList<String> listAllies, 
			listEnemies;
	JList<Event> listEvents;
	JList<Character>listMonsters;
	public JLabel lblTimePassed;
	public JProgressBar pbTemp, pbHumid, pbVis, pbWind, pbRugged, pbFloor, pbHP, pbHP2, pbMP, pbMP2, pbSP, pbSP2, pbBal, pbBal2;
	public JLabel lblWeather, lblTerrain, lblPName, lblPName2, lblStatuses, lblStatuses2, lblBuffs, lblBuffs2;
	public TextPaneMango tfTime;
	JTextPane tfDesc, tfSkill, tfSkill2;
	
	public JScrollPane scrollMango, scrollLog;
	public JDesktopPane desktopPane = new JDesktopPane();
//	JLayeredPane layeredPane = new JLayeredPane();
	JPanel contentPanel  = new JPanel(new BorderLayout());
	JSplitPane splitMain;
	public int maxEnemies = 1, mouseX, mouseY;
	public JButton btnNextTick;
	
	/**
	 * Create the frame.
	 */
	public FrameGame() {
		setExtendedState(MAXIMIZED_BOTH);
		setContentPane(contentPanel);
		instance =this;
		setTitle("Epic Mango Adventure");
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new MenuSetting());
		menuBar.add(new MenuHelp());
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 763, 434);
		setSize(1360, 780);
		splitMain = new JSplitPane();
		splitMain.setResizeWeight(0.05);
		//getContentPane().add(splitPane, BorderLayout.CENTER);
		setContentPane(contentPanel);
		contentPanel.add(desktopPane);
		desktopPane.add(splitMain, JLayeredPane.DEFAULT_LAYER);
		
		/*layeredPane.add(desktopPane);
		layeredPane.setBorder(BorderFactory.createTitledBorder("Blub"));
		layeredPane.setSize(100, 100);		*/
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.2);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitMain.setLeftComponent(splitPane_1);
		
		
		tabInfo = new JTabbedPane();
		
		scrollLog = new JScrollPane();
		tabInfo.addTab("Log", scrollLog);

		
		 listLog = new JList(modelLog);
		 listLog.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollLog.setViewportView(listLog);
	//	listLog.setCellRenderer(new MyCellRenderer());
		listLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						LogMsg msg = listLog.getSelectedValue();
						new FrameLogInfo(msg);
				}
				super.mouseClicked(e);
			}
		});
		
		JScrollPane scrollEvent = new JScrollPane();
		tabInfo.addTab("Event", scrollEvent);
		splitPane_1.setLeftComponent(tabInfo);
		 listEvents = new JList(modelEvent);
		 listEvents.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		scrollEvent.setViewportView(listEvents);
	//	listLog.setCellRenderer(new MyCellRenderer());
		listEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						//LogMsg msg = listLog.getSelectedValue();
						//new FrameLogInfo(msg);
				}
				super.mouseClicked(e);
			}
		});
		JTabbedPane tabLower = new JTabbedPane();
		
		JPanel panel = new JPanel(null);
		tabLower.addTab("Info", null, panel, null);
		
		lblPName = new JLabel("PName");
		lblPName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblPName.setBounds(10, 11, 267, 14);
		panel.add(lblPName);
		
		pbHP = new JProgressBar();
		pbHP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbHP.setValue(0);
		pbHP.setStringPainted(true);
		pbHP.setString("0.0/0.0");
		pbHP.setBounds(171, 88, 89, 16);
		panel.add(pbHP);
		
		JLabel label = new JLabel("MP:");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label.setBounds(140, 115, 44, 14);
		panel.add(label);
		
		pbMP = new JProgressBar();
		pbMP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbMP.setValue(0);
		pbMP.setStringPainted(true);
		pbMP.setString("0.0/0.0");
		pbMP.setBounds(171, 113, 89, 16);
		panel.add(pbMP);
		
		pbSP = new JProgressBar();
		pbSP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbSP.setValue(0);
		pbSP.setStringPainted(true);
		pbSP.setString("0.0/0.0");
		pbSP.setBounds(41, 88, 89, 16);
		panel.add(pbSP);
		
		JLabel label_1 = new JLabel("SP:");
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_1.setBounds(10, 88, 100, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("HP:");
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_2.setBounds(140, 90, 44, 14);
		panel.add(label_2);
		
		lblStatuses = new JLabel("Statuses:");
		lblStatuses.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblStatuses.setBounds(139, 140, 138, 14);
		panel.add(lblStatuses);
		
		lblBuffs = new JLabel("Buffs:");
		lblBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblBuffs.setBounds(10, 140, 120, 14);
		panel.add(lblBuffs);
		
		lblPName2 = new JLabel("PName");
		lblPName2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblPName2.setBounds(10, 173, 250, 14);
		panel.add(lblPName2);
		
		pbHP2 = new JProgressBar();
		pbHP2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbHP2.setValue(0);
		pbHP2.setStringPainted(true);
		pbHP2.setString("0.0/0.0");
		pbHP2.setBounds(177, 337, 83, 16);
		panel.add(pbHP2);
		
		JLabel label_4 = new JLabel("MP:");
		label_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_4.setBounds(146, 364, 100, 14);
		panel.add(label_4);
		
		pbMP2 = new JProgressBar();
		pbMP2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbMP2.setValue(0);
		pbMP2.setStringPainted(true);
		pbMP2.setString("0.0/0.0");
		pbMP2.setBounds(177, 362, 83, 16);
		panel.add(pbMP2);
		
		pbSP2 = new JProgressBar();
		pbSP2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbSP2.setValue(0);
		pbSP2.setStringPainted(true);
		pbSP2.setString("0.0/0.0");
		pbSP2.setBounds(51, 337, 83, 16);
		panel.add(pbSP2);
		
		JLabel label_5 = new JLabel("SP:");
		label_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_5.setBounds(20, 337, 100, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("HP:");
		label_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_6.setBounds(146, 339, 100, 14);
		panel.add(label_6);
		
		lblStatuses2 = new JLabel("Statuses:");
		lblStatuses2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblStatuses2.setBounds(139, 389, 138, 14);
		panel.add(lblStatuses2);
		
		lblBuffs2 = new JLabel("Buffs:");
		lblBuffs2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblBuffs2.setBounds(10, 389, 120, 14);
		panel.add(lblBuffs2);
		
		 tfDesc = new JTextPane();
		 tfDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		 tfDesc.setBounds(10, 198, 267, 65);
		 panel.add(tfDesc);
		 
		 JSeparator separator = new JSeparator();
		 separator.setBounds(0, 160, 287, 14);
		 panel.add(separator);
		 
		 JLabel lblBal = new JLabel("Bal:");
		 lblBal.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		 lblBal.setBounds(10, 115, 100, 14);
		 panel.add(lblBal);
		 
		 pbBal = new JProgressBar();
		 pbBal.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		 pbBal.setValue(0);
		 pbBal.setStringPainted(true);
		 pbBal.setString("0.0/0.0");
		 pbBal.setBounds(41, 115, 89, 16);
		 panel.add(pbBal);
		 
		 JLabel label_7 = new JLabel("Bal:");
		 label_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		 label_7.setBounds(20, 364, 100, 14);
		 panel.add(label_7);
		 
		 pbBal2 = new JProgressBar();
		 pbBal2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		 pbBal2.setValue(0);
		 pbBal2.setStringPainted(true);
		 pbBal2.setString("0.0/0.0");
		 pbBal2.setBounds(51, 364, 83, 16);
		 panel.add(pbBal2);
		 
		 tfSkill = new JTextPane();
		 tfSkill.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		 tfSkill.setBounds(10, 36, 267, 41);
		 panel.add(tfSkill);
		 
		 tfSkill2 = new JTextPane();
		 tfSkill2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		 tfSkill2.setBounds(10, 274, 267, 41);
		 panel.add(tfSkill2);


		JPanel panelTerrain = new JPanel();


		tabLower.addTab("Environment", panelTerrain);
		splitPane_1.setRightComponent(tabLower);
		
		
		
		panelTerrain.setLayout(null);
		
		lblWeather = new JLabel("Weather");
		lblWeather.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblWeather.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeather.setBounds(62, 11, 176, 14);
		panelTerrain.add(lblWeather);
		
		JLabel lblTemp = new JLabel("Temp:");
		lblTemp.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTemp.setBounds(10, 36, 46, 14);
		panelTerrain.add(lblTemp);
		
		JLabel lblNewLabel = new JLabel("Humid:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 61, 46, 14);
		panelTerrain.add(lblNewLabel);
		
		JLabel lblVisibility = new JLabel("Visibility:");
		lblVisibility.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblVisibility.setBounds(10, 86, 70, 14);
		panelTerrain.add(lblVisibility);
		
		JLabel lblWind = new JLabel("Wind:");
		lblWind.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblWind.setBounds(10, 111, 46, 14);
		panelTerrain.add(lblWind);
		
		lblTerrain = new JLabel("Terrain");
		lblTerrain.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTerrain.setBounds(51, 136, 200, 14);
		lblTerrain.setHorizontalAlignment(JLabel.CENTER);
		panelTerrain.add(lblTerrain);
		
		JLabel lblRoughness = new JLabel("Rugged:");
		lblRoughness.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblRoughness.setBounds(10, 158, 66, 14);
		panelTerrain.add(lblRoughness);
		
		 pbTemp = new JProgressBar();
		 pbTemp.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbTemp.setStringPainted(true);
		pbTemp.setBounds(72, 36, 196, 16);
		panelTerrain.add(pbTemp);
		
		 pbHumid = new JProgressBar();
		 pbHumid.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbHumid.setStringPainted(true);
		pbHumid.setBounds(72, 61, 196, 16);
		panelTerrain.add(pbHumid);
		
		 pbVis = new JProgressBar();
		 pbVis.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbVis.setStringPainted(true);
		pbVis.setBounds(72, 86, 196, 16);
		panelTerrain.add(pbVis);
		
		 pbWind = new JProgressBar();
		 pbWind.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbWind.setStringPainted(true);
		pbWind.setBounds(72, 111, 196, 16);
		panelTerrain.add(pbWind);
		
		pbRugged = new JProgressBar();
		pbRugged.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbRugged.setStringPainted(true);
		pbRugged.setBounds(60, 160, 196, 16);
		panelTerrain.add(pbRugged);
		
		JButton btnNextFloor = new JButton("Next Floor");
		btnNextFloor.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnNextFloor.setBounds(171, 204, 97, 23);
		panelTerrain.add(btnNextFloor);
		btnNextFloor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.nextFloor = true;
				game.lastFloor = false;
				Utility.narrate("You are now heading towards the next floor.");
			}
		});
		
		listMonsters = new JList<Character>(modelMonster);
		listMonsters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
				/*	boolean wasRunning = false;
					if (game.timer.isRunning()){
						game.timer.stop();
						wasRunning = true;
					}*/
					int index = listMonsters.getSelectedIndex();
					if (index < 0)
						index = 0;
						Character character = listMonsters.getSelectedValue();
						for (FrameCharacterInfo info : charInfos)
							if (character == info.character){
								info.requestFocus();
								info.setVisible(true);
								return;
							}
						FrameCharacterInfo info = new FrameCharacterInfo(character);
						info.setVisible(true);
						charInfos.add(info);
						/*if (wasRunning){
							game.timer.start();		// TODO Auto-generated method stub
						}*/
							
				}

				super.mouseClicked(e);
			}
		});
		JScrollPane scrollPane = new JScrollPane(listMonsters);
		scrollPane.setBounds(10, 263, 272, 148);
		panelTerrain.add(scrollPane);
		
		JLabel lblMonsters = new JLabel("Monsters on this floor:");
		lblMonsters.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMonsters);
		
		JButton btnLastFloor = new JButton("Last Floor");
		btnLastFloor.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnLastFloor.setBounds(20, 204, 97, 23);
		panelTerrain.add(btnLastFloor);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnStay.setBounds(111, 204, 64, 23);
		btnStay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.nextFloor = false;
				game.lastFloor = false;
				Utility.narrate("You decided to stay at this floor");	
			}
		});
		panelTerrain.add(btnStay);
		
		pbFloor = new JProgressBar();
		pbFloor.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		pbFloor.setBounds(10, 183, 272, 16);
		pbFloor.setStringPainted(true);
		panelTerrain.add(pbFloor);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTime.setBounds(10, 238, 272, 14);
		panelTerrain.add(lblTime);
		

		
		btnLastFloor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.nextFloor = false;
				game.lastFloor = true;
				Utility.narrate("You are now heading back to the last floor");
			}
		});
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setResizeWeight(0.9);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitMain.setRightComponent(splitPane_2);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_2.setRightComponent(splitPane_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_3.setLeftComponent(scrollPane_2);
		
		 listAllies = new JList(modelAllies);
		 listAllies.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_2.setViewportView(listAllies);
		listAllies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
				/*	boolean wasRunning = false;
					if (game.timer.isRunning()){
						game.timer.stop();
						wasRunning = true;
					}*/
					int index = listAllies.getSelectedIndex();
					if (index < 0)
						index = 0;
						Character character = game.charsAllies.get(index);
						for (FrameCharacterInfo info : charInfos)
							if (character == info.character){
								info.requestFocus();
								info.setVisible(true);
								return;
							}
						FrameCharacterInfo info = new FrameCharacterInfo(character);
						info.setVisible(true);
						charInfos.add(info);
						/*if (wasRunning){
							game.timer.start();		// TODO Auto-generated method stub
						}*/
							
				}

				super.mouseClicked(e);
			}
		});
		
		JLabel lblAllies = new JLabel("Allies");
		lblAllies.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAllies.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblAllies);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_3);
		
		listEnemies = new JList<String>(modelEnemies);
		listEnemies.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_3.setViewportView(listEnemies);
		listEnemies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listEnemies.getSelectedIndex();
					if (index < 0)
						index = 0;
						Character character = game.charsEnemies.get(index);
						for (FrameCharacterInfo info : charInfos)
							if (character == info.character){
								info.requestFocus();
								info.setVisible(true);
								return;
							}
						FrameCharacterInfo info = new FrameCharacterInfo(character);
						info.setVisible(true);
						charInfos.add(info);
				}
				super.mouseClicked(e);
			}
		});
		
		JLabel lblEnemies = new JLabel("Enemies");
		lblEnemies.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblEnemies.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(lblEnemies);
		
		JPanel panelNarration = new JPanel();
		splitPane_2.setLeftComponent(panelNarration);
		panelNarration.setLayout(new BorderLayout(0, 0));

		
		 panelAction = new JPanel(new BorderLayout());
		panelNarration.add(panelAction, BorderLayout.SOUTH);
		
		btnNextTick = new JButton("Go");
		btnNextTick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (btnNextTick.getText().equals("Go")){
				if (Game.getInstance().timer.isRunning())
					Utility.narrate("Please wait for your turn!");
				else
					if (command.execute())
						Game.getInstance().timer.start();
				}else{
					Skill skill = CharacterPlayer.instance.skillCurrent;
					if (skill != null){
						StylePainter.append(new Msg("$name's $skill is cancelled").getMessage(CharacterPlayer.instance, null, 0));
						skill.cancel();
					}
				}
			}
		});
		panelAction.setLayout(new BorderLayout(0, 0));
		panelAction.add(btnNextTick, BorderLayout.EAST);
		panelAction.add(paneCommand, BorderLayout.CENTER);
		/*tfCommand = new TextPaneMango();
		tfCommand.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		tfCommand.setText("use skill basic kick on Bob");
		tfCommand.setBorder(BorderFactory.createEtchedBorder());
		tfCommand.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				
				case KeyEvent.VK_ENTER:
					Command.execute(tfCommand.getText());
					e.consume();
					break;

				default:
					break;
				}
				super.keyTyped(e);
			}
		});;
		panelAction.add(tfCommand, BorderLayout.CENTER);*/
		lblTimePassed = new JLabel("Time Passed:");
		lblTimePassed.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTimePassed.setHorizontalAlignment(SwingConstants.CENTER);
		panelAction.add(lblTimePassed, BorderLayout.NORTH);
		
		 scrollMango = new JScrollPane();
		//splitPane_4.setLeftComponent(scrollPane_1);
		panelNarration.add(scrollMango, BorderLayout.CENTER);
		
		 tfNarration = new TextPaneMango();
		 tfNarration.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		 tfNarration.setEditable(false);
		scrollMango.setViewportView(tfNarration);
		
		JLabel lblNarration = new JLabel("Narration");
		lblNarration.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNarration.setHorizontalAlignment(SwingConstants.CENTER);
		scrollMango.setColumnHeaderView(lblNarration);
		
		 tfTime = new TextPaneMango();
		 tfTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		 tfTime.setToolTipText("Indicates the number of seconds passed since last tick");
		 tfTime.setEditable(false);
		//tfTime.setText("[0.0]\n");
		scrollMango.setRowHeaderView(tfTime);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FrameMainMenu mainMenu = new FrameMainMenu();
				mainMenu.setVisible(true);
				super.windowClosing(e);
			}
		});
		
		addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				revalidate();
				refreshSize();
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				refreshSize();
				super.componentResized(e);
			}
		});
		
		
		/*timer= new Timer(110, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addFrame(new FrameBattleCreate());
				timer.stop();
			}
		});
		timer.start();*/

	}
	Timer timer;
    public class MyCellRenderer extends DefaultListCellRenderer
    {
        final JPanel p = new JPanel(new BorderLayout());
        final JPanel IconPanel = new JPanel(new BorderLayout());
        final JLabel l = new JLabel("icon"); //<-- this will be an icon instead of a text
        final JLabel lt = new JLabel();
        String pre = "<html><body style='width: 200px;'>";

        MyCellRenderer() {
            //icon
            //IconPanel.add(l, BorderLayout.NORTH);
          //  p.add(IconPanel, BorderLayout.WEST);

            p.add(lt, BorderLayout.CENTER);
            //text
        }

        @Override
        public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus)
        {
            final LogMsg text = (LogMsg) value;
            lt.setText(pre + text);

            return p;
        }
    }

	public void updateEventList() {
		modelEvent.clear();
		for (Event event:game.events)
			modelEvent.addElement(event);
		if (game.events.size()==0)
			FrameGame.instance.tabInfo.setSelectedIndex(0);
	}
	
	public void updateInfoTab(){
		Character character;
		if (Game.getInstance().getCharacter(CharacterPlayer.instance.name) != null || game.charsAllies.size() == 0)
			character = CharacterPlayer.instance;
		else
			character = game.charsAllies.getFirst();
		lblPName.setText(character.name + " \t , crystals: " + character.getCrystals());
		pbHP.setValue((int) ((int) character.getHp()/character.getMaxHP()*100));
		pbHP.setString( character.getHp() + "/" +character.getMaxHP());
		pbMP.setValue((int) ((int) character.getMp()/character.getMaxMP()*100));
		pbMP.setString( character.getMp() + "/" +character.getMaxMP());
		pbSP.setValue((int) ((int) character.getSp()/character.getMaxSP()*100));
		pbSP.setString( character.getSp() + "/" +character.getMaxSP());
		pbBal.setValue((int) ((int) character.getBal()/character.getMaxBal()*100));
		pbBal.setString( character.getBal() + "/" +character.getMaxBal());
		String str = "Statuses: ";
		for (Status status: character.statuses)
			str += status.toString();
		lblStatuses.setText(str);
		
		str = "Buffs: ";
		for (Buff buff: character.buffs)
			str += buff.toString();
		lblBuffs.setText(str);
		
		tfSkill.setText("");
		if (character.skillCurrent != null){
			Skill skill = character.skillCurrent;
			 str = skill.name;
			if (skill.steps.size() > 1)
				 str += ": " +character.getCurrentStep().name + "(step "+ skill.stepCurrent + ")\n";
			else
				str += "\n";
			if (skill.isLoading)
				str += "Loading";
			if (skill.isExecuting)
				str += "Executing";
			if (skill.isCooldown)
				str += "Cooldown";
			
			
			str += " for " + character.skillCurrent.tick + " seconds";
			tfSkill.setText(str);		
		}else
			tfSkill.setText("Idling");
		
		
		
		
		character = character.target;
		if (character == null || (character != null && character.isDead)){
			if (game.charsEnemies.size() == 0)
				return;
				character = game.charsEnemies.getFirst();
		}
		lblPName2.setText(character.name);
		pbHP2.setValue((int) ((int) character.getHp()/character.getMaxHP()*100));
		pbHP2.setString( character.getHp() + "/" +character.getMaxHP());
		pbMP2.setValue((int) ((int) character.getMp()/character.getMaxMP()*100));
		pbMP2.setString( character.getMp() + "/" +character.getMaxMP());
		pbSP2.setValue((int) ((int) character.getSp()/character.getMaxSP()*100));
		pbSP2.setString( character.getSp() + "/" +character.getMaxSP());
		pbBal2.setValue((int) ((int) character.getBal()/character.getMaxBal()*100));
		pbBal2.setString( character.getBal() + "/" +character.getMaxBal());
		tfDesc.setText(character.desc);
		
		str = "Statuses: ";
		for (Status status: character.statuses)
			str += status.toString();
		lblStatuses2.setText(str);
		
		str = "Buffs: ";
		for (Buff buff: character.buffs)
			str += buff.toString();
		lblBuffs2.setText(str);
		
		tfSkill2.setText("");
		if (character.skillCurrent != null){
			Skill skill = character.skillCurrent;
			 str = skill.name;
			if (skill.steps.size() > 1)
				 str += ": " +character.getCurrentStep().name + "(step "+ skill.stepCurrent + ")\n";
			else
				str += "\n";
			if (skill.isLoading)
				str += "Loading";
			if (skill.isExecuting)
				str += "Executing";
			if (skill.isCooldown)
				str += "Cooldown";
			str += " for " + character.skillCurrent.tick + " seconds";
			tfSkill2.setText(str);		
		}else
			tfSkill2.setText("Idling");
	}
	
	public void refreshSize(){
		//desktopPane.setSize(layeredPane.getSize());
		splitMain.setSize(desktopPane.getSize());
	}
	
	public void addFrame(JInternalFrame frame){
		desktopPane.add(frame);
		frame.setVisible(true);
		frame.setLocation(mouseX, mouseY);
		if (frame.getX() < 0)
			frame.setLocation(0, frame.getY());
		if (frame.getY() < 0)
			frame.setLocation(frame.getX(), 0);
	}
	
	public Point getMousePos(){
		if (!isVisible())
			return null;
		Point pt = MouseInfo.getPointerInfo().getLocation(),
		pt2 = getLocationOnScreen();
		return new Point(pt.x - pt2.x, pt.y - pt2.y);
	}
	public Command command;
	public CommandHandler cmdHandler;
	
	public void setCommand(CommandHandler handler){
		if (viewMode)
			return;
		cmdHandler = handler;
		paneCommand.removeAll();
		CommandHandler last = null;
			if (handler.getSubCommands().size() > 0)
				last = handler.getSubCommands().getFirst().getNextCommand();
		while (last != null && last.getSubCommands().size() > 0){
			handler = last;
			last = last.getSubCommands().getFirst().getNextCommand();
		}
		command = handler.getSelectedCommand();
		if (command == null && handler.getPrevious().size() > 1)
			command = handler.getPrevious().get(handler.getPrevious().size()-2).getSelectedCommand();
		for (CommandHandler component: handler.getPrevious()){
			paneCommand.add((Component) component); 
		}
		paneCommand.revalidate();
		paneCommand.repaint();
		//paneCommand.add((Component) handler); 
	}
	
	public void refreshCommand(){
		setCommand(new CmdUse(null));
	}
}
