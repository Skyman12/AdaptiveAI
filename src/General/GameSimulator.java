package General;

public class GameSimulator {
	private Game game;
	
	public GameSimulator(Game game) {
		this.game = game;
		addPlayers();
	}
	
	public void simulateGame() {
		game.simulateGame();
	}
	
	public void simulateManyGames(int numOfGames) {
		for (int i = 0; i < numOfGames; i++) {
			game.simulateGame();
			game.resetGame();
		}
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
