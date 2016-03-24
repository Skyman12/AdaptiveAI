package General;

import java.util.ArrayList;

import UI.CharacterElement;
import UI.ComputerCharacterElement;

public class BoardManager {
	
	private ArrayList<CharacterElement> playerElements;
	private ArrayList<ComputerCharacterElement> computerElements;
	
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
	
	public void addElements(ArrayList<CharacterElement> playerElements, ArrayList<ComputerCharacterElement> computerElements) {
		this.playerElements = playerElements;
		this.computerElements = computerElements;
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
