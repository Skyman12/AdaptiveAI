package General;
import java.util.ArrayList;
import java.util.Random;

public abstract class Attacks {

	public int damage;
	public int critChance;
	public int cost;
	public int speed;
	public int numOfTargets;
	
	public Class theAttacker;
	public ArrayList<Class> theTargets;
	public ArrayList<Class> allPlayers;
	
	public AttackType attackType;
	
	public String attackName;
	public String attackDescription;
	
	public Attacks(Class attacker) {
		theAttacker = attacker;
		theTargets = new ArrayList<>();
	}
	
	protected abstract String attack(Class target);
	
	public abstract void chooseTargetForAttack(Class target);
	
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
	
	public boolean doBeginningActions(Class attacker, Class target) {
		if (target == null) {
			return false;
		}
		
		Class targetFound = target;
		
		if (attacker.turnsStunned > 0) return false;
		if (attacker.turnsConfused > 0) {
			targetFound = chooseRandomPlayer();
		}
		
		targetFound = target.protectedBy;
		theAttacker = attacker;
		
		if (targetFound.invunerable) return false;
	
		return true;
	}
	
	public ArrayList<Class> getAliveOpponents(Class attacker) {
		ArrayList<Class> newPlayers = new ArrayList<>();
		for (Class player : allPlayers) {
			if (player.team != attacker.team && player.alive) newPlayers.add(player);
		}
		
		return newPlayers;
	}
	
	public void restore (int healthAmount, int shieldAmount, int energyAmount, Class players) {
		ArrayList<Class> newPlayers = new ArrayList<>();
		newPlayers.add(players);
		restore(healthAmount, shieldAmount, energyAmount, newPlayers);
	}
	
	public void restore (int healthAmount, int shieldAmount, int energyAmount, ArrayList<Class> players) {
		for (Class player : players) {
			player.currentEnergy = Math.min(player.baseEnergy, player.currentEnergy + energyAmount);
			player.currentShield = Math.min(player.baseShield, player.currentShield + shieldAmount);
			player.currentHealth = Math.min(player.baseHealth, player.currentHealth + healthAmount);
		}
	}
	
	public void stun (Class attacker, Class target, int damage, int turnsStunned) {
		dealDamage(attacker, target, damage, 0);
		if (target.turnsCleansed > 0) return;
		
		target.turnsStunned = turnsStunned;
	}
	
	public String buildAttackString(Class attacker, Class target, int damage) {
		return attacker.name + " attacked " + target.name + " and dealt " + damage + "damage";
	}
	
}
