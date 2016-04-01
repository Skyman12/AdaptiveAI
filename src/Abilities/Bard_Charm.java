package Abilities;

import java.util.ArrayList;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_Charm extends Attacks {
	public Bard_Charm(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
	
		attackName = "Charm";
		attackDescription = "Choose an enemy, this person and the enemy will be stunned for the remainder of this turn and next 2 turns.";
	
		damage = 0;
		speed = 2;
		cost = 70;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		stun (theAttacker, theTarget, damage, 3);
		
		stun (theAttacker, theAttacker, damage, 3);
		
		return "Used " + attackName + " on " + theTarget.name + " -- Stunned the target\n";
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
