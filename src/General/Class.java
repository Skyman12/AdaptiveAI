package General;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import WeightTemplates.TemplateType;
import WeightTemplates.WeightTemplate;

public abstract class Class {
	
	public int baseHealth;
	public int currentHealth;
	public int baseShield;
	public int currentShield;
	public int shieldRechargeRate;
	public int baseEnergy;
	public int currentEnergy;
	public int energyRechargeRate;
	
	public int bonusCritChance;
	public int bonusCritChanceTurns;
	
	public ArrayList<Attacks> basicAttackList;
	public ArrayList<Attacks> abilitiesList;
	
	public int numberOfAttacksThisRound;
	public int turnsStunned;
	public int turnsConfused;
	
	public Class protectedBy;
	public boolean lastLife;
	public boolean alive;
	
	public boolean invunerable;
	public int turnsCleansed;
	public boolean countered;
	public Class forcedTarget;
	
	public Team team;
	
	public String name;
	
	public WeightTemplate weightTemplate;
	
	public PlayerType playerType;
	
	public Class() {
		bonusCritChance = 0;
		bonusCritChanceTurns = 0;
		numberOfAttacksThisRound = 1;
		turnsStunned = 0;
		turnsConfused = 0;
		protectedBy = this;
		lastLife = false;
		alive = true;
		invunerable = false;
		turnsCleansed = 0;
		countered = false;
		forcedTarget = null;
		basicAttackList = new ArrayList<>();
		abilitiesList = new ArrayList<>();
	}
	
	public void processRound() {
		currentShield = Math.min(baseShield, currentShield + shieldRechargeRate);
		currentEnergy = Math.min(baseEnergy, currentEnergy + energyRechargeRate);
		turnsStunned = Math.max(0, turnsStunned - 1);
		turnsConfused = Math.max(0, turnsConfused - 1);
		turnsCleansed = Math.max(0, turnsCleansed - 1);
		bonusCritChanceTurns = Math.max(0, bonusCritChanceTurns - 1);
		if (bonusCritChanceTurns == 0) {
			bonusCritChance = 0;
		}
		
		protectedBy = this;
		forcedTarget = null;
		lastLife = false;
		invunerable = false;
		countered = false;
	}
	
	public void reset() {
		bonusCritChance = 0;
		bonusCritChanceTurns = 0;
		numberOfAttacksThisRound = 1;
		turnsStunned = 0;
		turnsConfused = 0;
		protectedBy = this;
		lastLife = false;
		alive = true;
		invunerable = false;
		turnsCleansed = 0;
		countered = false;
		forcedTarget = null;
		currentEnergy = baseEnergy;
		currentHealth = baseHealth;
		currentShield = baseShield;
	}
	
	public int getCurrentPlayerScore() {	
		int score = 0;
		score += currentHealth * 4;
		score += (int) currentShield * 3;
		score += currentEnergy;
		score += (int) bonusCritChance * .5;
		score += (int) bonusCritChanceTurns * 25;
		score += (int) turnsCleansed * 50; 
		score += lastLife == true ? 20 : 0;
		score += protectedBy != this ? 50 : 0;
		
		score -= (int) turnsStunned * 100;
		score -= (int) turnsConfused * 80;
		score -= forcedTarget != null ? 50 : 0;
			
		return score;	
	}
	
	public void assignWeights(TemplateType type) {
		ArrayList<Double> weights = null;
		switch (type) {
			case AGGRESSIVE:
				weights = weightTemplate.getAggressiveTemplate();
				break;
			case DEFENSIVE:
				weights = weightTemplate.getDefensiveTemplate();
				break;
			case BALANCED:
				weights = weightTemplate.getBalancedTemplate();
				break;
			case CC:
				weights = weightTemplate.getCCTemplate();
				break;
		}
		
		// 0 - 2 are basics
		// 3 - 6 are abilities
		for (int i = 0; i < 3; i++) {
			basicAttackList.get(i).weight = weights.get(i);
		}
		
		for (int i = 0; i < 4; i++) {
			abilitiesList.get(i).weight = weights.get(i + 3);
		}
		
	}
	
	public void updateWeights(Attacks attack, int score) {
		double scale = 1000.00;
		
		if (attack.attackType == AttackType.ABILITIES) {
			for (Attacks theAttack : abilitiesList) {
				if (attack == theAttack) {
					if (score >= 0) {
						theAttack.weight = Math.min(100.0, theAttack.weight + score / scale);
					} else {
						theAttack.weight = Math.max(0.0, theAttack.weight + score / scale);
					}
				} else {
					if (score < 0) {
						theAttack.weight = Math.min(100.0, theAttack.weight - score / ( 2 * scale));
					} else {
						theAttack.weight = Math.max(0.0, theAttack.weight - score /  ( 2 * scale));
					}
				}
			}
		} else {
			for (Attacks theAttack : basicAttackList) {
				if (attack == theAttack) {
					if (score >= 0) {
						theAttack.weight = Math.min(100.0, theAttack.weight + score / scale);
					} else {
						theAttack.weight = Math.max(0.0, theAttack.weight + score / scale);
					}
				} else {
					if (score < 0) {
						theAttack.weight = Math.min(100.0, theAttack.weight - score /  ( 3 * scale));
					} else {
						theAttack.weight = Math.max(0.0, theAttack.weight - score /  ( 3 * scale));
					}
				}
			}
		}
		
//		try {
//			updateFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void updateFile() throws IOException {
		FileWriter fileWriter = new FileWriter("ability_weights.txt");
		
		for (Attacks attack : basicAttackList) {
			fileWriter.addNewWeight(attack, attack.weight);
		}
		
		for (Attacks attack : abilitiesList) {
			fileWriter.addNewWeight(attack, attack.weight);
		}	
	}

	public ArrayList<Attacks> getStaticAIMove() {
		ArrayList<Attacks> movesToMake = new ArrayList<>();
		
		ArrayList<Attacks> possibleBasicAttacks = new ArrayList<>();
		int basicTotal = 0;
		for (Attacks attack : basicAttackList) {
			if (attack.getHardCap() && attack.getSoftCap()) {
				possibleBasicAttacks.add(attack);
				basicTotal += attack.weight;
			}
		}
		
		ArrayList<Attacks> possibleAbilities = new ArrayList<>();
		int abilitiesTotal = 0;
		for (Attacks attack : abilitiesList) {
			if (attack.getHardCap() && attack.getSoftCap()) {
				possibleAbilities.add(attack);
				abilitiesTotal += attack.weight;
			}
		}	
		
		Random random = new Random();
		int basicChoice = random.nextInt(basicTotal);
		int abilitesChoice = 0;
		if (abilitiesTotal != 0) {
			 abilitesChoice = random.nextInt(abilitiesTotal);
		}
		
		int total = 0;
		int count = 0;
		while (total < basicChoice) {
			total += possibleBasicAttacks.get(count).weight;
			if (total >= basicChoice) {
				break;
			}
			count++;
		}
		possibleBasicAttacks.get(count).chooseAITarget();
		movesToMake.add(possibleBasicAttacks.get(count));
		
		// Cannot use an ability this turn
		if (abilitiesTotal == 0) {
			return movesToMake;
		}
		
		total = 0;
		count = 0;
		while (total < abilitesChoice) {
			total += possibleAbilities.get(count).weight;
			if (total >= abilitesChoice) {
				break;
			}
			count++;
		}
		possibleAbilities.get(count).chooseAITarget();
		movesToMake.add(possibleAbilities.get(count));
		
		return movesToMake;
	}
	
}
