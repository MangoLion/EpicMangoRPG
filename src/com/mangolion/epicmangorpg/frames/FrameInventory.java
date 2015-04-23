package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemStack;

public class FrameInventory extends JFrame {
	Character character;
	
	JPanel contentPane = new JPanel(new BorderLayout());
	DefaultListModel<ItemStack>modelItems = new DefaultListModel<ItemStack>();
	JList<ItemStack>listItems = new JList<ItemStack>(modelItems);
	public FrameInventory(final Character character) {
		this.character = character;
		setSize(250, 400);
		setContentPane(contentPane);
		JScrollPane scrollItems = new JScrollPane(listItems);
		contentPane.add(scrollItems);
		refresh();
		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						ItemStack stack = listItems.getSelectedValue();
						stack.use(character, character);
				}
				super.mouseClicked(e);
			}
		});
	}
	
	public void refresh(){
		modelItems.clear();
		for (ItemStack stack: character.inventory.itemStacks)
			modelItems.addElement(stack);
	}
}
