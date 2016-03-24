package Abilities;

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
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		stun (theAttacker, target, damage, 2);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}

	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
