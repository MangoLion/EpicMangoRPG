package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Status;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

public class FrameCharacterInfo extends JFrame {

	private JPanel contentPane;
	
	public Character character;
	
	public void refresh(){

		
		pbHP.setValue((int) ((int) character.getHp()/character.getMaxHP()*100));
		pbHP.setString( character.getHp() + "/" +character.getMaxHP());
		pbMP.setValue((int) ((int) character.getMp()/character.getMaxMP()*100));
		pbMP.setString( character.getMp() + "/" +character.getMaxMP());
		pbSP.setValue((int) ((int) character.getSp()/character.getMaxSP()*100));
		pbSP.setString( character.getSp() + "/" +character.getMaxSP());
		pbBal.setValue((int) ((int) character.getBal()/character.getMaxBal()*100));
		pbBal.setString( character.getBal() + "/" +character.getMaxBal());
		lblStr.setText("Str: " + String.valueOf(character.getStr()));
		lblInt.setText("Int: " + String.valueOf(character.getInt()));
		lblDex.setText("Dex: " + String.valueOf(character.getDex()));
		lblAgi.setText("Agi: " + String.valueOf(character.getAgi()));
		lblDef.setText("Def: " + String.valueOf(character.getDef()));
		lblProt.setText("Prot: " + String.valueOf(character.getProt()));
		if (character == CharacterPlayer.instance)
			lblCp.setText("CP: " + character.getCP());
		else
			lblCp.setText("CP: " + character.getCP() + "(" + new Proficiency(CharacterPlayer.instance, character).str + ")");
		
		tfDesc.setText(character.desc);
		
		modelSkill.clear();
		for (Skill skill: character.skills)
			modelSkill.addElement(skill);
		modelStatus.clear();
		for (Status status:character.statuses)
			modelStatus.addElement(status);
		
		if (Game.getInstance().findTick(character) == null)
			return;
		tfCurrent.setText("");
		if (character.skillCurrent != null){
			Skill skill = character.skillCurrent;
			String str = skill.name;
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
			str += " for " + Game.getInstance().findTick(character).time + " seconds";
			tfCurrent.setText(str);		
		}
	}
	
	JProgressBar pbSP, pbHP, pbMP, pbBal;
	JLabel lblStr, lblAgi, lblInt, lblDex, lblDef, lblProt, lblCp;
	JList<Skill>listSkill;
	JList<Status>listStatus;
	DefaultListModel<Status> modelStatus = new DefaultListModel<Status>();
			DefaultListModel<Skill> 	modelSkill = new DefaultListModel<Skill>();
			
	public JTextPane tfDesc, tfCurrent;
	/**
	 * Create the frame.
	 */
	public FrameCharacterInfo(final Character character) {
		setResizable(false);
		this.character = character;
		setTitle(character.name);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 476, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHp = new JLabel("HP:");
		lblHp.setBounds(10, 11, 100, 14);
		contentPane.add(lblHp);
		
		JLabel lblMp = new JLabel("MP:");
		lblMp.setBounds(10, 36, 100, 14);
		contentPane.add(lblMp);
		
		pbHP = new JProgressBar();
		pbHP.setBounds(41, 9, 150, 16);
		pbHP.setStringPainted(true);
		contentPane.add(pbHP);
		
		pbMP = new JProgressBar();
		pbMP.setBounds(41, 34, 150, 16);
		pbMP.setStringPainted(true);
		contentPane.add(pbMP);
		
		JLabel lblSp = new JLabel("SP:");
		lblSp.setBounds(10, 61, 100, 14);
		contentPane.add(lblSp);
		
		pbSP = new JProgressBar();
		pbSP.setBounds(41, 61, 150, 16);
		pbSP.setStringPainted(true);
		contentPane.add(pbSP);
		
		JLabel lblBal = new JLabel("Bal:");
		lblBal.setBounds(10, 86, 100, 14);
		contentPane.add(lblBal);
		
		pbBal = new JProgressBar();
		pbBal.setBounds(41, 86, 150, 16);
		pbBal.setStringPainted(true);
		contentPane.add(pbBal);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(229, 0, 10, 479);
		contentPane.add(separator);
		
		lblStr = new JLabel("Str:");
		lblStr.setBounds(249, 11, 100, 14);
		contentPane.add(lblStr);
		
		lblInt = new JLabel("Int:");
		lblInt.setBounds(249, 36, 100, 14);
		contentPane.add(lblInt);
		
		lblAgi = new JLabel("Agi:");
		lblAgi.setBounds(249, 61, 100, 14);
		contentPane.add(lblAgi);
		
		lblDex = new JLabel("Dex:");
		lblDex.setBounds(358, 11, 100, 14);
		contentPane.add(lblDex);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 340, 209, 139);
		contentPane.add(scrollPane);
		
		JLabel lblStatuses = new JLabel("Statuses");
		lblStatuses.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblStatuses);
		listStatus = new JList<Status>(modelStatus);
		scrollPane.setViewportView(listStatus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(241, 163, 217, 166);
		contentPane.add(scrollPane_1);
		listSkill = new JList<Skill>(modelSkill);
		listSkill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						FrameSkillInfo info = new FrameSkillInfo(listSkill.getSelectedValue());
						info.setVisible(true);
				}
				super.mouseClicked(e);
			}
		});

		
		JLabel lblSkills = new JLabel("Skills");
		lblSkills.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblSkills);
		scrollPane_1.setViewportView(listSkill);
		
		lblDef = new JLabel("Def: 0.0");
		lblDef.setBounds(359, 36, 100, 14);
		contentPane.add(lblDef);
		
		lblProt = new JLabel("Prot: 0.0");
		lblProt.setBounds(359, 61, 100, 14);
		contentPane.add(lblProt);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInventory inventory = new FrameInventory(character);
				inventory.setVisible(true);
			}
		});
		btnInventory.setBounds(290, 109, 100, 23);
		contentPane.add(btnInventory);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 163, 209, 166);
		contentPane.add(scrollPane_2);
		
		JLabel lblDesc = new JLabel("Desc");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblDesc);
		
		tfDesc = new JTextPane();
		scrollPane_2.setViewportView(tfDesc);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(241, 340, 217, 139);
		contentPane.add(scrollPane_3);
		
		JLabel lblCurrentSkill = new JLabel("Current Skill");
		lblCurrentSkill.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(lblCurrentSkill);
		
		tfCurrent = new JTextPane();
		scrollPane_3.setViewportView(tfCurrent);
		
		lblCp = new JLabel("CP:");
		lblCp.setBounds(249, 86, 200, 14);
		contentPane.add(lblCp);
		
		JLabel lblHu = new JLabel("Hu:");
		lblHu.setBounds(10, 113, 46, 14);
		contentPane.add(lblHu);
		
		JProgressBar pbHunger = new JProgressBar();
		pbHunger.setValue(0);
		pbHunger.setStringPainted(true);
		pbHunger.setString("0.0/0.0");
		pbHunger.setBounds(41, 111, 150, 16);
		contentPane.add(pbHunger);
		
		
		refresh();
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				refresh();
				super.focusGained(e);
			}
		});
	}
}


