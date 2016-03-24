package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_IceLance extends Attacks {
	public Mage_IceLance(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Ice Lance";
		attackDescription = "Chose an enemy, deal damage with high crit chance.";
	
		damage = 70;
		speed = 4;
		cost = 80;
		critChance = 40;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "";
		
		dealDamage(theAttacker, target, damage, critChance);	
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
