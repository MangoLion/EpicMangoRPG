package com.mangolion.epicmangorpg.frames;

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
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class PaneItemCustoms extends JPanel {

	public ItemCustom item;
	DefaultListModel<Step>modelSteps = new DefaultListModel<Step>();
	JTextArea lblDesc;
	private JLabel lblName;
	private JScrollPane scrollPane;
	private JList<ItemCustom> listItems;
	DefaultListModel<ItemCustom> model = new DefaultListModel<ItemCustom>();
	private JLabel lblSkills, lblPrice;
	private JButton btnBuy, btnViewDetail;
	private JLabel lblDurability;
	LinkedList<ItemCustom> items;
	public PaneItemCustoms(String title, final LinkedList<ItemCustom> items) {
		this.items = items;
		setSize(601, 312);
		setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(256, 45, 312, 93);
		add(lblDesc);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblName.setBounds(256, 11, 312, 34);
		add(lblName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 236, 231);
		add(scrollPane);
		
		listItems = new JList<ItemCustom>(model);
		listItems.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				item = listItems.getSelectedValue();
				refresh();
				super.mouseClicked(e);
			}
		});
		listItems.setSelectedIndex(0);
		item = items.getFirst();
		
		scrollPane.setViewportView(listItems);
		
		lblSkills = new JLabel("Items");
		lblSkills.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblSkills.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSkills);
		
		btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnBuy.setBounds(363, 193, 91, 23);
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnBuy.getText().equals("Buy")){
				if (CharacterPlayer.instance.getCrystals() >= item.price){
					CharacterPlayer.instance.changeCrystal(- item.price);
					CharacterPlayer.instance.inventory.addItem(item);			
				}
				}else{
						CharacterPlayer.instance.changeCrystal( item.price);
						CharacterPlayer.instance.inventory.itemCustoms.remove(item);
				}
				refresh();		
			}
		});
		add(btnBuy);
		
		btnViewDetail = new JButton("View Details");
		btnViewDetail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnViewDetail.setBounds(363, 230, 120, 23);
		btnViewDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (item instanceof Armor)
					new FrameArmorView((Armor) item, null).setVisible(true);
				else if (item instanceof Weapon)
					new FrameWeaponView((Weapon) item, null).setVisible(true);
				else new FrameViewItemCustom(item);
			}
		});
		
		add(btnViewDetail);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblPrice.setBounds(256, 149, 157, 23);
		add(lblPrice);
		
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
		final JComboBox<String> cbBuy = new JComboBox<String>(cbModel);
		cbBuy.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = cbBuy.getSelectedItem().toString();
				model.clear();
				if (str.equals("Buy")){
					btnBuy.setText("Buy");
					
				}else{
					btnBuy.setText("Sell");
					
				}
				refresh();
			}
		});
		cbBuy.setBounds(72, 11, 99, 22);
		add(cbBuy);
		
		 lblDurability = new JLabel("Durability:");
		lblDurability.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDurability.setBounds(423, 149, 181, 23);
		add(lblDurability);
		cbModel.addElement("Buy");
		cbModel.addElement("Sell");
		
		model.clear();
		item = items.getFirst();
		for (ItemCustom item: items)
		model.addElement(item);
		refresh();
	}

	
	public void refresh(){
		lblName.setText(item.name);
		lblDesc.setText(item.getDesc());
		lblDurability.setText("Durability" + item.maxDurability);
		if (btnBuy.getText().equals("Buy")){
			model.clear();
			for (ItemCustom item: items)
				model.addElement(item);
			lblPrice.setText("Price: " + item.price + " crystals");;
		}else{
			model.clear();
			for (ItemCustom item: CharacterPlayer.instance.inventory.itemCustoms)
				model.addElement(item);
			lblPrice.setText("Price: " + item.price * 0.7f + " crystals");
		}
	}
}
