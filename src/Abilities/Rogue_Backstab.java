package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Backstab extends Attacks {
	public Rogue_Backstab(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Backstab";
		attackDescription = "Chose an enemy, deal damage with high crit chance.";
	
		damage = 60;
		speed = 1;
		cost = 50;
		critChance = 50;
		numOfTargets = 1;
	}

	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		dealDamage(theAttacker, target, damage, critChance);
	
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
}
