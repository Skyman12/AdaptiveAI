package BasicAttacks;


import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_LifeTap extends Attacks {
	public Warlock_LifeTap(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Life Tab";
		attackDescription = "Chose an enemy, deal damage to the enemy, restores between 10%-50% of damage dealt to self.";
	
		damage = 20;
		speed = 2;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		int damageDealt = dealDamage(theAttacker, target, damage, critChance);
		
		Random random = new Random();
		double percentHealed = (random.nextInt(40) + 10) / 100;
		
		restore((int) (damageDealt * percentHealed), 0, 0, theAttacker);
		
		return "Used " + attackName + " on " + target.name + " --- Healed for " + (int) (damageDealt * percentHealed) + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
