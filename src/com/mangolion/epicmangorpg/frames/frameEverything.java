package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.Characters;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class frameEverything extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameEverything frame = new frameEverything();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frameEverything() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);


		final JPanel panelMain = new JPanel(new BorderLayout());
		splitPane.setRightComponent(panelMain);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Characters", null, scrollPane, null);

		final DefaultListModel<Character> mCharacter = new DefaultListModel<Character>();
		final JList<Character> listCharacters = new JList<Character>(mCharacter);
		scrollPane.setViewportView(listCharacters);
		listCharacters.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Character character = listCharacters.getSelectedValue();
				
				setTitle(character.name);
				FrameCharacterInfo info = new FrameCharacterInfo(character);
				Container panel = info.getContentPane();
				//info.remove(panel);
				panelMain.removeAll();
				panelMain.add(panel, BorderLayout.CENTER);
				panelMain.revalidate();
				setSize((int) ((listCharacters.getWidth() +info.getWidth()) * 1.05f), (int) (info.getHeight()*1.05));
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Skills", null, scrollPane_1, null);

		final DefaultListModel<Skill> mSkill = new DefaultListModel<Skill>();
		final JList<Skill> listSkills = new JList<Skill>(mSkill);
		scrollPane_1.setViewportView(listSkills);
		listSkills.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Skill skill = listSkills.getSelectedValue();
				setTitle(skill.name);
				FrameSkillInfo info = new FrameSkillInfo(skill);
				Container panel = info.getContentPane();
				//info.remove(panel);
				panelMain.removeAll();
				panelMain.add(panel, BorderLayout.CENTER);
				panelMain.revalidate();
				setSize((int) ((listCharacters.getWidth() +info.getWidth()) * 1.1f), (int) (info.getHeight()*1.05));
			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Statuses", null, scrollPane_2, null);

		final DefaultListModel<Status> mStatus = new DefaultListModel<Status>();
		final JList<Status> listStatuses = new JList<Status>(mStatus);
		scrollPane_2.setViewportView(listStatuses);

		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Equips", null, scrollPane_3, null);

		final DefaultListModel<ItemCustom> mCustom = new DefaultListModel<ItemCustom>();
		final JList<ItemCustom> listEquipments = new JList<ItemCustom>(mCustom);
		scrollPane_3.setViewportView(listEquipments);

		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Consumables/Misc", null, scrollPane_4, null);

		final DefaultListModel<Item> mItem = new DefaultListModel<Item>();
		final JList<Item> listConsumables = new JList<Item>(mItem);
		scrollPane_4.setViewportView(listConsumables);
		

		/*
		 * Skill skill = new Skill("Blub", "", ActionType.Alchemy);
		 * FrameSkillInfo info = new FrameSkillInfo(skill); Container panel =
		 * info.getContentPane(); info.remove(panel); contentPane.add(panel);
		 */

		for (Character character : Characters.getAllCharacters()) {
			mCharacter.addElement(character);
			boolean found = false;
			for (Skill skill : character.skills) {
				for (Object obj : mSkill.toArray())
					if (skill.name.equalsIgnoreCase(obj.toString()))
						found = true;
				if (!found) {
					mSkill.addElement(skill);
					for (Step step : skill.steps)
						for (Status status : step.statuses) {
							status.time = 0;
							for (int i = 0; i < mStatus.getSize(); i++)
								if (status.name
										.equalsIgnoreCase(mStatus.get(i).name))
									found = true;
							if (!found)
								mStatus.addElement(status);
							else
								found = false;
						}
				}else 
					found = false;
			}
		}
	}

}
