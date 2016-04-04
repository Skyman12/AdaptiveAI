package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_PowerCord extends Attacks {
	public Bard_PowerCord(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Power Cord";
		attackDescription = "Chose an enemy, deals small damage, 50% chance that enemy is stuned for the rest of this turn.";
	
		damage = 10;
		speed = 2;
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
		int stunned = random.nextInt(2);
		
		stun(theAttacker, theTarget, damage, stunned);	
		
		return "Used " + attackName + " on " + theTarget.name + " -- Stunned for " + stunned + " turns\n";
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
