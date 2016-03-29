package Abilities;

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
		cost = 50;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		stun (theAttacker, target, damage, 3);
		
		stun (theAttacker, theAttacker, damage, 3);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
