package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Taunt extends Attacks {

	public Warrior_Taunt(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Taunt";
		attackDescription = "Choose an enemy, that enemy must attack this player this round.";
		
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
		target.forcedTarget = theAttacker;
				
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
