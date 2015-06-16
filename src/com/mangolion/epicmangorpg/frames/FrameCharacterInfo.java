package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Status;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Font;

public class FrameCharacterInfo extends JInternalFrame {

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
		lblCrystals.setText("Crystals: " + String.valueOf(character.getCrystals()));
		
		//if (character != CharacterPlayer.instance)
		if (CharacterPlayer.instance != null){
			lblAccuracy.setText("Accuracy: " + Utility.format(CharacterPlayer.instance.getAccuracy(character)*100) + "%");
			lblCrit.setText("Critical: " + Utility.format(CharacterPlayer.instance.getCritical(character)*100) + "%");
		}
		
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
		modelBuff.clear();
		
		for (Buff buff:character.buffs)
			modelBuff.addElement(buff);
		
		modelBarrier.clear();
		for (Barrier barrier: character.barriers)
			modelBarrier.addElement(barrier);
		
		//update elements
		String str;
		
		/*if (character.getElements().size()>0){
		str  = "";
		float ice = -2, water = -2, lightning = -2, fire = -2, lava = -2, earth = -2, wind = -2, light = -2, dark = -2;
		for (Element element: character.getElements()){
			str +=  Elements.getElement(element.type).name + " ";
			ice = (ice == -2)?  Elements.getElement(element.type).calculate(Elements.Ice): (ice +  Elements.getElement(element.type).calculate(Elements.Ice))/2;
			water = (water == -2)?  Elements.getElement(element.type).calculate(Elements.Water): (water +  Elements.getElement(element.type).calculate(Elements.Water))/2;
			lightning = (lightning == -2)?  Elements.getElement(element.type).calculate(Elements.Lightning): (lightning +  Elements.getElement(element.type).calculate(Elements.Lightning))/2;
			fire = (fire == -2)?  Elements.getElement(element.type).calculate(Elements.Fire): (fire +  Elements.getElement(element.type).calculate(Elements.Fire))/2;
			lava = (lava == -2)?  Elements.getElement(element.type).calculate(Elements.Lava): (lava +  Elements.getElement(element.type).calculate(Elements.Lava))/2;
			earth = (earth == -2)?  Elements.getElement(element.type).calculate(Elements.Earth): (earth +  Elements.getElement(element.type).calculate(Elements.Earth))/2;
			wind = (wind == -2)?  Elements.getElement(element.type).calculate(Elements.Wind): (wind +  Elements.getElement(element.type).calculate(Elements.Wind))/2;
			light = (light == -2)?  Elements.getElement(element.type).calculate(Elements.Light): (light +  Elements.getElement(element.type).calculate(Elements.Light))/2;
			dark = (dark == -2)?  Elements.getElement(element.type).calculate(Elements.Dark): (dark +  Elements.getElement(element.type).calculate(Elements.Dark))/2;
		}
		lblElement.setText(str);
		lblIce.setText("" + ice*100 + "%");
		lblWater.setText("" + water*100 + "%");
		lblLightning.setText("" + lightning*100 + "%");
		lblFire.setText("" + fire*100 + "%");
		lblLava.setText("" + lava*100 + "%");
		lblEarth.setText("" + earth*100 + "%");
		lblWind.setText("" + wind*100 + "%");
		lblLight.setText("" + light*100 + "%");
		lblDark.setText("" + dark*100 + "%");

		}else {
			lblElement.setText("None");
			lblIce.setText(" 100%");
			lblWater.setText(" 100%");
			lblLightning.setText(" 100%");
			lblFire.setText(" 100%");
			lblLava.setText(" 100%");
			lblEarth.setText(" 100%");
			lblWind.setText(" 100%");
			lblLight.setText(" 100%");
			lblDark.setText(" 100%");
		}*/
		
