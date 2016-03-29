package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Curse extends Attacks {
	public Warlock_Curse(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Confusion";
		attackDescription = "Chose an enemy, for the remainder of this turn and next turn, that enemy will randomly selected a target - including teammates.";
	
		damage = 0;
		speed = 4;
		cost = 75;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		confuse(theAttacker, target, damage, 2);

		return "Used " + attackName + " on " + target.name + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
