package Abilities;


import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_LifeDrain extends Attacks {
	public Warlock_LifeDrain(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Life Drain";
		attackDescription = "Chose an enemy, deal damage to the enemy, restores between 50% of damage dealt to self.";
	
		damage = 50;
		speed = 7;
		cost = 80;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		int damageDealt = dealDamage(theAttacker, target, damage, critChance);
		
		restore((int) (damageDealt * 50), 0, 0, theAttacker);
		
		return "Used " + attackName + " on " + target.name + " --- Healed for " + (int) (damageDealt * 50) + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
