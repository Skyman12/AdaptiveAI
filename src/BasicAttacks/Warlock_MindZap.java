package BasicAttacks;


import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_MindZap extends Attacks {
	public Warlock_MindZap(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Mind Zap";
		attackDescription = "Chose an enemy, 50% chance that enemy is stuned for the rest of this turn.";
	
		damage = 0;
		speed = 5;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int stunned = random.nextInt(2);
		
		if (stunned == 1) {
			stun(theAttacker, target, damage, 1);
			return "Used " + attackName + " on " + target.name + " -- Successful\n";
		} else {
			return "Used " + attackName + " on " + target.name + " -- Fail \n";
		}
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
