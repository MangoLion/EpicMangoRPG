package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.mangolion.epicmangorpg.characters.BlueSlime;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterBob;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.characters.Dummy;
import com.mangolion.epicmangorpg.characters.FangFox;
import com.mangolion.epicmangorpg.characters.HealSlime;
import com.mangolion.epicmangorpg.characters.KingSlime;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.WolfSpider;
import com.mangolion.epicmangorpg.components.Command;
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

import javax.swing.JProgressBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class FrameGame extends JFrame {
 
	public LinkedList<FrameCharacterInfo> charInfos = new LinkedList<FrameCharacterInfo>();
	
	public static FrameGame instance;
	static Timer start;
	public static File infoFile = new File(
			System.getProperty("user.dir") + "\\\\" + "gameinfo.txt");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					instance = new FrameGame();
					
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
	static Game game;
	public static void init(){
		new Game();
		game = Game.getInstance();
	/*	game.charsEnemies.add(new FangFox());
		game.charsAllies.add(new CharacterPlayer());*/
		
		game.begin();
		
		instance.updateWeather(game.weather);
		instance.updateTerrain(game.terrain);
		new StylePainter();
	}
	
	DefaultListModel<String> modelAllies = new DefaultListModel<String>();
	DefaultListModel<String> modelEnemies = new DefaultListModel<String>();
	public DefaultListModel<Character> modelMonster = new DefaultListModel<Character>();
	public DefaultListModel<LogMsg> modelLog = new DefaultListModel<LogMsg>();
	DefaultListModel<Event> modelEvent = new DefaultListModel<Event>();
	public JTabbedPane tabInfo;
	public void updateCharacterList(){
		modelAllies.clear();
		modelEnemies.clear();
		for (Character character : game.charsAllies)
			modelAllies.addElement(character.name);
		for (Character character : game.charsEnemies)
			modelEnemies.addElement(character.name);
		
		for (FrameCharacterInfo info : charInfos)
			info.refresh();
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
	public JProgressBar pbTemp, pbHumid, pbVis, pbWind, pbRugged, pbFloor;
	public JLabel lblWeather, lblTerrain;
	public TextPaneMango tfCommand, tfTime;
	
	public JScrollPane scrollMango, scrollLog;

	public int maxEnemies = 1;
	
	/**
	 * Create the frame.
	 */
	public FrameGame() {
		setTitle("Epic Mango Adventure");
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new MenuSetting());
		menuBar.add(new MenuHelp());
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 763, 434);
		setSize(1360, 780);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.05);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.3);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);
		
		
		tabInfo = new JTabbedPane();
		
		scrollLog = new JScrollPane();
		tabInfo.addTab("Log", scrollLog);

		
		 listLog = new JList(modelLog);
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
		


		
		JPanel panelTerrain = new JPanel();
		splitPane_1.setRightComponent(panelTerrain);
		panelTerrain.setLayout(null);
		
		lblWeather = new JLabel("Weather");
		lblWeather.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeather.setBounds(62, 11, 176, 14);
		panelTerrain.add(lblWeather);
		
		JLabel lblTemp = new JLabel("Temp:");
		lblTemp.setBounds(10, 36, 46, 14);
		panelTerrain.add(lblTemp);
		
		JLabel lblNewLabel = new JLabel("Humid:");
		lblNewLabel.setBounds(10, 61, 46, 14);
		panelTerrain.add(lblNewLabel);
		
		JLabel lblVisibility = new JLabel("Visibility:");
		lblVisibility.setBounds(10, 86, 70, 14);
		panelTerrain.add(lblVisibility);
		
		JLabel lblWind = new JLabel("Wind:");
		lblWind.setBounds(10, 111, 46, 14);
		panelTerrain.add(lblWind);
		
		lblTerrain = new JLabel("Terrain");
		lblTerrain.setBounds(51, 136, 200, 14);
		lblTerrain.setHorizontalAlignment(JLabel.CENTER);
		panelTerrain.add(lblTerrain);
		
		JLabel lblRoughness = new JLabel("Rugged:");
		lblRoughness.setBounds(10, 158, 66, 14);
		panelTerrain.add(lblRoughness);
		
		 pbTemp = new JProgressBar();
		pbTemp.setStringPainted(true);
		pbTemp.setBounds(60, 36, 196, 16);
		panelTerrain.add(pbTemp);
		
		 pbHumid = new JProgressBar();
		pbHumid.setStringPainted(true);
		pbHumid.setBounds(60, 61, 196, 16);
		panelTerrain.add(pbHumid);
		
		 pbVis = new JProgressBar();
		pbVis.setStringPainted(true);
		pbVis.setBounds(60, 86, 196, 16);
		panelTerrain.add(pbVis);
		
		 pbWind = new JProgressBar();
		pbWind.setStringPainted(true);
		pbWind.setBounds(60, 111, 196, 16);
		panelTerrain.add(pbWind);
		
		pbRugged = new JProgressBar();
		pbRugged.setStringPainted(true);
		pbRugged.setBounds(60, 160, 196, 16);
		panelTerrain.add(pbRugged);
		
		JButton btnNextFloor = new JButton("Next Floor");
		btnNextFloor.setBounds(178, 204, 85, 23);
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
		scrollPane.setBounds(10, 238, 272, 138);
		panelTerrain.add(scrollPane);
		
		JLabel lblMonsters = new JLabel("Monsters on this floor:");
		lblMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMonsters);
		
		JButton btnLastFloor = new JButton("Last Floor");
		btnLastFloor.setBounds(40, 204, 81, 23);
		panelTerrain.add(btnLastFloor);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setBounds(120, 204, 64, 23);
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
		pbFloor.setBounds(10, 183, 272, 16);
		pbFloor.setStringPainted(true);
		panelTerrain.add(pbFloor);
		
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
		splitPane.setRightComponent(splitPane_2);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.5);
		splitPane_2.setRightComponent(splitPane_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_3.setLeftComponent(scrollPane_2);
		
		 listAllies = new JList(modelAllies);
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
		lblAllies.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblAllies);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_3);
		
		listEnemies = new JList<String>(modelEnemies);
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
		lblEnemies.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(lblEnemies);
		
		JPanel panelNarration = new JPanel();
		splitPane_2.setLeftComponent(panelNarration);
		panelNarration.setLayout(new BorderLayout(0, 0));
		
		/*JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setResizeWeight(0.85);
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);*/

		
		JPanel panelAction = new JPanel();
		panelNarration.add(panelAction, BorderLayout.SOUTH);
		//splitPane_4.setRightComponent(panelAction);
		
		JButton btnNextTick = new JButton("Go");
		btnNextTick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Game.getInstance().nextTick(true);
				Command.execute(tfCommand.getText());
				/*Timer timer = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Game.getInstance().nextTick(true);
					}
				});
				timer.start();*/
			}
		});
		panelAction.setLayout(new BorderLayout(0, 0));
		panelAction.add(btnNextTick, BorderLayout.EAST);
		
		tfCommand = new TextPaneMango();
		tfCommand.setText("use skill basic kick on Bob");
		tfCommand.setBorder(BorderFactory.createEtchedBorder());
		tfCommand.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				
				case KeyEvent.VK_ENTER:
					Command.execute(tfCommand.getText());
					System.out.println("called");
					e.consume();
					break;

				default:
					break;
				}
				super.keyTyped(e);
			}
		});;
		panelAction.add(tfCommand, BorderLayout.CENTER);
		
		lblTimePassed = new JLabel("Time Passed:");
		lblTimePassed.setHorizontalAlignment(SwingConstants.CENTER);
		panelAction.add(lblTimePassed, BorderLayout.NORTH);
		
		 scrollMango = new JScrollPane();
		//splitPane_4.setLeftComponent(scrollPane_1);
		panelNarration.add(scrollMango, BorderLayout.CENTER);
		
		 tfNarration = new TextPaneMango();
		 tfNarration.setEditable(false);
		scrollMango.setViewportView(tfNarration);
		
		JLabel lblNarration = new JLabel("Narration");
		lblNarration.setHorizontalAlignment(SwingConstants.CENTER);
		scrollMango.setColumnHeaderView(lblNarration);
		
		 tfTime = new TextPaneMango();
		 tfTime.setToolTipText("Indicates the number of seconds passed since last tick");
		 tfTime.setEditable(false);
		//tfTime.setText("[0.0]\n");
		scrollMango.setRowHeaderView(tfTime);
	}
	
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
}
