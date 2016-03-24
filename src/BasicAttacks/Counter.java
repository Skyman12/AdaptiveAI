package BasicAttacks;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Counter extends Attacks {
	public Counter(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Counter";
		attackDescription = "If hit with a basic attack this round, deal 1/2 of that damage back to the player.";
	
		damage = 0;
		speed = 0;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		if (theAttacker.turnsStunned > 0) return "No attack";
		
		target.countered = true;
		
		return "Countered " + "\n";
	}

	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
