package Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Flury extends Attacks {
	public Rogue_Flury(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Flury";
		attackDescription = "Chose an enemy, that enemy will be struck between 2 - 5 times.";
	
		damage = 15;
		speed = 4;
		cost = 40;
		critChance = 20;
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
		
		Random random = new Random();
		int numberOfAttacks = random.nextInt(4) + 2;
		int damageDealt = 0;
		for (int i = 0; i < numberOfAttacks; i++) {
			damageDealt += dealDamage(theAttacker, theTarget, damage, critChance);
		}
		
		effectivness = damageDealt;
		
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
