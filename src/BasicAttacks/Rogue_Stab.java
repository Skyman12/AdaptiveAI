package BasicAttacks;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Stab extends Attacks {
	public Rogue_Stab(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Stab";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 20;
		speed = 2;
		critChance = 35;
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
