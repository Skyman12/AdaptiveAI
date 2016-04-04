package Abilities;

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
		
		stun (theAttacker, theTarget, damage, 2);
		
		effectivness = 45;
		
		return "Used " + attackName + " on " + target.name + " -- Stunned target\n";
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
