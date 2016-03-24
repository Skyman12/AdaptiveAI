package Abilities;

import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Flury extends Attacks {
	public Rogue_Flury(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Flury";
		attackDescription = "Chose an enemy, that enemy will be struck between 2 - 5 times.";
	
		damage = 15;
		speed = 4;
		cost = 40;
		critChance = 20;
		numOfTargets = 1;
	}

	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int numberOfAttacks = random.nextInt(4) + 2;
		
		for (int i = 0; i < numberOfAttacks; i++) {
			dealDamage(theAttacker, target, damage, critChance);
		}
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
}