		//update current skill
		tfCurrent.setText("");
		

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
			tfCurrent.setText(str);		
		}
		

	}
	
	JProgressBar pbSP, pbHP, pbMP, pbBal;
	JLabel lblStr, lblAgi, lblInt, lblDex, lblDef, lblProt, lblCp, lblCrystals, lblAccuracy;
	JList<Skill>listSkill;
	JList<Status>listStatus;
	JList<Buff> listBuff;
	DefaultListModel<Status> modelStatus = new DefaultListModel<Status>();
			DefaultListModel<Skill> 	modelSkill = new DefaultListModel<Skill>();
			DefaultListModel<Buff> modelBuff = new DefaultListModel<Buff>();
			DefaultListModel<Barrier> modelBarrier = new DefaultListModel<Barrier>();
			
	JList<Barrier> listBarriers;
			
	public JTextPane tfDesc, tfCurrent;
	private JLabel lblCrit;
	/**
	 * Create the 
	 */
	public FrameCharacterInfo(final Character character) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		setResizable(false);
		this.character = character;
		setTitle(character.name);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 688, 525);
		Point pt = FrameGame.getInstance().getMousePos();
		if (pt != null)
		setLocation(pt.x - getWidth()/2, pt.y - getHeight());
		if (getX() < 0)
			setLocation(0, getY());
		if (getY() < 0)
			setLocation(getX(), 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHp = new JLabel("HP:");
		lblHp.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblHp.setBounds(10, 11, 100, 14);
		contentPane.add(lblHp);
		
		JLabel lblMp = new JLabel("MP:");
		lblMp.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMp.setBounds(10, 36, 100, 14);
		contentPane.add(lblMp);
		
		pbHP = new JProgressBar();
		pbHP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbHP.setBounds(41, 9, 150, 16);
		pbHP.setStringPainted(true);
		contentPane.add(pbHP);
		
		pbMP = new JProgressBar();
		pbMP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbMP.setBounds(41, 34, 150, 16);
		pbMP.setStringPainted(true);
		contentPane.add(pbMP);
		
		JLabel lblSp = new JLabel("SP:");
		lblSp.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblSp.setBounds(10, 61, 100, 14);
		contentPane.add(lblSp);
		
		pbSP = new JProgressBar();
		pbSP.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbSP.setBounds(41, 61, 150, 16);
		pbSP.setStringPainted(true);
		contentPane.add(pbSP);
		
		JLabel lblBal = new JLabel("Bal:");
		lblBal.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBal.setBounds(10, 86, 100, 14);
		contentPane.add(lblBal);
		
		pbBal = new JProgressBar();
		pbBal.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbBal.setBounds(41, 86, 150, 16);
		pbBal.setStringPainted(true);
		contentPane.add(pbBal);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(229, 0, 10, 479);
		contentPane.add(separator);
		
		lblStr = new JLabel("Str:");
		lblStr.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblStr.setBounds(249, 11, 100, 14);
		contentPane.add(lblStr);
		
		lblInt = new JLabel("Int:");
		lblInt.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblInt.setBounds(249, 36, 100, 14);
		contentPane.add(lblInt);
		
		lblAgi = new JLabel("Agi:");
		lblAgi.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAgi.setBounds(249, 61, 100, 14);
		contentPane.add(lblAgi);
		
		lblDex = new JLabel("Dex:");
		lblDex.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDex.setBounds(358, 11, 100, 14);
		contentPane.add(lblDex);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 340, 209, 139);
		contentPane.add(scrollPane);
		
		JLabel lblStatuses = new JLabel("Statuses");
		lblStatuses.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblStatuses.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblStatuses);
		listStatus = new JList<Status>(modelStatus);
		listStatus.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane.setViewportView(listStatus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(241, 163, 217, 166);
		contentPane.add(scrollPane_1);
		listSkill = new JList<Skill>(modelSkill);
		listSkill.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
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
		lblSkills.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblSkills.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblSkills);
		scrollPane_1.setViewportView(listSkill);
		
		lblDef = new JLabel("Def: 0.0");
		lblDef.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDef.setBounds(359, 36, 100, 14);
		contentPane.add(lblDef);
		
		lblProt = new JLabel("Prot: 0.0");
		lblProt.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblProt.setBounds(359, 61, 100, 14);
		contentPane.add(lblProt);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInventory inventory = new FrameInventory(character);
				inventory.setVisible(true);
			}
		});
		
		if (character != CharacterPlayer.instance)
			btnInventory.setText("Drops");
		
		btnInventory.setBounds(520, 36, 109, 23);
		contentPane.add(btnInventory);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 163, 209, 166);
		contentPane.add(scrollPane_2);
		
		JLabel lblDesc = new JLabel("Desc");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblDesc);
		
		tfDesc = new JTextPane();
		tfDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_2.setViewportView(tfDesc);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(241, 340, 217, 139);
		contentPane.add(scrollPane_3);
		
		JLabel lblCurrentSkill = new JLabel("Current Skill");
		lblCurrentSkill.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblCurrentSkill.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(lblCurrentSkill);
		
		tfCurrent = new JTextPane();
		tfCurrent.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_3.setViewportView(tfCurrent);
		
		lblCp = new JLabel("CP:");
		lblCp.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblCp.setBounds(249, 86, 200, 14);
		contentPane.add(lblCp);
		
		JLabel lblHu = new JLabel("Hu:");
		lblHu.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblHu.setBounds(10, 113, 46, 14);
		contentPane.add(lblHu);
		
		JProgressBar pbHunger = new JProgressBar();
		pbHunger.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		pbHunger.setValue(0);
		pbHunger.setStringPainted(true);
		pbHunger.setString("0.0/0.0");
		pbHunger.setBounds(41, 111, 150, 16);
		contentPane.add(pbHunger);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(468, 0, 17, 487);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(481, 340, 183, 139);
		contentPane.add(scrollPane_4);
		
		JLabel lblBuffs = new JLabel("Buffs");
		lblBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBuffs.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_4.setColumnHeaderView(lblBuffs);
		
		 listBuff = new JList<Buff>(modelBuff);
		 listBuff.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_4.setViewportView(listBuff);
		
		JButton btnEquipments = new JButton("Equipments");
		btnEquipments.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnEquipments.setBounds(520, 11, 109, 23);
		btnEquipments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameEquips(character).setVisible(true); 
			}
		});
		contentPane.add(btnEquipments);
		
		lblCrystals = new JLabel("Crystals");
		lblCrystals.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblCrystals.setBounds(249, 111, 209, 23);
		contentPane.add(lblCrystals);
		
		lblAccuracy = new JLabel("Accuracy:");
		lblAccuracy.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAccuracy.setBounds(358, 86, 105, 23);
		contentPane.add(lblAccuracy);
		
		lblCrit = new JLabel("Critical: 0.0%");
		lblCrit.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblCrit.setBounds(358, 109, 105, 23);
		contentPane.add(lblCrit);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(481, 163, 183, 166);
		contentPane.add(scrollPane_5);
		
		JLabel lblBarriers = new JLabel("Barriers");
		lblBarriers.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarriers.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane_5.setColumnHeaderView(lblBarriers);

		
		listBarriers = new JList<Barrier>(modelBarrier);
		scrollPane_5.setViewportView(listBarriers);
		listBarriers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						new FrameBarrierInfo(listBarriers.getSelectedValue()).setVisible(true);
						System.out.println("called");
				}
				super.mouseClicked(e);
			}
		});
		
		JButton btnElements = new JButton("Elements");
		btnElements.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnElements.setBounds(520, 61, 109, 23);
		contentPane.add(btnElements);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refresh();
				super.mouseEntered(e);
			}
		});
		refresh();
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
}


