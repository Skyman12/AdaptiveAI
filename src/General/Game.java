package General;

import java.util.ArrayList;

import UI.CharacterElement;

public class Game {
	public ArrayList<Attacks> roundAttacks;
	public BoardManager boardManager;
	public ArrayList<Class> players;
	
	public Game(BoardManager boardManager, ArrayList<Class> players) {
		this.boardManager = boardManager;
		this.roundAttacks = new ArrayList<>();
		this.players = players;
	}
	
	public void processRound() {
		clear();
		
		for (Attacks attack : roundAttacks) {
			String string = attack.executeAttack();
			printString(attack.theAttacker, string);
			attack.theTargets.clear();
		}
		updateAfterRound();
	}
	
	private void clear() {
		boardManager.clear();
	}
	
	private void printString(Class theAttacker, String string) {
		boardManager.addString(theAttacker, string);
	}
	
	private void updateAfterRound() {
		boardManager.updateBoard();
		roundAttacks.clear();
		
		for (Class c : players) {
			c.processRound();
		}
		
		boardManager.updateButtons();
	}
}
