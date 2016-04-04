package UI;

import java.util.ArrayList;

import Classes.Bard;
import Classes.Mage;
import Classes.Warlock;
import Classes.Warrior;
import General.BoardManager;
import General.Class;
import General.Game;
import General.GameSimulator;
import General.PlayerType;
import General.Team;

public class GameSimulatorCommandLine {

	public static void main(String[] args) {
		ArrayList<Class> players = new ArrayList<>();
		
		players.add(new Mage(Team.TEAM1, PlayerType.STATIC_AI));
		players.add(new Warrior(Team.TEAM1, PlayerType.STATIC_AI));
		players.add(new Warlock(Team.TEAM1, PlayerType.STATIC_AI));
		players.add(new Bard(Team.TEAM1, PlayerType.STATIC_AI));
		
		players.add(new Mage(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Warrior(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Warlock(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Bard(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		
		Game game = new Game(new BoardManager(), players);
		
		GameSimulator gameSimluator = new GameSimulator(game);
		gameSimluator.getAverageOfSimuations(10, 10000, true);

	}

}
