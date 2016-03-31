package General;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	public ArrayList<Attacks> roundAttacks;
	public BoardManager boardManager;
	public ArrayList<Class> players;
	public boolean gameOver;
	public int roundCount;
	public Team winningTeam;
	public boolean roundInProgress;
	
	public Game(BoardManager boardManager, ArrayList<Class> players) {
		this.boardManager = boardManager;
		this.roundAttacks = new ArrayList<>();
		this.players = players;
		gameOver = false;
		winningTeam = null;
		roundInProgress = false;
		roundCount = 0;
	}
	
	public void resetGame() {
		for (Class c : players) {
			c.reset();
		}
		
		gameOver = false;
		roundInProgress = false;
		roundAttacks.clear();
		roundCount = 0;
	}
	
	public void startRound() {
		clear();
		makeAIMoves();
		orderAttacks();
		roundInProgress = true;
	}
	
	public void next() {
		if (roundAttacks.size() != 0) {
			Attacks attack = roundAttacks.get(0);
			String string = attack.executeAttack();
			printString(attack.theAttacker, string);
			attack.theTargets.clear();
			boardManager.updateBoard();
			checkForGameOver();
			roundAttacks.remove(0);
			if (gameOver) updateGameOver();
		} else {
			updateAfterRound();
			roundInProgress = false;
		}
	}
	
	public void processRound() {
		makeAIMoves();
		orderAttacks();
		
		for (Attacks attack : roundAttacks) {
			attack.executeAttack();
			attack.theTargets.clear();
			checkForGameOver();
			if (gameOver) {
				return;
			}
		}
		
		roundAttacks.clear();
		roundCount++;
		
		if (roundCount > 2000) {
			gameOver = true;
			winningTeam = Team.TIE_GAME;
			return;
		}
		
		for (Class c : players) {
			c.processRound();
		}
		
	}
	
	private void makeAIMoves() {
		for (Class player : players) {
			if (player.playerType == PlayerType.STATIC_AI) {
				roundAttacks.addAll(player.getStaticAIMove());
			}
		}
	}
	
	private void updateGameOver() {
		boardManager.gameOverUpdate("Game Over: " + winningTeam.toString() + " wins.");
	}
	
	private void checkForGameOver() {
		boolean team1Wins = true;
		boolean team2Wins = true;
		for (Class p : players) {
			if (p.team == Team.TEAM1 && p.alive) {
				team1Wins = false;
			}
			
			if (p.team == Team.TEAM2 && p.alive) {
				team2Wins = false;
			}
		}
		
		gameOver = team1Wins || team2Wins;
		if (team1Wins) {
			winningTeam = Team.TEAM1;
		} 
		if (team2Wins) {
			winningTeam = Team.TEAM2;
		} 
		if (team1Wins && team2Wins) {
			winningTeam = Team.TIE_GAME;
		} 
	}
	
	private void orderAttacks() {
		Collections.shuffle(roundAttacks);
		Collections.sort(roundAttacks);
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
	
	public Team simulateGame() {
		while (!gameOver) {
			processRound();
		}	
		
		return winningTeam;
	}
}
