package Abilities;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_IceLance extends Attacks {
	public Mage_IceLance(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Ice Lance";
		attackDescription = "Chose an enemy, deal damage with high crit chance.";
	
		damage = 70;
		speed = 4;
		cost = 80;
		critChance = 40;
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
