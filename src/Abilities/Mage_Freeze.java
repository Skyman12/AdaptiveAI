package Abilities;

import java.util.ArrayList;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_Freeze extends Attacks {
	public Mage_Freeze(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
	
		attackName = "Freeze";
		attackDescription = "Stuns an enemy for the remainder of this turn and next turn.";
	
		damage = 10;
		speed = 5;
		cost = 75;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		stun (theAttacker, theTarget, damage, 2);
		
		return "Used " + attackName + " on " + target.name + " -- Stunned target\n";
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
		parameters.add(AIAttackOptions.LOWEST_HEALTH);
		chooseAttackTargetAI(parameters);
	}

}
