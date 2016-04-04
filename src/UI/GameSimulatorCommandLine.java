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
import WeightTemplates.TemplateType;

public class GameSimulatorCommandLine {

	public static void main(String[] args) {
		ArrayList<Class> players = new ArrayList<>();
		
		Mage mage = new Mage(Team.TEAM1,  PlayerType.STATIC_AI);
		mage.assignWeights(TemplateType.CC);
		players.add(mage);
		
		Warrior warrior = new Warrior(Team.TEAM1,  PlayerType.STATIC_AI);
		warrior.assignWeights(TemplateType.CC);
		players.add(warrior);
		
		Warlock warlock = new Warlock(Team.TEAM1,  PlayerType.STATIC_AI);
		warlock.assignWeights(TemplateType.CC);
		players.add(warlock);
		
		Bard bard = new Bard(Team.TEAM1,  PlayerType.STATIC_AI);
		bard.assignWeights(TemplateType.CC);
		players.add(bard);
		
		
		players.add(new Mage(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Warrior(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Warlock(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		players.add(new Bard(Team.TEAM2, PlayerType.ADAPTIVE_AI));
		
		Game game = new Game(new BoardManager(), players);
		
		GameSimulator gameSimluator = new GameSimulator(game);
		gameSimluator.getAverageOfSimuations(100, 10000, true);

	}

}
