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
	public int team1WinsAfterTurningPoint;
	public int team1WinsBeforeTurningPoint;
	public int team2Wins;
	public int team2WinsAfterTurningPoint;
	public int team2WinsBeforeTurningPoint;
	public int tie;
	public int tieAfterTurningPoint;
	public int tieBeforeTurningPoint;
	public int turningPoint;
	
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
				fileWriter.addNewWeight(spells, spells.weight);
			}
			
			int count = 0;
			for (Attacks basicAttacks : c.basicAttackList) {
				if (count == 2) {
					fileWriter.addNewWeight(basicAttacks, basicAttacks.weight);
				} else {
					fileWriter.addNewWeight(basicAttacks, basicAttacks.weight);
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
	
	public void getAverageOfSimuations(int numberOfRuns, int numOfGames, boolean print) {
		for (int i = 0; i < numberOfRuns; i++) {
			resetWeights();
			simulateManyGames(numOfGames, print);
		}
		
		printStats(numberOfRuns);		
	}
	
	private void resetWeights() {
		for (Class player : game.players) {
			player.assignWeights(player.templateType);
		}	
	}

	public void simulateManyGames(int numOfGames, boolean print) {
		int turningPoint = 0;
		boolean turningPointFound = false;
		
		for (int i = 0; i < numOfGames; i++) {
			Team winner = game.simulateGame();
			switch (winner) {
				case TEAM1:
					team1Wins++;
					if (turningPointFound) team1WinsAfterTurningPoint++;
					else team1WinsBeforeTurningPoint++;
					break;
				case TEAM2:
					team2Wins++;
					if (turningPointFound) team2WinsAfterTurningPoint++;
					else team2WinsBeforeTurningPoint++;
					break;
				case TIE_GAME:
					tie++;
					if (turningPointFound) tieAfterTurningPoint++;
					else tieBeforeTurningPoint++;
					break;
			}
			
			if (winner == Team.TEAM2 && !turningPointFound) {
				turningPoint++;
			} else if (!turningPointFound){
				turningPoint = 0;
			}
			
			if (turningPoint == 10) {
				turningPointFound = true;
				turningPoint = i + 1;
				this.turningPoint += i + 1;
			}
			
			game.resetGame();
		}
		
		try {
			game.updateFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printStats(int numberOfRuns) {
		System.out.println("--------Simulation Complete ------------");
		System.out.println("Average number of Team 1 Wins over " + numberOfRuns + " simulations: " + team1Wins / numberOfRuns);
		System.out.println("Average number of Team 2 Wins over " + numberOfRuns + " simulations: " + team2Wins / numberOfRuns);
		System.out.println("Average number of Ties over " + numberOfRuns + " simulations: " + tie / numberOfRuns);
		System.out.println("---------Turning Point Found------------");
		System.out.println("Average turning point over " + numberOfRuns + " simulations: " + turningPoint / numberOfRuns);
		System.out.println("---------Before Turning Point ---------------");
		System.out.println("Average number of Team 1 Wins over " + numberOfRuns + " simulations: " + team1WinsBeforeTurningPoint / numberOfRuns);
		System.out.println("Average number of Team 2 Wins over " + numberOfRuns + " simulations: " + team2WinsBeforeTurningPoint / numberOfRuns);
		System.out.println("Average number of Ties over " + numberOfRuns + " simulations: " + tieBeforeTurningPoint / numberOfRuns);
		System.out.println("---------After Turning Point-----------------");
		System.out.println("Average number of Team 1 Wins over " + numberOfRuns + " simulations: " + team1WinsAfterTurningPoint / numberOfRuns);
		System.out.println("Average number of Team 2 Wins over " + numberOfRuns + " simulations: " + team2WinsAfterTurningPoint / numberOfRuns);
		System.out.println("Average number of Ties over " + numberOfRuns + " simulations: " + tieAfterTurningPoint / numberOfRuns);
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
