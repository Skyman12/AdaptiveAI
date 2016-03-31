package Abilities;

import java.util.ArrayList;

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
	}

	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		stun (theAttacker, theTarget, damage, 2);
		
		return "Used " + attackName + " on " + theTarget.name + " -- Stunned target \n";
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
