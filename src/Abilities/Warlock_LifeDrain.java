package Abilities;


import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_LifeDrain extends Attacks {
	public Warlock_LifeDrain(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Life Drain";
		attackDescription = "Chose an enemy, deal damage to the enemy, restores between 50% of damage dealt to self.";
	
		damage = 50;
		speed = 7;
		cost = 80;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.PRIEST, 25.0);
		attackParameters.put(AIAttackOptions.BARD, 25.0);
		attackParameters.put(AIAttackOptions.MAGE, 25.0);
		attackParameters.put(AIAttackOptions.RANDOM, 25.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);
		
		restore((int) (damageDealt * .5), 0, 0, theAttacker);
		
		return "Used " + attackName + " on " + theTarget.name + " --- Healed for " + (int) (damageDealt * .5) + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public boolean getSoftCap() {
		if (theAttacker.baseHealth - theAttacker.currentHealth > 25) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		chooseAttackTargetAI(attackParameters);
	}

}
