package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import General.Attacks;
import General.Class;
import General.Game;

public class ComputerCharacterElement extends Elements {
	
	public ComputerCharacterElement (Class className, Game game) {
		super(className, game);
	}

	public void add(JPanel contentPane, int x, int y) {
		infoPane.setEditable(false);
		
		scroll.setBounds(x, y, 354, 100);
		contentPane.add(scroll);
		
		healthBar.setBounds(x, 110 + y, 354, 20);
		healthBar.setMaximum(className.baseHealth);
		healthBar.setValue(className.currentHealth);
		healthBar.setStringPainted(true);
		healthBar.setString(healthBar.getValue() + "");
		healthBar.setForeground(Color.red);
		contentPane.add(healthBar);
		
		shieldBar.setBounds(x, 135 + y, 354, 20);
		shieldBar.setMaximum(className.baseShield);
		shieldBar.setValue(className.currentShield);
		shieldBar.setStringPainted(true);
		shieldBar.setString(shieldBar.getValue() + "");
		shieldBar.setForeground(Color.black);
		contentPane.add(shieldBar);
		
		energyBar.setBounds(x, 160 + y, 354, 20);
		energyBar.setMaximum(className.baseEnergy);
		energyBar.setValue(className.currentEnergy);
		energyBar.setStringPainted(true);
		energyBar.setString(energyBar.getValue() + "");
		energyBar.setForeground(Color.blue);
		contentPane.add(energyBar);
		
		btnTarget.setBounds(x, 185 + y, 354, 50);
		contentPane.add(btnTarget);
		
		classType.setBounds(x, 220 + y, 354, 80);
		classType.setText(className.name);
		classType.setFont(new Font("Tahoma", Font.PLAIN, 25));
		classType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(classType);
		
		buildButtonListeners();	
	}
	
	private void buildButtonListeners() {
		btnTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attacks theAttack = game.roundAttacks.get(game.roundAttacks.size() - 1);
				theAttack.chooseTargetForAttack(className);
			}
		});
	}
}
