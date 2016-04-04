package Abilities;


import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Confusion extends Attacks {
	public Warlock_Confusion(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Confusion";
		attackDescription = "Chose an enemy, for the remainder of this turn and next turn, that enemy will randomly selected a target - including teammates.";
	
		damage = 0;
		speed = 4;
		cost = 40;
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
		
		confuse(theAttacker, theTarget, damage, 2);

		return "Used " + attackName + " on " + theTarget.name + " -- Confused target \n";
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
