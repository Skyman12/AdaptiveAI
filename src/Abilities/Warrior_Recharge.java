package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Recharge extends Attacks {
	public Warrior_Recharge(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Recharge";
		attackDescription = "+30 Shield, +30 Energy";
	
		damage = 0;
		speed = 1;
		cost = 0;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		if (theAttacker.turnsStunned > 0) return "No attack";
		
		restore(0, 30, 30, theAttacker);
		
		return "Used " + attackName + "\n";
	}

	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
}
