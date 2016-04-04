package Abilities;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Stun extends Attacks {
	public Rogue_Stun(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
	
		attackName = "Stun";
		attackDescription = "Choose an enemy, that enemy is stunned for the remainder of this turn and next turn.";
	
		damage = 0;
		speed = 3;
		cost = 50;
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
		
		stun (theAttacker, theTarget, damage, 2);
		
		effectivness = 18;
		
		return "Used " + attackName + " on " + theTarget.name + " -- Stunned target \n";
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
