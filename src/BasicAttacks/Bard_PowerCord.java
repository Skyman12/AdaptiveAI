package BasicAttacks;

import java.util.Random;

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
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int stunned = random.nextInt(2);
		
		stun(theAttacker, target, damage, stunned);	
		
		return "Used " + attackName + " on " + target.name + " -- Stunned for " + stunned + " turns\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
