package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.floors.FloorTrainning;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.Weapon;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FrameShopItemCustoms extends JInternalFrame {

	JPanel contentPane = new JPanel(new BorderLayout());
	JTabbedPane tabbedPane;
	public FrameShopItemCustoms(String title) {
		super(title, false, true, true, false);
		FrameGame.getInstance().addFrame(this);
		setSize(700, 400);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane);
		
		super.moveToFront();
	}
	
	public void addTab(String title, ItemCustom ... items ){
		LinkedList<ItemCustom> result = new LinkedList<ItemCustom>();
		result.addAll(Arrays.asList(items));
		PaneItemCustoms pane = new PaneItemCustoms(title, result);
		tabbedPane.add(title, pane);
	}
	
	@Override
	public void moveToFront() {
	}
}
