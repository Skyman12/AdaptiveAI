package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Protect extends Attacks {
	public Warrior_Protect(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Protect";
		attackDescription = "Chose a friendly, any attacks to that player will be redirected to this player";
	
		damage = 0;
		speed = 1;
		cost = 40;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		restore(0, 10, 0, theAttacker);
		target.protectedBy = theAttacker;
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
