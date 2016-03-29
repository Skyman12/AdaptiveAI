package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Mage;
import Classes.Rogue;
import Classes.Warrior;
import General.Attacks;
import General.BoardManager;
import General.Class;
import General.Game;
import General.Team;

public class GameScreen extends JFrame {

	private JPanel contentPane;
	private JButton btnProcessTurn;
	private Game game;
	private ArrayList<Class> team1List;
	private ArrayList<Class> team2List;
	private ArrayList<Class> allCharacters;
	
	private ArrayList<CharacterElement> playerElements;
	private ArrayList<ComputerCharacterElement> computerElements;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen frame = new GameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		team1List = new ArrayList<>();
		team1List.add(new Mage(Team.TEAM1));
		team1List.add(new Warrior(Team.TEAM1));
		team1List.add(new Rogue(Team.TEAM1));
		team1List.add(new Warrior(Team.TEAM1));
		
		team2List = new ArrayList<>();
		team2List.add(new Mage(Team.TEAM2));
		team2List.add(new Warrior(Team.TEAM2));
		team2List.add(new Rogue(Team.TEAM2));
		team2List.add(new Warrior(Team.TEAM2));
		
		allCharacters = new ArrayList<>();
		allCharacters.addAll(team1List);
		allCharacters.addAll(team2List);
		
		playerElements = new ArrayList<>();
		computerElements = new ArrayList<>();
		
		BoardManager boardManager = new BoardManager();
	
		game = new Game(boardManager, allCharacters);
		
		CharacterElement element1 = new CharacterElement(team1List.get(0), game);
		element1.add(contentPane, 120, 550);
		playerElements.add(element1);
		
		CharacterElement element2 = new CharacterElement(team1List.get(1), game);
		element2.add(contentPane, 495, 550);
		playerElements.add(element2);
		
		CharacterElement element3 = new CharacterElement(team1List.get(2), game);
		element3.add(contentPane, 870, 550);
		playerElements.add(element3);
		
		CharacterElement element4 = new CharacterElement(team1List.get(3), game);
		element4.add(contentPane, 1245, 550);
		playerElements.add(element4);
		
		ComputerCharacterElement computerElement1 = new ComputerCharacterElement(team2List.get(0), game);
		computerElement1.add(contentPane, 120, 100);
		computerElements.add(computerElement1);
		
		ComputerCharacterElement computerElement2 = new ComputerCharacterElement(team2List.get(1), game);
		computerElement2.add(contentPane, 495, 100);
		computerElements.add(computerElement2);
		
		ComputerCharacterElement computerElement3 = new ComputerCharacterElement(team2List.get(2), game);
		computerElement3.add(contentPane, 870, 100);
		computerElements.add(computerElement3);
		
		ComputerCharacterElement computerElement4 = new ComputerCharacterElement(team2List.get(3), game);
		computerElement4.add(contentPane, 1245, 100);
		computerElements.add(computerElement4);
		
		btnProcessTurn = new JButton("Process Turn");
		btnProcessTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.processRound();
			}
		});
		btnProcessTurn.setBounds(732, 447, 256, 76);
		contentPane.add(btnProcessTurn);	
		
		addPlayers();
		boardManager.addElements(playerElements, computerElements);
	}
	
	private void addPlayers() {
		for (CharacterElement c : playerElements) {
			for (Attacks spells : c.className.abilitiesList) {
				spells.addAllPlayers(allCharacters);
			}
			
			for (Attacks basicAttacks : c.className.basicAttackList) {
				basicAttacks.addAllPlayers(allCharacters);
			}
		}
		
		for (ComputerCharacterElement c : computerElements) {
			for (Attacks spells : c.className.abilitiesList) {
				spells.addAllPlayers(allCharacters);
			}
			
			for (Attacks basicAttacks : c.className.basicAttackList) {
				basicAttacks.addAllPlayers(allCharacters);
			}
		}
	}
}
