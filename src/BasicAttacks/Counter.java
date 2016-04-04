package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Counter extends Attacks {
	public Counter(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Counter";
		attackDescription = "If hit with a basic attack this round, deal that damage back to the player.";
	
		damage = 0;
		speed = 0;
		critChance = 0;
		numOfTargets = 0;
		
		attackParameters.put(AIAttackOptions.ROGUE, 25.0);
		attackParameters.put(AIAttackOptions.WARRIOR, 25.0);
		attackParameters.put(AIAttackOptions.MAGE, 25.0);
		attackParameters.put(AIAttackOptions.WARLOCK, 25.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		theTarget.countered = true;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
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
