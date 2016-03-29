package Abilities;


import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Confusion extends Attacks {
	public Warlock_Confusion(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Curse";
		attackDescription = "Chose an enemy, deal flat damage plus a bonus.";
	
		damage = 25;
		speed = 6;
		cost = 60;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int bonus = random.nextInt(10) + 15;
		
		int damageDealt = dealDamage(theAttacker, target, damage + bonus, critChance);

		return "Used " + attackName + " on " + target.name + " -- It did " + damageDealt + " damage\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
