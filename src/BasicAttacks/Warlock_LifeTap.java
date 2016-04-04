package BasicAttacks;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_LifeTap extends Attacks {
	public Warlock_LifeTap(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Life Tab";
		attackDescription = "Chose an enemy, deal damage to the enemy, restores between 10%-50% of damage dealt to self.";
	
		damage = 10;
		speed = 2;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.PRIEST, 20.0);
		attackParameters.put(AIAttackOptions.BARD, 20.0);
		attackParameters.put(AIAttackOptions.MAGE, 20.0);
		attackParameters.put(AIAttackOptions.RANDOM, 20.0);
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 20.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);
		
		Random random = new Random();
		double percentHealed = (random.nextInt(40) + 10) / 100;
		
		restore((int) (damageDealt * percentHealed), 0, 0, theAttacker);
		
		effectivness = (int) (damageDealt * percentHealed);
		
		return "Used " + attackName + " on " + theTarget.name + " --- Healed for " + (int) (damageDealt * percentHealed) + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public boolean getSoftCap() {
		if (theAttacker.baseHealth - theAttacker.currentHealth > 15) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		chooseAttackTargetAI(attackParameters);
	}

}
