package General;
import java.util.ArrayList;

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
	
}
