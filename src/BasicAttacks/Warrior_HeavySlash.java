package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_HeavySlash extends Attacks {
	public Warrior_HeavySlash(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Heavy Slash";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 30;
		speed = 7;
		critChance = 50;
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
		
		return "Used " + attackName + " on " + theTarget.name + " -- Dealt " + damageDealt + " damage\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		chooseAttackTargetAI(attackParameters);
	}

}
