package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_Savior extends Attacks {
	public Priest_Savior(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Savior";
		attackDescription = "Choose a friendly, this turn, that player cannot be reduced below 1 health.";
	
		damage = 0;
		speed = 0;
		cost = 80;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if (theAttacker.turnsStunned > 0) return "No attack";
		
		target.invunerable = true;
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
