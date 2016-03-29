package BasicAttacks;


import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Corruption extends Attacks {
	public Warlock_Corruption(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Corruption";
		attackDescription = "Chose an enemy, deal flat damage plus a bonus.";
	
		damage = 10;
		speed = 7;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int bonus = random.nextInt(10) + 5;
		
		int damageDealt = dealDamage(theAttacker, target, damage + bonus, critChance);

		return "Used " + attackName + " on " + target.name + " -- It did " + damageDealt + " damage\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
