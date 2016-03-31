package General;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import UI.CharacterElement;
import UI.ComputerCharacterElement;

public class BoardManager {
	
	private ArrayList<CharacterElement> playerElements;
	private ArrayList<ComputerCharacterElement> computerElements;
	private JLabel winningText;
	private JButton continueButton;
	
	public void updateBoard() {
		for (CharacterElement c : playerElements) {
			c.update();
		}
		
		for (ComputerCharacterElement c : computerElements) {
			c.update();
		}
	}
	
	public void updateButtons() {
		for (CharacterElement c : playerElements) {
			c.turnButtonsOn();
		}
	}
	
	public void gameOverUpdate(String text) {
		winningText.setVisible(true);
		winningText.setText(text);
		continueButton.setVisible(false);
	}
	
	public void addElements(ArrayList<CharacterElement> playerElements, ArrayList<ComputerCharacterElement> computerElements,
			JLabel winningText, JButton continueButton) {
		this.playerElements = playerElements;
		this.computerElements = computerElements;
		this.winningText = winningText;
		this.continueButton = continueButton;
	}

	public void addString(Class theAttacker, String string) {
		for (CharacterElement c : playerElements) {
			if (c.className == theAttacker) {
				c.infoPane.setText(c.infoPane.getText() + string);
				return;
			}
		}
		
		for (ComputerCharacterElement c : computerElements) {
			if (c.className == theAttacker) {
				c.infoPane.setText(c.infoPane.getText() + string);
				return;
			}
		}	
	}

	public void clear() {
		for (CharacterElement c : playerElements) {
			c.infoPane.setText("");
		}
		
		for (ComputerCharacterElement c : computerElements) {
			c.infoPane.setText("");
		}	
	}

}
