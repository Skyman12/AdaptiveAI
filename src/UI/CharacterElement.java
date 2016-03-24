package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import General.Attacks;
import General.Class;
import General.Game;

public class CharacterElement extends Elements {
	
	JButton btnSpell1 = new JButton("Spell 1");
	JButton btnSpell2 = new JButton("Spell 2");
	JButton btnSpell3 = new JButton("Spell 3");
	JButton btnSpell4 = new JButton("Spell 4");
	JButton btnBasic1 = new JButton("Basic 1");
	JButton btnBasic2 = new JButton("Basic 2");
	JButton btnBasic3 = new JButton("Basic 3");
	
	ArrayList<JButton> spellButtonSet = new ArrayList<JButton>();
	
	ArrayList<JButton> basicAttacksButtonSet = new ArrayList<JButton>();
	
	public CharacterElement (Class className, Game game) {
		super(className, game);
		spellButtonSet.add(btnSpell1);
		spellButtonSet.add(btnSpell2);
		spellButtonSet.add(btnSpell3);
		spellButtonSet.add(btnSpell4);
		basicAttacksButtonSet.add(btnBasic1);
		basicAttacksButtonSet.add(btnBasic2);
		basicAttacksButtonSet.add(btnBasic3);
		buildButtonListeners();
	}

	public void add(JPanel contentPane, int x, int y) {
		infoPane.setEditable(false);
		
		btnSpell1.setBounds(x, 144 + y, 84, 25);
		btnSpell1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSpell1.setText(className.abilitiesList.get(0).attackName);
		btnSpell1.setToolTipText(className.abilitiesList.get(0).getToolTip());
		contentPane.add(btnSpell1);
		
		btnSpell2.setBounds(90 + x, 144 + y, 84, 25);
		btnSpell2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSpell2.setText(className.abilitiesList.get(1).attackName);
		btnSpell2.setToolTipText(className.abilitiesList.get(1).getToolTip());
		contentPane.add(btnSpell2);
		
		btnSpell3.setBounds(180 + x, 144 + y, 84, 25);
		btnSpell3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSpell3.setText(className.abilitiesList.get(2).attackName);
		btnSpell3.setToolTipText(className.abilitiesList.get(2).getToolTip());
		contentPane.add(btnSpell3);
		
		btnSpell4.setBounds(270 + x, 144 + y, 84, 25);
		btnSpell4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSpell4.setText(className.abilitiesList.get(3).attackName);
		btnSpell4.setToolTipText(className.abilitiesList.get(3).getToolTip());
		contentPane.add(btnSpell4);
		
		btnBasic1.setBounds(x, 110 + y, 115, 25);
		btnBasic1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBasic1.setText(className.basicAttackList.get(0).attackName);
		btnBasic1.setToolTipText(className.basicAttackList.get(0).getToolTip());
		contentPane.add(btnBasic1);
		
		btnBasic2.setBounds(120 + x, 110 + y, 115, 25);
		btnBasic2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBasic2.setText(className.basicAttackList.get(1).attackName);
		btnBasic2.setToolTipText(className.basicAttackList.get(1).getToolTip());
		contentPane.add(btnBasic2);
		
		btnBasic3.setBounds(240 + x, 110 + y, 115, 25);
		btnBasic3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBasic3.setText(className.basicAttackList.get(2).attackName);
		btnBasic3.setToolTipText(className.basicAttackList.get(2).getToolTip());
		contentPane.add(btnBasic3);
		
		scroll.setBounds(x, y, 354, 100);
		contentPane.add(scroll);
		
		healthBar.setBounds(x, 180 + y, 354, 20);
		healthBar.setMaximum(className.baseHealth);
		healthBar.setValue(className.currentHealth);
		healthBar.setStringPainted(true);
		healthBar.setString(healthBar.getValue() + "");
		healthBar.setForeground(Color.red);
		contentPane.add(healthBar);
		
		shieldBar.setBounds(x, 205 + y, 354, 20);
		shieldBar.setMaximum(className.baseShield);
		shieldBar.setValue(className.currentShield);
		shieldBar.setStringPainted(true);
		shieldBar.setString(shieldBar.getValue() + "");
		shieldBar.setForeground(Color.black);
		contentPane.add(shieldBar);
		
		energyBar.setBounds(x, 230 + y, 354, 20);
		energyBar.setMaximum(className.baseEnergy);
		energyBar.setValue(className.currentEnergy);
		energyBar.setStringPainted(true);
		energyBar.setString(energyBar.getValue() + "");
		energyBar.setForeground(Color.blue);
		contentPane.add(energyBar);
		
		btnTarget.setBounds(x, 255 + y, 354, 50);
		contentPane.add(btnTarget);
		
		classType.setBounds(x, 290 + y, 354, 80);
		classType.setText(className.name);
		classType.setFont(new Font("Tahoma", Font.PLAIN, 25));
		classType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(classType);
			
	}
	
	public void turnButtonsOn() {
		for (int i = 0; i < 4; i++) {
			if (className.abilitiesList.get(i).cost <= className.currentEnergy) {
				spellButtonSet.get(i).setVisible(true);
			}
		}
		
		for (JButton button : basicAttacksButtonSet) {
			button.setVisible(true);
		}
	}
	
	private void turnButtons(ArrayList<JButton> buttons, boolean turn) {
		for (JButton button : buttons) {
			if (turn) {
				button.setVisible(true);
			} else {
				button.setVisible(false);
			}
		}
	}
	
	private void buildButtonListeners() {
		btnSpell1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.abilitiesList.get(0));
				if (className.abilitiesList.get(0).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(spellButtonSet, false);
			}
		});
		
		btnSpell2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.abilitiesList.get(1));
				if (className.abilitiesList.get(1).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(spellButtonSet, false);
			}
		});
		
		btnSpell3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.abilitiesList.get(2));
				if (className.abilitiesList.get(2).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(spellButtonSet, false);
			}
		});
		
		btnSpell4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.abilitiesList.get(3));
				if (className.abilitiesList.get(3).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(spellButtonSet, false);
			}
		});
		
		btnBasic1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.basicAttackList.get(0));
				if (className.basicAttackList.get(0).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(basicAttacksButtonSet, false);
			}
		});
		
		btnBasic2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.basicAttackList.get(1));
				if (className.basicAttackList.get(1).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(basicAttacksButtonSet, false);
			}
		});
		
		btnBasic3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.roundAttacks.add(className.basicAttackList.get(2));
				if (className.basicAttackList.get(2).numOfTargets == 0) {
					Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
					theAttack.chooseTargetForAttack(className);
				}
				turnButtons(basicAttacksButtonSet, false);
			}
		});
		
		
		btnTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
				if (theAttack.numOfTargets != theAttack.theTargets.size()) {
					theAttack.chooseTargetForAttack(className);
				}
			}
		});
	}
}
