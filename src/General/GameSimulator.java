package General;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Classes.Bard;
import Classes.Mage;
import Classes.Priest;
import Classes.Rogue;
import Classes.Warlock;
import Classes.Warrior;

public class GameSimulator {
	private Game game;
	private FileWriter fileWriter;
	
	public int team1Wins;
	public int team2Wins;
	public int tie;
	
	public GameSimulator(Game game) {
		this.game = game;
		this.fileWriter = new FileWriter("ability_weights.txt");
		try {
			buildInitialWeights();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addPlayers();
	}
	
	public void simulateGame() {
		game.simulateGame();
	}
	
	public void buildInitialWeights() throws IOException {
		ArrayList<Class> allPlayers = new ArrayList<Class>();
		allPlayers.add(new Warrior(null, null));
		allPlayers.add(new Warlock(null, null));
		allPlayers.add(new Priest(null, null));
		allPlayers.add(new Mage(null, null));
		allPlayers.add(new Bard(null, null));
		allPlayers.add(new Rogue(null, null));
		
		for (Class c : allPlayers) {
			for (Attacks spells : c.abilitiesList) {
				fileWriter.addNewWeight(spells, 25.0);
			}
			
			int count = 0;
			for (Attacks basicAttacks : c.basicAttackList) {
				if (count == 2) {
					fileWriter.addNewWeight(basicAttacks, 34.0);
				} else {
					fileWriter.addNewWeight(basicAttacks, 33.0);
				}
				count++;
			}
		}
	}
	
	public void printAllWeights() throws FileNotFoundException {
		ArrayList<Class> allPlayers = new ArrayList<Class>();
		allPlayers.add(new Warrior(null, null));
		allPlayers.add(new Warlock(null, null));
		allPlayers.add(new Priest(null, null));
		allPlayers.add(new Mage(null, null));
		allPlayers.add(new Bard(null, null));
		allPlayers.add(new Rogue(null, null));
		
		for (Class c : allPlayers) {
			for (Attacks spells : c.abilitiesList) {
				System.out.println(fileWriter.getSpellWeight(spells) + "");
			}
			
			for (Attacks basicAttacks : c.basicAttackList) {
				System.out.println(fileWriter.getSpellWeight(basicAttacks) + "");
			}
		}
	}
	
	public void simulateManyGames(int numOfGames) {
		for (int i = 0; i < numOfGames; i++) {
			Team winner = game.simulateGame();
			switch (winner) {
				case TEAM1:
					team1Wins++;
					break;
				case TEAM2:
					team2Wins++;
					break;
				case TIE_GAME:
					tie++;
					break;
			}
			
			game.resetGame();
			System.out.println("Game over : " + i);
		}
		
		System.out.println("Number of Team 1 Wins: " + team1Wins);
		System.out.println("Number of Team 2 Wins: " + team2Wins);
		System.out.println("Number of Ties: " + tie);
	}
	
	private void addPlayers() {
		for (Class c : game.players) {
			for (Attacks spells : c.abilitiesList) {
				spells.addAllPlayers(game.players);
			}
			
			for (Attacks basicAttacks : c.basicAttackList) {
				basicAttacks.addAllPlayers(game.players);
			}
		}
	}
}
