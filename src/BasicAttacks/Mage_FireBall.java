package BasicAttacks;


import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_FireBall extends Attacks {
	public Mage_FireBall(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Fireball";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 15;
		speed = 2;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.RANDOM, 50.0);
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 50.0);
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
