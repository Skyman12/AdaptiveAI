package Abilities;

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
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		stun (theAttacker, target, damage, 2);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
