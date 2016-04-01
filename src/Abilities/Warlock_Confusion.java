package Abilities;


import java.util.ArrayList;

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
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.PRIEST);
		parameters.add(AIAttackOptions.BARD);
		parameters.add(AIAttackOptions.MAGE);
		parameters.add(AIAttackOptions.RANDOM);
		chooseAttackTargetAI(parameters);
	}
}
