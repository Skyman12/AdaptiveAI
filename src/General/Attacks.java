package General;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public abstract class Attacks implements Comparable<Attacks>{

	public int damage;
	public int critChance;
	public int cost;
	public int speed;
	public int numOfTargets;
	
	public int effectivness;
	
	public Double weight;
	
	public Class theAttacker;
	public Class theTarget;
	public ArrayList<Class> theTargets;
	public ArrayList<Class> allPlayers;
	
	public AttackType attackType;
	
	public String attackName;
	public String attackDescription;
	
	public HashMap<AIAttackOptions, Double> attackParameters;
	public AIAttackOptions lastUsedParameter;
	
	public Attacks(Class attacker) {
		theAttacker = attacker;
		theTargets = new ArrayList<>();
		weight = 0.0;
		
		attackParameters = new HashMap<AIAttackOptions, Double>();
	}
	
	protected abstract String attack(Class target);
	
	public abstract void chooseTargetForAttack(Class target);
	
	public abstract void chooseAITarget();
	
	public void addAllPlayers(ArrayList<Class> allPlayers) {
		this.allPlayers = allPlayers;
	}
	
	public String executeAttack() {
		String string = "";
		for (Class target : theTargets) {
			string += attack(target);
		}
		
		theAttacker.currentEnergy -= cost;
		
		return string;
	}
	
	public String toString() {
		return attackName + " --  Attacker: " + theAttacker.name + " -- Team: " + theAttacker.team;
	}

	public int compareTo(Attacks o) {
		return this.speed - o.speed;
	} 
	
	public boolean getHardCap() {
		// Check for energy cap - do they have enough energy
		if (theAttacker.currentEnergy < cost) {
			return false;
		}
		
		return true;
	}
	
	public boolean getSoftCap() {
		return true;
	}
	
	public String getToolTip() {
		String s = "<html> <b>";
		s += attackName + "</b><br><i>";
		s += attackDescription + "</i><br>";
		s += "----------------" + "<br>";
		s += "Damage: " + damage + "<br>";
		s += "Speed: " + speed + "<br>";
		s += "Cost: " + cost + "<br>";
		s += "Crit Chance: " + critChance + "<br>";
		s += "</html>";
		return s;
	}
	
	public Class chooseRandomPlayer() {
		Random random = new Random();
		int ran = random.nextInt(allPlayers.size() - 1);
		return allPlayers.get(ran);
	}
	
	public int dealDamage(Class attacker, Class target, int damage, int critChance) {
		Random random = new Random();
		int chance = random.nextInt(100);
		boolean crit = chance < critChance + attacker.bonusCritChance;
		int damageAmount = (int) (crit ? damage * 1.5 : damage);
		int damageDealt = damageAmount;
		
		// Check is someone is protecting the target
		if (attacker.forcedTarget != null) {
			target = attacker.forcedTarget;
		}
		
		// Check if the target countered
		if (attackType == AttackType.BASIC_ATTACK && target.countered) {
			target.countered = false;
			target = theAttacker;
		}
		
		if (target.currentShield >= damageAmount) {
			target.currentShield -= damageAmount;
			return damageDealt;
		}  else {
			damageAmount = damageAmount - target.currentShield;
			target.currentShield = 0;
		}
		
		if (target.currentHealth > damageAmount) {
			target.currentHealth -= damageAmount;
			return damageDealt;
		} else {
			if (target.lastLife) {
				target.currentHealth = 1;
			} else {
				target.currentHealth = 0;
				target.alive = false;
			}
		}
		
		return damageDealt;
	}
	
	public String doBeginningActions(Class attacker, Class target) {
		effectivness = 0;
		if (target == null) {
			return "Invalid Action";
		}
		
		if (!attacker.alive) {
			return "Invalid Action - Attacker Dead\n";
		}
		
		Class targetFound = target;
		
		if (attacker.turnsStunned > 0) return "No attack, stunned!";
		if (attacker.turnsConfused > 0) {
			targetFound = chooseRandomPlayer();
		}
		
		targetFound = target.protectedBy;
		theAttacker = attacker;
		theTarget = targetFound;
		
		if (targetFound.invunerable) return "No attack, target was invunerable";
	
		return "Success";
	}
	
	public ArrayList<Class> getAliveOpponents(Class attacker) {
		ArrayList<Class> newPlayers = new ArrayList<>();
		for (Class player : allPlayers) {
			if (player.team != attacker.team && player.alive) newPlayers.add(player);
		}
		
		return newPlayers;
	}
	
	public ArrayList<Class> getAliveAllies(Class attacker) {
		ArrayList<Class> newPlayers = new ArrayList<>();
		for (Class player : allPlayers) {
			if (player.team == attacker.team && player.alive && player != attacker) newPlayers.add(player);
		}
		
		return newPlayers;
	}
	
	public ArrayList<Class> getAlivePlayers(Class attacker) {
		ArrayList<Class> newPlayers = new ArrayList<>();
		for (Class player : allPlayers) {
			if (player.alive && player != attacker) newPlayers.add(player);
		}
		
		return newPlayers;
	}
	
	public void restore (int healthAmount, int shieldAmount, int energyAmount, Class target) {
		target.currentEnergy = Math.min(target.baseEnergy, target.currentEnergy + energyAmount);
		target.currentShield = Math.min(target.baseShield, target.currentShield + shieldAmount);
		target.currentHealth = Math.min(target.baseHealth, target.currentHealth + healthAmount);
	}
	
	public void stun (Class attacker, Class target, int damage, int turnsStunned) {
		dealDamage(attacker, target, damage, 0);
		if (target.turnsCleansed > 0) return;
		
		target.turnsStunned = turnsStunned;
	}
	
	public void cleanse (Class attacker, Class target, int turnsCleansed) {
		if (target.turnsCleansed > 0 || target.turnsConfused > 0) {
			effectivness = 60;
		} else {
			effectivness = 20;
		}
		target.turnsStunned = 0;
		target.turnsConfused = 0;
		target.turnsCleansed = turnsCleansed;
	}
	
	public void confuse (Class attacker, Class target, int damage, int turnsConfused) {
		dealDamage(attacker, target, damage, 0);
		if (target.turnsCleansed > 0) return;
		
		target.turnsConfused = turnsConfused;
	}
	
	public String buildAttackString(Class attacker, Class target, int damage) {
		return attacker.name + " attacked " + target.name + " and dealt " + damage + "damage";
	}
	
	public AIAttackOptions getAttackType(HashMap<AIAttackOptions, Double> attackOptions) {
		if (attackOptions.size() == 0) {
			return AIAttackOptions.RANDOM;
		}
		
		Random random = new Random();
		ArrayList<AIAttackOptions> options = new ArrayList<AIAttackOptions>();
		options.addAll(attackOptions.keySet());
		
		double x = 0.0;
		for (Double d : attackOptions.values()) {
			x += d;
		}
		
		int choice = random.nextInt((int) x);
		int total = 0;
		int count = 0;
		while (total < choice && count < options.size() - 1) {
			total += attackOptions.get(options.get(count));
			if (total >= choice) {
				break;
			}
			count++;
		}
		
		return options.get(count);
	}
	
	public void chooseAttackTargetAI(HashMap<AIAttackOptions, Double> attackOptions) {
		boolean targetsFound = false;
		
		while (!targetsFound) {
			AIAttackOptions attackType = getAttackType(attackOptions);
			
			switch (attackType) {
				case RANDOM: 
					chooseTargetRandomEnemy();
					targetsFound = true;
					break;
				case LOWEST_HEALTH: 
					chooseLowestHealthTargetEnemy();
					targetsFound = true;
					break;
				case BARD:
					targetsFound = chooseByClassEnemy("Bard");
					break;
				case MAGE:
					targetsFound = chooseByClassEnemy("Mage");
					break;
				case PRIEST:
					targetsFound = chooseByClassEnemy("Priest");
					break;
				case ROGUE:
					targetsFound = chooseByClassEnemy("Rogue");
					break;
				case WARLOCK:
					targetsFound = chooseByClassEnemy("Warlock");
					break;
				case WARRIOR:
					targetsFound = chooseByClassEnemy("Warrior");
					break;
				default:
					break;
			}
			
			if (targetsFound) {
				lastUsedParameter = attackType;
				return;
			}
			
			attackOptions.remove(attackType);
		}
	}
	
	public void chooseSupportTargetAI(HashMap<AIAttackOptions, Double> attackOptions) {
		boolean targetsFound = false;
		
		while (!targetsFound) {
			AIAttackOptions attackType = getAttackType(attackOptions);
			
			switch (attackType) {
				case RANDOM: 
					chooseTargetRandomAlly();
					targetsFound = true;
					break;
				case LOWEST_HEALTH: 
					chooseLowestHealthTargetAlly();
					targetsFound = true;
					break;
				case LOWEST_SHIELD: 
					chooseLowestShieldTargetAlly();
					targetsFound = true;
					break;
				case LOWEST_ENERGY: 
					chooseLowestEnergyTargetAlly();
					targetsFound = true;
					break;
				case BARD:
					targetsFound = chooseByClassAlly("Bard");
					break;
				case MAGE:
					targetsFound = chooseByClassAlly("Mage");
					break;
				case PRIEST:
					targetsFound = chooseByClassAlly("Priest");
					break;
				case ROGUE:
					targetsFound = chooseByClassAlly("Rogue");
					break;
				case WARLOCK:
					targetsFound = chooseByClassAlly("Warlock");
					break;
				case WARRIOR:
					targetsFound = chooseByClassAlly("Warrior");
					break;
				default:
					break;
			}
			
			if (targetsFound) {
				lastUsedParameter = attackType;
				return;
			}
			
			attackOptions.remove(attackType);
		}
		
	}
	
	private void chooseTargetRandomEnemy() {
		Random random = new Random();
		
		ArrayList<Class> enemies = getAliveOpponents(theAttacker);
		int enemyChoice = random.nextInt(enemies.size());
		theTargets.add(enemies.get(enemyChoice));
	}
	
	private void chooseTargetRandomAlly() {
		Random random = new Random();
		
		ArrayList<Class> allies = getAliveAllies(theAttacker);
		allies.add(theAttacker);
		int allyChoice = random.nextInt(allies.size());
		theTargets.add(allies.get(allyChoice));
	}
	
	private void chooseLowestHealthTargetAlly() {
		ArrayList<Class> allies = getAliveAllies(theAttacker);
		allies.add(theAttacker);
		Class theAlly = allies.get(0);
		
		for (Class ally : allies) {
			if (theAlly.currentHealth > ally.currentHealth) {
				theAlly = ally;
			}
		}
		
		theTargets.add(theAlly);
	}
	
	private void chooseLowestEnergyTargetAlly() {
		ArrayList<Class> allies = getAliveAllies(theAttacker);
		allies.add(theAttacker);
		Class theAlly = allies.get(0);
		
		for (Class ally : allies) {
			if (theAlly.currentEnergy > ally.currentEnergy) {
				theAlly = ally;
			}
		}
		
		theTargets.add(theAlly);
	}
	
	private void chooseLowestShieldTargetAlly() {
		ArrayList<Class> allies = getAliveAllies(theAttacker);
		allies.add(theAttacker);
		Class theAlly = allies.get(0);
		
		for (Class ally : allies) {
			if (theAlly.currentShield > ally.currentShield) {
				theAlly = ally;
			}
		}
		
		theTargets.add(theAlly);
	}
	
	private void chooseLowestHealthTargetEnemy() {
		ArrayList<Class> enemies = getAliveOpponents(theAttacker);
		Class theEnemy = enemies.get(0);
		
		for (Class enemy : enemies) {
			if (theEnemy.currentHealth + theEnemy.currentShield > enemy.currentHealth + enemy.currentShield) {
				theEnemy = enemy;
			}
		}
		
		theTargets.add(theEnemy);
	}
	
	private boolean chooseByClassEnemy(String className) {
		ArrayList<Class> enemies = getAliveOpponents(theAttacker);
		for (Class enemy : enemies) {
			if (enemy.name.equals(className)) {
				theTargets.add(enemy);
				return true;
			}
		}
		
		return false;
	}
	
	private boolean chooseByClassAlly(String className) {
		ArrayList<Class> allies = getAliveAllies(theAttacker);
		for (Class ally : allies) {
			if (ally.name.equals(className)) {
				theTargets.add(ally);
				return true;
			}
		}
		
		return false;
	}
	
}
