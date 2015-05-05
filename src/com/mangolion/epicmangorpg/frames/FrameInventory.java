package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.Weapon;

import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrameInventory extends JInternalFrame {
	Character character;
	
	JPanel contentPane = new JPanel(new BorderLayout());
	DefaultListModel<ItemStack>modelItems = new DefaultListModel<ItemStack>();
	DefaultListModel<ItemCustom>modelItemEquip = new DefaultListModel<ItemCustom>();
	
	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList<ItemStack> listConsumable = new JList<ItemStack>(modelItems);
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JList<ItemCustom> listEquip = new JList<ItemCustom>(modelItemEquip);
	private final JLabel lblConsumablesmaterials = new JLabel("Consumables/Materials");
	private final JLabel lblEquipmentsunique = new JLabel("Equipments/Unique");
	public FrameInventory(final Character character) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		this.character = character;
		setSize(250, 400);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		setContentPane(contentPane);
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setLeftComponent(scrollPane);
		
		scrollPane.setViewportView(listConsumable);
		lblConsumablesmaterials.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane.setColumnHeaderView(lblConsumablesmaterials);
		
		splitPane.setRightComponent(scrollPane_1);
		
		scrollPane_1.setViewportView(listEquip);
		lblEquipmentsunique.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane_1.setColumnHeaderView(lblEquipmentsunique);
		listEquip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						ItemCustom item = listEquip.getSelectedValue();
						if (item instanceof Armor)
							new FrameArmorView((Armor) item, character).setVisible(true);
						else if (item instanceof Weapon)
							new FrameWeaponView((Weapon) item, character).setVisible(true);
						else new FrameViewItemCustom(item);
				}
				super.mouseClicked(e);
			}
		});
		
		
		refresh();
	}
	
	public void refresh(){
		modelItems.clear();
		for (ItemStack stack: character.inventory.itemStacks)
			modelItems.addElement(stack);
		
		modelItemEquip.clear();
		for (ItemCustom item: character.inventory.itemCustoms)
			modelItemEquip.addElement(item);
	}
}
