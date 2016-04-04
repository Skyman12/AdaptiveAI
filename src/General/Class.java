package General;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import WeightTemplates.TemplateType;
import WeightTemplates.WeightTemplate;

public abstract class Class {
	public static int powerChord = 0;
	
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
	public TemplateType templateType;
	
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
		if (!alive) {
			return 0;
		}
		
		int score = 0;
		score += currentHealth * 2;
		score += (int) currentShield * 1.5;
		score += currentEnergy;
		score += (int) bonusCritChance * .5;
		score += (int) bonusCritChanceTurns * 25;
		score += (int) turnsCleansed * 10; 
		score += lastLife == true ? 20 : 0;
		score += protectedBy != this ? 40 : 0;
		
		score -= (int) turnsStunned * 30;
		score -= (int) turnsConfused * 10;
		score -= forcedTarget != null ? 40 : 0;
			
		return score;	
	}
	
	public void assignWeights(TemplateType type) {
		templateType = type;
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
	
	public void updateTargetWeights(Attacks attack, double changeAmount) {
		if (attack.attackParameters.size() < 2) {
			return;
		} 
		
		double change = 0.0;
		
		for (AIAttackOptions option : attack.attackParameters.keySet()) {
			if (option == attack.lastUsedParameter) {
				if (changeAmount >= 0) {
					if (attack.attackParameters.get(option) + changeAmount > 100.00) {
						change = 100.00 - attack.attackParameters.get(option);
					} else {
						change = changeAmount;
					}
					
					attack.attackParameters.put(option, attack.attackParameters.get(option) + change);
				} 
				else {		
					if (attack.attackParameters.get(option) + changeAmount < 0.00) {
						change = 0.00 - attack.attackParameters.get(option);
					} else {
						change = changeAmount;
					}
					
					attack.attackParameters.put(option, attack.attackParameters.get(option) + change);
				}
			}
		}
		
		int remaining = attack.attackParameters.size() - 1;
		change = (-1.0 * change);
		for (AIAttackOptions option : attack.attackParameters.keySet()) {
			if (option != attack.lastUsedParameter) {
				changeAmount =  change / remaining;
				
				if (changeAmount >= 0) {
					if (attack.attackParameters.get(option) + changeAmount > 100.00) {
						change = 100.00 - attack.attackParameters.get(option);
					} else {
						change = changeAmount;
					}
					
					attack.attackParameters.put(option, attack.attackParameters.get(option) + change);
				} 
				else {		
					if (attack.attackParameters.get(option) + changeAmount < 0.00) {
						change = 0.00 - attack.attackParameters.get(option);
					} else {
						change = changeAmount;
					}
					
					attack.attackParameters.put(option, attack.attackParameters.get(option) + change);
				}
	
				remaining--;
			}
		}
		
		rebalanceAttackChoices(attack);
	}
	
	public void updateWeights(Attacks attack, int score) {
		double scale = 1000.00;
		double change = 0.0;
		
		if (attack.attackType == AttackType.ABILITIES) {
			for (Attacks theAttack : abilitiesList) {
				if (attack == theAttack) {
					double changeAmount = score / scale;
					if (changeAmount >= 0) {
						if (theAttack.weight + changeAmount > 100.00) {
							change = 100.00 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					} 
					else {
						if (theAttack.weight + changeAmount < 0.0) {
							change = 0 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					}
				}
			}
			
			double remaining = 3.0;
			change = (-1.0 * change);
			for (Attacks theAttack : abilitiesList) {
				if (attack != theAttack) {
					double changeAmount =  change / remaining;
					if (changeAmount >= 0) {
						if (theAttack.weight + changeAmount > 100.00) {
							change = 100.00 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					} 
					else {
						if (theAttack.weight + changeAmount < 0.0) {
							change = 0 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					}
					
					remaining--;
				}
			}
		} else {
			for (Attacks theAttack : basicAttackList) {
				if (attack == theAttack) {
					double changeAmount = score / scale;
					if (changeAmount >= 0) {
						if (theAttack.weight + changeAmount > 100.00) {
							change = 100.00 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					} 
					else {
						if (theAttack.weight + changeAmount < 0.0) {
							change = 0 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					}
				}
			}
			
			double remaining = 2.0;
			change = (-1.0 * change);
			for (Attacks theAttack : basicAttackList) {
				if (attack != theAttack) {
					double changeAmount =  change / remaining;
					if (changeAmount >= 0) {
						if (theAttack.weight + changeAmount > 100.00) {
							change = 100.00 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					} 
					else {
						if (theAttack.weight + changeAmount < 0.0) {
							change = 0 - theAttack.weight;
						} else {
							change = changeAmount;
						}
						
						theAttack.weight += change;
					}
					
					remaining--;
				}
			}
		}
			
		rebalance(basicAttackList);
		rebalance(abilitiesList);
		
//		try {
//			updateFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	private void rebalance(ArrayList<Attacks> attacks) {
		double total = 0.0;
		for (Attacks attack : attacks) {
			total += attack.weight;
		}
		
		if (total > 100.0) {
			Random random = new Random();
			int choice = random.nextInt(attacks.size());
			attacks.get(choice).weight -= total - 100.0;
		}
	}	
	
	private void rebalanceAttackChoices(Attacks attack) {
		double total = 0.0;
		for (Double d : attack.attackParameters.values()) {
			total += d;
		}
		
		if (total > 100.0) {
			Random random = new Random();
			ArrayList<AIAttackOptions> list = new ArrayList<AIAttackOptions>();
			list.addAll(attack.attackParameters.keySet());
			int choice = random.nextInt(list.size());
			attack.attackParameters.put(list.get(choice), 
					attack.attackParameters.get(list.get(choice)) - (total - 100.0));
		}
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
		int basicChoice = random.nextInt(Math.max(basicTotal, 1));
		int abilitesChoice = 0;
		if (abilitiesTotal >= 1) {
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
