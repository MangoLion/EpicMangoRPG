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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.floors.FloorTrainning;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.Weapon;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FrameShopItems extends JInternalFrame {

	public Item item;
	DefaultListModel<Step>modelSteps = new DefaultListModel<Step>();
	JTextArea lblDesc;
	private JLabel lblName;
	private JScrollPane scrollPane;
	private JList<ItemStack> listItems;
	DefaultListModel<ItemStack> model = new DefaultListModel<ItemStack>();
	private JLabel lblSkills, lblPrice;
	private JButton btnBuy;
	private JTextField tfAmount;
	private JLabel lblBuyAmount;
	private JLabel lblTotalCost;
	private JLabel lblHave;
	public FrameShopItems(String title, final LinkedList<Item> items) {
		super(title, false, true, true, false);
		FrameGame.getInstance().addFrame(this);
		setSize(628, 312);
		Point pt = FrameGame.getInstance().getMousePos();
		//setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		getContentPane().setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(256, 68, 312, 70);
		getContentPane().add(lblDesc);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblName.setBounds(256, 11, 312, 46);
		getContentPane().add(lblName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 236, 231);
		getContentPane().add(scrollPane);
		
		listItems = new JList<ItemStack>(model);
		listItems.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				item = listItems.getSelectedValue().item;
				refresh();
				tfAmount.setText("0");
				super.mouseClicked(e);
			}
		});
		scrollPane.setViewportView(listItems);
		
		lblSkills = new JLabel("Items");
		lblSkills.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblSkills.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSkills);
		
		btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnBuy.setBounds(355, 222, 91, 23);
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int amount =  Integer.valueOf(tfAmount.getText());
				float totalCost = amount* item.price;
				if (btnBuy.getText().equals("Buy")){
				if (CharacterPlayer.instance.getCrystals() >= totalCost){
					CharacterPlayer.instance.changeCrystal(-totalCost);
					CharacterPlayer.instance.inventory.addItem(item, amount);			
				}
				}else{
					if (amount <= CharacterPlayer.instance.inventory.getItemNumber(item)){
						CharacterPlayer.instance.changeCrystal(totalCost*0.7f);
						CharacterPlayer.instance.inventory.removeItem(item, amount);
					}
				}
				refresh();		
			}
		});
		getContentPane().add(btnBuy);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblPrice.setBounds(256, 149, 310, 14);
		getContentPane().add(lblPrice);
		
		tfAmount = new JTextField("1");
		tfAmount.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		tfAmount.setBounds(355, 173, 213, 23);
		getContentPane().add(tfAmount);
		tfAmount.setColumns(10);
		tfAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refresh();
				if (!lblTotalCost.getText().equals(""))
					if (btnBuy.getText().equals("Buy"))
						lblTotalCost.setText("Total: "  + item.price * Integer.valueOf(tfAmount.getText()));
					else
						lblTotalCost.setText("Total: "  + item.price * Integer.valueOf(tfAmount.getText())*0.7f);

				super.keyReleased(e);
			}
		});
		
		lblBuyAmount = new JLabel("Buy Amount:");
		lblBuyAmount.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBuyAmount.setBounds(256, 174, 89, 14);
		getContentPane().add(lblBuyAmount);
		
		lblTotalCost = new JLabel("Total :");
		lblTotalCost.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTotalCost.setBounds(256, 202, 200, 14);
		getContentPane().add(lblTotalCost);
		
		lblHave = new JLabel("You Have");
		lblHave.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblHave.setBounds(256, 251, 318, 14);
		getContentPane().add(lblHave);
		
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
		final JComboBox<String> cbBuy = new JComboBox<String>(cbModel);
		cbBuy.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				model.clear();
				String str = cbBuy.getSelectedItem().toString();
				if (str.equals("Buy")){
					btnBuy.setText("Buy");
					model.clear();
					for (Item item: items)
						model.addElement(new ItemStack(null, item, 1));
				}else{
					btnBuy.setText("Sell");
					model.clear();
					for (ItemStack item: CharacterPlayer.instance.inventory.itemStacks)
						model.addElement(item);
				}
			}
		});
		cbBuy.setBounds(72, 11, 99, 22);
		getContentPane().add(cbBuy);
		cbModel.addElement("Buy");
		cbModel.addElement("Sell");
		
		item = items.getFirst();
		for (Item item: items)
		model.addElement(new ItemStack(null, item, 1));
		refresh();
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
	
	public void refresh(){
		lblName.setText(item.name);
		lblDesc.setText(item.desc);
		lblTotalCost.setText("Total: ");
		if (btnBuy.getText().equals("Buy")){
			lblPrice.setText("Price: " + item.price + " crystals");;
			lblHave.setText("You have " + CharacterPlayer.instance.getCrystals() + " crystals") ;
		}else{
			lblPrice.setText("Price: " + item.price * 0.7f + " crystals");;
			lblHave.setText("You have " + CharacterPlayer.instance.inventory.getItemNumber(item) + " " + item.name) ;
		}
	}
}
