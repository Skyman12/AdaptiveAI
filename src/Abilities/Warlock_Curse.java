package Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Curse extends Attacks {
	public Warlock_Curse(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Curse";
		attackDescription = "Chose an enemy, deal flat damage plus a bonus.";
	
		damage = 25;
		speed = 6;
		cost = 60;
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
		
		Random random = new Random();
		int bonus = random.nextInt(10) + 15;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage + bonus, critChance);
		
		effectivness = damageDealt;

		return "Used " + attackName + " on " + theTarget.name + " -- It did " + damageDealt + " damage\n";
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
