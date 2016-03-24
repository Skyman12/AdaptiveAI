package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import General.Class;
import General.Game;

public abstract class Elements {
	
	public Class className;
	
	public JButton btnTarget = new JButton("Target");
	
	public JTextPane infoPane = new JTextPane();
	public JScrollPane scroll = new JScrollPane(infoPane);
	
	public JProgressBar healthBar = new JProgressBar();
	public JProgressBar shieldBar = new JProgressBar();
	public JProgressBar energyBar = new JProgressBar();
	
	public JLabel classType = new JLabel();
	public Game game;
	
	public Elements(Class className, Game game) {
		this.className = className;
		this.game = game;
	}
	
	
	public void update() {
		healthBar.setValue(className.currentHealth);
		shieldBar.setValue(className.currentShield);
		energyBar.setValue(className.currentEnergy);
	}

}
