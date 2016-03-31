package General;

public class GameSimulator {
	private Game game;
	
	public int team1Wins;
	public int team2Wins;
	public int tie;
	
	public GameSimulator(Game game) {
		this.game = game;
		addPlayers();
	}
	
	public void simulateGame() {
		game.simulateGame();
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
